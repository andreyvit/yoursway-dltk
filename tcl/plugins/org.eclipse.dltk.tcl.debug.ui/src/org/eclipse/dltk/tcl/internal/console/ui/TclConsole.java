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
