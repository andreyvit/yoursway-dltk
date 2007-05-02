/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibm.icu.text.MessageFormat;

/**
 * Default user buildpath entries for a script project
 */
public class DefaultProjectBuildpathEntry extends AbstractRuntimeBuildpathEntry {
	
	public static final String TYPE_ID = "org.eclipse.dltk.launching.buildpathentry.defaultBuildpath"; //$NON-NLS-1$
	
	/**
	 * Whether only exported entries should be on the runtime buildpath.
	 * By default all entries are on the runtime buildpath.
	 */
	private boolean fExportedEntriesOnly = false;
	
	/**
	 * Default constructor need to instantiate extensions
	 */
	public DefaultProjectBuildpathEntry() {
	}
	
	/**
	 * Constructs a new buildpath entry for the given project.
	 * 
	 * @param project Script project
	 */
	public DefaultProjectBuildpathEntry(IDLTKProject project) {
		setDLTKProject(project);
	}
		
	protected void buildMemento(Document document, Element memento) throws CoreException {
		memento.setAttribute("project", getDLTKProject().getElementName()); //$NON-NLS-1$
		memento.setAttribute("exportedEntriesOnly", Boolean.toString(fExportedEntriesOnly)); //$NON-NLS-1$
	}
		
	public void initializeFrom(Element memento) throws CoreException {
		String name = memento.getAttribute("project"); //$NON-NLS-1$
		if (name == null) {
			abort(LaunchingMessages.DefaultProjectBuildpathEntry_3, null); 
		}		
		IDLTKProject project = DLTKCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject(name));
		setDLTKProject(project);
		name = memento.getAttribute("exportedEntriesOnly"); //$NON-NLS-1$
		if (name == null) {
			fExportedEntriesOnly = false;
		} else {
			fExportedEntriesOnly = Boolean.valueOf(name).booleanValue();
		}
	}
	
	public String getTypeId() {
		return TYPE_ID;
	}
	
	public int getType() {
		return OTHER;
	}
	
	protected IProject getProject() {
		return getDLTKProject().getProject();
	}
		
	public String getLocation() {
		return getProject().getLocation().toOSString();
	}
		
	public IPath getPath() {
		return getProject().getFullPath();
	}
		
	public IResource getResource() {
		return getProject();
	}

	public IRuntimeBuildpathEntry[] getRuntimeBuildpathEntries(ILaunchConfiguration configuration) throws CoreException {
		IBuildpathEntry entry = DLTKCore.newProjectEntry(getDLTKProject().getProject().getFullPath());
		List buildpathEntries = new ArrayList(5);
		List expanding = new ArrayList(5);
		expandProject(entry, buildpathEntries, expanding);
		IRuntimeBuildpathEntry[] runtimeEntries = new IRuntimeBuildpathEntry[buildpathEntries.size()];
		for (int i = 0; i < runtimeEntries.length; i++) {
			Object e = buildpathEntries.get(i);
			if (e instanceof IBuildpathEntry) {
				IBuildpathEntry cpe = (IBuildpathEntry)e;
				runtimeEntries[i] = new RuntimeBuildpathEntry(cpe);
			} else {
				runtimeEntries[i] = (IRuntimeBuildpathEntry)e;				
			}
		}
		// remove bootpath entries - this is a default user buildpath
		List ordered = new ArrayList(runtimeEntries.length);
		for (int i = 0; i < runtimeEntries.length; i++) {
			if (runtimeEntries[i].getBuildpathProperty() == IRuntimeBuildpathEntry.USER_ENTRY) {
				ordered.add(runtimeEntries[i]);
			} 
		}
		return (IRuntimeBuildpathEntry[]) ordered.toArray(new IRuntimeBuildpathEntry[ordered.size()]);		
	}
	
	/**
	 * Returns the transitive closure of buildpath entries for the
	 * given project entry.
	 * 
	 * @param projectEntry project buildpath entry
	 * @param expandedPath a list of entries already expanded, should be empty
	 * to begin, and contains the result
	 * @param expanding a list of projects that have been or are currently being
	 * expanded (to detect cycles)
	 * @exception CoreException if unable to expand the buildpath
	 */
	private void expandProject(IBuildpathEntry projectEntry, List expandedPath, List expanding) throws CoreException {
		expanding.add(projectEntry);
		// 1. Get the raw buildpath
		// 2. Replace source folder entries with a project entry
		IPath projectPath = projectEntry.getPath();
		IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(projectPath.lastSegment());
		if (res == null) {
			// add project entry and return
			expandedPath.add(projectEntry);
			return;
		}
		IDLTKProject project = (IDLTKProject)DLTKCore.create(res);
		if (project == null || !project.getProject().isOpen() || !project.exists()) {
			// add project entry and return
			expandedPath.add(projectEntry);
			return;
		}
		
		IBuildpathEntry[] buildPath = project.getRawBuildpath();
		List unexpandedPath = new ArrayList(buildPath.length);
		//boolean projectAdded = false;
		for (int i = 0; i < buildPath.length; i++) {
			IBuildpathEntry buildpathEntry = buildPath[i];
			if (buildpathEntry.getEntryKind() == IBuildpathEntry.BPE_SOURCE) { //sources are always added
				unexpandedPath.add(buildpathEntry);
			} else {
				// add exported entires, as configured
				if (buildpathEntry.isExported()) {
					unexpandedPath.add(buildpathEntry);
				} else if (!isExportedEntriesOnly() || project.equals(getDLTKProject())) {
					// add non exported entries from root project or if we are including all entries
					unexpandedPath.add(buildpathEntry);
				}
			}
		}
		// 3. expand each project entry (except for the root project)
		// 4. replace each container entry with a runtime entry associated with the project
		Iterator iter = unexpandedPath.iterator();
		while (iter.hasNext()) {
			IBuildpathEntry entry = (IBuildpathEntry)iter.next();
			if (entry == projectEntry) {
				expandedPath.add(entry);
			} else {
				switch (entry.getEntryKind()) {
					case IBuildpathEntry.BPE_PROJECT:
						if (!expanding.contains(entry)) {
							expandProject(entry, expandedPath, expanding);
						}
						break;
					case IBuildpathEntry.BPE_CONTAINER:
						IBuildpathContainer container = DLTKCore.getBuildpathContainer(entry.getPath(), project);
						int property = -1;
						if (container != null) {
							switch (container.getKind()) {
								case IBuildpathContainer.K_APPLICATION:
									property = IRuntimeBuildpathEntry.USER_ENTRY;
									break;
								case IBuildpathContainer.K_DEFAULT_SYSTEM:
									property = IRuntimeBuildpathEntry.STANDARD_ENTRY;
									break;	
								case IBuildpathContainer.K_SYSTEM:
									property = IRuntimeBuildpathEntry.BOOTSTRAP_ENTRY;
									break;
							}
							IRuntimeBuildpathEntry r = ScriptRuntime.newRuntimeContainerBuildpathEntry(entry.getPath(), property, project);
							// check for duplicate/redundant entries 
							boolean duplicate = false;
							BuildpathContainerInitializer initializer = DLTKCore.getBuildpathContainerInitializer(r.getPath().segment(0));
							for (int i = 0; i < expandedPath.size(); i++) {
								Object o = expandedPath.get(i);
								if (o instanceof IRuntimeBuildpathEntry) {
									IRuntimeBuildpathEntry re = (IRuntimeBuildpathEntry)o;
									if (re.getType() == IRuntimeBuildpathEntry.CONTAINER) {
										BuildpathContainerInitializer initializer2 = DLTKCore.getBuildpathContainerInitializer(re.getPath().segment(0));
										Object id1 = null;
										Object id2 = null;
										if (initializer == null) {
											id1 = r.getPath().segment(0);
										} else {
											id1 = initializer.getComparisonID(r.getPath(), project);
										}
										if (initializer2 == null) {
											id2 = re.getPath().segment(0);
										} else {
											IDLTKProject context = re.getDLTKProject();
											if (context == null) {
												context = project;
											}
											id2 = initializer2.getComparisonID(re.getPath(), context);
										}
										if (id1 == null) {
											duplicate = id2 == null;
										} else {
											duplicate = id1.equals(id2);
										}
										if (duplicate) {
											break;
										}
									}
								}
							}
							if (!duplicate) {
								expandedPath.add(r);
							}	
						}
						break;
					default:
						if (!expandedPath.contains(entry)) {
							expandedPath.add(entry);
						}
						break;
				}
			}
		}
		return;
	}	
	
	public boolean isComposite() {
		return true;
	}
	
	public String getName() {
		if (isExportedEntriesOnly()) {
			return MessageFormat.format(LaunchingMessages.DefaultProjectBuildpathEntry_2, new String[] {getDLTKProject().getElementName()});
		}
		return MessageFormat.format(LaunchingMessages.DefaultProjectBuildpathEntry_4, new String[] {getDLTKProject().getElementName()}); 
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof DefaultProjectBuildpathEntry) {
			DefaultProjectBuildpathEntry entry = (DefaultProjectBuildpathEntry) obj;
			return entry.getDLTKProject().equals(getDLTKProject()) &&
				entry.isExportedEntriesOnly() == isExportedEntriesOnly();
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getDLTKProject().hashCode();
	}
	
	/**
	 * Sets whether the runtime buildpath computaion should only
	 * include exported entries in referenced projects.
	 * 
	 * @param exportedOnly
	 *
	 */
	public void setExportedEntriesOnly(boolean exportedOnly) {
		fExportedEntriesOnly = exportedOnly;
	}
	
	/**
	 * Returns whether the buildpath computation only includes exported
	 * entries in referenced projects.
	 * 
	 * @return
	 *
	 */
	public boolean isExportedEntriesOnly() {
		return fExportedEntriesOnly;
	}

}
