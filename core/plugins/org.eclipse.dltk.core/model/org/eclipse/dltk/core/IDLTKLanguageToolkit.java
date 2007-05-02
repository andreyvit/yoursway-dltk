/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;


public interface IDLTKLanguageToolkit {

	/*
	 * Validation of language toolkit resources
	 */

	IStatus validateSourceModule(IResource resource);

	IStatus validateSourceModule(IPath path);

	IStatus validateSourceModuleName(String name);

	boolean validateSourcePackage(IPath path);

	/*
	 * Different stuff
	 */
	
	boolean languageSupportZIPBuildpath();

	String getNatureID();

	String getDelimeterReplacerString();
	
	String[] getLanguageFileExtensions();
	
	String getLanguageName();
}