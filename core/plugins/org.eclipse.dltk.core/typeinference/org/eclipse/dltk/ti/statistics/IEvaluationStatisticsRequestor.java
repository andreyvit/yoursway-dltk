package org.eclipse.dltk.ti.statistics;

import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public interface IEvaluationStatisticsRequestor {
	
	/**
	 * Called only once, when root goal were posted
	 * @param rootGoal
	 */
	void evaluationStarted (IGoal rootGoal);

	/**
	 * Called, when goal state are changed (for ex. pruned)
	 * @param goal
	 * @param state
	 */
	void goalStateChanged (IGoal goal, GoalState state, GoalState oldState);
	
	/**
	 * Called if goal were not pruned or considered recursive, and so evalutor were
	 * assigned to it
	 * @param goal
	 * @param evaluator
	 */
	void goalEvaluatorAssigned (IGoal goal, GoalEvaluator evaluator);
	
	/**
	 * Called after init() call for some goal evaluator 
	 * @param evaluator
	 * @param subgoals subgoals, that this evalutor posted
	 * @param time time, that evaluator spent in init() method
	 */
	void evaluatorInitialized (GoalEvaluator evaluator, IGoal[] subgoals, long time);	
	
	/**
	 * Called, when evaluator accepted subgoal result, i.e. subGoalDone called
	 * @param evaluator
	 * @param subgoals
	 * @param time
	 */
	void evaluatorReceivedResult (GoalEvaluator evaluator, IGoal finishedGoal, IGoal[] newSubgoals, long time);
	
	/**
	 * Called, when evaluator finally produced a result
	 * @param evaluator
	 * @param result
	 * @param time
	 */
	void evaluatorProducedResult (GoalEvaluator evaluator, Object result, long time);
		
}
