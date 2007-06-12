/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.console.ui.actions;


import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.debug.ui.actions.ViewFilterAction;
import org.eclipse.dltk.javascript.internal.ui.preferences.JavaScriptConsolePreferences;
import org.eclipse.jface.viewers.Viewer;

/**
 * Shows non-final static variables
 */
public class ShowFunctionsAction extends ViewFilterAction {

	public ShowFunctionsAction() {
		super();
	}

	protected String getPreferenceKey() {
		return "show_functions"; 
	}
	
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IScriptVariable) {
			IScriptVariable variable = (IScriptVariable)element;
			return !variable.getType().getName().equals("function"); 
		}
		return true;
	}	
}
