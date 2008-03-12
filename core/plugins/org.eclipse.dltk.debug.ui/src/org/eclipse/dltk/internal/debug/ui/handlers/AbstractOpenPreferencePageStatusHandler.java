/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
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
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * 
 * @author kds
 * 
 */
public abstract class AbstractOpenPreferencePageStatusHandler implements
		IStatusHandler {
	protected void showPreferencePage(String pageId) {
		final PreferenceDialog dialog = PreferencesUtil
				.createPreferenceDialogOn(null, pageId, null, null);
		dialog.open();
	}

	public Object handleStatus(final IStatus status, Object source)
			throws CoreException {

		final String pageId = getPreferencePageId(source);

		final boolean[] result = new boolean[1];

		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				final String title = getTitle();
				if (pageId == null) {
					MessageDialog.openError(DLTKDebugUIPlugin
							.getActiveWorkbenchShell(), title, status
							.getMessage());
				} else {
					final String message = status.getMessage() + " " //$NON-NLS-1$
							+ getQuestion();
					result[0] = (MessageDialog.openQuestion(DLTKDebugUIPlugin
							.getActiveWorkbenchShell(), title, message));
					if (result[0]) {
						showPreferencePage(pageId);
					}
				}
			}
		});

		return new Boolean(result[0]);
	}

	/**
	 * returns the dialog's title, or <code>null</code> if there is no title
	 */
	protected abstract String getTitle();

	/**
	 * returns the dialog message
	 */
	protected abstract String getQuestion();

	/**
	 * returns the id of the preference page that will be openned if the user
	 * clicks the 'OK' button on the message dialog box.
	 */
	protected abstract String getPreferencePageId(Object source);
}
