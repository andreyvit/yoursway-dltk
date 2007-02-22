package org.eclipse.dltk.compiler;

import org.eclipse.core.runtime.CoreException;

public interface IProblemReporter {
	void reportProblem(IProblem problem) throws CoreException;
}
