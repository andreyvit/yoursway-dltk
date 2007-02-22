/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Messages;


public class CopyProjectFragmentOperation extends ModelOperation {
	IPath destination;
	int updateResourceFlags;
	int updateModelFlags;
	IBuildpathEntry sibling;

	public CopyProjectFragmentOperation(
		IProjectFragment root,
		IPath destination,
		int updateResourceFlags,
		int updateModelFlags,
		IBuildpathEntry sibling) {
			
		super(root);
		this.destination = destination;
		this.updateResourceFlags = updateResourceFlags;
		this.updateModelFlags = updateModelFlags;
		this.sibling = sibling;
	}
	protected void executeOperation() throws ModelException {
		
		IProjectFragment root = (IProjectFragment)this.getElementToProcess();
		IBuildpathEntry rootEntry = root.getRawBuildpathEntry();
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		// copy resource
		if (!root.isExternal() && (this.updateModelFlags & IProjectFragment.NO_RESOURCE_MODIFICATION) == 0) {
			copyResource(root, rootEntry, workspaceRoot);
		}
		
		// update buildpath if needed
		if ((this.updateModelFlags & IProjectFragment.DESTINATION_PROJECT_BUILDPATH) != 0) {
			addEntryToBuildpath(rootEntry, workspaceRoot);
		}
	}
	protected void copyResource(
		IProjectFragment root,
		IBuildpathEntry rootEntry,
		final IWorkspaceRoot workspaceRoot)
		throws ModelException {
		final char[][] exclusionPatterns = ((BuildpathEntry)rootEntry).fullExclusionPatternChars();
		IResource rootResource = root.getResource();
		if (root.getKind() == IProjectFragment.K_BINARY || exclusionPatterns == null) {
			try {
				IResource destRes;
				if ((this.updateModelFlags & IProjectFragment.REPLACE) != 0) {
					if (rootEntry.getPath().equals(this.destination)) return;
					if ((destRes = workspaceRoot.findMember(this.destination)) != null) {
						destRes.delete(this.updateResourceFlags, progressMonitor);
					}
				}
				rootResource.copy(this.destination, this.updateResourceFlags, progressMonitor);
			} catch (CoreException e) {
				throw new ModelException(e);
			}
		} else {
			final int sourceSegmentCount = rootEntry.getPath().segmentCount();
			final IFolder destFolder = workspaceRoot.getFolder(this.destination);
			final IPath[] nestedFolders = getNestedFolders(root);
			IResourceProxyVisitor visitor = new IResourceProxyVisitor() {
				public boolean visit(IResourceProxy proxy) throws CoreException {
					if (proxy.getType() == IResource.FOLDER) {
						IPath path = proxy.requestFullPath();
						if (prefixesOneOf(path, nestedFolders)) {
							if (equalsOneOf(path, nestedFolders)) {
								// nested source folder
								return false;
							} else {
								// folder containing nested source folder
								IFolder folder = destFolder.getFolder(path.removeFirstSegments(sourceSegmentCount));
								if ((updateModelFlags & IProjectFragment.REPLACE) != 0
										&& folder.exists()) {
									return true;
								}
								folder.create(updateResourceFlags, true, progressMonitor);
								return true;
							}
						} else {
							// subtree doesn't contain any nested source folders
							IPath destPath = destination.append(path.removeFirstSegments(sourceSegmentCount));
							IResource destRes;
							if ((updateModelFlags & IProjectFragment.REPLACE) != 0
									&& (destRes = workspaceRoot.findMember(destPath)) != null) {
								destRes.delete(updateResourceFlags, progressMonitor);
							}
							proxy.requestResource().copy(destPath, updateResourceFlags, progressMonitor);
							return false;
						}
					} else {
						IPath path = proxy.requestFullPath();
						IPath destPath = destination.append(path.removeFirstSegments(sourceSegmentCount));
						IResource destRes;
						if ((updateModelFlags & IProjectFragment.REPLACE) != 0
								&& (destRes = workspaceRoot.findMember(destPath)) != null) {
							destRes.delete(updateResourceFlags, progressMonitor);
						}
						proxy.requestResource().copy(destPath, updateResourceFlags, progressMonitor);
						return false;
					}
				}
			};
			try {
				rootResource.accept(visitor, IResource.NONE);
			} catch (CoreException e) {
				throw new ModelException(e);
			}
		}
		this.setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE); 
	}
	protected void addEntryToBuildpath(IBuildpathEntry rootEntry, IWorkspaceRoot workspaceRoot) throws ModelException {
		
		IProject destProject = workspaceRoot.getProject(this.destination.segment(0));
		IDLTKProject jProject = DLTKCore.create(destProject);
		IBuildpathEntry[] buildpath = jProject.getRawBuildpath();
		int length = buildpath.length;
		IBuildpathEntry[] newBuildpath;
		
		// case of existing entry and REPLACE was specified
		if ((this.updateModelFlags & IProjectFragment.REPLACE) != 0) {
			// find existing entry
			for (int i = 0; i < length; i++) {
				if (this.destination.equals(buildpath[i].getPath())) {
					newBuildpath = new IBuildpathEntry[length];
					System.arraycopy(buildpath, 0, newBuildpath, 0, length);
					newBuildpath[i] = copy(rootEntry);
					jProject.setRawBuildpath(newBuildpath, progressMonitor);
					return;
				}
			}
		} 
		
		// other cases
		int position;
		if (this.sibling == null) {
			// insert at the end
			position = length;
		} else {
			// insert before sibling
			position = -1;
			for (int i = 0; i < length; i++) {
				if (this.sibling.equals(buildpath[i])) {
					position = i;
					break;
				}
			}
		}
		if (position == -1) {
			throw new ModelException(new ModelStatus(IModelStatusConstants.INVALID_SIBLING, this.sibling.toString()));
		}
		newBuildpath = new IBuildpathEntry[length+1];
		if (position != 0) {
			System.arraycopy(buildpath, 0, newBuildpath, 0, position);
		}
		if (position != length) {
			System.arraycopy(buildpath, position, newBuildpath, position+1, length-position);
		}
		IBuildpathEntry newEntry = copy(rootEntry);
		newBuildpath[position] = newEntry;
		jProject.setRawBuildpath(newBuildpath, progressMonitor);
	}
	/*
	 * Copies the given buildpath entry replacing its path with the destination path
	 * if it is a source folder or a library.
	 */
	protected IBuildpathEntry copy(IBuildpathEntry entry) throws ModelException {
		switch (entry.getEntryKind()) {
			case IBuildpathEntry.BPE_CONTAINER:
				return DLTKCore.newContainerEntry(entry.getPath(), entry.getAccessRules(), entry.getExtraAttributes(), entry.isExported());
			case IBuildpathEntry.BPE_LIBRARY:
				try {
					return DLTKCore.newLibraryEntry(this.destination, entry.getAccessRules(), entry.getExtraAttributes(), entry.isExported(), entry.isExternal());
				} catch (AssertionFailedException e) {
					IModelStatus status = new ModelStatus(IModelStatusConstants.INVALID_PATH, e.getMessage());
					throw new ModelException(status);
				}
			case IBuildpathEntry.BPE_PROJECT:
				return DLTKCore.newProjectEntry(entry.getPath(), entry.getAccessRules(), entry.combineAccessRules(), entry.getExtraAttributes(), entry.isExported());
			case IBuildpathEntry.BPE_SOURCE:
				return DLTKCore.newSourceEntry(this.destination, entry.getInclusionPatterns(), entry.getExclusionPatterns(), entry.getExtraAttributes());			
			default:
				throw new ModelException(new ModelStatus(IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, this.getElementToProcess()));
		}
	}
	public IModelStatus verify() {
		IModelStatus status = super.verify();
		if (!status.isOK()) {
			return status;
		}
		IProjectFragment root = (IProjectFragment)getElementToProcess();
		if (root == null || !root.exists()) {
			return new ModelStatus(IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, root);
		}

		IResource resource = root.getResource();
		if (resource instanceof IFolder) {
			if (resource.isLinked()) {
				return new ModelStatus(IModelStatusConstants.INVALID_RESOURCE, root);
			}
		}

		if ((this.updateModelFlags & IProjectFragment.DESTINATION_PROJECT_BUILDPATH) != 0) {
			String destProjectName = this.destination.segment(0);
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(destProjectName);
			if (DLTKLanguageManager.hasScriptNature(project)) {
				try {
					IDLTKProject destProject = DLTKCore.create(project);
					IBuildpathEntry[] destBuildpath = destProject.getRawBuildpath();
					boolean foundSibling = false;
					boolean foundExistingEntry = false;
					for (int i = 0, length = destBuildpath.length; i < length; i++) {
						IBuildpathEntry entry = destBuildpath[i];
						if (entry.equals(this.sibling)) {
							foundSibling = true;
							break;
						}
						if (entry.getPath().equals(this.destination)) {
							foundExistingEntry = true;
						}
					}
					if (this.sibling != null && !foundSibling) {
						return new ModelStatus(IModelStatusConstants.INVALID_SIBLING, this.sibling == null ? "null" : this.sibling.toString()); //$NON-NLS-1$
					}
					if (foundExistingEntry && (this.updateModelFlags & IProjectFragment.REPLACE) == 0) {
						return new ModelStatus(
							IModelStatusConstants.NAME_COLLISION, 
							Messages.bind(Messages.status_nameCollision, new String[] {this.destination.toString()})); 
					}
				} catch (ModelException e) {
					return e.getModelStatus();
				}
			}
		}

		return ModelStatus.VERIFIED_OK;
	}
}
