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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.evaluation.types.AmbiguousType;
import org.eclipse.dltk.ruby.ast.RubyReturnStatement;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.core.model.FakeMethod;
import org.eclipse.dltk.ruby.internal.parser.mixin.IRubyMixinElement;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixin;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinClass;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinMethod;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
import org.eclipse.dltk.ruby.typeinference.MethodContext;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyModelUtils;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.InstanceContext;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.goals.MethodReturnTypeGoal;
import org.eclipse.dltk.ti.types.ClassType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodReturnTypeEvaluator extends GoalEvaluator {
	
	private final List possibilities = new ArrayList();
	private final List evaluated = new ArrayList();
	
	private IEvaluatedType rdocResult = null;

	private MethodContext innerContext;

	public MethodReturnTypeEvaluator(IGoal goal) {
		super(goal);
	}
	
	private MethodReturnTypeGoal getTypedGoal () {
		return (MethodReturnTypeGoal) this.getGoal();
	}
	
	private InstanceContext getTypedContext () {
		return (InstanceContext) this.getGoal().getContext();
	}

	public Object produceResult() {
		if (rdocResult != null)
			return rdocResult;
		if (!evaluated.isEmpty()) {
			return RubyTypeInferencingUtils.combineTypes(evaluated);			
		}
		return null;
	}
	
	public IGoal[] init() {
		MethodReturnTypeGoal typedGoal = getTypedGoal();
		InstanceContext typedContext = getTypedContext();
		IEvaluatedType instanceType = typedContext.getInstanceType();
		if (instanceType instanceof AmbiguousType)
			instanceType = ((AmbiguousType)instanceType).getPossibleTypes()[0];
		String methodName = typedGoal.getMethodName();
					
		if (!(instanceType instanceof RubyClassType))
			return null;
		
		IEvaluatedType intrinsicMethodReturnType = checkSpecialMethodReturnType((ClassType) instanceType, methodName, typedGoal.getArguments());
		if (intrinsicMethodReturnType != null) {
			evaluated.add(intrinsicMethodReturnType);
			return IGoal.NO_GOALS;
		}
		
		MethodDeclaration decl = null;
		List methods = new ArrayList ();
		if (instanceType == null) {
			instanceType = new RubyClassType("Object"); //$NON-NLS-1$			
		}
		if (instanceType instanceof RubyClassType) {
			RubyClassType rubyClassType = (RubyClassType) instanceType;
			RubyMixinClass class1 = RubyMixinModel.getInstance().createRubyClass(rubyClassType);
			if (class1 != null) {
				RubyMixinMethod mixinMethods = class1.getMethod(methodName);
//				System.out.println();
				if (mixinMethods != null)
					methods.addAll(Arrays.asList(mixinMethods.getSourceMethods()));
			}
			if (rubyClassType.getModelKey().equals("Object")) { //$NON-NLS-1$
				IRubyMixinElement element = RubyMixinModel.getInstance().createRubyElement(methodName);
				if (element instanceof RubyMixinMethod) {
					RubyMixinMethod rubyMixinMethod = (RubyMixinMethod) element;
					methods.addAll(Arrays.asList(rubyMixinMethod.getSourceMethods()));
				}
			}
		}
				
		IMethod resultMethod = null;
		// in case of ambiguity, prefer methods from the same module
		IMethod resultMethodFromSameModule = null; 
		for (Iterator iterator = methods.iterator(); iterator.hasNext();) {
			IMethod method = (IMethod) iterator.next();
			if (method instanceof FakeMethod || method == null)
				continue;
			String elementName = method.getElementName();			
			if (elementName.equals(methodName)) {
				if (method.getSourceModule().equals(typedContext.getSourceModule()))
					resultMethodFromSameModule = method;
				resultMethod = method;				
			}				
		}
		if (resultMethodFromSameModule != null)
			resultMethod = resultMethodFromSameModule;		
		
		if (resultMethod == null)
			return IGoal.NO_GOALS;		
		
		ISourceModule sourceModule = resultMethod.getSourceModule();
		ModuleDeclaration module = RubyTypeInferencingUtils.parseSource(sourceModule);
		try {
			decl = RubyModelUtils.getNodeByMethod(module, resultMethod);
		} catch (ModelException e) {
			e.printStackTrace();
		}			
		String[] parameters;
		try {
			parameters = resultMethod.getParameters();
		} catch (ModelException e1) {
			RubyPlugin.log(e1);
			parameters = new String[0];
		}
		innerContext = new MethodContext(goal.getContext(), sourceModule, module, 
				parameters, typedGoal.getArguments());

		ASTVisitor visitor = new ASTVisitor () {
			
			public boolean visitGeneral(ASTNode node) throws Exception {
				if (node instanceof RubyReturnStatement) {
					RubyReturnStatement statement = (RubyReturnStatement) node;
					CallArgumentsList list = statement.getValue();
					if (list.getChilds().size() == 0) {
						MethodReturnTypeEvaluator.this.evaluated.add(new RubyClassType("NilClass")); //$NON-NLS-1$
					} else if (list.getChilds().size() > 1) {
						MethodReturnTypeEvaluator.this.evaluated.add(new RubyClassType("Array")); //$NON-NLS-1$
					} else {
						possibilities.add(list.getChilds().get(0));				
					}
				}
				return super.visitGeneral(node);
			}
			
		};
		if (decl != null) {				
			try {
				decl.traverse(visitor);
			} catch (Exception e) {
				RubyPlugin.log(e);
			}
			if (decl.getBody() != null)
				possibilities.add(decl.getBody());
		}
		
		IGoal[] newGoals = new IGoal[possibilities.size()];
		int i = 0;
		for (Iterator iterator = possibilities.iterator(); iterator.hasNext();) {
			ASTNode st = (ASTNode) iterator.next();
			ExpressionTypeGoal subgoal = new ExpressionTypeGoal(innerContext, st);
			newGoals[i++] = subgoal;
		}		
		return newGoals;
	}

	private IEvaluatedType checkSpecialMethodReturnType(ClassType instanceType,
			String methodName, IEvaluatedType[] arguments) {
		if (methodName.equals("clone") &&  instanceType.getModelKey().endsWith(RubyMixin.INSTANCE_SUFFIX)) //$NON-NLS-1$
			return instanceType;
		if (instanceType == null || instanceType.getModelKey().endsWith(RubyMixin.INSTANCE_SUFFIX) || 
				instanceType.getModelKey().endsWith(RubyMixin.VIRTUAL_SUFFIX) )
			return null;
		if (methodName.equals("new")) //$NON-NLS-1$ 
			return new RubyClassType (instanceType.getModelKey() + RubyMixin.INSTANCE_SUFFIX);
		return null;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		if (result != null)
			evaluated.add(result);
		return IGoal.NO_GOALS;
	}

}
