/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.navigation;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IPackageDeclaration;

public class PackagesView extends ElementsView {
	public String getElementName(Object element) {		
		if( element instanceof IPackageDeclaration ) {
			return ((IPackageDeclaration)element).getElementName();
		}
		return null;
	}

	public String getJobTitle() {
		return "Packages view search...";
	}

	public boolean isElement(IModelElement e) {
		return e instanceof IPackageDeclaration;
	}	
	
	public boolean needProcessChildren(IModelElement e) {
		return true;
	}
	protected String getPreferencesId() {		
		return "PackagesView_";
	}	
}
