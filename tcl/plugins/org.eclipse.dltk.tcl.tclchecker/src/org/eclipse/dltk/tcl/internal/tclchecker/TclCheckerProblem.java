package org.eclipse.dltk.tcl.internal.tclchecker;

public class TclCheckerProblem {
	private String source;

	private int lineNumber;

	private TclCheckerProblemDescription description;

	public TclCheckerProblem(String source, int lineNumber, String messageID, String message) {
		this.source = source;
		this.lineNumber = lineNumber;
		this.description = TclCheckerProblemDescription.getProblemDescription(messageID, message);
	}

	public String getFile() {
		return source;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public TclCheckerProblemDescription getDescription() {
		return description;
	}
}
