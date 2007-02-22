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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ModelException;


public class DeleteProjectFragmentOperation extends ModelOperation {

	int updateResourceFlags;
	int updateModelFlags;

	public DeleteProjectFragmentOperation(
		IProjectFragment root,
		int updateResourceFlags,
		int updateModelFlags) {
			
		super(root);
		this.updateResourceFlags = updateResourceFlags;
		this.updateModelFlags = updateModelFlags;
	}

	protected void executeOperation() throws ModelException {
		
		IProjectFragment root = (IProjectFragment)this.getElementToProcess();
		IBuildpathEntry rootEntry = root.getRawBuildpathEntry();
		
		// remember olds roots
		DeltaProcessor deltaProcessor = ModelManager.getModelManager().getDeltaProcessor();
		if (deltaProcessor.oldRoots == null)
			deltaProcessor.oldRoots = new HashMap();
		
		// update buildpath if needed
		if ((updateModelFlags & IProjectFragment.ORIGINATING_PROJECT_BUILDPATH) != 0) {
			updateProjectBuildpath(rootEntry.getPath(), root.getScriptProject(), deltaProcessor.oldRoots);
		}
		if ((updateModelFlags & IProjectFragment.OTHER_REFERRING_PROJECTS_BUILDPATH) != 0) {
			updateReferringProjectBuildpaths(rootEntry.getPath(), root.getScriptProject(), deltaProcessor.oldRoots);
		}
		
		// delete resource
		if (!root.isExternal() && (this.updateModelFlags & IProjectFragment.NO_RESOURCE_MODIFICATION) == 0) {
			deleteResource(root, rootEntry);
		}
	}

	protected void deleteResource(
		IProjectFragment root,
		IBuildpathEntry rootEntry)
		throws ModelException {
		final char[][] exclusionPatterns = ((BuildpathEntry)rootEntry).fullExclusionPatternChars();
		IResource rootResource = root.getResource();
		if (rootEntry.getEntryKind() != IBuildpathEntry.BPE_SOURCE || exclusionPatterns == null) {
			try {
				rootResource.delete(this.updateResourceFlags, progressMonitor);
			} catch (CoreException e) {
				throw new ModelException(e);
			}
		} else {
			final IPath[] nestedFolders = getNestedFolders(root);
			IResourceProxyVisitor visitor = new IResourceProxyVisitor() {
				public boolean visit(IResourceProxy proxy) throws CoreException {
					if (proxy.getType() == IResource.FOLDER) {
						IPath path = proxy.requestFullPath();
						if (prefixesOneOf(path, nestedFolders)) {
							// equals if nested source folder
							return !equalsOneOf(path, nestedFolders);
						} else {
							// subtree doesn't contain any nested source folders
							proxy.requestResource().delete(updateResourceFlags, progressMonitor);
							return false;
						}
					} else {
						proxy.requestResource().delete(updateResourceFlags, progressMonitor);
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
	 * Deletes the buildpath entries equals to the given rootPath from all Script projects.
	 */
	protected void updateReferringProjectBuildpaths(IPath rootPath, IDLTKProject projectOfRoot, Map oldRoots) throws ModelException {
		IScriptModel model = this.getModel();
		IDLTKProject[] projects = model.getScriptProjects();
		for (int i = 0, length = projects.length; i < length; i++) {
			IDLTKProject project = projects[i];
			if (project.equals(projectOfRoot)) continue;
			updateProjectBuildpath(rootPath, project, oldRoots);
		}
	}

	/*
	 * Deletes the buildpath entries equals to the given rootPath from the given project.
	 */
	protected void updateProjectBuildpath(IPath rootPath, IDLTKProject project, Map oldRoots) throws ModelException {
		// remember old roots
		oldRoots.put(project, project.getProjectFragments());
		
		IBuildpathEntry[] buildpath = project.getRawBuildpath();
		IBuildpathEntry[] newBuildpath = null;
		int cpLength = buildpath.length;
		int newCPIndex = -1;
		for (int j = 0; j < cpLength; j++) {
			IBuildpathEntry entry = buildpath[j];
			if (rootPath.equals(entry.getPath())) {
				if (newBuildpath == null) {
					newBuildpath = new IBuildpathEntry[cpLength-1];
					System.arraycopy(buildpath, 0, newBuildpath, 0, j);
					newCPIndex = j;
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
	protected IModelStatus verify() {
		IModelStatus status = super.verify();
		if (!status.isOK()) {
			return status;
		}
		IProjectFragment root = (IProjectFragment) this.getElementToProcess();
		if (root == null || !root.exists()) {
			return new ModelStatus(IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, root);
		}

		IResource resource = root.getResource();
		if (resource instanceof IFolder) {
			if (resource.isLinked()) {
				return new ModelStatus(IModelStatusConstants.INVALID_RESOURCE, root);
			}
		}
		return ModelStatus.VERIFIED_OK;
	}

}
