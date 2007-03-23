package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ti.DefaultTypeInferencer;
import org.eclipse.dltk.ti.IPruner;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyTypeInferencer extends DefaultTypeInferencer {
	
	public RubyTypeInferencer() {
		super( new RubyEvaluatorFactory());
	}

	public IEvaluatedType evaluateType(AbstractTypeGoal goal, IPruner pruner) {
		return super.evaluateType(goal, pruner);
	}

}
