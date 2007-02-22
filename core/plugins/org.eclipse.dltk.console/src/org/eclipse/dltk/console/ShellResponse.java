package org.eclipse.dltk.console;

import java.util.List;

public class ShellResponse {
	private List completions;

	private String description;

	public ShellResponse() {

	}

	public ShellResponse(String description) {
		this.description = description;
	}

	public ShellResponse(List completions) {
		this.completions = completions;
	}

	public List getCompletions() {
		return completions;
	}

	public String getDescription() {
		return this.description;
	}
}
