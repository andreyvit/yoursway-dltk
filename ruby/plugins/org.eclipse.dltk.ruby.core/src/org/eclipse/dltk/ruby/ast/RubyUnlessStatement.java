package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.statements.IfStatement;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyUnlessStatement extends IfStatement {

	public RubyUnlessStatement(Statement condition, Statement thenStatement,
			Statement elseStatement) {
		super(condition, thenStatement, elseStatement);
	}

}
