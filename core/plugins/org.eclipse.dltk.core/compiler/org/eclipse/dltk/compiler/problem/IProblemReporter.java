package org.eclipse.dltk.compiler.problem;

import org.eclipse.core.runtime.CoreException;

public interface IProblemReporter {
	void reportProblem(IProblem problem) throws CoreException;
}
