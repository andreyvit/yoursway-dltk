package org.eclipse.dltk.ti;

public interface GoalState {
	
	public final static GoalState DONE = new GoalState () {};
	
	public final static GoalState WAITING = new GoalState () {};
	
	public final static GoalState PRUNED = new GoalState () {};
	
	public final static GoalState RECURSIVE = new GoalState () {};

}
