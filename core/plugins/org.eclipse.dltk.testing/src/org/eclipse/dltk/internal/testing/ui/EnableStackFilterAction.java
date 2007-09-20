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
package org.eclipse.dltk.internal.testing.ui;


import org.eclipse.jface.action.Action;

import org.eclipse.ui.PlatformUI;

import org.eclipse.dltk.testing.DLTKTestingPlugin;

/**
 * Action to enable/disable stack trace filtering.
 */
public class EnableStackFilterAction extends Action {

	private FailureTrace fView;	
	
	public EnableStackFilterAction(FailureTrace view) {
		super(DLTKTestingMessages.EnableStackFilterAction_action_label);  
		setDescription(DLTKTestingMessages.EnableStackFilterAction_action_description);  
		setToolTipText(DLTKTestingMessages.EnableStackFilterAction_action_tooltip); 
		
		setDisabledImageDescriptor(DLTKTestingPlugin.getImageDescriptor("dlcl16/cfilter.gif")); //$NON-NLS-1$
		setHoverImageDescriptor(DLTKTestingPlugin.getImageDescriptor("elcl16/cfilter.gif")); //$NON-NLS-1$
		setImageDescriptor(DLTKTestingPlugin.getImageDescriptor("elcl16/cfilter.gif")); //$NON-NLS-1$
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IDLTKTestingHelpContextIds.ENABLEFILTER_ACTION);

		fView= view;
//		setChecked(JUnitPreferencePage.getFilterStack());
	}

	/*
	 * @see Action#actionPerformed
	 */		
	public void run() {
//		JUnitPreferencePage.setFilterStack(isChecked());
		fView.refresh();
	}
}
