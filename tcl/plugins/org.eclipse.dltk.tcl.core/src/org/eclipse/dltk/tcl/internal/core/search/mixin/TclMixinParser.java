package org.eclipse.dltk.tcl.internal.core.search.mixin;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementParser;

public class TclMixinParser implements IMixinParser {
	private IMixinRequestor requestor;

	public void parserSourceModule(char[] contents, boolean signature,
			ISourceModule module, ISourceModuleInfo info) {

		ModuleDeclaration moduleDeclaration = TclSourceElementParser
				.parseModule(info, contents, null, null);

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
