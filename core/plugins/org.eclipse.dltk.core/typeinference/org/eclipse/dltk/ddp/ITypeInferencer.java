package org.eclipse.dltk.ddp;

import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public interface ITypeInferencer {
	
	IEvaluatedType evaluateGoal(IGoal goal, long timeLimit);

}
