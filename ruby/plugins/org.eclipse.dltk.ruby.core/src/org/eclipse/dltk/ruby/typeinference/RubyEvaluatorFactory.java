package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.expressions.BigNumericLiteral;
import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.IfStatement;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.ast.RubyArrayExpression;
import org.eclipse.dltk.ruby.ast.RubyBacktickStringLiteral;
import org.eclipse.dltk.ruby.ast.RubyCallArgument;
import org.eclipse.dltk.ruby.ast.RubyCaseStatement;
import org.eclipse.dltk.ruby.ast.RubyColonExpression;
import org.eclipse.dltk.ruby.ast.RubyConstantDeclaration;
import org.eclipse.dltk.ruby.ast.RubyDynamicBackquoteStringExpression;
import org.eclipse.dltk.ruby.ast.RubyDynamicStringExpression;
import org.eclipse.dltk.ruby.ast.RubyHashExpression;
import org.eclipse.dltk.ruby.ast.RubyRegexpExpression;
import org.eclipse.dltk.ruby.ast.RubySelfReference;
import org.eclipse.dltk.ruby.ast.RubySymbolReference;
import org.eclipse.dltk.ruby.typeinference.evaluators.AssignmentEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.BlockEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.BooleanLiteralEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.CaseStatementTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.ColonExpressionEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.ConstantReferenceEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.FieldParentKeyVerificator;
import org.eclipse.dltk.ruby.typeinference.evaluators.IfStatementTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.MethodCallTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.MethodCallVerificator;
import org.eclipse.dltk.ruby.typeinference.evaluators.MethodReturnTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.NonTypeConstantTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.RubyArgumentTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.RubyVariableTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.SelfReferenceEvaluator;
import org.eclipse.dltk.ruby.typeinference.evaluators.VariableReferenceEvaluator;
import org.eclipse.dltk.ruby.typeinference.goals.ColonExpressionGoal;
import org.eclipse.dltk.ruby.typeinference.goals.NonTypeConstantTypeGoal;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.IGoalEvaluatorFactory;
import org.eclipse.dltk.ti.ITypeInferencer;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.FieldPositionVerificationGoal;
import org.eclipse.dltk.ti.goals.FixedAnswerEvaluator;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.goals.MethodCallVerificationGoal;
import org.eclipse.dltk.ti.goals.MethodReturnTypeGoal;

public class RubyEvaluatorFactory implements IGoalEvaluatorFactory {
	
	/*private static final String TYPE_EVALUATORS = "org.eclipse.dltk.ruby.core.goalEvaluators";
	private final static Map evaluators = new HashMap();
	
	static {		
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(TYPE_EVALUATORS);
		IExtension[] ext = extensionPoint.getExtensions();
		ArrayList resolvers = new ArrayList();
		for (int a = 0; a < ext.length; a++) {
			IConfigurationElement[] elements = ext[a]
					.getConfigurationElements();
			IConfigurationElement myElement = elements[0];
			try {
				String nature = myElement.getAttribute("nature");
				List list = (List) evaluatorsByNatures.get(nature);
				if (list == null) {
					list = new ArrayList ();
					evaluatorsByNatures.put(nature, list);
				}
				ITypeInferencer resolver = (ITypeInferencer) myElement
						.createExecutableExtension("evaluator");
				resolvers.add(resolver);
				list.add(resolver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
	

	private GoalEvaluator createExpressionEvaluator(ExpressionTypeGoal goal) {
		Statement expr = goal.getExpression();

		// literals
		if (expr instanceof RubyRegexpExpression)
			return new FixedAnswerEvaluator(goal, new RubyClassType("Regexp%"));
		if (expr instanceof RubyHashExpression)
			return new FixedAnswerEvaluator(goal, new RubyClassType("Hash%"));
		if (expr instanceof BigNumericLiteral)
			return new FixedAnswerEvaluator(goal, new RubyClassType("Bignum%"));
		if (expr instanceof NumericLiteral)
			return new FixedAnswerEvaluator(goal, new RubyClassType("Fixnum%"));
		if (expr instanceof StringLiteral)
			return new FixedAnswerEvaluator(goal, new RubyClassType("String%"));
		if (expr instanceof RubyDynamicBackquoteStringExpression)
			return new FixedAnswerEvaluator(goal, new RubyClassType("String%"));
		if (expr instanceof RubyDynamicStringExpression)
			return new FixedAnswerEvaluator(goal, new RubyClassType("String%"));
		if (expr instanceof RubyBacktickStringLiteral)
			return new FixedAnswerEvaluator(goal, new RubyClassType("String%"));
		if (expr instanceof RubyArrayExpression)
			return new FixedAnswerEvaluator(goal, new RubyClassType("Array%"));
		if (expr instanceof MethodDeclaration)
			return new FixedAnswerEvaluator(goal,
					new RubyClassType("NilClass%"));
		if (expr instanceof RubySymbolReference)
			return new FixedAnswerEvaluator(goal, new RubyClassType("Symbol%"));

		if (expr instanceof BooleanLiteral)
			return new BooleanLiteralEvaluator(goal);
		if (expr instanceof VariableReference)
			return new VariableReferenceEvaluator(goal);

		if (expr instanceof Assignment)
			return new AssignmentEvaluator(goal);
		if (expr instanceof ConstantReference)
			return new ConstantReferenceEvaluator(goal);
		if (expr instanceof RubySelfReference)
			return new SelfReferenceEvaluator(goal);
		if (expr instanceof CallExpression)
			return new MethodCallTypeEvaluator((ExpressionTypeGoal) goal);
		if (expr instanceof IfStatement)
			return new IfStatementTypeEvaluator((ExpressionTypeGoal) goal);
		if (expr instanceof RubyCaseStatement)
			return new CaseStatementTypeEvaluator(goal);
		if (expr instanceof Block)
			return new BlockEvaluator((ExpressionTypeGoal) goal);
		if (expr instanceof RubyColonExpression)
			return new ColonExpressionEvaluator(goal);
		if (expr instanceof RubyConstantDeclaration)
			return new ConstantReferenceEvaluator(goal);
		if (expr instanceof RubyCallArgument)
			return new RubyArgumentTypeEvaluator(goal);

		// return new NullGoalEvaluator(goal);
		return null;
	}

	public GoalEvaluator createEvaluator(IGoal goal) {
		if (goal instanceof FieldPositionVerificationGoal)
			return new FieldParentKeyVerificator(goal);
		
		if (goal instanceof MethodCallVerificationGoal)
			return new MethodCallVerificator(goal);

		if (goal instanceof ExpressionTypeGoal) {
			ExpressionTypeGoal exprGoal = (ExpressionTypeGoal) goal;
			return createExpressionEvaluator(exprGoal);
		} else if (goal instanceof ConstantTypeGoal)
			return new ConstantReferenceEvaluator((ConstantTypeGoal) goal);
		else if (goal instanceof MethodReturnTypeGoal)
			return new MethodReturnTypeEvaluator(goal);
		else if (goal instanceof ColonExpressionGoal)
			return new ColonExpressionEvaluator(goal);
		else if (goal instanceof NonTypeConstantTypeGoal)
			return new NonTypeConstantTypeEvaluator(goal);
		else if (goal instanceof VariableTypeGoal) {
			return new RubyVariableTypeEvaluator(goal);			
		}

		return null;
		// return new NullGoalEvaluator(goal);
	}

	public static IGoal translateGoal(IGoal goal) {
		if (goal instanceof ExpressionTypeGoal) {
			ExpressionTypeGoal exprGoal = (ExpressionTypeGoal) goal;
			Statement expr = exprGoal.getExpression();
			if (expr instanceof ConstantReference) {
				ConstantReference reference = (ConstantReference) expr;
				return new ConstantTypeGoal(goal.getContext(), reference
						.sourceStart(), reference.getName());
			} else if (expr instanceof RubyConstantDeclaration) {
				SimpleReference reference = ((RubyConstantDeclaration) expr)
						.getName();
				// TODO: consider the constant's path
				return new ConstantTypeGoal(goal.getContext(), reference
						.sourceStart(), reference.getName());
			} else if (expr instanceof RubyColonExpression) {
				RubyColonExpression colonExpression = (RubyColonExpression) expr;
				return new ColonExpressionGoal(
						(BasicContext) goal.getContext(), colonExpression);
			}
		}
		return goal;
	}

}
