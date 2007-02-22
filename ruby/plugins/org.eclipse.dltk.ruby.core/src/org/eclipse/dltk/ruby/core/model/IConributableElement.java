package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.ast.ASTNode;

public interface IConributableElement {
	
	ISourceModuleContribution getContribution();
	
	ASTNode getNode();
	
}
