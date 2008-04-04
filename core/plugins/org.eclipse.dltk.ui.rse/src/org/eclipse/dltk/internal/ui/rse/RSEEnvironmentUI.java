package org.eclipse.dltk.internal.ui.rse;

import org.eclipse.dltk.core.internal.rse.RSEEnvironment;
import org.eclipse.dltk.ui.environment.IEnvironmentUI;
import org.eclipse.rse.files.ui.dialogs.SystemRemoteFileDialog;
import org.eclipse.rse.files.ui.dialogs.SystemRemoteFolderDialog;
import org.eclipse.rse.subsystems.files.core.subsystems.RemoteFile;
import org.eclipse.swt.widgets.Shell;

public class RSEEnvironmentUI implements IEnvironmentUI {
	private RSEEnvironment environment;

	public RSEEnvironmentUI(RSEEnvironment environment) {
		this.environment = environment;
	}

	public String selectFolder(Shell shell) {
		SystemRemoteFolderDialog dialog = new SystemRemoteFolderDialog(shell);
		dialog.setDefaultSystemConnection(this.environment.getHost(), true);
		if (dialog.open() == SystemRemoteFolderDialog.OK) {
			Object selectedObject = dialog.getSelectedObject();
			if (selectedObject instanceof RemoteFile) {
				RemoteFile file = (RemoteFile) selectedObject;
				return file.getAbsolutePath();
			}
		}
		return null;
	}

	public String selectFile(Shell shell, int executable2) {
		SystemRemoteFileDialog dialog = new SystemRemoteFileDialog(shell);
		dialog.setDefaultSystemConnection(this.environment.getHost(), true);
		if( dialog.open() == SystemRemoteFileDialog.OK ) {
			Object selectedObject = dialog.getSelectedObject();
			if (selectedObject instanceof RemoteFile) {
				RemoteFile file = (RemoteFile) selectedObject;
				return file.getAbsolutePath();
			}
		}
		return null;
	}
}
