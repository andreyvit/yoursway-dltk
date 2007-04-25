package org.eclipse.dltk.ruby.typeinference.evaluators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.VariableKind;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.ast.RubyVariableKind;
import org.eclipse.dltk.ruby.typeinference.IArgumentsContext;
import org.eclipse.dltk.ruby.typeinference.LocalVariableInfo;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ruby.typeinference.VariableTypeGoal;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class VariableReferenceEvaluator extends GoalEvaluator {

	private LocalVariableInfo info;

	private List results = new ArrayList();

	public VariableReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	public Object produceResult() {
		return RubyTypeInferencingUtils.combineTypes(results);
	}

	public IGoal[] init() {
		VariableReference ref = (VariableReference) ((ExpressionTypeGoal) goal)
				.getExpression();
		if (ref.getVariableKind() == RubyVariableKind.LOCAL) {
			IContext context = goal.getContext();
			ModuleDeclaration rootNode = ((ISourceModuleContext) context)
					.getRootNode();
			VariableReference expression = (VariableReference) ((ExpressionTypeGoal) goal)
					.getExpression();
			String varName = expression.getName().trim();
			if (expression.getVariableKind() instanceof VariableKind.Local) {
				if (context instanceof IArgumentsContext) {
					IArgumentsContext argumentsContext = (IArgumentsContext) context;
					IEvaluatedType argumentType = argumentsContext
							.getArgumentType(varName);
					if (argumentType != null) {
						results.add(argumentType);
						return IGoal.NO_GOALS;
					}
				}
			}
			info = RubyTypeInferencingUtils.findLocalVariable(rootNode,
					expression.sourceStart(), varName);

			List poss = new ArrayList();

			if (info != null && info.assignments != null) {
				for (int i = 0; i < info.assignments.length; i++) {
					IGoal subgoal = new ExpressionTypeGoal(context,
							info.assignments[i].getRight());
					poss.add(subgoal);
				}
			}

			return (IGoal[]) poss.toArray(new IGoal[poss.size()]);

		} else {
			IEvaluatedType selfClass = RubyTypeInferencingUtils
					.determineSelfClass(goal.getContext(), ref.sourceStart());
			if (selfClass instanceof RubyClassType) {
				String selfKey = ((RubyClassType) selfClass).getModelKey();
				return new IGoal[] { new VariableTypeGoal(goal.getContext(), ref.getName(), 
						selfKey, ref.getVariableKind()) };
			}
		}
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		if (result != null)
			results.add(result);
		return IGoal.NO_GOALS;
	}

}
