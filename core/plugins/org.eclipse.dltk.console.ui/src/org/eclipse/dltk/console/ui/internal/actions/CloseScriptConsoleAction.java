package org.eclipse.dltk.console.ui.internal.actions;

import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleManager;
import org.eclipse.dltk.console.ui.ScriptConsoleUIConstants;
import org.eclipse.dltk.console.ui.ScriptConsoleUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;


public class CloseScriptConsoleAction extends Action {

	private ScriptConsole console;

	public CloseScriptConsoleAction(ScriptConsole console, String text,
			String tooltip) {
		this.console = console;

		setText(text);
		setToolTipText(tooltip);		
	}

	public void run() {
		ScriptConsoleManager.getInstance().close(console);
	}

	public void update() {
		setEnabled(true);
	}

	public ImageDescriptor getImageDescriptor() {
		return ScriptConsoleUIPlugin.getDefault().getImageDescriptor(
				ScriptConsoleUIConstants.TERMINATE_ICON);
	}
}
