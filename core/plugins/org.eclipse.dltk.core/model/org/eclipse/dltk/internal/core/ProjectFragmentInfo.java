/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Util;


class ProjectFragmentInfo extends OpenableElementInfo {

	/**
	 * The kind of the root associated with this info.
	 */
	protected int fRootKind= IProjectFragment.K_SOURCE;
	
	protected Object[] foreignResources;
	
	/**
	 * Returns the kind of this root.
	 */
	public int getRootKind() {
		return this.fRootKind;
	}
	
	void setForeignResources(Object[] resources) {
		foreignResources = resources;
	}

	public Object[] getForeignResources(IScriptProject scriptProject, IResource resource, ProjectFragment fragment) {
		Object[] nonScriptResources = this.foreignResources;
		if (nonScriptResources == null) {
			nonScriptResources = this.computeForeignResources(scriptProject, resource, fragment);
			this.foreignResources = nonScriptResources;
		}
		return nonScriptResources;
	}

	private Object[] computeForeignResources(IScriptProject scriptProject, IResource underlyingResource, ProjectFragment handle) {
		Object[] nonScriptResources = NO_NON_SCRIPT_RESOURCES;
		try {
			// the underlying resource may be a folder or a project (in the case that the project folder
			// is actually the package fragment root)
			if (underlyingResource.getType() == IResource.FOLDER || underlyingResource.getType() == IResource.PROJECT) {
				nonScriptResources = 
					computeFolderForeignResources(
						(ScriptProject)scriptProject, 
						(IContainer) underlyingResource,  
						handle.fullInclusionPatternChars(),
						handle.fullExclusionPatternChars());
			}
		} catch (ModelException e) {
			// ignore
		}
		return nonScriptResources;
	}

	public static Object[] computeFolderForeignResources(ScriptProject project, IContainer folder, char[][] inclusionPatterns, char[][] exclusionPatterns) throws ModelException {		
			Object[] nonScriptResources = new IResource[5];
			int nonScriptResourcesCounter = 0;
			try {
				IBuildpathEntry[] classpath = project.getResolvedBuildpath();
				IResource[] members = folder.members();
				nextResource: for (int i = 0, max = members.length; i < max; i++) {
					IResource member = members[i];
					switch (member.getType()) {
						case IResource.FILE :
							String fileName = member.getName();
							
							// ignore .java files that are not excluded
							if (Util.isValidSourceModule(project, member) && !Util.isExcluded(member, inclusionPatterns, exclusionPatterns)) 
								continue nextResource;
							// ignore .zip file on buildpath
							if (org.eclipse.dltk.compiler.util.Util.isArchiveFileName(fileName) && isBuildpathEntry(member.getFullPath(), classpath)) 
								continue nextResource;
							// All other resources should be in folders.
							//continue nextResource;							
							break;

						case IResource.FOLDER :
							// ignore valid packages or excluded folders that correspond to a nested pkg fragment root
							if (Util.isValidFolderNameForPackage(member.getName())
									&& (!Util.isExcluded(member, inclusionPatterns, exclusionPatterns) 
										|| isBuildpathEntry(member.getFullPath(), classpath)))
								continue nextResource;
							break;
					}
					if (nonScriptResources.length == nonScriptResourcesCounter) {
						// resize
						System.arraycopy(nonScriptResources, 0, (nonScriptResources = new IResource[nonScriptResourcesCounter * 2]), 0, nonScriptResourcesCounter);
					}
					nonScriptResources[nonScriptResourcesCounter++] = member;

				}
				if (nonScriptResources.length != nonScriptResourcesCounter) {
					System.arraycopy(nonScriptResources, 0, (nonScriptResources = new IResource[nonScriptResourcesCounter]), 0, nonScriptResourcesCounter);
				}
				return nonScriptResources;
			} catch (CoreException e) {
				throw new ModelException(e);
			}
		
	}	
	private static boolean isBuildpathEntry(IPath path, IBuildpathEntry[] resolvedClasspath) {
		for (int i = 0, length = resolvedClasspath.length; i < length; i++) {
			IBuildpathEntry entry = resolvedClasspath[i];
			if (entry.getPath().equals(path)) {
				return true;
			}
		}
		return false;
	}
}
