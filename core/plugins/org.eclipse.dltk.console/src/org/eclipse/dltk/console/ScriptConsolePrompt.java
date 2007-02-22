/**
 * 
 */
package org.eclipse.dltk.console;

public class ScriptConsolePrompt {
	private String newCommand;

	private String continueCommand;

	private boolean mode;

	public ScriptConsolePrompt(String newCommand, String appendCommand) {
		this.newCommand = newCommand;
		this.continueCommand = appendCommand;
		this.mode = true;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

	public String toString() {
		return mode ? newCommand : continueCommand;
	}
}