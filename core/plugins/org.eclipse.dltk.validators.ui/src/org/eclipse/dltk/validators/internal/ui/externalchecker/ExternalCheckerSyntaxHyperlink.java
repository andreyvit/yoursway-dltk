package org.eclipse.dltk.validators.internal.ui.externalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalCheckerProblem;
import org.eclipse.ui.console.TextConsole;

public class ExternalCheckerSyntaxHyperlink extends
		ExternalCheckerGenericHyperlink {
	
	private ExternalCheckerProblem problem;

	public ExternalCheckerSyntaxHyperlink(TextConsole console, ExternalCheckerProblem problem) {
		super(console);
		this.problem = problem;
	}

	protected String getFileName(){
		return problem.getFilename();
	}

	protected int getLineNumber() {
		return problem.getLineNumber();
	}
}


