/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.IPath;

public class LibraryLocation {
	private IPath fSystemLibrary;

	/**
	 * Creates a new library location.
	 * 
	 * @param libraryPath
	 *            The location of the archive containing java.lang.Object Must
	 *            not be <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If the library path is <code>null</code>.
	 * 
	 */
	public LibraryLocation(IPath libraryPath) {
		if (libraryPath == null) {
			throw new IllegalArgumentException();
		}

		fSystemLibrary = libraryPath;
	}

	/**
	 * Returns the InterpreterEnvironment library archive location.
	 * 
	 * @return The InterpreterEnvironment library archive location.
	 */
	public IPath getSystemLibraryPath() {
		return fSystemLibrary;
	}

	public boolean equals(Object obj) {
		if (obj instanceof LibraryLocation) {
			LibraryLocation lib = (LibraryLocation) obj;
			return getSystemLibraryPath().equals(lib.getSystemLibraryPath());
		}
		return false;
	}

	public int hashCode() {
		return getSystemLibraryPath().hashCode();
	}

	/**
	 * Returns whether the given paths are equal - either may be
	 * <code>null</code>.
	 * 
	 * @param path1
	 *            path to be compared
	 * @param path2
	 *            path to be compared
	 * @return whether the given paths are equal
	 */
	protected boolean equals(IPath path1, IPath path2) {
		return equalsOrNull(path1, path2);
	}

	/**
	 * Returns whether the given objects are equal - either may be
	 * <code>null</code>.
	 * 
	 * @param o1
	 *            object to be compared
	 * @param o2
	 *            object to be compared
	 * @return whether the given objects are equal or both null
	 * 
	 */
	private boolean equalsOrNull(Object o1, Object o2) {
		if (o1 == null) {
			return o2 == null;
		}
		if (o2 == null) {
			return false;
		}
		return o1.equals(o2);
	}
}
