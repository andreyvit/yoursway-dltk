/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.evaluators;

import java.util.HashMap;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinElementInfo;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.goals.NonTypeConstantTypeGoal;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

public class NonTypeConstantTypeEvaluator extends GoalEvaluator {
	
	private HashMap hardcoredTypes = new HashMap ();
	private Object result;

	public NonTypeConstantTypeEvaluator(IGoal goal) {
		super(goal);
		hardcoredTypes.put("FALSE", new RubyClassType("FalseClass%"));
		hardcoredTypes.put("RUBY_RELEASE_DATE", new RubyClassType("String%"));
		hardcoredTypes.put("STDERR", new RubyClassType("IO%"));
		hardcoredTypes.put("MatchingData", new RubyClassType("Class%"));
		hardcoredTypes.put("TOPLEVEL_BINDING", new RubyClassType("Binding%"));
		hardcoredTypes.put("ENV", new RubyClassType("Object%"));
		hardcoredTypes.put("STDIN", new RubyClassType("IO%"));
		hardcoredTypes.put("TRUE", new RubyClassType("TrueClass%"));
		hardcoredTypes.put("RELEASE_DATE", new RubyClassType("String%"));
		hardcoredTypes.put("NIL", new RubyClassType("NilClass%"));
		hardcoredTypes.put("RUBY_PLATFORM", new RubyClassType("String%"));
		hardcoredTypes.put("RUBY_VERSION", new RubyClassType("String%"));
		hardcoredTypes.put("STDOUT", new RubyClassType("IO%"));
		hardcoredTypes.put("PLATFORM", new RubyClassType("String%"));
		hardcoredTypes.put("ARGV", new RubyClassType("Array%"));
		hardcoredTypes.put("ARGF", new RubyClassType("Object%"));
		hardcoredTypes.put("VERSION", new RubyClassType("String%"));
	}
	
	private NonTypeConstantTypeGoal getTypedGoal () {
		return (NonTypeConstantTypeGoal) this.goal;
	}

	public IGoal[] init() {
		NonTypeConstantTypeGoal g = getTypedGoal();
		IMixinElement element = g.getElement();
		Object[] allObjects = element.getAllObjects();
		if (allObjects == null)
			return IGoal.NO_GOALS;

		for (int i = 0; i < allObjects.length; i++) {
			RubyMixinElementInfo info = (RubyMixinElementInfo) allObjects[i];
			if (info != null && info.getKind() == RubyMixinElementInfo.K_VARIABLE) {
				if (info.getObject() instanceof IField) {
					IField field = (IField) info.getObject();
					String name = field.getElementName();
					if (hardcoredTypes.containsKey(name)) {
						this.result = hardcoredTypes.get(name);
						break;
					}
				}
			}
		}
		
		return IGoal.NO_GOALS;
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return IGoal.NO_GOALS;
	}

}
