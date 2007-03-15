package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ArrayEvaluator extends GoalEvaluator {
	
	public ArrayEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		return null;
	}

	public IEvaluatedType produceResult() {
		return new SimpleType(SimpleType.TYPE_ARRAY);
	}

}
