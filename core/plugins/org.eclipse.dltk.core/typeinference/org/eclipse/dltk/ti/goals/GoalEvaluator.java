package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.GoalState;

/**
 * Goal evaluator. Created for every evaluating goal.
 */
public abstract class GoalEvaluator {

	protected final IGoal goal;

	public GoalEvaluator(IGoal goal) {
		this.goal = goal;
	}

	public IGoal getGoal() {
		return goal;
	}

	/**
	 * Called first time to fetch primary subgoals.
	 * @return array of required subgoals or <code>IGoal.NO_GOALS</code>
	 */
	public abstract IGoal[] init();

	
	/**
	 * Called when some subgoal are done.
	 * @param subgoal completed subgoal
	 * @param result result of that subgoal
	 * @param state final state of subgoal (DONE, PRUNED or RECURSION)
	 * @return array of new required subgoals or <code>IGoal.NO_GOALS</code>
	 */	
	public abstract IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state);

	/**
	 * Called when all posted subgoals are done
	 * @return result of evaluation this goal
	 */
	public abstract Object produceResult();

}
