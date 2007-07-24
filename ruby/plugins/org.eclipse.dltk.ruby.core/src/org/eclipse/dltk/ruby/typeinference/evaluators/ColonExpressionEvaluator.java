/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.ruby.ast.RubyColonExpression;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinElementInfo;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
import org.eclipse.dltk.ruby.typeinference.DefaultRubyEvaluatorFactory;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.goals.ColonExpressionGoal;
import org.eclipse.dltk.ruby.typeinference.goals.NonTypeConstantTypeGoal;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.ClassType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ColonExpressionEvaluator extends GoalEvaluator {

	private AbstractTypeGoal helperGoal = null;

	private IEvaluatedType helperResult = null;

	private IEvaluatedType answer = null;

	public ColonExpressionEvaluator(IGoal goal) {
		super(goal);
	}

	private ColonExpressionGoal getTypedGoal() {
		return (ColonExpressionGoal) DefaultRubyEvaluatorFactory
				.translateGoal(this.goal);
	}

	public Object produceResult() {

		return answer;
	}

	public IGoal[] init() {
		RubyColonExpression expr = getTypedGoal().getColonExpression();

		ASTNode left = expr.getLeft();

		if (left != null) {
			helperGoal = new ExpressionTypeGoal(getGoal().getContext(), left);
			return new IGoal[] { helperGoal };
		} else {
			IMixinElement mixinElement = RubyMixinModel.getRawInstance().get(
					expr.getName());
			if (mixinElement != null)
				answer = new RubyClassType(mixinElement.getKey());
		}

		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.helperResult = (IEvaluatedType) result;

		RubyColonExpression expr = getTypedGoal().getColonExpression();
		ASTNode left = expr.getLeft();

		if (left != null) {
			if (helperResult instanceof ClassType) { // TODO: check existance
														// of the new key
				ClassType classType = (ClassType) helperResult;
				String modelKey = classType.getModelKey();
				modelKey += IMixinRequestor.MIXIN_NAME_SEPARATOR
						+ expr.getName();
				IMixinElement mixinElement = RubyMixinModel.getRawInstance()
						.get(modelKey);
				if (mixinElement != null) {
					Object[] objects = mixinElement.getAllObjects();
					boolean found = false;
					for (int i = 0; i < objects.length; i++) {
						if (objects[i] instanceof RubyMixinElementInfo) {
							RubyMixinElementInfo info = (RubyMixinElementInfo) objects[i];
							if (info.getKind() == RubyMixinElementInfo.K_VARIABLE) {
								found = true;
								break;
							}
						}
					}

					if (found) { // non-type constant
						NonTypeConstantTypeGoal g = new NonTypeConstantTypeGoal(
								goal.getContext(), mixinElement);
						return new IGoal[] { g };
					}

					answer = new RubyClassType(modelKey);
				} else 
					answer = null;
			}
		} else {
			IMixinElement mixinElement = RubyMixinModel.getRawInstance().get(
					expr.getName());
			if (mixinElement != null)
				answer = new RubyClassType(mixinElement.getKey());
		}

		return IGoal.NO_GOALS;
	}

}
