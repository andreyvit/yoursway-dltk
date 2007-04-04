package org.eclipse.dltk.javascript.internal.console.ui;

import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.javascript.console.JavaScriptInterpreter;

public class JavaScriptConsole extends ScriptConsole {
	public static final String CONSOLE_TYPE = "tcl_console";

	public static final String CONSOLE_NAME = "Tcl Console";
	
	public JavaScriptConsole(JavaScriptInterpreter interpreter, String id) {
		super(CONSOLE_NAME + " [" + id + "]", CONSOLE_TYPE);

		setInterpreter(interpreter);
		setTextHover(new JavaScriptConsoleTextHover(interpreter));
		setContentAssistProcessor(new JavaScriptConsoleCompletionProcessor(interpreter));
	}	
}
