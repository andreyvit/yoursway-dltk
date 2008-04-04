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
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.ast.RubyAssignment;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyFieldReference;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.FieldPositionVerificationGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.goals.PossiblePosition;

public class FieldParentKeyVerificator extends GoalEvaluator {

	private RubyFieldReference result = null;

	public FieldParentKeyVerificator(IGoal goal) {
		super(goal);
	}

	private ASTNode translateNode(ASTNode node, ModuleDeclaration decl) {
		ASTNode[] way = ASTUtils.restoreWayToNode(decl, node);
		if (way.length >= 2 && way[way.length - 2] instanceof RubyAssignment) {
			RubyAssignment assignment = (RubyAssignment) way[way.length - 2];
			if (assignment.getLeft().equals(node)) 
				return way[way.length - 2];
		}
		return node;
	}

	public IGoal[] init() {
		FieldPositionVerificationGoal goal = (FieldPositionVerificationGoal) this.goal;
		PossiblePosition position = goal.getPosition();

		boolean constant = false;
		String name = goal.getGoal().getName();
		if (Character.isUpperCase(name.charAt(0))) {
			constant = true;
		}

		if (position.getNode() != null) {
			ASTNode node = position.getNode();
			IModelElement element = DLTKCore.create(position.getResource());
			if (element instanceof ISourceModule) {
				ISourceModule sourceModule = (ISourceModule) element;
				ModuleDeclaration module = ASTUtils.getAST(sourceModule);
				RubyClassType selfClass = RubyTypeInferencingUtils
						.determineSelfClass(sourceModule, module, node
								.sourceStart());
				
				if (selfClass == null)
					return null;
				
				node = translateNode(node, module);
				
				boolean approve = false;
				
				if (name.startsWith("$")) //$NON-NLS-1$
					approve = true;
				else if (goal.getGoal().getParentModelKey().equals(
								selfClass.getModelKey())) {
					approve = true;
				} else if (constant) { //up-scope visible
					if (goal.getGoal().getParentModelKey().startsWith(selfClass.getModelKey())) {
						approve = true;
					}
				}
									
				if (approve) {
					result = new RubyFieldReference(goal.getGoal().getName(),
							selfClass.getModelKey(), position,  node);
				}
			}
		}

		return null;
	}

	public Object produceResult() {
		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		return null;
	}

}
