package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class AssignmentEvaluator extends GoalEvaluator {

	private IEvaluatedType result;

	public AssignmentEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal[] init() {
		ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
		Statement expression = (typedGoal).getExpression();
		if (!(expression instanceof Assignment)) {
			return IGoal.NO_GOALS;
		}
		Assignment expr = (Assignment) expression;
		return new IGoal[] { new ExpressionTypeGoal(typedGoal.getContext(),
				expr.getRight()) };
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.result = (IEvaluatedType) result;
		return IGoal.NO_GOALS;
	}

	public Object produceResult() {
		return result;
	}
}
