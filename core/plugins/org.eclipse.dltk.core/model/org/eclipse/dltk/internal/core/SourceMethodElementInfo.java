/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.compiler.env.ISourceMethod;
import org.eclipse.dltk.core.DLTKCore;

class SourceMethodElementInfo extends MemberElementInfo implements ISourceMethod {

	/**
	 * For a source method (that is, a method contained in a source module)
	 * this is a collection of the names of the parameters for this method,
	 * in the order the parameters are delcared. 
	 */
	private String[] argumentNames;
	private String[] argumentInitializers;
	
	protected void setArgumentNames(String[] names) {
		this.argumentNames = names;
	}
	
	public String[] getArgumentNames() {
		return this.argumentNames;
	}
	protected void setArgumentInializers(String[] initializers) {
		this.argumentInitializers = initializers;
	}
	
	public String[] getArgumentInitializers() {
		return this.argumentInitializers;
	}

	public boolean isConstructor() {
		if( DLTKCore.DEBUG) {
			System.err.println("Implement is Constructor");
		}
		return false;
	}

	public char[][] getExceptionTypeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] getReturnTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[][][] getTypeParameterBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[][] getTypeParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}
}
