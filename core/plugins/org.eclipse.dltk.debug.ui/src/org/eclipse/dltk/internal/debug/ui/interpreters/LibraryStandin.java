/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;

import java.io.File;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.IDLTKDebugUIConstants;
import org.eclipse.dltk.launching.LibraryLocation;



/**
 * Wrapper for an original library location, to support editing.
 * 
 */
public final class LibraryStandin {
	private IPath fSystemLibrary;
		
	/**
	 * Creates a new library standin on the given library location.
	 */	
	public LibraryStandin(LibraryLocation libraryLocation) {
		fSystemLibrary= libraryLocation.getLibraryPath();
	}		
		
	/**
	 * Returns the InterpreterEnvironment library archive location.
	 * 
	 * @return The InterpreterEnvironment library archive location.
	 */
	public IPath getSystemLibraryPath() {
		return fSystemLibrary;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof LibraryStandin) {
			LibraryStandin lib = (LibraryStandin)obj;
			return getSystemLibraryPath().equals(lib.getSystemLibraryPath());
		} 
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getSystemLibraryPath().hashCode();
	}
	
	/**
	 * Returns whether the given paths are equal - either may be <code>null</code>.
	 * @param path1 path to be compared
	 * @param path2 path to be compared
	 * @return whether the given paths are equal
	 */
	protected boolean equals(IPath path1, IPath path2) {
		return equalsOrNull(path1, path2);
	}
	
	/**
	 * Returns whether the given objects are equal - either may be <code>null</code>.
	 * @param o1 object to be compared
	 * @param o2 object to be compared
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

	
	/**
	 * Returns an equivalent library location.
	 * 
	 * @return library location
	 */
	public LibraryLocation toLibraryLocation() {
		return new LibraryLocation(getSystemLibraryPath());
	}
	
	/**
	 * Returns a status for this library describing any error states
	 * 
	 * @return
	 */
	public IStatus validate() {
		File f = getSystemLibraryPath().toFile();
		if (!f.exists()) {
			IPath path = getSystemLibraryPath();
			String message = "Path is empty";
			if( path != null ) {
				message = path.toOSString();
			}
			return new Status(IStatus.ERROR, DLTKDebugUIPlugin.PLUGIN_ID, IDLTKDebugUIConstants.INTERNAL_ERROR, 
					MessageFormat.format(InterpretersMessages.LibraryStandin_0, new String[]{message}), null);
		}	
		return Status.OK_STATUS;
	}
	
}
