package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.BinaryExpression;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class OrExpression extends BinaryExpression {

	public OrExpression(Expression left, Statement right) {
		super(left, E_BOR, right);
	}

}
