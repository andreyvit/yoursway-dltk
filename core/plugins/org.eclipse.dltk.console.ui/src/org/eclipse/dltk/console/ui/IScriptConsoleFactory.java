package org.eclipse.dltk.console.ui;

import org.eclipse.dltk.console.IScriptInterpreter;

public interface IScriptConsoleFactory {
	void openConsole(IScriptInterpreter interpreter, String id);
}
