/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.PropertyDialogAction;

/**
 * Presents the standard properties dialog to configure the attributes of a
 * Script Breakpoint.
 */
public class ScriptBreakpointPropertiesAction implements IObjectActionDelegate {

	private IWorkbenchPart part;
	private IScriptBreakpoint breakpoint;

	public void run(IAction action) {
		IShellProvider provider;
		if (part != null) {
			provider = part.getSite();
		} else {
			provider = new IShellProvider() {
				public Shell getShell() {
					return DLTKDebugUIPlugin.getActiveWorkbenchShell();
				}
			};
		}
		
		PropertyDialogAction propertyAction = new PropertyDialogAction(
				provider, new ISelectionProvider() {
					public void addSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					public ISelection getSelection() {
						return new StructuredSelection(breakpoint);
					}

					public void removeSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					public void setSelection(ISelection selection) {
					}
				});
		propertyAction.run();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			if (ss.isEmpty() || ss.size() > 1) {
				return;
			}
			Object element = ss.getFirstElement();
			if (element instanceof IScriptBreakpoint) {
				setBreakpoint((IScriptBreakpoint) element);
			}
		}
	}

	public void setBreakpoint(IScriptBreakpoint breakpoint) {
		this.breakpoint = breakpoint;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		part = targetPart;
	}
}
