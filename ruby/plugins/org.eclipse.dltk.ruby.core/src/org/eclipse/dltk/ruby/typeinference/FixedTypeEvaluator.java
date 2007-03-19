package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class FixedTypeEvaluator extends GoalEvaluator {

	private final IEvaluatedType result;

	public FixedTypeEvaluator(IGoal goal, IEvaluatedType result) {
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
