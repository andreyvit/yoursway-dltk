package org.eclipse.dltk.ti;

import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public interface ITypeInferencer {

	public IEvaluatedType evaluateType(AbstractTypeGoal goal, IPruner pruner);

}