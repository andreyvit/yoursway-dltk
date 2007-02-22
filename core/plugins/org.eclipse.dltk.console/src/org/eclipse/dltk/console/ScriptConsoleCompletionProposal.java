package org.eclipse.dltk.console;

public class ScriptConsoleCompletionProposal {
	private String insert;

	private String display;

	private String type;

	public ScriptConsoleCompletionProposal(String insert, String display, String type) {
		this.insert = insert;
		this.display = display;
		this.type = type;
	}

	public String getDisplay() {
		return display;
	}

	public String getInsert() {
		return insert;
	}

	public String getType() {
		return type;
	}
}
