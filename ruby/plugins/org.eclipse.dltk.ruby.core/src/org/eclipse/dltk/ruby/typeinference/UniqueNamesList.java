/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.dltk.core.IModelElement;

class UniqueNamesList extends ArrayList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4866432224140371654L;
	
	HashSet names = new HashSet ();
	
	public boolean add(Object elem) {
		if (elem instanceof IModelElement) {
			IModelElement modelElement = (IModelElement) elem;
			if (names.contains(modelElement.getElementName()))
					return false;
			names.add(modelElement.getElementName());
		}
		return super.add(elem);
	}

	public void clear() {			
		super.clear();
		names.clear();
	}

	public boolean contains(Object elem) {
		if (elem instanceof IModelElement) {
			IModelElement modelElement = (IModelElement) elem;
			return names.contains(modelElement.getElementName());
		}
		return super.contains(elem);
	}

}