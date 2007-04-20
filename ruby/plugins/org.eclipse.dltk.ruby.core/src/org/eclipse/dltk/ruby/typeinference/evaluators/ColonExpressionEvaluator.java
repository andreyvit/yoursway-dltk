package org.eclipse.dltk.ruby.typeinference.evaluators;

import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.ruby.ast.RubyColonExpression;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
import org.eclipse.dltk.ruby.typeinference.ConstantTypeGoal;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyEvaluatorFactory;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencer;
import org.eclipse.dltk.ruby.typeinference.goals.ColonExpressionGoal;
import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.ClassType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ColonExpressionEvaluator extends GoalEvaluator {

	private AbstractTypeGoal helperGoal = null;
	
	private IEvaluatedType helperResult = null;
	
	public ColonExpressionEvaluator(IGoal goal) {
		super(goal);
	}
	
	private ColonExpressionGoal getTypedGoal () {
		return (ColonExpressionGoal)  RubyEvaluatorFactory.translateGoal(this.goal);
	}

	public Object produceResult() { 		
		RubyColonExpression expr = getTypedGoal().getColonExpression();
		Statement left = expr.getLeft();
		
		if (left != null) {
			if (helperResult instanceof ClassType) { //TODO: check existance of the new key
				ClassType classType = (ClassType) helperResult;
				String modelKey = classType.getModelKey();
				modelKey += IMixinRequestor.MIXIN_NAME_SEPARATOR + expr.getName();
				IMixinElement mixinElement = RubyMixinModel.getRawInstance().get(modelKey);
				if (mixinElement != null)
					return new RubyClassType(modelKey);
				return null;
			}
		} else { 
			IMixinElement mixinElement = RubyMixinModel.getRawInstance().get(expr.getName());
			if (mixinElement != null)
				return new RubyClassType(mixinElement.getKey());
		}
		
		return null;
	}

	public IGoal[] init() {
		RubyColonExpression expr = getTypedGoal().getColonExpression();
		
		Statement left = expr.getLeft();
		
		if (left != null) {
			helperGoal =  new ExpressionTypeGoal(getGoal().getContext(), left);
			return new IGoal[] {helperGoal};
		} 
		
		return IGoal.NO_GOALS;
	}

	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		this.helperResult = (IEvaluatedType) result;
		return IGoal.NO_GOALS;
	}

}
