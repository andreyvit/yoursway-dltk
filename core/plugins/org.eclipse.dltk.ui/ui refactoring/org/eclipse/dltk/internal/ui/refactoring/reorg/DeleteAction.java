/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ScriptModelUtil;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringExecutionStarter;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.DeleteResourceAction;



public class DeleteAction extends SelectionDispatchAction {

	public DeleteAction(IWorkbenchSite site) {
		super(site);
		setText(ReorgMessages.DeleteAction_3); 
		setDescription(ReorgMessages.DeleteAction_4); 
		ISharedImages workbenchImages= DLTKUIPlugin.getDefault().getWorkbench().getSharedImages();
		setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setHoverImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.DELETE_ACTION);
	}

	/*
	 * @see SelectionDispatchAction#selectionChanged(IStructuredSelection)
	 */
	public void selectionChanged(IStructuredSelection selection) {
		if (ReorgUtils.containsOnlyProjects(selection.toList())) {
			setEnabled(createWorkbenchAction(selection).isEnabled());
			return;
		}
		try {
			setEnabled(RefactoringAvailabilityTester.isDeleteAvailable(selection.toArray()));
		} catch (CoreException e) {
			//no ui here - this happens on selection changes
			// http://bugs.eclipse.org/bugs/show_bug.cgi?id=19253
			if (ScriptModelUtil.isExceptionToBeLogged(e))
				DLTKUIPlugin.log(e);
			setEnabled(false);
		}
	}

	private IAction createWorkbenchAction(IStructuredSelection selection) {
		DeleteResourceAction action= new DeleteResourceAction(getShell());
		action.selectionChanged(selection);
		return action;
	}

	public void run(IStructuredSelection selection) {
		if (ReorgUtils.containsOnlyProjects(selection.toList())) {
			createWorkbenchAction(selection).run();
			return;
		}
		try {
			RefactoringExecutionStarter.startDeleteRefactoring(selection.toArray(), getShell());
		} catch (CoreException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		}
	}
}
