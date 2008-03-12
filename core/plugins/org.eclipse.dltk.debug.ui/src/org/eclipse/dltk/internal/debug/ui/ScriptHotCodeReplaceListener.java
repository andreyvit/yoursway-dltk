package org.eclipse.dltk.internal.debug.ui;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.IHotCodeReplaceListener;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.IDLTKDebugUIPreferenceConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ScriptHotCodeReplaceListener implements IHotCodeReplaceListener {

	/**
	 * @see IScriptHotCodeReplaceListener#hotCodeReplaceSucceeded(IScriptDebugTarget)
	 */
	public void hotCodeReplaceSucceeded(IScriptDebugTarget target) {
	}

	/**
	 * @see IScriptHotCodeReplaceListener#hotCodeReplaceFailed(IScriptDebugTarget,
	 *      DebugException)
	 */
	public void hotCodeReplaceFailed(final IScriptDebugTarget target,
			final DebugException exception) {
		if ((exception != null && !DLTKDebugUIPlugin.getDefault()
				.getPreferenceStore().getBoolean(
						IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED))
				|| ((exception == null) && !DLTKDebugUIPlugin
						.getDefault()
						.getPreferenceStore()
						.getBoolean(
								IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED))) {
			return;
		}

		final Display display = DLTKDebugUIPlugin.getStandardDisplay();
		if (display.isDisposed()) {
			return;
		}

		final IStatus status;
		final String preference;
		final String alertMessage;
		final String launchName = target.getLaunch().getLaunchConfiguration()
				.getName();
		if (exception == null) {
			status = new Status(IStatus.WARNING, DLTKDebugUIPlugin
					.getUniqueIdentifier(), IStatus.WARNING,
					Messages.ScriptHotCodeReplaceListener_theTargetDoesntSupportHotCodeReplace, null);
			preference = IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED;
			alertMessage = Messages.ScriptHotCodeReplaceListener_doNotShowErrorWhenHotCodeReplaceIsNotSupported;
		} else {
			status = new Status(IStatus.WARNING, DLTKDebugUIPlugin
					.getUniqueIdentifier(), IStatus.WARNING, exception
					.getMessage(), exception.getCause());
			preference = IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED;
			alertMessage = Messages.ScriptHotCodeReplaceListener_doNotShowErrorWhenHotCodeReplaceFails;
		}
		final String title = Messages.ScriptHotCodeReplaceListener_hotCodeReplaceFailed;
		final String message = MessageFormat.format(Messages.ScriptHotCodeReplaceListener_someCodeChangesCannotBeHotSwappedIntoARunningInterpreter,
						new Object[] { launchName });
		display.asyncExec(new Runnable() {
			public void run() {
				if (display.isDisposed()) {
					return;
				}
				Shell shell = DLTKDebugUIPlugin.getActiveWorkbenchShell();
				HotCodeReplaceErrorDialog dialog = new HotCodeReplaceErrorDialog(
						shell, title, message, status, preference,
						alertMessage, DLTKDebugUIPlugin.getDefault()
								.getPreferenceStore(), target);
				dialog.setBlockOnOpen(false);
				dialog.open();
			}
		});
	}
}
