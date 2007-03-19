package org.eclipse.dltk.ruby.typeinference.evaluators;

import java.util.List;

import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class BlockEvaluator extends GoalEvaluator {

	private Statement lastStatement;
	private IEvaluatedType result;

	public BlockEvaluator(IGoal goal) {
		super(goal);
	}
	
	private ExpressionTypeGoal getTypedGoal () {
		return (ExpressionTypeGoal) this.getGoal();
	}
	
	public Object produceResult() {
		return this.result;
	}

	public IGoal[] init() {
		ExpressionTypeGoal typedGoal = getTypedGoal();
		Block block = (Block) typedGoal.getExpression();
		List statements = block.getStatements();
		if (statements.size() > 0) {
			this.lastStatement = (Statement) statements.get(statements.size() - 1);
			ExpressionTypeGoal subgoal = new ExpressionTypeGoal(goal.getContext(),this.lastStatement);
			return new IGoal[] {subgoal};
		}
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.result = (IEvaluatedType) result;
		return IGoal.NO_GOALS;
	}

}
