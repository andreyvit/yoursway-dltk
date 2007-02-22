package org.eclipse.dltk.ddp;

public interface IGoalsEvaluator {
	
	/**
	 * <code>evaluateGoal()</code> method called when this goal is 
	 * considered first time. As result goal can post any subgoals.
	 * @return whether this goal's answer had changed or not
	 */
	boolean updateGoal (IGoal goal);
		
//	/**
//	 * Called by manager when answer of one of subgoals were changed.
//	 * @param child child which had been changed
//	 * @return whether this goal's answer had changed or not
//	 */
//	boolean invalidateGoal (IGoal goal, IGoal child);

}
