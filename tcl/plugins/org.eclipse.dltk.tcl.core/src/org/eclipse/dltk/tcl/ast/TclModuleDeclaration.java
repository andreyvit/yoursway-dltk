package org.eclipse.dltk.tcl.ast;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.tcl.internal.parser.TclASTBuilder;

public class TclModuleDeclaration extends ModuleDeclaration {
	public TclModuleDeclaration(int sourceLength) {
		super(sourceLength, true);
	}

	protected void doRebuild() {
		TclASTBuilder.buildAST(this, getTypeList(), getFunctionList(), getVariablesList());
	}
}
