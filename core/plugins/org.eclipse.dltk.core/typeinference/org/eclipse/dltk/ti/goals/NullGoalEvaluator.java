package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.GoalState;

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
