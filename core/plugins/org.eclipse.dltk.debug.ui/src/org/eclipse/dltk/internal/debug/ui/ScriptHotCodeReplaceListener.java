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
					"The target doesn't support hot code replace", null);
			preference = IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED;
			alertMessage = "Do not show error &when hot code replace is not supported";
		} else {
			status = new Status(IStatus.WARNING, DLTKDebugUIPlugin
					.getUniqueIdentifier(), IStatus.WARNING, exception
					.getMessage(), exception.getCause());
			preference = IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED;
			alertMessage = "Do not show error &when hot code replace fails";
		}
		final String title = "Hot Code Replace Failed";
		final String message = MessageFormat
				.format("Some code changes cannot be hot swapped into a running interpreter, " +
						"such as changing method names or introducing errors into running code.\n\n" +
						"The current target interpreter from launch [{0}] was unable to replace " +
						"the running code with the code in the workspace.\n\nIt is safe to " +
						"continue running the application, but you may notice discrepancies " +
						"when debugging this application.",
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
