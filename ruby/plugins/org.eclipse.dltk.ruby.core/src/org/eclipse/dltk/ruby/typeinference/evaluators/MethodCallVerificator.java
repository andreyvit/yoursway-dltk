package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinClass;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinMethod;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyMethodReference;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.goals.MethodCallVerificationGoal;
import org.eclipse.dltk.ti.goals.PossiblePosition;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodCallVerificator extends GoalEvaluator {

	private static final int INIT = 0;
	private static final int RECEIVER_WAIT = 1;

	private int state = INIT;
	private RubyMethodReference result = null;
	private IEvaluatedType receiverType;
	private PossiblePosition position;

	public MethodCallVerificator(IGoal goal) {
		super(goal);
	}

	private MethodCallVerificationGoal getTypedGoal() {
		return (MethodCallVerificationGoal) this.getGoal();
	}

	public IGoal[] init() {
		MethodCallVerificationGoal goal2 = getTypedGoal();
		position = goal2.getPosition();
		IResource resource = position.getResource();

		IModelElement element = DLTKCore.create(resource);
		if (element instanceof ISourceModule) {
			ModuleDeclaration decl = ASTUtils.getAST((ISourceModule) element);
			ASTNode node = position.getNode();
			if (node == null) {
				node = ASTUtils.findMinimalNode(decl, position.getOffset(),
						position.getOffset() + position.getLength() - 1);
			}
			if (node instanceof CallExpression) {
				receiverType = null;
				CallExpression call = (CallExpression) node;
				Statement receiver = call.getReceiver();
				if (receiver != null) {
					ExpressionTypeGoal rgoal = new ExpressionTypeGoal(
							new BasicContext((ISourceModule) element, decl),
							receiver);
					state = RECEIVER_WAIT;
					return new IGoal[] { rgoal };
				} else {
					receiverType = RubyTypeInferencingUtils.determineSelfClass(
							(ISourceModule) element, decl, node.sourceStart());
				}
			}
		}

		//				
		return null;
	}

	public Object produceResult() { // TODO: add partial results support
		if (!(receiverType instanceof RubyClassType))
			return null;

		MethodCallVerificationGoal goal2 = getTypedGoal();
		RubyClassType type = (RubyClassType) receiverType;
		String requiredKey = goal2.getGoal().getParentModelKey()
				+ MixinModel.SEPARATOR + goal2.getGoal().getName();
		RubyMixinClass rclass = RubyMixinModel.getInstance().createRubyClass(
				type);
		if (rclass != null) {
			RubyMixinMethod method = rclass
					.getMethod(goal2.getGoal().getName());
			if (method != null) {
				String key = method.getKey();
				if (key.equals(requiredKey)) {
					result = new RubyMethodReference(goal2.getGoal().getName(),
							goal2.getGoal().getParentModelKey(), position,
							RubyMethodReference.ACCURATE);
					if (position.getNode() instanceof CallExpression) {
						result.setNode((CallExpression) position.getNode());
					}
				}
			}
		}

		return result;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		if (this.state == RECEIVER_WAIT) {
			receiverType = (IEvaluatedType) result;
		}
		return null;
	}

}
