package org.eclipse.dltk.ruby.typeinference.evaluators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.ast.RubyCaseStatement;
import org.eclipse.dltk.ruby.ast.RubyWhenStatement;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class CaseStatementTypeEvaluator extends GoalEvaluator {
	
	private List types = new ArrayList ();

	public CaseStatementTypeEvaluator(IGoal goal) {
		super(goal);
	}

	private ExpressionTypeGoal getTypedGoal () {
		return (ExpressionTypeGoal) getGoal();
	}
	
	public IGoal[] init() {
		ExpressionTypeGoal typedGoal = this.getTypedGoal();
		Statement expression = typedGoal.getExpression();
		if (!(expression instanceof RubyCaseStatement))
			return IGoal.NO_GOALS;
		RubyCaseStatement caseSt = (RubyCaseStatement) expression;
		List subgoals = new ArrayList ();
		List whens = caseSt.getWhens();
		for (Iterator iterator = whens.iterator(); iterator.hasNext();) {
			RubyWhenStatement when = (RubyWhenStatement) iterator.next();
			Statement body = when.getBody();
			subgoals.add(new ExpressionTypeGoal(this.goal.getContext(), body));			
		}
		return (IGoal[]) subgoals.toArray(new IGoal[subgoals.size()]);
	}

	public Object produceResult() {		
		return RubyTypeInferencingUtils.combineTypes(types);
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		if (result != null)
			this.types.add(result);
		return IGoal.NO_GOALS;
	}

}
