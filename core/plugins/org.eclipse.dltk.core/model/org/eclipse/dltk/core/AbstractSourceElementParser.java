package org.eclipse.dltk.core;

import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public abstract class AbstractSourceElementParser implements ISourceElementParser {
	private ISourceElementRequestor sourceElementRequestor = null;
	private IProblemReporter problemReporter;

	public void setReporter(IProblemReporter reporter) {
		this.problemReporter = reporter;
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.sourceElementRequestor = requestor;
	}

	public ISourceElementRequestor getRequestor() {
		return sourceElementRequestor;
	}

	public IProblemReporter getProblemReporter() {
		return problemReporter;
	}

}
