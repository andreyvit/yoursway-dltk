package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

public class DbgpServiceNotAvailableStatusHandler implements IStatusHandler {
	public Object handleStatus(IStatus status, Object source)
			throws CoreException {
		
		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = "Dbgp service not avaialble";
				String message = "Dbgp service not avaialble. Check port settings";
				
				MessageDialog.openError(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message);
			}
		});
		
		return null;
	}
}
