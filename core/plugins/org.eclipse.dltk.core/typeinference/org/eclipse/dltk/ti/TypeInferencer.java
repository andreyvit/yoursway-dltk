package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class TypeInferencer implements ITypeInferencer {
	
	private final GoalEngine engine;
	
	public TypeInferencer(IGoalEvaluatorFactory factory) {
		engine = new GoalEngine(factory);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dltk.ti.ITypeInferencer#evaluateType(org.eclipse.dltk.ti.AbstractTypeGoal, long)
	 */
	public IEvaluatedType evaluateType(AbstractTypeGoal goal, long timeLimit) {
		Object result = engine.evaluateGoal(goal, timeLimit);
		if (result == GoalEngine.RECURSION_RESULT)
			throw new RecursionGoalException();
		return (IEvaluatedType) result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.ti.ITypeInferencer#evaluateType(org.eclipse.dltk.ti.AbstractTypeGoal)
	 */
	public IEvaluatedType evaluateType(AbstractTypeGoal goal) {
		return evaluateType(goal, 0);
	}

}
