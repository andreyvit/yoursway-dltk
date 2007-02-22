package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class NoDebuggingEgineTclStatusHandler implements IStatusHandler {

	public Object handleStatus(IStatus status, Object source)
			throws CoreException {

		final boolean result[] = new boolean[] { false };
		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = "Debugging engine not specified";
				String message = "Specify now?";
				result[0] = MessageDialog.openQuestion(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message);
				if (result[0]) {
					String id = "org.eclipse.dltk.tcl.internal.debug.ui.preferences.TclDebugPreferences";

					PreferenceDialog dialog = PreferencesUtil
							.createPreferenceDialogOn(null, id,
									new String[] { id }, null);
					dialog.open();
				}
			}
		});
		return Boolean.valueOf(result[0]);
	}
}
