package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.IContext;

/**
 * Task of this goal is to verify given possible position as a
 * real position, where given method were called.
 * 
 * As result, object of ItemReference should be returned. 
 */
public class MethodCallVerificationGoal extends AbstractGoal {

	private final PossiblePosition position;
	private final MethodCallsGoal goal;

	public MethodCallVerificationGoal(IContext context,
			MethodCallsGoal goal, PossiblePosition postion) {
		super(context);
		this.goal = goal;
		this.position = postion;
	}

	public PossiblePosition getPosition() {
		return position;
	}

	public MethodCallsGoal getGoal() {
		return goal;
	}

}

