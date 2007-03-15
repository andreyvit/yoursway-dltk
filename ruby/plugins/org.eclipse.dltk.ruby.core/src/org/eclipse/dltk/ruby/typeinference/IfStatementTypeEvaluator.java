package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.statements.IfStatement;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class IfStatementTypeEvaluator extends GoalEvaluator {

	private final static int STATE_TRY_THEN = 0;

	private final static int STATE_WAITING_THEN = 1;

	private final static int STATE_TRY_ELSE = 2;

	private final static int STATE_WAITING_ELSE = 3;

	private final static int STATE_DONE = -2;

	private int state = STATE_TRY_THEN;

	private IEvaluatedType[] evaluatedTypes = new IEvaluatedType[2];

	private int index = STATE_TRY_THEN;

	public IfStatementTypeEvaluator(ExpressionTypeGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		if (state == STATE_TRY_THEN) {
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			IfStatement expression = (IfStatement) typedGoal.getExpression();
			Statement clause = expression.getThen();
			if (clause == null)
				state = STATE_TRY_ELSE;
			else {
				state = STATE_WAITING_THEN;
				return new ExpressionTypeGoal(goal.getContext(), clause);
			}
		}
		if (state == STATE_WAITING_THEN || state == STATE_WAITING_ELSE) {
			if (previousResult != null)
				evaluatedTypes[index++] = (IEvaluatedType) previousResult;
			state = (state == STATE_WAITING_THEN ? STATE_TRY_ELSE : STATE_DONE);
		} else {
			Assert.isTrue(previousGoal == null);
		}
		if (state == STATE_TRY_ELSE) {
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			IfStatement expression = (IfStatement) typedGoal.getExpression();
			Statement clause = expression.getElse();
			if (clause == null)
				state = STATE_DONE;
			else {
				state = STATE_WAITING_ELSE;
				return new ExpressionTypeGoal(goal.getContext(), clause);
			}
		}
		return null;
	}

	public IEvaluatedType produceResult() {
		return RubyTypeInferencingUtils.combineTypes(evaluatedTypes);
	}

}
