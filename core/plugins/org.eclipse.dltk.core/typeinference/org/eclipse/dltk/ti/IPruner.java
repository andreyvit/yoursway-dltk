package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;


/**
 * Pruner is thing, that can prune some goals from working queue.
 * It could be time limits, goals count or more complex criterias. 
 */
public interface IPruner {
	
	/**
	 * Are called when evaluating were started. 
	 */
	public void init ();
	
	/**
	 * Called every time before getting new goal from evaluating 
	 * queue. 
	 * @param goal goal to prune
	 * @param stat information about created evaluator
	 */
	public boolean prune (IGoal goal, EvaluatorStatistics stat);

}
