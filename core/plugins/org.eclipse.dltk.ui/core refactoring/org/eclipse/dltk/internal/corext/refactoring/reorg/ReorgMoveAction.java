/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringExecutionStarter;
import org.eclipse.dltk.internal.ui.editor.ModelTextSelection;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.MoveProjectAction;


public class ReorgMoveAction extends SelectionDispatchAction {
	public ReorgMoveAction(IWorkbenchSite site) {
		super(site);
		setText(ReorgMessages.ReorgMoveAction_3); 
		setDescription(ReorgMessages.ReorgMoveAction_4); 
		/*PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.MOVE_ACTION);*/
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
	}

	public void selectionChanged(IStructuredSelection selection) {
		if (!selection.isEmpty()) {
			if (ReorgUtils.containsOnlyProjects(selection.toList())) {
				setEnabled(createWorkbenchAction(selection).isEnabled());
				return;
			}
			try {
				List elements= selection.toList();
				IResource[] resources= ReorgUtils.getResources(elements);
				IModelElement[] modelElements= ReorgUtils.getModelElements(elements);
				if (elements.size() != resources.length + modelElements.length)
					setEnabled(false);
				else
					setEnabled(RefactoringAvailabilityTester.isMoveAvailable(resources, modelElements));
			} catch (ModelException e) {
				// no ui here - this happens on selection changes
				// http://bugs.eclipse.org/bugs/show_bug.cgi?id=19253
				if (DLTKModelUtil.isExceptionToBeLogged(e))
					DLTKUIPlugin.log(e);
				setEnabled(false);
			}
		} else
			setEnabled(false);
	}

	public void selectionChanged(ITextSelection selection) {
		setEnabled(true);
	}

	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	public void selectionChanged(ModelTextSelection selection) {
		try {
			setEnabled(RefactoringAvailabilityTester.isMoveAvailable(selection));
		} catch (ModelException e) {
			setEnabled(false);
		}
	}

	private MoveProjectAction createWorkbenchAction(IStructuredSelection selection) {
		MoveProjectAction action= new MoveProjectAction(getShell());
		action.selectionChanged(selection);
		return action;
	}

	public void run(IStructuredSelection selection) {
		if (ReorgUtils.containsOnlyProjects(selection.toList())) {
			createWorkbenchAction(selection).run();
			return;
		}
		try {
			List elements= selection.toList();
			IResource[] resources= ReorgUtils.getResources(elements);
			IModelElement[] modelElements= ReorgUtils.getModelElements(elements);
			if (RefactoringAvailabilityTester.isMoveAvailable(resources, modelElements))
				RefactoringExecutionStarter.startRefactoring(resources, modelElements, getShell());
		} catch (ModelException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		}
	}
}
