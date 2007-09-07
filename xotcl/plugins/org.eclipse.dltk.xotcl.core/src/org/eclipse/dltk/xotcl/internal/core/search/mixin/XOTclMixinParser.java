package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclSourceElementParser;

public class XOTclMixinParser implements IMixinParser {
	private IMixinRequestor requestor;

	public void parserSourceModule(char[] contents, boolean signature,
			ISourceModule module, ISourceModuleInfo info) {

		ModuleDeclaration moduleDeclaration = XOTclSourceElementParser
				.parseModule(info, contents, null, null);

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
