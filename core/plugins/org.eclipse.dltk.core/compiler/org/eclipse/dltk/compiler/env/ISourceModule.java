/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.compiler.env;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IModelElement;

/**
 * This interface denotes a compilation unit, providing its name and content.
 */
public interface ISourceModule extends IDependent {

	/**
	 * Answer the name of the package according to the directory structure or
	 * null if package consistency checks should be ignored. For example, {java,
	 * lang}.
	 */
	IPath getScriptFolder();

	/**
	 * Answer the contents of the compilation unit.
	 * 
	 * In normal use, the contents are requested twice. Once during the initial
	 * lite parsing step, then again for the more detailed parsing step.
	 */
	String getSourceContents();

	IModelElement getModelElement();
}
