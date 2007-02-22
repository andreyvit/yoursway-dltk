package org.eclipse.dltk.console;

import java.io.IOException;

public interface IScriptConsoleIO {
	String getId();	
	InterpreterResponse execInterpreter(String command) throws IOException;
	ShellResponse execShell(String command, String[] args) throws IOException;
	void close() throws IOException;
}
