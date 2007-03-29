package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinElementInfo;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
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
		
		String elementKey = RubyTypeInferencingUtils.searchConstantElement(typedContext.getRootNode(), calculationOffset, constantName);
		
		IMixinElement constantElement = null;
		
		if (elementKey != null)
			constantElement = RubyMixinModel.getRawInstance().get(elementKey);
		
		if (constantElement == null)
			return IGoal.NO_GOALS;
		
		Object[] realObjs = constantElement.getAllObjects();
		for (int i = 0; i < realObjs.length; i++) {
			RubyMixinElementInfo realObj = (RubyMixinElementInfo) realObjs[i];
			if (realObj == null)
				continue;
			if (realObj.getKind() == RubyMixinElementInfo.K_CLASS || 
					realObj.getKind() == RubyMixinElementInfo.K_MODULE) { 
				result = new RubyClassType(constantElement.getKey());
			} else if (realObj.getKind() == RubyMixinElementInfo.K_VARIABLE) {
				helperGoal = new NonTypeConstantTypeGoal (goal.getContext(), constantElement);
			}
			break;
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
