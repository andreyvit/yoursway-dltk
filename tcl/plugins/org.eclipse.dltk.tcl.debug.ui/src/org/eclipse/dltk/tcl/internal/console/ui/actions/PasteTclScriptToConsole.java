/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.console.ui.actions;

import java.util.Iterator;

import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.tcl.internal.console.ui.TclConsole;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class PasteTclScriptToConsole implements IObjectActionDelegate {

	private ISelection selection;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
			
	}

	public void run(IAction action) {
		ScriptConsoleManager manager = ScriptConsoleManager.getInstance();

		ScriptConsole console = manager
				.getActiveScriptConsole(TclConsole.CONSOLE_TYPE);

		if (console == null) {
			return;
		}

		if (selection instanceof ITextSelection) {
			String text = ((ITextSelection) selection).getText();
			console.getInput().insertText(text);
		}
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;

			for (Iterator iterator2 = sel.iterator(); iterator2
					.hasNext();) {
				Object obj = iterator2.next();
				String text = null;
				if( obj instanceof ISourceModule ) {
					ISourceModule mo = (ISourceModule) obj;
					try {
						text = "#Tcl source:" + mo.getElementName() + "\n" + mo.getSource() + "\n";
					} catch (ModelException e) {
						if( DLTKCore.DEBUG ) {
							e.printStackTrace();
						}
					}
				}
				if(text != null) {
					console.getInput().insertText(text);
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;		
	}
}
