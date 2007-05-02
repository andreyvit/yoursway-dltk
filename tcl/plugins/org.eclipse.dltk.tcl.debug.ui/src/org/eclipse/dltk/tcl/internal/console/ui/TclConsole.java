/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.console.ui;

import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.tcl.console.TclInterpreter;

public class TclConsole extends ScriptConsole {
	public static final String CONSOLE_TYPE = "tcl_console";

	public static final String CONSOLE_NAME = "Tcl Console";
	
	public TclConsole(TclInterpreter interpreter, String id) {
		super(CONSOLE_NAME + " [" + id + "]", CONSOLE_TYPE);

		setInterpreter(interpreter);
		setTextHover(new TclConsoleTextHover(interpreter));
		setContentAssistProcessor(new TclConsoleCompletionProcessor(interpreter));
	}	
}
