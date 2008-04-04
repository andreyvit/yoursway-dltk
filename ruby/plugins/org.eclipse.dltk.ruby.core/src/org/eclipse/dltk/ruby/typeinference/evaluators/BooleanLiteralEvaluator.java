/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
			return new RubyClassType ("TrueClass%"); //$NON-NLS-1$
		else
			return new RubyClassType ("FalseClass%");// TrueClass || FalseClass //$NON-NLS-1$
	}

	public IGoal[] init() {
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
