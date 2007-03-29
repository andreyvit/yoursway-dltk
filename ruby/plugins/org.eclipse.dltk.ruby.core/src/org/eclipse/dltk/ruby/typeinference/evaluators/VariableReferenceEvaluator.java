package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.VariableKind;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.ruby.typeinference.IArgumentsContext;
import org.eclipse.dltk.ruby.typeinference.LocalVariableInfo;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class VariableReferenceEvaluator extends GoalEvaluator {
	
	private final int STATE_INIT = 0;
	
	private final int STATE_ASSIGNMENT_SUBGOAL_0 = 1;
	
	private final int STATE_NOT_FOUND = -1;
	
	private final int STATE_DONE = -2;
	
	private int state = STATE_INIT;
	
	private LocalVariableInfo info;
	
	private IEvaluatedType[] evaluatedTypes;
	
	private int countOfEvaluatedTypes;

	private IEvaluatedType result;

	public VariableReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	private IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		if (state == STATE_DONE) 
			return null;
		if (state == STATE_INIT) {
			initialize();
			if (state == STATE_DONE) 
				return null;
			if (info == null) {
				state = STATE_NOT_FOUND;
				return null;
			}
			evaluatedTypes = new IEvaluatedType[info.assignments.length];
			state = STATE_ASSIGNMENT_SUBGOAL_0;
		}
		if (state > STATE_ASSIGNMENT_SUBGOAL_0 && previousGoal != null)
			storePreviousSubgoalResult(previousGoal, (IEvaluatedType) previousResult);
		if (state >= STATE_ASSIGNMENT_SUBGOAL_0 && state < STATE_ASSIGNMENT_SUBGOAL_0 + info.assignments.length)
			return generateNextSubgoal();
		return null;
	}

	private void initialize() {
		IContext context = goal.getContext();
		ModuleDeclaration rootNode = ((ISourceModuleContext) context).getRootNode();
		VariableReference expression = (VariableReference) ((ExpressionTypeGoal) goal).getExpression();
		String varName = expression.getName().trim();
		if (expression.getVariableKind() instanceof VariableKind.Local) {
			if (context instanceof IArgumentsContext) {
				IArgumentsContext argumentsContext = (IArgumentsContext) context;
				IEvaluatedType argumentType = argumentsContext.getArgumentType(varName);
				if (argumentType != null) {
					result = argumentType;
					state = STATE_DONE;
					return;
				}
			}
		}
		info = RubyTypeInferencingUtils.findLocalVariable(rootNode, expression.sourceStart(), 
				varName);
	}
	
	private void storePreviousSubgoalResult(IGoal previousGoal, IEvaluatedType previousResult) {
		if (previousResult != null)
			evaluatedTypes[countOfEvaluatedTypes++] = previousResult;
	}

	private IGoal generateNextSubgoal() {
		IContext context = goal.getContext();
		IGoal subgoal = new ExpressionTypeGoal(context, info.assignments[state - STATE_ASSIGNMENT_SUBGOAL_0].getRight());
		state++;
		return subgoal;
	}

	public Object produceResult() {
		if (state == STATE_NOT_FOUND)
			return UnknownType.INSTANCE;
		else if (state == STATE_DONE)
			return result;
		else
			return RubyTypeInferencingUtils.combineTypes(evaluatedTypes);
	}

	public IGoal[] init() {
		IGoal goal = produceNextSubgoal(null, null); 
		if (goal != null)
			return new IGoal[] { goal };
		return IGoal.NO_GOALS; 
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		IGoal goal = produceNextSubgoal(subgoal, result);
		if (goal != null)
			return new IGoal[] { goal };
		return IGoal.NO_GOALS;
	}

}
