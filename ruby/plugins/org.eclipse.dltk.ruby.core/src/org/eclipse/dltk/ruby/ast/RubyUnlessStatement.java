package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;

public class RubyUnlessStatement extends RubyIfStatement {

	public RubyUnlessStatement(ASTNode condition, ASTNode thenStatement, ASTNode elseStatement) {
		super(condition, thenStatement, elseStatement);
	}

}
