package org.eclipse.dltk.tcl.internal.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclSourceElementParser extends AbstractSourceElementParser {
	public void parseSourceModule(char[] contents, ISourceModuleInfo astCache,
			char[] filename) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(filename, contents, TclNature.NATURE_ID,
						this.getProblemReporter(), astCache);

		TclSourceElementRequestVisitor requestor = createVisitor();

		try {
			moduleDeclaration.traverse(requestor);

		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	protected TclSourceElementRequestVisitor createVisitor() {
		return new TclSourceElementRequestVisitor(this.getRequestor(), this
				.getProblemReporter());
	}
}
