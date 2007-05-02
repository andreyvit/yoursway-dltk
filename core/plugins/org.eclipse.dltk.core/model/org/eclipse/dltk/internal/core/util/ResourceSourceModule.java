/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.util;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.internal.core.SourceModule;

/**
 * An ICompilationUnit that retrieves its contents using an IFile
 */
public class ResourceSourceModule extends SourceModule {
	
	private IFile file;
	
	public ResourceSourceModule(IFile file, URI location) {
		super(null/*no contents*/, location == null ? file.getFullPath().toString() : location.getPath(), null/*encoding is used only when retrieving the contents*/);
		this.file = file;
	}

	public char[] getContents() {
		// otherwise retrieve it
		try {
			return Util.getResourceContentsAsCharArray(this.file);
		} catch (CoreException e) {
			return CharOperation.NO_CHAR;
		}
	}
}
