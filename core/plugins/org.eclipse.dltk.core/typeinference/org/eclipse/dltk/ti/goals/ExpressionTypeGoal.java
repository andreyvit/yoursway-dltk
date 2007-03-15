package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ti.IContext;

public class ExpressionTypeGoal extends AbstractTypeGoal {

	private final Statement expression;

	public ExpressionTypeGoal(IContext context, Statement expression) {
		super(context);
		this.expression = expression;
	}

	public Statement getExpression() {
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
		return expression.hashCode();		
	}

}
