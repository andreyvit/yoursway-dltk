/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;


/**
 * Used to provide a description for interpreter selections in the 
 * installed interpreters block.
 */
public abstract class InterpreterDescriptor {

	/**
	 * Returns a description of the interpreter setting.
	 * 
	 * @return description of the interpeter setting
	 */
	public abstract String getDescription();
	
}
