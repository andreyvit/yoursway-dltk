package org.eclipse.dltk.tcl.internal.problem;

import java.util.Locale;

import org.eclipse.dltk.compiler.IProblem;
import org.eclipse.dltk.compiler.IProblemFactory;


public class TclProblemFactory implements IProblemFactory {

	public IProblem createProblem(String originatingFileName, int problemId, String[] problemArguments, String[] messageArguments, int severity, int startPosition, int endPosition, int lineNumber) {
		return null;
	}

	public Locale getLocale() {
		return null;
	}

	public String getLocalizedMessage(int problemId, String[] messageArguments) {
		return null;
	}

}
