package org.eclipse.dltk.tcl.internal.core.search.mixin;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParserConstants;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.mixin.IMixinRequestor;

public class TclMixinParser implements IMixinParser {
	private IMixinRequestor requestor;

	public void parserSourceModule(boolean signature, ISourceModule module) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(module, null, ISourceParserConstants.RUNTIME_MODEL);

		TclMixinBuildVisitor visitor = new TclMixinBuildVisitor(
				moduleDeclaration, module, signature, requestor);
		try {
			moduleDeclaration.traverse(visitor);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public void setRequirestor(IMixinRequestor requestor) {
		this.requestor = requestor;
	}
}
