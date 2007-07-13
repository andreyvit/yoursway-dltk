package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

public abstract class AbstractOpenPreferencePageStatusHandler implements
		IStatusHandler {

	private void showPreferencePage(String natureId) {
		final IDLTKUILanguageToolkit languageToolkit = DLTKUILanguageManager
				.getLanguageToolkit(natureId);
		if (languageToolkit != null) {
			final String preferencePageId = getPreferencePageId(languageToolkit);
			if (preferencePageId != null) {
				PreferenceDialog dialog = PreferencesUtil
						.createPreferenceDialogOn(null, preferencePageId, null,
								null);
				dialog.open();
			}
		}
	}

	public Object handleStatus(IStatus status, Object source) {
		final boolean[] result = new boolean[1];

		final String natureId = ((AbstractScriptLaunchConfigurationDelegate) source)
				.getLanguageId();

		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				final String title = getTitle();
				final String message = getMessage();

				result[0] = (MessageDialog.openQuestion(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message));
				if (result[0]) {
					showPreferencePage(natureId);
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
	protected abstract String getMessage();

	/**
	 * returns the id of the preference page that will be openned if the user
	 * clicks the 'OK' button on the message dialog box.
	 */
	protected abstract String getPreferencePageId(IDLTKUILanguageToolkit toolkit);
}
