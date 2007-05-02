/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ruby.ast.RubyCallArgument;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class RubyArgumentTypeEvaluator extends GoalEvaluator {

	private Object result;
	
	public RubyArgumentTypeEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal[] init() {
		ExpressionTypeGoal goal = (ExpressionTypeGoal) this.getGoal();
		RubyCallArgument arg = (RubyCallArgument) goal.getExpression();
		if (arg.getValue() != null)		
			return new IGoal[] { new ExpressionTypeGoal(goal.getContext(), arg.getValue()) };
		return IGoal.NO_GOALS;
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.result = result;
		return IGoal.NO_GOALS;
	}

}
