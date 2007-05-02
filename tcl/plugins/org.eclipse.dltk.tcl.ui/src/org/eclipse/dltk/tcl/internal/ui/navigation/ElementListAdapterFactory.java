/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.navigation;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.internal.ui.navigation.ElementsView.ElementList;

public class ElementListAdapterFactory implements IAdapterFactory {
	private static Class[] PROPERTIES = new Class[] { IModelElement.class };

	public Class[] getAdapterList() {
		return PROPERTIES;
	}

	public Object getAdapter(Object element, Class key) {
		if (IModelElement.class.equals(key)) {
			if (element instanceof ElementList) {
				return ((ElementList) element).getFirstElement();
			}
		}
		return null;
	}
}
