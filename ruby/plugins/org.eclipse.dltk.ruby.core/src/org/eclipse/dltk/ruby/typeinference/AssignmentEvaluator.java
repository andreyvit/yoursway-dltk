package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class AssignmentEvaluator extends GoalEvaluator {
	
	public static final int STATE_BEFORE = 0;
	
	public static final int STATE_AFTER = 1;
	
	private int state = STATE_BEFORE;

	private IEvaluatedType result;
	
	public AssignmentEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		if (state == STATE_BEFORE) {
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			Assignment expr = (Assignment) (typedGoal).getExpression();
			state = STATE_AFTER;
			return new ExpressionTypeGoal(typedGoal.getContext(), expr.getRight());
		}
		result = (IEvaluatedType) previousResult;
		return null;
	}

	public IEvaluatedType produceResult() {
		return result;
	}

}
