/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ModelException;


public class MoveProjectFragmentOperation extends CopyProjectFragmentOperation {
	/*
	 * Renames the buildpath entries equal to the given path in the given project.
	 * If an entry with the destination path already existed, remove it.
	 */
	protected void renameEntryInBuildpath(IPath rootPath, IDLTKProject project) throws ModelException {
			
		IBuildpathEntry[] buildpath = project.getRawBuildpath();
		IBuildpathEntry[] newBuildpath = null;
		int cpLength = buildpath.length;
		int newCPIndex = -1;

		for (int i = 0; i < cpLength; i++) {
			IBuildpathEntry entry = buildpath[i];
			IPath entryPath = entry.getPath();
			if (rootPath.equals(entryPath)) {
				// rename entry
				if (newBuildpath == null) {
					newBuildpath = new IBuildpathEntry[cpLength];
					System.arraycopy(buildpath, 0, newBuildpath, 0, i);
					newCPIndex = i;
				}
				newBuildpath[newCPIndex++] = copy(entry);
			} else if (this.destination.equals(entryPath)) {
				// remove entry equals to destination
				if (newBuildpath == null) {
					newBuildpath = new IBuildpathEntry[cpLength];
					System.arraycopy(buildpath, 0, newBuildpath, 0, i);
					newCPIndex = i;
				}
			} else if (entry.getEntryKind() == IBuildpathEntry.BPE_SOURCE) {
				// update exclusion/inclusion patterns
				IPath projectRelativePath = rootPath.removeFirstSegments(1);
				IPath[] newExclusionPatterns = renamePatterns(projectRelativePath, entry.getExclusionPatterns());
				IPath[] newInclusionPatterns = renamePatterns(projectRelativePath, entry.getInclusionPatterns());
				if (newExclusionPatterns != null || newInclusionPatterns != null) {
					if (newBuildpath == null) {
						newBuildpath = new IBuildpathEntry[cpLength];
						System.arraycopy(buildpath, 0, newBuildpath, 0, i);
						newCPIndex = i;
					}
					newBuildpath[newCPIndex++] = 
						DLTKCore.newSourceEntry(
							entry.getPath(), 
							newInclusionPatterns == null ? entry.getInclusionPatterns() : newInclusionPatterns, 
							newExclusionPatterns == null ? entry.getExclusionPatterns() : newExclusionPatterns, 							
							entry.getExtraAttributes());
				} else if (newBuildpath != null) {
					newBuildpath[newCPIndex++] = entry;
				}
			} else if (newBuildpath != null) {
				newBuildpath[newCPIndex++] = entry;
			}
		}
		
		if (newBuildpath != null) {
			if (newCPIndex < newBuildpath.length) {
				System.arraycopy(newBuildpath, 0, newBuildpath = new IBuildpathEntry[newCPIndex], 0, newCPIndex);
			}
			IModelStatus status = BuildpathEntry.validateBuildpath(project, newBuildpath);
			if (status.isOK())
				project.setRawBuildpath(newBuildpath, progressMonitor);
			// don't update buildpath if status is not ok to avoid ScriptModelException (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=129991)
		}
	}

	private IPath[] renamePatterns(IPath rootPath, IPath[] patterns) {
		IPath[] newPatterns = null;
		int newPatternsIndex = -1;
		for (int i = 0, length = patterns.length; i < length; i++) {
			IPath pattern = patterns[i];
			if (pattern.equals(rootPath)) {
				if (newPatterns == null) {
					newPatterns = new IPath[length];
					System.arraycopy(patterns, 0, newPatterns, 0, i);
					newPatternsIndex = i;
				}
				IPath newPattern = this.destination.removeFirstSegments(1);
				if (pattern.hasTrailingSeparator())
					newPattern = newPattern.addTrailingSeparator();
				newPatterns[newPatternsIndex++] = newPattern;
			}
		}
		return newPatterns;
	}

	public MoveProjectFragmentOperation(
		IProjectFragment root,
		IPath destination,
		int updateResourceFlags,
		int updateModelFlags,
		IBuildpathEntry sibling) {
			
		super(
			root,
			destination,
			updateResourceFlags,
			updateModelFlags,
			sibling);
	}
	protected void executeOperation() throws ModelException {
		
		IProjectFragment root = (IProjectFragment)this.getElementToProcess();
		IBuildpathEntry rootEntry = root.getRawBuildpathEntry();
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		
		// move resource
		if (!root.isExternal() && (this.updateModelFlags & IProjectFragment.NO_RESOURCE_MODIFICATION) == 0) {
			moveResource(root, rootEntry, workspaceRoot);
		}
		
		// update refering projects buildpath excluding orignating project
		IDLTKProject originatingProject = root.getScriptProject();
		if ((this.updateModelFlags & IProjectFragment.OTHER_REFERRING_PROJECTS_BUILDPATH) != 0) {
			updateReferringProjectBuildpaths(rootEntry.getPath(), originatingProject);
		}

		boolean isRename = this.destination.segment(0).equals(originatingProject.getElementName());
		boolean updateOriginating = (this.updateModelFlags & IProjectFragment.ORIGINATING_PROJECT_BUILDPATH) != 0;
		boolean updateDestination = (this.updateModelFlags & IProjectFragment.DESTINATION_PROJECT_BUILDPATH) != 0;

		// update originating buildpath
		if (updateOriginating) {
			if (isRename && updateDestination) {
				renameEntryInBuildpath(rootEntry.getPath(), originatingProject);
			} else {
				removeEntryFromBuildpath(rootEntry.getPath(), originatingProject);
			}
		}
		
		// update destination buildpath
		if (updateDestination) {
			if (!isRename || !updateOriginating) {
				addEntryToBuildpath(rootEntry, workspaceRoot);								
			}  // else reference has been updated when updating originating project buildpath
		}
	}
	protected void moveResource(
		IProjectFragment root,
		IBuildpathEntry rootEntry,
		final IWorkspaceRoot workspaceRoot)
		throws ModelException {
			
		final char[][] exclusionPatterns = ((BuildpathEntry)rootEntry).fullExclusionPatternChars();
		IResource rootResource = root.getResource();
		if (rootEntry.getEntryKind() != IBuildpathEntry.BPE_SOURCE || exclusionPatterns == null) {
			try {
				IResource destRes;
				if ((this.updateModelFlags & IProjectFragment.REPLACE) != 0
						&& (destRes = workspaceRoot.findMember(this.destination)) != null) {
					destRes.delete(this.updateResourceFlags, progressMonitor);
				}
				rootResource.move(this.destination, this.updateResourceFlags, progressMonitor);
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
							proxy.requestResource().move(destPath, updateResourceFlags, progressMonitor);
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
						proxy.requestResource().move(destPath, updateResourceFlags, progressMonitor);
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
	/*
	 * Renames the buildpath entries equal to the given path in all Script projects.
	 */
	protected void updateReferringProjectBuildpaths(IPath rootPath, IDLTKProject projectOfRoot) throws ModelException {
		IScriptModel model = this.getModel();
		IDLTKProject[] projects = model.getScriptProjects();
		for (int i = 0, length = projects.length; i < length; i++) {
			IDLTKProject project = projects[i];
			if (project.equals(projectOfRoot)) continue;
			renameEntryInBuildpath(rootPath, project);
		}
	}
	/*
	 * Removes the buildpath entry equal to the given path from the given project's buildpath.
	 */
	protected void removeEntryFromBuildpath(IPath rootPath, IDLTKProject project) throws ModelException {
		
		IBuildpathEntry[] buildpath = project.getRawBuildpath();
		IBuildpathEntry[] newBuildpath = null;
		int cpLength = buildpath.length;
		int newCPIndex = -1;
		
		for (int i = 0; i < cpLength; i++) {
			IBuildpathEntry entry = buildpath[i];
			if (rootPath.equals(entry.getPath())) {
				if (newBuildpath == null) {
					newBuildpath = new IBuildpathEntry[cpLength];
					System.arraycopy(buildpath, 0, newBuildpath, 0, i);
					newCPIndex = i;
				}
			} else if (newBuildpath != null) {
				newBuildpath[newCPIndex++] = entry;
			}
		}
		
		if (newBuildpath != null) {
			if (newCPIndex < newBuildpath.length) {
				System.arraycopy(newBuildpath, 0, newBuildpath = new IBuildpathEntry[newCPIndex], 0, newCPIndex);
			}
			project.setRawBuildpath(newBuildpath, progressMonitor);
		}
	}
}
