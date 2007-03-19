package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class StringLiteralEvaluator extends GoalEvaluator {
	
	public StringLiteralEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		return null;
	}

	public Object produceResult() {
		return new SimpleType(SimpleType.TYPE_STRING);
		//return new RubyClassType ("String");
	}

	public IGoal[] init() {
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}