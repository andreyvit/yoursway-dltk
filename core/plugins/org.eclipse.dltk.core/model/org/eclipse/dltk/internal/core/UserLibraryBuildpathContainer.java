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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.core.util.Util;

/**
 *
 */
public class UserLibraryBuildpathContainer implements IBuildpathContainer {
	
	private String name;
	private IDLTKLanguageToolkit toolkit;
	public UserLibraryBuildpathContainer(String name, IDLTKLanguageToolkit languageToolkit) {
		this.name = name;
		this.toolkit = languageToolkit;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	public IBuildpathEntry[] getBuildpathEntries(IScriptProject project) {
		UserLibrary library= getUserLibrary();
		if (library != null) {
			return library.getEntries();
		}
		return new IBuildpathEntry[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getDescription()
	 */
	public String getDescription(IScriptProject project) {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getKind()
	 */
	public int getKind() {
		UserLibrary library= getUserLibrary();
		if (library != null && library.isSystemLibrary()) {
			return K_SYSTEM;
		}
		return K_APPLICATION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getPath()
	 */
	public IPath getPath() {
		return new Path(DLTKCore.USER_LIBRARY_CONTAINER_ID).append(this.name);
	}
	
	private UserLibrary getUserLibrary() {
		UserLibrary userLibrary = ModelManager.getUserLibraryManager().getUserLibrary(this.name, toolkit);
		if (userLibrary == null && ModelManager.BP_RESOLVE_VERBOSE) {
			verbose_no_user_library_found(this.name);
		}
		return userLibrary;
	}

	private void verbose_no_user_library_found(String userLibraryName) {
		Util.verbose(
			"UserLibrary INIT - FAILED (no user library found)\n" + //$NON-NLS-1$
			"	userLibraryName: " + userLibraryName); //$NON-NLS-1$
	}

	public IBuiltinModuleProvider getBuiltinProvider(IScriptProject project) {
		return null;
	}
}
