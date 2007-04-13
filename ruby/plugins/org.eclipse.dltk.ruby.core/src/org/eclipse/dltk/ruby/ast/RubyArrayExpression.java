package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.statements.CompoundStatement;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyArrayExpression extends CompoundStatement {

	private Statement asterixElement;

	public Statement getAsterixElement() {
		return asterixElement;
	}

	public void setAsterixElement(Statement asterixElement) {
		this.asterixElement = asterixElement;
	}

}
