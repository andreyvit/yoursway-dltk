package org.eclipse.dltk.validators.internal.core.externalchecker;

public class ExternalCheckerProblem {
	private String source;

	private int lineNumber;
	private String description;
	public ExternalCheckerProblem(String source, int lineNumber, String message) {
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
