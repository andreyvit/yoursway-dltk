package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.typeinference.ConstantTypeGoal;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyEvaluatorFactory;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencer;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ruby.typeinference.goals.NonTypeConstantTypeGoal;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ConstantReferenceEvaluator extends GoalEvaluator {

	private IEvaluatedType result;

	private IGoal helperGoal;

	public ConstantReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	private ConstantTypeGoal getTypedGoal() {
		return (ConstantTypeGoal) RubyEvaluatorFactory.translateGoal(goal);
	}

	private ISourceModuleContext getTypedContext() {
		return (ISourceModuleContext) goal.getContext();
	}

	public Object produceResult() {		
		return result; 
	}

	public IGoal[] init() {
		helperGoal = null;
		ISourceModuleContext typedContext = getTypedContext();
		ConstantTypeGoal typedGoal = getTypedGoal();
		String constantName = typedGoal.getName();
		int calculationOffset = typedGoal.getOffset();
		
		MixinModel model = RubyTypeInferencer.getModel();
		IMixinElement[] modelStaticScopes = RubyTypeInferencingUtils.getModelStaticScopes(model, 
				typedContext.getRootNode(), calculationOffset);
		
		IMixinElement constantElement = null;
		
		for (int i = modelStaticScopes.length - 1; i >= 0; i--) {
			IMixinElement scope = modelStaticScopes[i];
			String possibleKey = scope.getKey() + IMixinRequestor.MIXIN_NAME_SEPARATOR + constantName;
			constantElement = model.get(possibleKey);
			if (constantElement != null)
				break;
		}
		
		//check top-most scope
		if (constantElement == null)
			constantElement = model.get(constantName);
		
		if (constantElement == null)
			return IGoal.NO_GOALS;
		
		Object realObj = constantElement.getAllObjects()[0];
		if (realObj instanceof IType) { 
			result = new RubyClassType(constantElement.getKey());
		} else if (realObj instanceof IField) {
			helperGoal = new NonTypeConstantTypeGoal (goal.getContext(), constantElement);
		}
		if (helperGoal != null) {
			return new IGoal[] { helperGoal };
		}
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.result = (IEvaluatedType) result;
		return IGoal.NO_GOALS;
	}

}
