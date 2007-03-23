package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.ReturnStatement;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.ast.RubyReturnStatement;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class ReturnStatementEvaluator extends GoalEvaluator {

	Object result = null;
	
	public ReturnStatementEvaluator(IGoal goal) {
		super(goal);
		// TODO Auto-generated constructor stub
	}

	public IGoal[] init() {
		ExpressionTypeGoal tg = (ExpressionTypeGoal) goal;
		Statement value = null;
		if (tg.getExpression() instanceof ReturnStatement) {
			ReturnStatement statement = (ReturnStatement) tg.getExpression();
			value = statement.getExpression();
		} else if (tg.getExpression() instanceof RubyReturnStatement) {
			RubyReturnStatement statement = (RubyReturnStatement) tg.getExpression();
			value = (Statement) statement.getValue();
		}
		if (value == null)
			return IGoal.NO_GOALS;
		return new IGoal[] { new ExpressionTypeGoal(this.getGoal().getContext(), value) };
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.result = result;
		return IGoal.NO_GOALS;
	}

}
