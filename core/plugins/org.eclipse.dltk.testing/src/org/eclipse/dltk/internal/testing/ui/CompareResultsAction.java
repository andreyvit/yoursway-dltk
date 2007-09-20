/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.ui;


import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;

import org.eclipse.jface.action.Action;

import org.eclipse.dltk.internal.testing.model.TestElement;
import org.eclipse.dltk.testing.DLTKTestingPlugin;


/**
 * Action to enable/disable stack trace filtering.
 */
public class CompareResultsAction extends Action {

	private FailureTrace fView;
	private CompareResultDialog fOpenDialog;
	
	public CompareResultsAction(FailureTrace view) {
		super(DLTKTestingMessages.CompareResultsAction_label);   
		setDescription(DLTKTestingMessages.CompareResultsAction_description);   
		setToolTipText(DLTKTestingMessages.CompareResultsAction_tooltip);  
		
		setDisabledImageDescriptor(DLTKTestingPlugin.getImageDescriptor("dlcl16/compare.gif"));  //$NON-NLS-1$
		setHoverImageDescriptor(DLTKTestingPlugin.getImageDescriptor("elcl16/compare.gif"));  //$NON-NLS-1$
		setImageDescriptor(DLTKTestingPlugin.getImageDescriptor("elcl16/compare.gif"));  //$NON-NLS-1$
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJUnitHelpContextIds.ENABLEFILTER_ACTION);
		fView= view;
	}

	/*
	 * @see Action#actionPerformed
	 */		
	public void run() {
		TestElement failedTest= fView.getFailedTest();
		if (fOpenDialog != null) {
			fOpenDialog.setInput(failedTest);
			fOpenDialog.getShell().setActive();
				
		} else {
			fOpenDialog= new CompareResultDialog(fView.getShell(), failedTest);
			fOpenDialog.create();
			fOpenDialog.getShell().addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					fOpenDialog= null;
				}
			});
			fOpenDialog.setBlockOnOpen(false);
			fOpenDialog.open();
		}
	}
	
	public void updateOpenDialog(TestElement failedTest) {
		if (fOpenDialog != null) {
			fOpenDialog.setInput(failedTest);
		}
	}
}
