/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.search;

import org.eclipse.dltk.core.IModelElement;

public class DLTKElementLine {
	private IModelElement fElement;
	private int fLine;
	private String fLineContents;
	
	/**
	 * @param element either an ISourceModule or an IClassFile
	 */
	public DLTKElementLine(IModelElement element, int line, String lineContents) {
		fElement= element;
		fLine= line;
		fLineContents= lineContents;
	}
	
	public IModelElement getModelElement() {
		return fElement;
	}
	
	public int getLine() {
		return fLine;
	}
	
	public String getLineContents() {
		return fLineContents;
	}
}
