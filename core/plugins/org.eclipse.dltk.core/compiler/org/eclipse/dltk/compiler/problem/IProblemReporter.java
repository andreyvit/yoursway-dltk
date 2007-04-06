package org.eclipse.dltk.compiler.problem;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

public interface IProblemReporter {
	IMarker reportProblem(IProblem problem) throws CoreException;
}
