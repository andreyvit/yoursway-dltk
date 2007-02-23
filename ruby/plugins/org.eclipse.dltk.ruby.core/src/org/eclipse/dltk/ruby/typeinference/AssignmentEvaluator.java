package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class AssignmentEvaluator extends GoalEvaluator {
	
	public static final int STATE_BEFORE = 0;
	
	public static final int STATE_AFTER = 1;
	
	private int state = STATE_BEFORE;

	private IEvaluatedType result;
	
	public AssignmentEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
		if (state == STATE_BEFORE) {
			ExpressionGoal typedGoal = (ExpressionGoal) goal;
			Assignment expr = (Assignment) (typedGoal).getExpression();
			state = STATE_AFTER;
			return new ExpressionGoal(typedGoal.getContext(), expr.getRight());
		}
		result = previousResult;
		return null;
	}

	public IEvaluatedType produceType() {
		return result;
	}

}
