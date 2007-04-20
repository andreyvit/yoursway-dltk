package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ruby.ast.RubyCallArgument;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class RubyArgumentTypeEvaluator extends GoalEvaluator {

	private Object result;
	
	public RubyArgumentTypeEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal[] init() {
		ExpressionTypeGoal goal = (ExpressionTypeGoal) this.getGoal();
		RubyCallArgument arg = (RubyCallArgument) goal.getExpression();
		if (arg.getValue() != null)		
			return new IGoal[] { new ExpressionTypeGoal(goal.getContext(), arg.getValue()) };
		return IGoal.NO_GOALS;
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.result = result;
		return IGoal.NO_GOALS;
	}

}
