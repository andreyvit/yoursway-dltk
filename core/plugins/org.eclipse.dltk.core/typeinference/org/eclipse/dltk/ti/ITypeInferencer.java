package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

/**
 * 
 *
 */
public interface ITypeInferencer {

	/**
	 * Should evaluate type for a "type goal". Type goal is an abstract thing with a
	 * context, that represents some kind of evaluation (expression type, method return type, 
	 * etc.) So, inferencer should know about kinds of tasks it should do.
	 * 
	 * @param goal
	 * @param timeLimit time in milliseconds, or -1 if no limits. 
	 * @return 
	 */
	public IEvaluatedType evaluateType(AbstractTypeGoal goal, int timeLimit);

}