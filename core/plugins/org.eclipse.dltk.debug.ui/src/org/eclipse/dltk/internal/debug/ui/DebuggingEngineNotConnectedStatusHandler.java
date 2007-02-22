package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

public class DebuggingEngineNotConnectedStatusHandler implements IStatusHandler {
	public Object handleStatus(IStatus status, Object source)
			throws CoreException {

		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = "Debugging engine not avaialble";
				String message = "Debugging engine cannot connect to dbgp service during period of time";

				MessageDialog.openError(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message);
			}
		});

		return null;
	}
}
