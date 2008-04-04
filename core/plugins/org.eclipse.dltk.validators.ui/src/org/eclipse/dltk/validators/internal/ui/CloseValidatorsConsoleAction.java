/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;


public class CloseValidatorsConsoleAction extends Action {

	private IOConsole console;

	public CloseValidatorsConsoleAction(IOConsole console, String text,
			String tooltip) {
		this.console = console;

		setText(text);
		setToolTipText(tooltip);		
	}

	public void run() {
		IConsoleManager consoleManager = ConsolePlugin.getDefault()
		.getConsoleManager();
		consoleManager.removeConsoles(new IConsole[]{this.console});
	}

	public void update() {
		setEnabled(true);
	}

	public ImageDescriptor getImageDescriptor() {
		return ValidatorsUI.getDefault().getImageDescriptor(
				"icons/terminate.gif"); //$NON-NLS-1$
	}	
}
