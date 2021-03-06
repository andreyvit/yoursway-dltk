/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.util.MementoTokenizer;
import org.eclipse.dltk.internal.core.util.Util;
import org.eclipse.dltk.utils.CorePrinter;


public class ProjectFragment extends Openable implements IProjectFragment {
	/**
	 * The resource associated with this root. (an IResource or a java.io.File
	 * (for externals))
	 */
	protected Object resource;

	/**
	 * Constructs a project fragment which is the root of the directory
	 * hierarchy.
	 */
	protected ProjectFragment(IResource resource, ScriptProject project) {
		super(project);
		this.resource = resource;
	}

	/**
	 * @see IProjectFragment
	 */
	public boolean isArchive() {
		return false;
	}

	protected Object createElementInfo() {
		return new ProjectFragmentInfo();
	}

	public int getElementType() {
		return PROJECT_FRAGMENT;
	}

	public IResource getResource() {
		return (IResource) this.resource;
	}

	public IPath getPath() {
		return this.getResource().getFullPath();
	}

	public int getKind() throws ModelException {
		Object elementInfo = this.getElementInfo();
		return ((ProjectFragmentInfo) elementInfo).getRootKind();
	}

	/**
	 * Compares two objects for equality; for <code>ProjectFragments</code>s,
	 * equality is having the same parent, same resources, and occurrence count.
	 *
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProjectFragment)) {
			return false;
		}
		ProjectFragment other = (ProjectFragment) o;
		if (this.resource == null && other.resource != null) {
			return false;
		}
		if (this.resource != null && other.resource == null) {
			return false;
		}
		if( this.resource != null && other.resource != null && !this.resource.equals(other.resource) ) {
			return false;
		}

		return this.parent.equals(other.parent);
	}

	public int hashCode() {
		return this.resource.hashCode();
	}

	protected boolean buildStructure(OpenableElementInfo info, IProgressMonitor pm, Map newElements, IResource underlyingResource)
			throws ModelException {
		// check whether this project fragment can be opened
		IStatus status = this.validateOnBuildpath();
		if (!status.isOK()) {
			throw this.newModelException(status);
		}
		if (!this.resourceExists()) {
			throw this.newNotPresentException();
		}
		// TODO determine fragment kind
		return this.computeChildren(info, newElements);
	}

	/*
	 * Validate whether this project fragment is on the buildpath of its
	 * project.
	 */
	protected IStatus validateOnBuildpath() {
		IPath path = this.getPath();
		try {
			// check project fragment on buildpath of its project
			ScriptProject project = (ScriptProject) this.getScriptProject();
			IBuildpathEntry[] buildpath = project.getResolvedBuildpath();
			for (int i = 0, length = buildpath.length; i < length; i++) {
				IBuildpathEntry entry = buildpath[i];
				if (entry.getPath().equals(path)) {
					return Status.OK_STATUS;
				}
			}
		} catch (ModelException e) {
			// could not read buildpath, then assume it is outside
			return e.getModelStatus();
		}
		return new ModelStatus(IModelStatusConstants.ELEMENT_NOT_ON_BUILDPATH, this);
	}

	/**
	 * Compute the project fragment children of this project fragment.
	 *
	 * @exception ModelException
	 *                The resource associated with this project fragment does
	 *                not exist
	 */
	protected boolean computeChildren(OpenableElementInfo info, Map newElements) throws ModelException {
		// Note the children are not opened (so not added to newElements) for a
		// regular package fragment root
		// However they are opened for a ZIP project fragment.
		try {
			// the underlying resource may be a folder or a project (in the case
			// that the project folder
			// is actually the package fragment root)
			IResource underlyingResource = this.getResource();
			if (underlyingResource.getType() == IResource.FOLDER || underlyingResource.getType() == IResource.PROJECT) {
				ArrayList vChildren = new ArrayList(5);
				IContainer rootFolder = (IContainer) underlyingResource;
				char[][] inclusionPatterns = this.fullInclusionPatternChars();
				char[][] exclusionPatterns = this.fullExclusionPatternChars();
				this.computeFolderChildren(rootFolder, !Util.isExcluded(rootFolder, inclusionPatterns, exclusionPatterns), Path.EMPTY,
						vChildren, inclusionPatterns, exclusionPatterns);
				IModelElement[] children = new IModelElement[vChildren.size()];
				vChildren.toArray(children);
				info.setChildren(children);
			}
		} catch (ModelException e) {
			// problem resolving children; structure remains unknown
			info.setChildren(new IModelElement[] {});
			throw e;
		}
		return true;
	}

	/**
	 * Starting at this folder, create folders and add them to the collection of
	 * children.
	 *
	 * @exception ModelException
	 *                The resource associated with this project fragment does
	 *                not exist
	 */
	protected void computeFolderChildren(IContainer folder, boolean isIncluded, IPath path, ArrayList vChildren,
			char[][] inclusionPatterns, char[][] exclusionPatterns) throws ModelException {
		if (isIncluded) {
			IScriptFolder pkg = this.getScriptFolder(path);
			vChildren.add(pkg);
		}
		try {
			ScriptProject scriptProject = (ScriptProject) this.getScriptProject();
			ModelManager manager = ModelManager.getModelManager();
			IResource[] members = folder.members();
			boolean hasIncluded = isIncluded;
			for (int i = 0, max = members.length; i < max; i++) {
				IResource member = members[i];
				String memberName = member.getName();
				switch (member.getType()) {
					case IResource.FOLDER:
						// TODO check is folder is valid for a package
						if (Util.isValidFolderNameForPackage(memberName)) {
							if (scriptProject.contains(member)) {
								IPath newPath = path.append(manager.intern(memberName));
								boolean isMemberIncluded = !Util.isExcluded(member, inclusionPatterns, exclusionPatterns);
								this.computeFolderChildren((IFolder) member, isMemberIncluded, newPath, vChildren, inclusionPatterns,
										exclusionPatterns);
							}
						}
						break;
					case IResource.FILE:
						// inclusion filter may only include files, in which
						// case we still want to include the immediate parent
						// package (lazily)
						if (!hasIncluded && Util.isValidSourceModule(this, member)
								&& !Util.isExcluded(member, inclusionPatterns, exclusionPatterns)) {
							hasIncluded = true;
							IScriptFolder pkg = this.getScriptFolder(path);
							vChildren.add(pkg);
						}
						break;
				}
			}
		} catch (IllegalArgumentException e) {
			throw new ModelException(e, IModelStatusConstants.ELEMENT_DOES_NOT_EXIST); // could
																						// be
																						// thrown
																						// by
																						// ElementTree
																						// when
																						// path
																						// is
																						// not
																						// found
		} catch (CoreException e) {
			throw new ModelException(e);
		}
	}

	public IScriptFolder getScriptFolder(IPath path) {
		return new ScriptFolder(this, path);
	}

	public String getElementName() {
		if (this.resource instanceof IFolder) {
			return ((IFolder) this.resource).getName();
		}
		return super.getElementName();
	}

	public boolean exists() {
		return super.exists() && this.validateOnBuildpath().isOK();
	}

	public boolean isExternal() {
		return false;
	}

	public IResource getUnderlyingResource() throws ModelException {
		if (!this.exists()) {
			throw this.newNotPresentException();
		}
		return this.getResource();
	}

	/**
	 * @private Debugging purposes
	 */
	protected void toStringInfo(int tab, StringBuffer buffer, Object info, boolean showResolvedInfo) {
		buffer.append(this.tabString(tab));
		IPath path = this.getPath();
		if (this.getScriptProject().getElementName().equals(path.segment(0))) {
			if (path.segmentCount() == 1) {
				buffer.append("<project root>"); //$NON-NLS-1$
			} else {
				buffer.append(path.removeFirstSegments(1).makeRelative());
			}
		} else {
			if (this.isExternal()) {
				buffer.append(path.toOSString());
			} else {
				buffer.append(path);
			}
		}
		if (info == null) {
			buffer.append(" (not open)"); //$NON-NLS-1$
		}
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("ScriptProject fragment:" + this.getPath().toOSString()); //$NON-NLS-1$
		output.indent();
		try {
			IModelElement modelElements[] = this.getChildren();
			for (int i = 0; i < modelElements.length; ++i) {
				IModelElement element = modelElements[i];
				if (element instanceof ModelElement) {
					((ModelElement) element).printNode(output);
				} else {
					output.print("Unknown element:" + element); //$NON-NLS-1$
				}
			}
		} catch (ModelException ex) {
			output.formatPrint(ex.getLocalizedMessage());
		}
		output.dedent();
	}

	public IScriptFolder getScriptFolder(String path) {
		// tolerate package names with spaces (e.g. 'x . y')
		// (http://bugs.eclipse.org/bugs/show_bug.cgi?id=21957)
		return this.getScriptFolder(new Path(path));
	}

	public Object[] getForeignResources() throws ModelException {
		return ((ProjectFragmentInfo) this.getElementInfo()).getForeignResources(this.getScriptProject(), this.getResource(), this);
	}

	/*
	 * Returns the exclusion patterns from the buildpath entry associated with
	 * this root.
	 */
	public char[][] fullExclusionPatternChars() {
		try {
			if (this.isOpen() && this.getKind() != IProjectFragment.K_SOURCE) {
				return null;
			}
			BuildpathEntry entry = (BuildpathEntry) this.getBuildpathEntry();
			if (entry == null) {
				return null;
			} else {
				return entry.fullExclusionPatternChars();
			}
		} catch (ModelException e) {
			return null;
		}
	}

	/*
	 * Returns the inclusion patterns from the buildpath entry associated with
	 * this root.
	 */

	public char[][] fullInclusionPatternChars() {
		try {
			if (this.isOpen() && this.getKind() != IProjectFragment.K_SOURCE) {
				return null;
			}
			BuildpathEntry entry = (BuildpathEntry) this.getBuildpathEntry();
			if (entry == null) {
				return null;
			} else {
				return entry.fullInclusionPatternChars();
			}
		} catch (ModelException e) {
			return null;
		}
	}

	/*
	 * @see IProjectFragment
	 */
	public IBuildpathEntry getRawBuildpathEntry() throws ModelException {
		IBuildpathEntry rawEntry = null;
		ScriptProject project = (ScriptProject)this.getScriptProject();
		project.getResolvedBuildpath(); // force the reverse rawEntry cache to be populated
		Map rootPathToRawEntries = project.getPerProjectInfo().resolvedPathToRawEntries;
		if (rootPathToRawEntries != null) {
			rawEntry = (IBuildpathEntry) rootPathToRawEntries.get(this.getPath());
		}
		if (rawEntry == null) {
			throw new ModelException(new ModelStatus(IModelStatusConstants.ELEMENT_NOT_ON_BUILDPATH, this));
		}
		return rawEntry;
	}
	public IBuildpathEntry getBuildpathEntry() throws ModelException {
		return this.getRawBuildpathEntry();
	}

	public IModelElement getHandleFromMemento(String token, MementoTokenizer memento, WorkingCopyOwner owner) {
		switch (token.charAt(0)) {
			case JEM_SCRIPTFOLDER:
				String pkgName;
				if (memento.hasMoreTokens()) {
					pkgName = memento.nextToken();
					char firstChar = pkgName.charAt(0);
					if (firstChar == JEM_SOURCEMODULE || firstChar == JEM_COUNT) {
						token = pkgName;
						pkgName = IProjectFragment.DEFAULT_SCRIPT_FOLDER_NAME;
					} else {
						token = null;
					}
				} else {
					pkgName = IScriptFolder.DEFAULT_FOLDER_NAME;
					token = null;
				}
				ModelElement pkg = (ModelElement) this.getScriptFolder(pkgName);
				if (token == null) {
					return pkg.getHandleFromMemento(memento, owner);
				} else {
					return pkg.getHandleFromMemento(token, memento, owner);
				}
		}
		return null;
	}

	protected char getHandleMementoDelimiter() {
		return JEM_PROJECTFRAGMENT;
	}

	/**
	 * @see IProjectFragment
	 */
	public IScriptFolder createScriptFolder(String pkgName, boolean force, IProgressMonitor monitor) throws ModelException {
		CreateScriptFolderOperation op = new CreateScriptFolderOperation(this, pkgName, force);
		op.runOperation(monitor);
		return this.getScriptFolder(op.pkgName);
	}

	public void delete(int updateResourceFlags, int updateModelFlags, IProgressMonitor monitor) throws ModelException {
		DeleteProjectFragmentOperation op = new DeleteProjectFragmentOperation(this, updateResourceFlags, updateModelFlags);
		op.runOperation(monitor);
	}

	public void copy(IPath destination, int updateResourceFlags, int updateModelFlags, IBuildpathEntry sibling, IProgressMonitor monitor) throws ModelException {
		CopyProjectFragmentOperation op = new CopyProjectFragmentOperation(this, destination, updateResourceFlags,
				updateModelFlags, sibling);
		op.runOperation(monitor);
	}

	public void move(IPath destination, int updateResourceFlags, int updateModelFlags, IBuildpathEntry sibling, IProgressMonitor monitor) throws ModelException {
		MoveProjectFragmentOperation op = new MoveProjectFragmentOperation(this, destination, updateResourceFlags,
				updateModelFlags, sibling);
		op.runOperation(monitor);
	}

	public IScriptFolder getScriptFolder(String[] pkgName) {
		if( pkgName.length == 0 ) {
			return this.getScriptFolder(Path.EMPTY);
		}
		IPath path = new Path(pkgName[0]);
		for( int i = 1; i < pkgName.length; ++i ) {
			path = path.append(pkgName[i]);
		}
		return this.getScriptFolder(path);
	}
}
