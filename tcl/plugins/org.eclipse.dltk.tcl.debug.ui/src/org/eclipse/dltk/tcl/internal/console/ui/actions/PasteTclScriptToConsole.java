/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.console.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class PasteTclScriptToConsole implements IObjectActionDelegate {

	private ISelection selection;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
			
	}

	public void run(IAction action) {
		//TODO: implement
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;		
	}
}
