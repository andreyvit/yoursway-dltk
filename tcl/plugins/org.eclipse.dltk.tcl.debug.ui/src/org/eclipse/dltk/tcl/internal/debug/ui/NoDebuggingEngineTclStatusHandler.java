/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class NoDebuggingEngineTclStatusHandler implements IStatusHandler {

	private static final String TCL_DEBUG_PREFERENCES_ID = "org.eclipse.dltk.tcl.preferences.debug";

	public Object handleStatus(IStatus status, Object source)
			throws CoreException {

		final boolean result[] = new boolean[] { false };
		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				result[0] = MessageDialog.openQuestion(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(),
						Messages.NoDebuggingEngine_title,
						Messages.NoDebuggingEngine_message);

				if (result[0]) {
					PreferenceDialog dialog = PreferencesUtil
							.createPreferenceDialogOn(null,
									TCL_DEBUG_PREFERENCES_ID,
									new String[] { TCL_DEBUG_PREFERENCES_ID },
									null);
					dialog.open();
				}
			}
		});

		return Boolean.valueOf(result[0]);
	}
}
