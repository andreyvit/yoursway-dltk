package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinRequestor;

public class XOTclMixinBuildVisitor extends ASTVisitor {
	private boolean signature = false;
	private ISourceModule sourceModule;
	private ModuleDeclaration moduleDeclaration;
	private IMixinRequestor requestor;
	public XOTclMixinBuildVisitor(ModuleDeclaration moduleDeclaration,
			ISourceModule module, boolean signature, IMixinRequestor requestor) {
		this.signature = signature;
		this.sourceModule = module;
		this.moduleDeclaration = moduleDeclaration;
		this.requestor = requestor;
	}
	public boolean visit(MethodDeclaration s) throws Exception {
		return super.visit(s);
	}
	public boolean visit(Statement s) throws Exception {
		return super.visit(s);
	}
	public boolean visit(TypeDeclaration s) throws Exception {
		return super.visit(s);
	}
	
}
