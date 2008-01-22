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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.core.util.Util;

/**
 *
 */
public class UserLibraryBuildpathContainerInitializer extends BuildpathContainerInitializer {

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#canUpdateClasspathContainer(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public boolean canUpdateBuildpathContainer(IPath containerPath, IScriptProject project) {
		return isUserLibraryContainer(containerPath);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getComparisonID(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public Object getComparisonID(IPath containerPath, IScriptProject project) {
		return containerPath;
	}
	
	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getDescription(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public String getDescription(IPath containerPath, IScriptProject project) {
		if (isUserLibraryContainer(containerPath)) {
			return containerPath.segment(1);
		}
		return super.getDescription(containerPath, project);
	}

	public void initialize(IPath containerPath, IScriptProject project) throws CoreException {
		if (isUserLibraryContainer(containerPath)) {
			String userLibName = containerPath.segment(1);
			UserLibrary userLibrary = ModelManager.getUserLibraryManager().getUserLibrary(userLibName);
			if (userLibrary != null) {
				UserLibraryBuildpathContainer container = new UserLibraryBuildpathContainer(userLibName);
				DLTKCore.setBuildpathContainer(containerPath, new IScriptProject[] { project }, new IBuildpathContainer[] { container }, null);
			} else if (ModelManager.BP_RESOLVE_VERBOSE) {
				verbose_no_user_library_found(project, userLibName);
			}
		} else if (ModelManager.BP_RESOLVE_VERBOSE) {
			verbose_not_a_user_library(project, containerPath);
		}
	}
	
	private boolean isUserLibraryContainer(IPath path) {
		return path != null && path.segmentCount() == 2 && DLTKCore.USER_LIBRARY_CONTAINER_ID.equals(path.segment(0));
	}
	
	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#requestClasspathContainerUpdate(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject, org.eclipse.jdt.core.IClasspathContainer)
	 */
	public void requestBuildpathContainerUpdate(IPath containerPath, IScriptProject project, IBuildpathContainer containerSuggestion) throws CoreException {
		if (isUserLibraryContainer(containerPath)) {
			String name = containerPath.segment(1);
			if (containerSuggestion != null) {
				ModelManager.getUserLibraryManager().setUserLibrary(name, containerSuggestion.getBuildpathEntries(), containerSuggestion.getKind() == IBuildpathContainer.K_SYSTEM);
			} else {
				ModelManager.getUserLibraryManager().removeUserLibrary(name);
			}
			// update of affected projects was done as a consequence of setUserLibrary() or removeUserLibrary()
		}
	}

	private void verbose_no_user_library_found(IScriptProject project, String userLibraryName) {
		Util.verbose(
			"UserLibrary INIT - FAILED (no user library found)\n" + //$NON-NLS-1$
			"	project: " + project.getElementName() + '\n' + //$NON-NLS-1$
			"	userLibraryName: " + userLibraryName); //$NON-NLS-1$
	}
	
	private void verbose_not_a_user_library(IScriptProject project, IPath containerPath) {
		Util.verbose(
			"UserLibrary INIT - FAILED (not a user library)\n" + //$NON-NLS-1$
			"	project: " + project.getElementName() + '\n' + //$NON-NLS-1$
			"	container path: " + containerPath); //$NON-NLS-1$
	}
}
