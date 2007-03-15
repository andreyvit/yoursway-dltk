package org.eclipse.dltk.ruby.typeinference;

import java.util.List;

import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.ast.SelfReference;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodCallTypeEvaluator extends GoalEvaluator {

	private final static int STATE_INIT = 0;

	private final static int STATE_WAITING_RECEIVER = 1;

	private final static int STATE_GOT_RECEIVER = 2;

	private final static int STATE_WAITING_ARGUMENT_0 = 3;
	
	private final static int STATE_WAITING_ARGUMENT_LAST = 9999;

	private final static int STATE_ARGS_DONE = 10000;

	private final static int STATE_WAITING_METHOD = 10001;

	private final static int STATE_UNKNOWN = -1;

	private final static int STATE_DONE = -2;

	private int state = STATE_INIT;

	private IEvaluatedType receiverType;

	private IEvaluatedType[] arguments;

	private IEvaluatedType result;

	public MethodCallTypeEvaluator(ExpressionTypeGoal goal) {
		super(goal);
	}

	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		if (state == STATE_INIT) {
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			CallExpression expression = (CallExpression) typedGoal.getExpression();
			Statement receiver = expression.getReceiver();
			if (receiver == null || receiver instanceof SelfReference) {
				// handling SelfReference here just for simplicity, could be
				// left to the TI engine as well
				receiverType = RubyTypeInferencingUtils.determineSelfClass(goal.getContext(),
						expression.sourceStart());
				state = STATE_GOT_RECEIVER;
			} else {
				state = STATE_WAITING_RECEIVER;
				return new ExpressionTypeGoal(goal.getContext(), receiver);
			}
		}
		if (state == STATE_WAITING_RECEIVER) {
			receiverType = (IEvaluatedType) previousResult;
			if (receiverType == null) {
				state = STATE_UNKNOWN;
				return null;
			}
			state = STATE_GOT_RECEIVER;
		}
		if (state == STATE_GOT_RECEIVER) {
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			CallExpression expression = (CallExpression) typedGoal.getExpression();
			List arguments = expression.getArgs().getExpressions();
			this.arguments = new IEvaluatedType[arguments.size()];
		}
		if (state >= STATE_WAITING_ARGUMENT_0 && state <= STATE_WAITING_ARGUMENT_LAST) {
			arguments[state - STATE_WAITING_ARGUMENT_0] = (IEvaluatedType) previousResult;
		}
		if (state == STATE_GOT_RECEIVER || state >= STATE_WAITING_ARGUMENT_0
				&& state <= STATE_WAITING_ARGUMENT_LAST) {
			int nextArg = (state == STATE_GOT_RECEIVER ? 0 : state - STATE_WAITING_ARGUMENT_0 + 1);
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			CallExpression expression = (CallExpression) typedGoal.getExpression();
			List arguments = expression.getArgs().getExpressions();
			if (nextArg < arguments.size()) {
				state = STATE_WAITING_ARGUMENT_0 + nextArg;
				return new ExpressionTypeGoal(goal.getContext(), (Statement) arguments.get(nextArg));
			} else {
				state = STATE_ARGS_DONE;
			}
		}
		if (state == STATE_ARGS_DONE) {
			ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
			CallExpression expression = (CallExpression) typedGoal.getExpression();
			state = STATE_WAITING_METHOD;
			return new MethodReturnTypeGoal(new InstanceContext((ISourceModuleContext) goal.getContext(),
					receiverType), expression.getName(), arguments);
		}
		if (state == STATE_WAITING_METHOD) {
			result = (IEvaluatedType) previousResult;
			state = STATE_DONE;
		}
		return null;
	}

	public IEvaluatedType produceResult() {
		if (state == STATE_UNKNOWN)
			return null;
		else
			return result;
	}

}
