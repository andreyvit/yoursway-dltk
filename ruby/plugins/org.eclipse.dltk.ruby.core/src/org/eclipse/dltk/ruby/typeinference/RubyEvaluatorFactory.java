package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.IfStatement;
import org.eclipse.dltk.ast.statements.ReturnStatement;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.ast.BacktickStringLiteral;
import org.eclipse.dltk.ruby.ast.ColonExpression;
import org.eclipse.dltk.ruby.ast.ConstantDeclaration;
import org.eclipse.dltk.ruby.ast.DynamicBackquoteStringExpression;
import org.eclipse.dltk.ruby.ast.DynamicStringExpression;
import org.eclipse.dltk.ruby.ast.RubyArrayExpression;
import org.eclipse.dltk.ruby.ast.RubyCaseStatement;
import org.eclipse.dltk.ruby.ast.RubyReturnStatement;
import org.eclipse.dltk.ruby.ast.SelfReference;
import org.eclipse.dltk.ruby.typeinference.evaluators.ArrayEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.AssignmentEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.BlockEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.BooleanLiteralEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.CaseStatementTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.ColonExpressionEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.ConstantReferenceEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.IfStatementTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.MethodCallTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.MethodReturnTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.NullGoalEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.NumericLiteralEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.ReturnStatementEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.SelfReferenceEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.StringLiteralEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.VariableReferenceEvaluator;
import org.eclipse.dltk.ruby.typeinference.goals.ColonExpressionGoal;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.IGoalEvaluatorFactory;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.goals.MethodReturnTypeGoal;

public class RubyEvaluatorFactory implements IGoalEvaluatorFactory {
	
	public GoalEvaluator createEvaluator(IGoal goal) {
		//goal = translateGoal(goal);
		if (goal instanceof ExpressionTypeGoal) {
			ExpressionTypeGoal exprGoal = (ExpressionTypeGoal) goal;
			Statement expr = exprGoal.getExpression();
			if (expr instanceof VariableReference)
				return new VariableReferenceEvaluator(goal);
			else if (expr instanceof NumericLiteral)
				return new NumericLiteralEvaluator(goal);
			else if (expr instanceof StringLiteral)
				return new StringLiteralEvaluator(goal);
			else if (expr instanceof DynamicBackquoteStringExpression)
				return new StringLiteralEvaluator(goal);
			else if (expr instanceof DynamicStringExpression)
				return new StringLiteralEvaluator(goal);
			else if (expr instanceof BacktickStringLiteral)
				return new StringLiteralEvaluator(goal);
			else if (expr instanceof BooleanLiteral)
				return new BooleanLiteralEvaluator(goal);
			else if (expr instanceof RubyArrayExpression)
				return new ArrayEvaluator(goal);
			else if (expr instanceof Assignment)
				return new AssignmentEvaluator(goal);
			else if (expr instanceof ConstantReference)
				//return new AssignmentEvaluator(goal);
				return new ConstantReferenceEvaluator(goal);
			else if (expr instanceof SelfReference)
				return new SelfReferenceEvaluator(goal);
			else if (expr instanceof CallExpression)
				return new MethodCallTypeEvaluator((ExpressionTypeGoal) goal);
			else if (expr instanceof IfStatement)
				return new IfStatementTypeEvaluator((ExpressionTypeGoal) goal);
			else if (expr instanceof Block)
				return new BlockEvaluator((ExpressionTypeGoal) goal);
			else if (expr instanceof MethodDeclaration) 
				return new FixedTypeEvaluator (goal, new RubyClassType("NilClass"));
			else if (expr instanceof ColonExpression) {
				return new ColonExpressionEvaluator(goal);
			} else if (expr instanceof ConstantDeclaration) {
				return new ConstantReferenceEvaluator(goal);
			} else if (expr instanceof ReturnStatement || expr instanceof RubyReturnStatement) {
				return new ReturnStatementEvaluator(goal);
			} else if (expr instanceof RubyCaseStatement)
				return new CaseStatementTypeEvaluator(goal);
		} else if (goal instanceof ConstantTypeGoal)
			return new ConstantReferenceEvaluator((ConstantTypeGoal) goal);
		else if (goal instanceof MethodReturnTypeGoal)
			return new MethodReturnTypeEvaluator(goal);
		else if (goal instanceof ColonExpressionGoal) 
			return new ColonExpressionEvaluator(goal);
		return new NullGoalEvaluator(goal);
	}

	public static IGoal translateGoal(IGoal goal) {
		if (goal instanceof ExpressionTypeGoal) {
			ExpressionTypeGoal exprGoal = (ExpressionTypeGoal) goal;
			Statement expr = exprGoal.getExpression();
			if (expr instanceof ConstantReference) {
				ConstantReference reference = (ConstantReference) expr;
				return new ConstantTypeGoal(goal.getContext(), reference.sourceStart(), reference.getName());
			} else if (expr instanceof ConstantDeclaration) {
				SimpleReference reference = ((ConstantDeclaration) expr).getName();
				// TODO: consider the constant's path
				return new ConstantTypeGoal(goal.getContext(), reference.sourceStart(), reference.getName());
			} else if (expr instanceof ColonExpression) {
				ColonExpression colonExpression = (ColonExpression) expr;
				return new ColonExpressionGoal((BasicContext) goal.getContext(), colonExpression);
			}
		}
		return goal;
	}

}
