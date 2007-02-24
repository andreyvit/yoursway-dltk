package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ddp.BasicContext;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.evaluation.types.UnknownType;

public class SelfReferenceEvaluator extends GoalEvaluator {

	private boolean calculated;

	private IEvaluatedType result;

	public SelfReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
		if (!calculated) {
			initialize();
			calculated = true;
		}
		return null;
	}

	private void initialize() {
		BasicContext typedContext = getTypedContext();
		if (typedContext instanceof InstanceContext) {
			result = ((InstanceContext) typedContext).getInstanceType();
		} else {
			ExpressionGoal typedGoal = getTypedGoal();
			result = RubyTypeInferencingUtils.determineSelfClass(typedContext.getSourceModule(),
					typedContext.getRootNode(), typedGoal.getExpression().sourceStart());
		}
		// TODO: check if static self type is a descendent of the type from
		// InstanceContext (and use the descendent in this case)
		Assert.isTrue(result != null);
	}

	private ExpressionGoal getTypedGoal() {
		return (ExpressionGoal) goal;
	}

	private BasicContext getTypedContext() {
		return (BasicContext) goal.getContext();
	}

	public IEvaluatedType produceType() {
		if (!calculated) {
			initialize();
			calculated = true;
		}
		return result;
	}

}
