package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class SelfReferenceEvaluator extends GoalEvaluator {

	private boolean calculated;

	private IEvaluatedType result;

	public SelfReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		if (!calculated) {
			initialize();
			calculated = true;
		}
		return null;
	}

	private void initialize() {
		ISourceModuleContext typedContext = getTypedContext();
		if (typedContext instanceof IInstanceContext) {
			result = ((IInstanceContext) typedContext).getInstanceType();
		} else {
			ExpressionTypeGoal typedGoal = getTypedGoal();
			result = RubyTypeInferencingUtils.determineSelfClass(typedContext.getSourceModule(),
					typedContext.getRootNode(), typedGoal.getExpression().sourceStart());
		}
		// TODO: check if static self type is a descendent of the type from
		// InstanceContext (and use the descendent in this case)
		Assert.isTrue(result != null);
	}

	private ExpressionTypeGoal getTypedGoal() {
		return (ExpressionTypeGoal) goal;
	}

	private ISourceModuleContext getTypedContext() {
		return (ISourceModuleContext) goal.getContext();
	}

	public IEvaluatedType produceResult() {
		if (!calculated) {
			initialize();
			calculated = true;
		}
		return result;
	}

}
