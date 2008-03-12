/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.BasicSearchEngine;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchDocument;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.internal.core.search.processing.JobManager;
import org.eclipse.dltk.internal.core.util.Util;


public class DLTKSearchDocument extends SearchDocument {
	private IFile file;
	protected byte[] byteContents;
	protected String charContents;
	private boolean external;

	public DLTKSearchDocument(String documentPath, SearchParticipant participant, boolean external) {
		super(documentPath, participant);
		this.external = external;
	}

	public DLTKSearchDocument(java.util.zip.ZipEntry zipEntry, IPath zipFilePath, byte[] contents, SearchParticipant participant) {
		super(zipFilePath + IDLTKSearchScope.FILE_ENTRY_SEPARATOR + zipEntry.getName(), participant);
		this.byteContents = contents;
	}

	public DLTKSearchDocument(String path, IPath containerPath, char[] contents, SearchParticipant participant, boolean external) {
		super(IDLTKSearchScope.FILE_ENTRY_SEPARATOR + path, participant );
		this.charContents = new String( contents );
		this.external = external;
	}	

	public String getContents() {
		char[] contents = getCharContents();
		if( contents == null ) {
			return ""; //$NON-NLS-1$
		}
		String ret = new String(contents);
		return ret;
	}

//	public byte[] getByteContents() {
//		if (this.byteContents != null)
//			return this.byteContents;
//		try {
//			if (!external) {
//				return Util.getResourceContentsAsByteArray(getFile());
//			} else {
//				File ffile = new File(this.getPath());
//				if (ffile != null && ffile.exists()) {
//					return Util.getResourceContentsAsByteArray(ffile);
//				}
//				return null;
//			}
//		} catch (ModelException e) {
//			if (BasicSearchEngine.VERBOSE || JobManager.VERBOSE) { // used
//																	// during
//																	// search
//																	// and
//																	// during
//																	// indexing
//				e.printStackTrace();
//			}
//			return null;
//		}
//	}

	public char[] getCharContents() {
		if (this.charContents != null)
			return this.charContents.toCharArray();
		try {
			if (!external) {
				IFile file = getFile();
				if( file.exists() ) {
					return Util.getResourceContentsAsCharArray(file);
				}
				else {
					File ffile = new File(this.getPath());
					if (ffile != null && ffile.exists()) {
						return Util.getResourceContentsAsCharArray(ffile);
					}
					return null;
				}
			} else {
				File ffile = new File(this.getPath());
				if (ffile != null && ffile.exists()) {
					return Util.getResourceContentsAsCharArray(ffile);
				}
				return null;
			}
		} catch (ModelException e) {
			if (BasicSearchEngine.VERBOSE || JobManager.VERBOSE) { // used
																	// during
																	// search
																	// and
																	// during
																	// indexing
				e.printStackTrace();
			}
			return null;
		}
	}

	public String getEncoding() {
		// Return the encoding of the associated file
		if (!external) {
			IFile resource = getFile();
			if (resource != null) {
				try {
					return resource.getCharset();
				} catch (CoreException ce) {
					try {
						return ResourcesPlugin.getWorkspace().getRoot().getDefaultCharset();
					} catch (CoreException e) {
						// use no encoding
					}
				}
			}
		}
		return null;
	}

	private IFile getFile() {
		if( !external ) {
			if (this.file == null) {
				this.file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getPath()));			
			}
		}		
		return this.file;
	}

	public String toString() {
		return "SearchDocument for " + getPath(); //$NON-NLS-1$
	}
	public boolean isExternal() {
		return this.external;
	}
}
