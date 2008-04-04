/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.workingsets;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IWorkingSetEditWizard;


/**
 * Displays an IWorkingSetEditWizard for editing a working set.
 * 
	 *
 */
public class EditWorkingSetAction extends Action {
	private IWorkbenchPartSite fSite;
	private Shell fShell;
	private WorkingSetFilterActionGroup fActionGroup;

	public EditWorkingSetAction(WorkingSetFilterActionGroup actionGroup, IWorkbenchPartSite site) {
		this(actionGroup); 
		fSite= site;
	}
	
	public EditWorkingSetAction(WorkingSetFilterActionGroup actionGroup, Shell shell) {
		this(actionGroup); 
		fShell= shell;
	}
	
	private EditWorkingSetAction(WorkingSetFilterActionGroup actionGroup) {
		super(WorkingSetMessages.EditWorkingSetAction_text); 
		setToolTipText(WorkingSetMessages.EditWorkingSetAction_toolTip); 
		setEnabled(actionGroup.getWorkingSet() != null);
		fActionGroup= actionGroup;
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here..."); //$NON-NLS-1$
		}
		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.EDIT_WORKING_SET_ACTION);
	}
	
	/*
	 * Overrides method from Action
	 */
	public void run() {
		Shell shell= getShell();
		IWorkingSetManager manager= PlatformUI.getWorkbench().getWorkingSetManager();
		IWorkingSet workingSet= fActionGroup.getWorkingSet();
		if (workingSet == null || workingSet.isAggregateWorkingSet()) {
			setEnabled(false);
			return;
		}
		IWorkingSetEditWizard wizard= manager.createWorkingSetEditWizard(workingSet);
		if (wizard == null) {
			String title= WorkingSetMessages.EditWorkingSetAction_error_nowizard_title; 
			String message= WorkingSetMessages.EditWorkingSetAction_error_nowizard_message; 
			MessageDialog.openError(shell, title, message);
			return;
		}
		WizardDialog dialog= new WizardDialog(shell, wizard);
	 	dialog.create();		
		if (dialog.open() == Window.OK)
			fActionGroup.setWorkingSet(wizard.getSelection(), true);
	}
	
	private Shell getShell() {
		if (fSite != null) {
			return fSite.getShell();
		} else if (fShell != null) {
			return fShell;
		} else {
			return DLTKUIPlugin.getActiveWorkbenchShell();
		}
	}
}
