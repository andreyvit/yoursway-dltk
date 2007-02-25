package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ddp.AbstractGoal;
import org.eclipse.dltk.ddp.BasicContext;
import org.eclipse.dltk.ruby.ast.ColonExpression;

public class ColonExpressionGoal extends AbstractGoal {

	private final ColonExpression expr;

	public ColonExpressionGoal(BasicContext context, ColonExpression expr) {
		super(context);
		this.expr = expr;
	}

	public ColonExpression getColonExpression() {
		return expr;
	}
	
}
