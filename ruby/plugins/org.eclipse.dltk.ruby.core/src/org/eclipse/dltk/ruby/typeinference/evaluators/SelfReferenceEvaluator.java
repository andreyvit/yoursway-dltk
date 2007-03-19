package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.IInstanceContext;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class SelfReferenceEvaluator extends GoalEvaluator {

	private IEvaluatedType result;

	public SelfReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	private ExpressionTypeGoal getTypedGoal() {
		return (ExpressionTypeGoal) goal;
	}

	private ISourceModuleContext getTypedContext() {
		return (ISourceModuleContext) goal.getContext();
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] init() {
		ISourceModuleContext typedContext = getTypedContext();
		if (typedContext instanceof IInstanceContext) {
			result = ((IInstanceContext) typedContext).getInstanceType();
		} else {
			ExpressionTypeGoal typedGoal = getTypedGoal();
			result = RubyTypeInferencingUtils.determineSelfClass(typedContext
					.getSourceModule(), typedContext.getRootNode(), typedGoal
					.getExpression().sourceStart());
		}
		// TODO: check if static self type is a descendent of the type from
		// InstanceContext (and use the descendent in this case)
		Assert.isTrue(result != null);
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
