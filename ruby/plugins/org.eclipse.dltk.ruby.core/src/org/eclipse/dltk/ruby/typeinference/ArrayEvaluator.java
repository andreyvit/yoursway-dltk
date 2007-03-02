package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.evaluation.types.SimpleType;

public class ArrayEvaluator extends GoalEvaluator {
	
	public ArrayEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
		return null;
	}

	public IEvaluatedType produceType() {
		return new SimpleType(SimpleType.TYPE_ARRAY);
	}

}
