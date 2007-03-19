package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ti.IPruner;
import org.eclipse.dltk.ti.ITypeInferencer;
import org.eclipse.dltk.ti.TypeInferencer;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyTypeInferencer implements ITypeInferencer {
	
	private static MixinModel model = new MixinModel(RubyLanguageToolkit.getDefault());
	private final TypeInferencer inferencer;
	
	public RubyTypeInferencer() {
		RubyEvaluatorFactory factory = new RubyEvaluatorFactory();		
		inferencer = new TypeInferencer(factory);
	}

	public IEvaluatedType evaluateType(AbstractTypeGoal goal, IPruner pruner) {
		return inferencer.evaluateType(goal, pruner);
	}
	
	public static MixinModel getModel () {
		return model;
	}

}
