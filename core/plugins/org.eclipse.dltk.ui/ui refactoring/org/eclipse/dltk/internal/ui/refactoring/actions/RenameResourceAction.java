/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.actions;


import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringExecutionStarter;
import org.eclipse.dltk.internal.ui.actions.ActionUtil;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;


public class RenameResourceAction extends SelectionDispatchAction {

	public RenameResourceAction(IWorkbenchSite site) {
		super(site);
	}
	
	public void selectionChanged(IStructuredSelection selection) {
		IResource element= getResource(selection);
		if (element == null)
			setEnabled(false);
		else
			setEnabled(RefactoringAvailabilityTester.isRenameAvailable(element));
	}

	public void run(IStructuredSelection selection) {
		IResource resource = getResource(selection);
		// Work around for http://dev.eclipse.org/bugs/show_bug.cgi?id=19104		
		if (!ActionUtil.isProcessable(getShell(), resource))
			return;
		if (!RefactoringAvailabilityTester.isRenameAvailable(resource))
			return;
		try {
			RefactoringExecutionStarter.startRenameResourceRefactoring(resource, getShell());
		} catch (CoreException e) {
			ExceptionHandler.handle(e, RefactoringMessages.RenameScriptElementAction_name, RefactoringMessages.RenameScriptElementAction_exception);  
		}
	}

	private static IResource getResource(IStructuredSelection selection) {
		if (selection.size() != 1)
			return null;
		Object first= selection.getFirstElement();
		if (! (first instanceof IResource))
			return null;
		return (IResource)first;
	}
}
