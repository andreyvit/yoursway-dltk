package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ddp.BasicContext;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.evaluation.types.UnknownType;

public class VariableReferenceEvaluator extends GoalEvaluator {
	
	private final int STATE_NOT_FOUND = -1;
	
	private int state = 0;
	
	private LocalVariableInfo info;
	
	private IEvaluatedType[] evaluatedTypes;
	
	private int countOfEvaluatedTypes;

	public VariableReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
		if (state == 0) {
			initialize();
			if (info == null) {
				state = STATE_NOT_FOUND;
				return null;
			}
			evaluatedTypes = new IEvaluatedType[info.assignments.length];
		}
		if (state > 0 && previousGoal != null)
			storePreviousSubgoalResult(previousGoal, previousResult);
		if (state < info.assignments.length)
			return generateNextSubgoal();
		return null;
	}

	private void initialize() {
		ModuleDeclaration rootNode = ((BasicContext) goal.getContext()).getRootNode();
		VariableReference expression = (VariableReference) ((ExpressionGoal) goal).getExpression();
		info = RubyTypeInferencingUtils.findLocalVariable(rootNode, expression.sourceStart(), 
				expression.getName());
	}
	
	private void storePreviousSubgoalResult(IGoal previousGoal, IEvaluatedType previousResult) {
		if (previousResult != null)
			evaluatedTypes[countOfEvaluatedTypes++] = previousResult;
	}

	private IGoal generateNextSubgoal() {
		BasicContext context = (BasicContext) goal.getContext();
		IGoal subgoal = new ExpressionGoal(context, info.assignments[state].getRight());
		state++;
		return subgoal;
	}

	public IEvaluatedType produceType() {
		if (state == STATE_NOT_FOUND)
			return UnknownType.INSTANCE;
		else
			return RubyTypeInferencingUtils.combineTypes(evaluatedTypes);
	}

}
