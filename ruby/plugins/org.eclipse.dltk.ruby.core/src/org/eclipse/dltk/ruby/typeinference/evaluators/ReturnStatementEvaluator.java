package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.statements.ReturnStatement;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.ast.RubyReturnStatement;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class ReturnStatementEvaluator extends GoalEvaluator {

	Object result = null;
	
	public ReturnStatementEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal[] init() {
		ExpressionTypeGoal tg = (ExpressionTypeGoal) goal;
		Statement value = null;
		if (tg.getExpression() instanceof ReturnStatement) {
			ReturnStatement statement = (ReturnStatement) tg.getExpression();
			value = statement.getExpression();
		} else if (tg.getExpression() instanceof RubyReturnStatement) {
			RubyReturnStatement statement = (RubyReturnStatement) tg.getExpression();
			CallArgumentsList list = statement.getValue();
			if (list.getExpressions().size() == 0) {
				result = new RubyClassType("NilClass");
				return IGoal.NO_GOALS;
			} else if (list.getExpressions().size() > 1) {
				result = new RubyClassType("Array");
				return IGoal.NO_GOALS;
			} else {
				value = (Statement) list.getExpressions().get(0);				
			}
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
