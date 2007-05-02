/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IModelElement;


/** Element info for IOpenable elements. */
public class OpenableElementInfo extends ModelElementInfo {

	/**
	 * Is the structure of this element known
	 * @see IModelElement#isStructureKnown()
	 */
	protected boolean isStructureKnown = false;

	/**
	 * @see IModelElement#isStructureKnown()
	 */
	public boolean isStructureKnown() {
		return this.isStructureKnown;
	}
	
	/**
	 * Sets whether the structure of this element known
	 * @see IModelElement#isStructureKnown()
	 */
	public void setIsStructureKnown(boolean newIsStructureKnown) {
		this.isStructureKnown = newIsStructureKnown;
	}
}
