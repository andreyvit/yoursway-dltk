/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.ModelException;


/**
 * A archive entry that represents a non-java resource found in a archive.
 * 
 * @see IStorage
 */
public class ExternalEntryFile extends PlatformObject implements IStorage {
	private IPath path;

	public ExternalEntryFile(IPath path) {		
		this.path = path;
	}

	public InputStream getContents() throws CoreException {
		try {
			return new FileInputStream(path.toOSString());
		} catch (IOException e) {
			throw new ModelException(e, IModelStatusConstants.IO_EXCEPTION);
		}
	}

	/**
	 * @see IStorage#getFullPath
	 */
	public IPath getFullPath() {
		return this.path;
	}

	/**
	 * @see IStorage#getName
	 */
	public String getName() {
		return this.path.lastSegment();
	}

	/**
	 * @see IStorage#isReadOnly()
	 */
	public boolean isReadOnly() {
		return true;
	}

	/**
	 * @see IStorage#isReadOnly()
	 */
	public String toString() {
		return "ExternalEntryFile[" + this.path.toOSString() + "]"; //$NON-NLS-2$ //$NON-NLS-1$
	}
}
