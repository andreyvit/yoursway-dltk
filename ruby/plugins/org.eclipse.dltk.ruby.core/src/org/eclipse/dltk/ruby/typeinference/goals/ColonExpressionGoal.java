package org.eclipse.dltk.ruby.typeinference.goals;

import org.eclipse.dltk.ruby.ast.RubyColonExpression;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;

public class ColonExpressionGoal extends AbstractTypeGoal {

	private final RubyColonExpression expr;

	public ColonExpressionGoal(BasicContext context, RubyColonExpression expr) {
		super(context);
		this.expr = expr;
	}

	public RubyColonExpression getColonExpression() {
		return expr;
	}
	
}
