package org.eclipse.dltk.tcl.internal.problem;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.compiler.problem.DLTKProblemReporter;
import org.eclipse.dltk.compiler.problem.IProblemFactory;


public class TclProblemReporter extends DLTKProblemReporter {
	public TclProblemReporter(IResource resource, IProblemFactory factory) {
		super(resource, factory);
	}

	public void reportSyntaxError(int from, int to)  {
	}
}