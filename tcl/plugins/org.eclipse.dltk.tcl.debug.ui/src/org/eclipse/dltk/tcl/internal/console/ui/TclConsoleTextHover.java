/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.console.ui;

import java.io.IOException;

import org.eclipse.dltk.console.IScriptConsoleShell;
import org.eclipse.dltk.console.ui.IScriptConsoleViewer;
import org.eclipse.dltk.console.ui.ScriptConsoleTextHover;
import org.eclipse.jface.text.IRegion;


public class TclConsoleTextHover extends ScriptConsoleTextHover {

	private IScriptConsoleShell interpreterShell;

	public TclConsoleTextHover(IScriptConsoleShell interpreterShell) {
		this.interpreterShell = interpreterShell;
	}

	protected String getHoverInfoImpl(IScriptConsoleViewer viewer,
			IRegion hoverRegion) {
		try {
			int cursorPosition = hoverRegion.getOffset()
					- viewer.getCommandLineOffset();

			String commandLine = viewer.getCommandLine();

			return interpreterShell.getDescription(commandLine, cursorPosition);
		} catch (IOException e) {
			//TODO: log exception
			e.printStackTrace();
			return null;
		}
	}
}
