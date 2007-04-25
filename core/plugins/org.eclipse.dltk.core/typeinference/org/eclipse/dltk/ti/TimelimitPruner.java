/**
 * 
 */
package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.IGoal;

public class TimelimitPruner implements IPruner {
	
	private long timeStart;
	private final long timeLimit;
	
	

	public TimelimitPruner(long timeLimit) {
		super();
		this.timeLimit = timeLimit;
	}

	public void init() {
		this.timeStart = System.currentTimeMillis();			
	}

	public boolean prune(IGoal goal, EvaluatorStatistics stat) {	
		if (timeLimit > 0 && System.currentTimeMillis() - timeStart > timeLimit)
			return true;
		return false;
	}
	
}