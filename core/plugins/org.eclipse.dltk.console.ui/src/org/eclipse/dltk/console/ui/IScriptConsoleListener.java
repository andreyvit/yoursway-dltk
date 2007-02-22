package org.eclipse.dltk.console.ui;

public interface IScriptConsoleListener {
	void userRequest(String text);
	void interpreterResponse(String text); 
}
