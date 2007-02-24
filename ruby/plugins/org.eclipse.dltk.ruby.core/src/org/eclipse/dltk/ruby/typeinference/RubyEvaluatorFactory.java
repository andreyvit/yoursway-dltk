package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.ddp.IGoalEvaluatorFactory;
import org.eclipse.dltk.ruby.ast.ConstantDeclaration;

public class RubyEvaluatorFactory implements IGoalEvaluatorFactory {
	
	public GoalEvaluator createEvaluator(IGoal goal) {
		if (goal instanceof ExpressionGoal) {
			ExpressionGoal exprGoal = (ExpressionGoal) goal;
			Statement expr = exprGoal.getExpression();
			if (expr instanceof VariableReference)
				return new VariableReferenceEvaluator(goal);
			else if (expr instanceof NumericLiteral)
				return new NumericLiteralEvaluator(goal);
			else if (expr instanceof StringLiteral)
				return new StringLiteralEvaluator(goal);
			else if (expr instanceof Assignment)
				return new AssignmentEvaluator(goal);
			else if (expr instanceof ConstantReference)
				return new AssignmentEvaluator(goal);
		} else if (goal instanceof ConstantTypeGoal)
			return new ConstantReferenceEvaluator((ConstantTypeGoal) goal);
		return null;
	}

	public IGoal translateGoal(IGoal goal) {
		if (goal instanceof ExpressionGoal) {
			ExpressionGoal exprGoal = (ExpressionGoal) goal;
			Statement expr = exprGoal.getExpression();
			if (expr instanceof ConstantReference) {
				ConstantReference reference = (ConstantReference) expr;
				return new ConstantTypeGoal(goal.getContext(), reference.sourceStart(), reference.getName());
			} else if (expr instanceof ConstantDeclaration) {
				SimpleReference reference = ((ConstantDeclaration) expr).getName();
				// TODO: consider the constant's path
				return new ConstantTypeGoal(goal.getContext(), reference.sourceStart(), reference.getName());
			}
		}
		return goal;
	}

}
