/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.dltk.core.ISourceModule;


public interface ISavePolicy {

	/**
	 *
	 */
	void preSave(ISourceModule unit);

	/**
	 * Returns the compilation unit in which the argument
	 * has been changed. If the argument is not changed, the
	 * returned result is <code>null</code>.
	 */
	ISourceModule postSave(ISourceModule unit);
}

