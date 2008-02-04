package org.eclipse.dltk.core;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;

public abstract class AbstractSourceElementParser implements
		ISourceElementParser {

	private ISourceElementRequestor sourceElementRequestor = null;
	private IProblemReporter problemReporter;

	public void parseSourceModule(char[] contents, ISourceModuleInfo astCache,
			char[] filename) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(filename, contents, getNatureId(),
						problemReporter, astCache);

		SourceElementRequestVisitor requestor = createVisitor();

		try {
			moduleDeclaration.traverse(requestor);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public void setReporter(IProblemReporter reporter) {
		this.problemReporter = reporter;
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.sourceElementRequestor = requestor;
	}

	protected ISourceElementRequestor getRequestor() {
		return sourceElementRequestor;
	}

	protected IProblemReporter getProblemReporter() {
		return problemReporter;
	}

	/**
	 * Returns the language's nature id
	 */
	protected abstract String getNatureId();

	/**
	 * Creates a new source element visitor
	 * 
	 * <p>
	 * Sub-classes should use <code>getRequstor</code> and
	 * <code>getProblemReporter</code> if they need access to a source element
	 * requestor and/or a problem reporter.
	 * </p>
	 */
	protected SourceElementRequestVisitor createVisitor() {
		return new SourceElementRequestVisitor(getRequestor());
	}
}
