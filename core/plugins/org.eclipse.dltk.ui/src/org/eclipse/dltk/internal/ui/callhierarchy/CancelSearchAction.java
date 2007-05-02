/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *          (report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.callhierarchy;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;


/**
 * This class is copied from the
 * org.eclipse.search2.internal.ui.CancelSearchAction class.
 */
public class CancelSearchAction extends Action {
	private CallHierarchyViewPart fView;

	public CancelSearchAction(CallHierarchyViewPart view) {
		super(CallHierarchyMessages.CancelSearchAction_label);
		fView = view;
		setToolTipText(CallHierarchyMessages.CancelSearchAction_tooltip);
		DLTKPluginImages.setLocalImageDescriptors(this, "ch_cancel.gif"); //$NON-NLS-1$
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
		// IJavaHelpContextIds.CALL_HIERARCHY_CANCEL_SEARCH_ACTION);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
	}

	public void run() {
		fView.cancelJobs();
	}
}
