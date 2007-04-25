package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.IContext;

/**
 * Task of this goal is to verify given possible position as a
 * real position, where given field were read or changed.
 * 
 * As result, object of ItemReference or null should be returned. 
 */
public class FieldPositionVerificationGoal extends AbstractGoal {

	private final PossiblePosition position;
	private final FieldReferencesGoal goal;

	public FieldPositionVerificationGoal(IContext context,
			FieldReferencesGoal goal, PossiblePosition postion) {
		super(context);
		this.goal = goal;
		this.position = postion;
	}

	public PossiblePosition getPosition() {
		return position;
	}

	public FieldReferencesGoal getGoal() {
		return goal;
	}

}
