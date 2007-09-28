package org.eclipse.dltk.tcl.internal.parser.ext;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementParser;

public class ExtTclSourceElementParser implements ISourceElementParser {
	public static final Object AST = "ast";
	private ISourceElementRequestor requestor;
	private IProblemReporter reporter;

	public ModuleDeclaration parseSourceModule(char[] contents,
			ISourceModuleInfo astCashe, char[] filename) {

		ModuleDeclaration moduleDeclaration = parseModule(astCashe, contents,
				this.reporter, filename);

		ExtTclSourceElementRequestVisitor requestor = createVisitor();

		try {
			moduleDeclaration.traverse(requestor);

		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return moduleDeclaration;
	}

	private ExtTclSourceElementRequestVisitor createVisitor() {
		return new ExtTclSourceElementRequestVisitor(
				this.requestor, reporter);
	}

	public static ModuleDeclaration parseModule(ISourceModuleInfo astCache,
			char[] content, IProblemReporter problemReporter, char[] filename) {
		return TclSourceElementParser.parseModule(astCache, content,
				problemReporter, filename);
	}

	public void setReporter(IProblemReporter reporter) {
		this.reporter = reporter;
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.requestor = requestor;
	}
}
