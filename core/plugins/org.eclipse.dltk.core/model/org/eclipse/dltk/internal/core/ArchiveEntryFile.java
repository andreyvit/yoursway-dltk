/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.ModelException;


public class ArchiveEntryFile extends PlatformObject implements IStorage {
	private String entryName;
	private String zipName;
	private IPath path;

	public ArchiveEntryFile(String entryName, String zipName, IPath parentRelativePath) {
		this.entryName = entryName;
		this.zipName = zipName;
		this.path = parentRelativePath;
	}

	public InputStream getContents() throws CoreException {
		try {
			if (ModelManager.ZIP_ACCESS_VERBOSE) {
				System.out.println("(" + Thread.currentThread() + ") [JarEntryFile.getContents()] Creating ZipFile on " + this.zipName); //$NON-NLS-1$	//$NON-NLS-2$
			}
			ZipFile zipFile = new ZipFile(this.zipName);
			ZipEntry zipEntry = zipFile.getEntry(this.entryName);
			if (zipEntry == null) {
				throw new ModelException(new ModelStatus(IModelStatusConstants.INVALID_PATH, this.entryName));
			}
			return zipFile.getInputStream(zipEntry);
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
		return "JarEntryFile[" + this.zipName + "::" + this.entryName + "]"; //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-1$
	}
}
