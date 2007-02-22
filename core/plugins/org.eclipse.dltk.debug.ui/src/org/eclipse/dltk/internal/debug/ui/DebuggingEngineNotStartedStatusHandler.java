package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

public class DebuggingEngineNotStartedStatusHandler implements IStatusHandler {
	public Object handleStatus(IStatus status, Object source)
			throws CoreException {

		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = "Debugging engine not started";
				String message = "Debugging engine not started. Please check its settings.";

				MessageDialog.openError(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message);
			}
		});

		return null;
	}
}
