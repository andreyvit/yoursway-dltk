/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker.ui.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.tcl.internal.tclchecker.TclCheckerNature;
import org.eclipse.dltk.tcl.internal.tclchecker.ui.preferences.TclCheckerPreferences;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;



public class AddTclCheckerNature implements IObjectActionDelegate {

	private ISelection selection;

	public AddTclCheckerNature() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	public void run(IAction action) {
		if (!TclCheckerPreferences.checkTclCheckerPath(true)) {
			return;
		}

		if (selection instanceof IStructuredSelection) {
			for (Iterator it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element)
							.getAdapter(IProject.class);
				}
				if (project != null) {
					try {						
						TclCheckerNature.addNature(project);
					} catch (CoreException e) {
						ErrorDialog.openError(null,
								ActionMessages.TclChecker_Error,
								ActionMessages.TclChecker_CannotAddNature, e
										.getStatus());
					}
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}