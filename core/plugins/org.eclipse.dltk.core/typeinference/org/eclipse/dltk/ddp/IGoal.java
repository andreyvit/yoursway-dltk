package org.eclipse.dltk.ddp;

import java.util.Collection;


public interface IGoal {
	
	public final IGoal[] NO_GOALS = new IGoal[0];
	
	/**
	 * Should return all the goals, who depends of the result 
	 * of this goal. 
	 * @return
	 */
	Collection getParents ();
	
	void setParents(Collection parents);
	
	/**
	 * Returns context, in which this goal should be considered. Context
	 * could contain, for ex. value of self method, precalculated scope
	 * or something like.
	 * @return
	 */
	IContext getContext ();
	
	/**
	 * Called when subgoal were updated
	 * @param subgoal
	 */
	void subgoalUpdated (IGoal subgoal);
	
}
