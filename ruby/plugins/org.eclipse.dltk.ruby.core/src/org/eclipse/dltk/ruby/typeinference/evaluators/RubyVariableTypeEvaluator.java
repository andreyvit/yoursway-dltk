/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.evaluators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.ast.RubyAssignment;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.RubyFieldReference;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ruby.typeinference.VariableTypeGoal;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.FieldReferencesGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.goals.ItemReference;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyVariableTypeEvaluator extends GoalEvaluator {

	private List possibilities = new ArrayList ();
	private List results = new ArrayList ();
	
	private boolean init = false; 
	
	public RubyVariableTypeEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal[] init() {
		init = true;
		VariableTypeGoal goal = (VariableTypeGoal) this.goal;
		String name = goal.getName();
		String parent = goal.getParentKey();
		
		return new IGoal[] {new FieldReferencesGoal(goal.getContext(), name, parent)};		
	}

	public Object produceResult() {
		IEvaluatedType type = RubyTypeInferencingUtils.combineTypes(results);
		return type;
	}
	
	private BasicContext buildContext (RubyFieldReference ref) {
		IModelElement element = DLTKCore.create(ref.getPosition().getResource());
		if (element instanceof ISourceModule) {
			ISourceModule sourceModule = (ISourceModule) element;
			ModuleDeclaration module = ASTUtils.getAST(sourceModule);
			return new BasicContext(sourceModule, module); //XXX: don't forget about options transfer
		}
		return (BasicContext) this.goal.getContext();
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		if (init) {
			init = false;
			if (result instanceof ItemReference[]) {
				ItemReference[] references = (ItemReference[]) result;
				for (int i = 0; i < references.length; i++) {
					if (references[i] instanceof RubyFieldReference) {
						RubyFieldReference ref = (RubyFieldReference) references[i];
						if (ref.getNode() instanceof RubyAssignment) {
							RubyAssignment assignment = (RubyAssignment) ref.getNode();							
							ExpressionTypeGoal s = new ExpressionTypeGoal(buildContext(ref), assignment.getRight());
							possibilities.add(s);
						}
					}
				}
			}
			return (IGoal[]) possibilities.toArray(new IGoal[possibilities.size()]);
		} else {
			if (result != null)
				results.add(result);
		}
		return null;
	}

}
