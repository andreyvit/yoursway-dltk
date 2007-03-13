package org.eclipse.dltk.ast.expressions;

public class UnaryNotExpression extends UnaryExpression {

	public UnaryNotExpression(int start, int end, Expression expression) {
		super(start, end, E_BNOT, expression);
	}

}
