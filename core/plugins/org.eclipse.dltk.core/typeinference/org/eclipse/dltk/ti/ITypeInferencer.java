package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public interface ITypeInferencer {

	/**
	 * Should evaluate type for a goal
	 * @param goal
	 * @param timeLimit time in milliseconds, or -1 if no limits
	 * @return
	 */
	public IEvaluatedType evaluateType(AbstractTypeGoal goal, int timeLimit);

}