/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class NoDefaultInterpreterStatusHandler implements IStatusHandler {

	public Object handleStatus(IStatus status, Object source) {
		final boolean[] result = new boolean[1];

		final String natureId = ((AbstractScriptLaunchConfigurationDelegate) source)
				.getLanguageId();

		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = LaunchingMessages.NoDefaultInterpreterStatusHandler_title;
				String message = LaunchingMessages.NoDefaultInterpreterStatusHandler_message;
				result[0] = (MessageDialog.openQuestion(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message));
				if (result[0]) {
					showInterpreterPreferencePage(natureId);
				}
			}
		});

		return new Boolean(result[0]);
	}

	protected void showInterpreterPreferencePage(String natureId) {
		String preferencePageId = null;
		IDLTKUILanguageToolkit languageToolkit = null;
		languageToolkit = DLTKUILanguageManager.getLanguageToolkit(natureId);
		if( languageToolkit == null ) {
			return;
		}
		preferencePageId = languageToolkit.getInterpreterPreferencePage();

		if (preferencePageId != null) {
			PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(
					null, preferencePageId, null, null);
			dialog.open();
		}
	}
}
