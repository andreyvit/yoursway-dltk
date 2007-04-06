package org.eclipse.dltk.console.ui.internal.actions;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleUIConstants;
import org.eclipse.dltk.console.ui.ScriptConsoleUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


public class SaveConsoleSessionAction extends Action {
	private ScriptConsole console;

	public SaveConsoleSessionAction(ScriptConsole console, String text,
			String tooltip) {
		this.console = console;
		setText(text);
		setToolTipText(tooltip);
	}

	public void run() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		FileDialog dialog = new FileDialog(window.getShell(), SWT.SAVE);

		String file = dialog.open();

		try {
			if (file != null) {
				FileWriter writer = null;
				try {
					writer = new FileWriter(file);
					writer.write(console.getSession().toString());
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		setEnabled(true);
	}

	public ImageDescriptor getImageDescriptor() {
		return ScriptConsoleUIPlugin.getDefault().getImageDescriptor(
				ScriptConsoleUIConstants.SAVE_SESSION_ICON);
	}
}
