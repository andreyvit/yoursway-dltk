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

	/**
	 * Return content type associated with this language. DLTK will check all
	 * derived content types to detect this language model elements.
	 */
	String getLanguageContentType();

	boolean validateSourcePackage(IPath path);
	IStatus validateSourceModule(IResource resource);

	/*
	 * Different stuff
	 */

	boolean languageSupportZIPBuildpath();

	String getNatureId();

	String getLanguageName();
}