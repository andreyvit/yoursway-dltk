package org.eclipse.dltk.ddp;

public interface IGoalEvaluatorFactory {
	
	GoalEvaluator createEvaluator(IGoal goal);
	
	IGoal translateGoal(IGoal goal);
	
}
