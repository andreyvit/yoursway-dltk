package org.eclipse.dltk.scriptchecker.internal.core;

public class ScriptCheckerProblem {
	private String source;

	private int lineNumber;
	private String description;
	public ScriptCheckerProblem(String source, int lineNumber, String message) {
		this.source = source;
		this.lineNumber = lineNumber;
		this.description = message;
	}

	public String getFile() {
		return source;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getDescription() {
		return description;
	}
}
