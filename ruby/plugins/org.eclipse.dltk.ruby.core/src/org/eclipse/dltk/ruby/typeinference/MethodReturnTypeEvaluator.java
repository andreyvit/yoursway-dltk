package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.ruby.ast.ReturnStatement;
import org.eclipse.dltk.ruby.core.RubyPlugin;

public class MethodReturnTypeEvaluator extends GoalEvaluator {
	
	private boolean done = false;
	private boolean initialized = false;
	
	private final List possibilities = new ArrayList();
	private final List evaluated = new ArrayList();
	
	private int current = 0;
	private MethodContext innerContext;

	public MethodReturnTypeEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal,
			IEvaluatedType previousResult) {
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
		ExpressionGoal subgoal = new ExpressionGoal(innerContext, (Statement) possibilities.get(current));
		current++;
		return subgoal;
	}
		
	private void initialize () {
		MethodReturnTypeGoal typedGoal = getTypedGoal();
		InstanceContext typedContext = getTypedContext();
		IEvaluatedType instanceType = typedContext.getInstanceType();
		String methodName = typedGoal.getMethodName();
		IEvaluatedType intrinsicMethodReturnType = BuiltinMethods.getIntrinsicMethodReturnType(instanceType, methodName, typedGoal.getArguments());
		if (intrinsicMethodReturnType != null) {
			evaluated.add(intrinsicMethodReturnType);
			done = true;
			return;
		}
		
		IMethod resultMethod = null;
		MethodDeclaration decl = null;
		if (instanceType instanceof RubyClassType) {
			RubyClassType type = (RubyClassType) instanceType;
			type = RubyTypeInferencingUtils.resolveMethods(typedContext.getSourceModule().getScriptProject(), type);
			IMethod[] allMethods = type.getAllMethods();
			for (int i = 0; i < allMethods.length; i++) {
				if (allMethods[i].getElementName().equals(methodName)) {
					resultMethod = allMethods[i];
				}
			}
		} else if (instanceType instanceof RubyMetaClassType) {
			RubyMetaClassType type = (RubyMetaClassType) instanceType;
			IMethod[] methods = type.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getElementName().equals(methodName)) {
					resultMethod = methods[i];				
				}				
			}
		}
		if (resultMethod == null)
			return;
		
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
				if (node instanceof ReturnStatement) {
					ReturnStatement statement = (ReturnStatement) node;
					if (statement.getValue() instanceof Expression) {
						possibilities.add(statement.getValue());
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
			if (decl.getBody() != null) {
				List statements = decl.getBody().getStatements();
				if (statements.size() > 0) {
					Object st = statements.get(statements.size() - 1);
					if (st instanceof Expression) {
						possibilities.add(st);
					}
				}
			}
		}		
		
		initialized = true;
	}
	
	private MethodReturnTypeGoal getTypedGoal () {
		return (MethodReturnTypeGoal) this.getGoal();
	}
	
	private InstanceContext getTypedContext () {
		return (InstanceContext) this.getGoal().getContext();
	}

	public IEvaluatedType produceType() {
		if (!evaluated.isEmpty()) {
			return RubyTypeInferencingUtils.combineTypes(evaluated);			
		}
		return null;
	}

}
