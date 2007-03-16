package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ExpressionTypeEvaluator extends GoalEvaluator {

	public ExpressionTypeEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		return null;
	}

	public Object produceResult() {
		return null;
	}

}
