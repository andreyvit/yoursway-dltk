package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class BooleanLiteralEvaluator extends GoalEvaluator {
	
	public BooleanLiteralEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		return null;
	}

	public Object produceResult() {
		ExpressionTypeGoal tg = (ExpressionTypeGoal) goal;
		BooleanLiteral l = (BooleanLiteral) tg.getExpression();
		if (l.boolValue())
			return new RubyClassType ("TrueClass%");
		else
			return new RubyClassType ("FalseClass%");// TrueClass || FalseClass
	}

	public IGoal[] init() {
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
