package org.eclipse.dltk.console;

import java.io.IOException;

public interface IScriptConsoleInterpreter {
	int WAIT_NEW_COMMAND = 0;
	int WAIT_CONTINUE_COMMAND = 1;
	int WAIT_USER_INPUT = 2;
	
	void exec(String command) throws IOException;
	String getOutput();
	int getState();	
}
