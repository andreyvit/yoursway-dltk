package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class MethodCallTypeEvaluator extends GoalEvaluator {

	public MethodCallTypeEvaluator(ExpressionGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
		return null;
	}

	public IEvaluatedType produceType() {
		return null;
	}

}
