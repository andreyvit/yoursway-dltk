package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class BlockEvaluator extends GoalEvaluator {
	
	private boolean done = false;
	private boolean initialized = false;
	
	private final List possibilities = new ArrayList();
	private final List evaluated = new ArrayList();
	
	private int current = 0;

	public BlockEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal,
			Object previousResult) {
		if (done)
			return null;
		if (!initialized) {
			initialize();
			if (done)
				return null;
			current = 0;
		}
		if (previousResult != null)
			evaluated.add(previousResult);
		if (current == possibilities.size()) {
			done = true;
			return null;
		}
		ExpressionTypeGoal subgoal = new ExpressionTypeGoal(goal.getContext(), (Statement) possibilities
				.get(current));
		current++;
		return subgoal;
	}
		
	private void initialize () {
		ExpressionTypeGoal typedGoal = getTypedGoal();
		Block block = (Block) typedGoal.getExpression();
		List statements = block.getStatements();
		if (statements.size() > 0) {
			Object st = statements.get(statements.size() - 1);
			possibilities.add(st);
		}
		initialized = true;
	}
	
	private ExpressionTypeGoal getTypedGoal () {
		return (ExpressionTypeGoal) this.getGoal();
	}
	
	public Object produceResult() {
		if (!evaluated.isEmpty()) {
			return RubyTypeInferencingUtils.combineTypes(evaluated);			
		}
		return null;
	}

}
