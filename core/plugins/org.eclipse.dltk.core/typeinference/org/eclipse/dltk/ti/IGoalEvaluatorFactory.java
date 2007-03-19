package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public interface IGoalEvaluatorFactory {
	
	public GoalEvaluator createEvaluator(IGoal goal);
	
		
}
