package org.eclipse.dltk.ddp;

import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public abstract class GoalEvaluator {
	
	protected final IGoal goal;
	
//	private IGoal[] listeners = IGoal.NO_GOALS;
//	
//	private int nextReportedListener = 0;
	
	public GoalEvaluator(IGoal goal) {
		this.goal = goal;
	}
	
	public IGoal getGoal() {
		return goal;
	}

//	void manager$addListener(IGoal goal) {
//		int oldLength = listeners.length;
//		IGoal[] newListeners = new IGoal[oldLength + 1];
//		System.arraycopy(listeners, 0, newListeners, 0, oldLength);
//		newListeners[oldLength] = goal;
//		listeners = newListeners;
//	}
//	
//	IGoal manager$provideListener() {
//		if (nextReportedListener >= listeners.length)
//			return null;
//		else
//			return listeners[nextReportedListener++];
//	}
	
	public abstract IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult);
	
	public abstract IEvaluatedType produceType();
	
}
