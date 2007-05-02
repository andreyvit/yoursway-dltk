package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ti.IContext;

public class ExpressionTypeGoal extends AbstractTypeGoal {

	private final ASTNode expression;

	public ExpressionTypeGoal(IContext context, ASTNode expression) {
		super(context);
		this.expression = expression;
	}

	public ASTNode getExpression() {
		return expression;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof ExpressionTypeGoal) {
			ExpressionTypeGoal goal = (ExpressionTypeGoal) obj;
			return expression == goal.expression;
		}
		return false;
	}

	public int hashCode() {
		if (expression != null)
			return expression.hashCode();
		return super.hashCode();
	}

}
