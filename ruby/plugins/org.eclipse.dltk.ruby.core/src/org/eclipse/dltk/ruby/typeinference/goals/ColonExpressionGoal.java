package org.eclipse.dltk.ruby.typeinference.goals;

import org.eclipse.dltk.ruby.ast.ColonExpression;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;

public class ColonExpressionGoal extends AbstractTypeGoal {

	private final ColonExpression expr;

	public ColonExpressionGoal(BasicContext context, ColonExpression expr) {
		super(context);
		this.expr = expr;
	}

	public ColonExpression getColonExpression() {
		return expr;
	}
	
}
