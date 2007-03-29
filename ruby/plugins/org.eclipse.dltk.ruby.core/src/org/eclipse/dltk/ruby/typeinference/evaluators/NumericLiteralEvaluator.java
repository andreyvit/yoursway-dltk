package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class NumericLiteralEvaluator extends GoalEvaluator {

	public NumericLiteralEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		return null;
	}

	public Object produceResult() {
//		return new SimpleType(SimpleType.TYPE_NUMBER);
		return new RubyClassType("Fixnum%");
	}

	public IGoal[] init() {
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
