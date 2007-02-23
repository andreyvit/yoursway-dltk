package org.eclipse.dltk.ddp;

import java.util.Collection;

import org.eclipse.dltk.ast.statements.Statement;

public class ExpressionGoal implements IGoal {
	
	private final Statement expression;
	private final IContext context;

	public ExpressionGoal(IContext context, Statement expression) {
		this.context = context;
		this.expression = expression;
	}

	public Statement getExpression() {
		return expression;
	}

	public IContext getContext() {
		return context;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof ExpressionGoal) {
			ExpressionGoal goal = (ExpressionGoal) obj;
			return expression.equals(goal.expression);
		}
		return false;
	}

	public int hashCode() {
		return expression.hashCode();
	}
	
	

}
