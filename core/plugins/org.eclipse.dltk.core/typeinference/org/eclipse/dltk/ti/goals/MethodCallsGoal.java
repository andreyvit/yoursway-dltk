package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.IContext;

/**
 * Task of this goal is to find all calls of the given method.
 * Evaluator for this goal is registered by default and uses standard
 * DLTK search. Cause without full type information it is impossible to
 * get fully correct results, goal will send MethodCallVerificationGoals.
 * So, to get them working user should register appropriate evaluator.
 * 
 * Result of this goal is array of ItemReference objects
 */
public class MethodCallsGoal extends AbstractReferencesGoal {

	public MethodCallsGoal(IContext context, String methodName, String parentModelKey) {
		super(context, methodName, parentModelKey);
	}
	
}