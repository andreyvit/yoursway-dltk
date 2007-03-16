package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class StringLiteralEvaluator extends GoalEvaluator {
	
	public StringLiteralEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		return null;
	}

	public Object produceResult() {
		return new SimpleType(SimpleType.TYPE_STRING);
	}

}
