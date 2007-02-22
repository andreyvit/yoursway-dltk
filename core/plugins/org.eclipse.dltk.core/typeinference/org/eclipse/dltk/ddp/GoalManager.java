package org.eclipse.dltk.ddp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.core.runtime.Assert;

public final class GoalManager implements IGoalManager {
	
	TypeGoal rootgoal;
	LinkedList worklist = new LinkedList ();
	
	Set doneset = new HashSet(); // item could be either in done or working list
	
	private IGoalsEvaluator evaluator;

	public void inferType(TypeGoal rootGoal, IGoalsEvaluator evaluator) {		
		Assert.isLegal(rootGoal != null);
		this.evaluator = evaluator;
		rootgoal = rootGoal; 
		worklist.clear();
		worklist.add(rootgoal);
		while (!worklist.isEmpty()) {
			//TODO: run prunner here
			update();
		}		
	}
	
	private void actualizeParents (IGoal goal) {
		//Queue parentlist = new SynchronousQueue ();
		Collection parents = goal.getParents();
		for (Iterator iterator = parents.iterator(); iterator.hasNext();) {
			IGoal parent = (IGoal) iterator.next();
			parent.subgoalUpdated(goal);
			if (doneset.contains(parent)) {
				doneset.remove(parent);				
				worklist.add(parent);
			} else {
				for (Iterator iterator2 = worklist.iterator(); iterator2
						.hasNext();) {
					IGoal g = (IGoal) iterator2.next();
					if (g.equals(parent)) {
						g.subgoalUpdated(goal);
					}
					break;
				}
			}
		}
	}

	private void update() {
		IGoal goal = (IGoal) worklist.getFirst();
		worklist.removeFirst();
		boolean hasChanged = evaluator.updateGoal(goal);
		if (hasChanged) {
			actualizeParents(goal);			
		}
		doneset.add(goal);
	}

	public void postGoal(IGoal goal) {
		if (doneset.contains(goal)) {
			Collection parents = goal.getParents();
			for (Iterator iterator = parents.iterator(); iterator.hasNext();) {
				IGoal parent = (IGoal) iterator.next();
				parent.subgoalUpdated(goal);
			}
			actualizeParents(goal);
		} else if (worklist.contains(goal)) { // should happen very rare
			for (Iterator iterator = worklist.iterator(); iterator.hasNext();) {
				IGoal existent = (IGoal) iterator.next();
				if (existent.equals(goal)) {
					HashSet tmp = new HashSet();
					tmp.addAll(existent.getParents());
					tmp.addAll(goal.getParents());
					existent.setParents(tmp);
					break;
				}
			}
		} else
			worklist.add(goal);	
	}
	
}
