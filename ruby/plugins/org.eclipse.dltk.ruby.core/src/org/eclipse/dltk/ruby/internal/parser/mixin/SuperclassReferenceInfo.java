package org.eclipse.dltk.ruby.internal.parser.mixin;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;

public class SuperclassReferenceInfo {

	private ASTNode node;
	private ModuleDeclaration decl;
	private ISourceModule module;

	public SuperclassReferenceInfo(ASTNode node, ModuleDeclaration decl,
			ISourceModule module) {
		super();
		this.node = node;
		this.decl = decl;
		this.module = module;
	}

	public ASTNode getNode() {
		return node;
	}

	public void setNode(ASTNode node) {
		this.node = node;
	}

	public ModuleDeclaration getDecl() {
		return decl;
	}

	public void setDecl(ModuleDeclaration decl) {
		this.decl = decl;
	}

	public ISourceModule getModule() {
		return module;
	}

	public void setModule(ISourceModule module) {
		this.module = module;
	}

}
