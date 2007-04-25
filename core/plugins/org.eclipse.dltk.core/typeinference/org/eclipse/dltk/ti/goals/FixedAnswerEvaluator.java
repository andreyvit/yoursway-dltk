package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.GoalState;

public class FixedAnswerEvaluator extends GoalEvaluator {

	private final Object result;

	public FixedAnswerEvaluator(IGoal goal, Object result) {
		super(goal);
		this.result = result;
	}

	public IGoal[] init() {
		return IGoal.NO_GOALS;
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
