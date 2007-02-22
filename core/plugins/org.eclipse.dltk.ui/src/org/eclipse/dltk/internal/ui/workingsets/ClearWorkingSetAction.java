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
package org.eclipse.dltk.internal.ui.workingsets;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.jface.action.Action;

/**
 * Clears the selected working set in the action group's view.
 * 
	 *
 */
public class ClearWorkingSetAction extends Action {
	
	private WorkingSetFilterActionGroup fActionGroup;

	public ClearWorkingSetAction(WorkingSetFilterActionGroup actionGroup) {
		super(WorkingSetMessages.ClearWorkingSetAction_text); 
		setToolTipText(WorkingSetMessages.ClearWorkingSetAction_toolTip); 
		setEnabled(actionGroup.getWorkingSet() != null);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.CLEAR_WORKING_SET_ACTION);
		fActionGroup= actionGroup;
	}

	/*
	 * Overrides method from Action
	 */
	public void run() {
		fActionGroup.setWorkingSet(null, true);
	}
}
