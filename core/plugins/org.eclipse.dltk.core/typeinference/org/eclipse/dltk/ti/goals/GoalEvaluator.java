package org.eclipse.dltk.ti.goals;


public abstract class GoalEvaluator {
	
	protected final IGoal goal;
	
	public GoalEvaluator(IGoal goal) {
		this.goal = goal;
	}
	
	public IGoal getGoal() {
		return goal;
	}
	
	public abstract IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult);
	
	public abstract Object produceResult();
	
}
