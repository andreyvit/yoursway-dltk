package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.ddp.ISourceModuleContext;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.evaluation.types.UnknownType;

public class ConstantReferenceEvaluator extends GoalEvaluator {

	private final int STATE_NOT_FOUND = -1;

	private int state = 0;

	// private IEvaluatedType[] evaluatedTypes;
	//	
	// private int countOfEvaluatedTypes;

	private IEvaluatedType result;

	public ConstantReferenceEvaluator(IGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
		if (state == 0) {
			initialize();
			state = 1;
		}
		return null;
	}

	private void initialize() {
		ISourceModuleContext typedContext = getTypedContext();
		ConstantTypeGoal typedGoal = getTypedGoal();
		result = RubyTypeInferencingUtils.evaluateConstantType(typedContext.getSourceModule(),
				typedContext.getRootNode(), typedGoal.getOffset(), typedGoal.getName());
		if (result == null)
			result = UnknownType.INSTANCE;
	}

	private ConstantTypeGoal getTypedGoal() {
		return (ConstantTypeGoal) goal;
	}

	private ISourceModuleContext getTypedContext() {
		return (ISourceModuleContext) goal.getContext();
	}

	public IEvaluatedType produceType() {
		if (state == 0) {
			initialize();
			state = 1;
		}
		return result; // RubyTypeInferencingUtils.combineTypes(evaluatedTypes);
	}

}
