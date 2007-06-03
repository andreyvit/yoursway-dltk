/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
