package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class NullGoalEvaluator extends GoalEvaluator {

	public NullGoalEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal[] init() {
		return IGoal.NO_GOALS;
	}

	public Object produceResult() {
		return null;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
