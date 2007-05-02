/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.navigation;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IType;

public class FunctionsView extends ElementsView {
	//TODO: Remove into preferences
	private boolean processNamespaceMethods = false;
	public String getElementName(Object element) {		
		if( element instanceof IMethod ) {
			String buffer = "";			
			IModelElement el  = (IModelElement)element;
			while( el != null ) {
				String str = getOriginalElementText(el);
				if( str.startsWith("::")) {
					buffer = str + buffer;
				}
				else {
					buffer = "::" + str + buffer;
				}
				if( el instanceof IParent ) {
					IModelElement parent = el.getParent();
					if( parent instanceof IType ) {
						el = parent;
					}
					else {
						el = null;
					}
				}
				else {
					el = null;
				}
			}
			String from = ((IMethod)element).getAncestor(IModelElement.SCRIPT_FOLDER).getElementName();
			if( from.length() > 0 ) {				
				return buffer + " (" + from + ")";
			}
			return buffer;
		}
		return null;
	}

	public String getJobTitle() {
		return "Functions view search...";
	}

	public boolean isElement(IModelElement e) {
		return e instanceof IMethod;
	}	
	
	public boolean needProcessChildren(IModelElement e) {
		if( e instanceof IType ) {
			return processNamespaceMethods;
		}		
		else if( e instanceof IMethod || e instanceof IField  ) {
			return false;
		}			
		return true;
	}

	protected String getPreferencesId() {		
		return "FunctionsView_";
	}	
}
