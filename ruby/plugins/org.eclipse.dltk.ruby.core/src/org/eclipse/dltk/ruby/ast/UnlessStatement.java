package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.statements.IfStatement;
import org.eclipse.dltk.ast.statements.Statement;

public class UnlessStatement extends IfStatement {

	public UnlessStatement(Statement condition, Statement thenStatement,
			Statement elseStatement) {
		super(condition, thenStatement, elseStatement);
	}

}
