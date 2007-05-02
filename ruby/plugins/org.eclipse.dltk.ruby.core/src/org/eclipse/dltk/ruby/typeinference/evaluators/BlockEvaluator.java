/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.evaluators;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class BlockEvaluator extends GoalEvaluator {

	private ASTNode lastStatement;
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
			this.lastStatement = (ASTNode) statements.get(statements.size() - 1);
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
