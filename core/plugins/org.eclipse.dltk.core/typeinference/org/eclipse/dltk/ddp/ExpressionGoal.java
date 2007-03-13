package org.eclipse.dltk.ddp;

import org.eclipse.dltk.ast.statements.Statement;

public class ExpressionGoal extends AbstractGoal {

	private final Statement expression;

	public ExpressionGoal(IContext context, Statement expression) {
		super(context);
		this.expression = expression;
	}

	public Statement getExpression() {
		return expression;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof ExpressionGoal) {
			ExpressionGoal goal = (ExpressionGoal) obj;
			//return expression.equals(goal.expression);
			return expression == goal.expression;
		}
		return false;
	}

	public int hashCode() {
		return expression.hashCode();
		
	}

}
