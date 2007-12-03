package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.mixin.IMixinRequestor;

public class XOTclMixinParser implements IMixinParser {
	private IMixinRequestor requestor;

	public void parserSourceModule(boolean signature, ISourceModule module) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(module, null);

		XOTclMixinBuildVisitor visitor = new XOTclMixinBuildVisitor(
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
