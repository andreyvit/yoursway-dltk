package org.eclipse.dltk.core.tests.ddp;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.GoalEvaluator;
import org.eclipse.dltk.ddp.TypeInferencer;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.ddp.IGoalEvaluatorFactory;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class DDPTests extends TestCase {

	private static final class FixedAnswerGoalEvaluator extends GoalEvaluator {
		private final IEvaluatedType answer;

		private FixedAnswerGoalEvaluator(IGoal goal, IEvaluatedType answer) {
			super(goal);
			this.answer = answer;
		}

		public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
			return null;
		}

		public IEvaluatedType produceType() {
			return answer;
		}
	}

	private static final class SingleDependentGoalEvaluator extends GoalEvaluator {
		private final IEvaluatedType answer;

		private final IGoal[] dependents;

		private int state = 0;

		private SingleDependentGoalEvaluator(IGoal goal, IGoal dependent, IEvaluatedType answer) {
			super(goal);
			this.dependents = new IGoal[] { dependent };
			this.answer = answer;
		}

		private SingleDependentGoalEvaluator(IGoal goal, IGoal[] dependents, IEvaluatedType answer) {
			super(goal);
			this.dependents = dependents;
			this.answer = answer;
		}

		public IGoal produceNextSubgoal(IGoal previousGoal, IEvaluatedType previousResult) {
			if (state > 0)
				assertTrue(previousResult instanceof MyNum);
			if (state < dependents.length) {
				return dependents[state++];
			}
			return null;
		}

		public IEvaluatedType produceType() {
			return answer;
		}
	}

	class MyNum implements IEvaluatedType {

		public String toString() {
			return "MyNum";
		}

		public String getTypeName() {
			return "MyNum";
		}

	}

	public void testSimple() throws Exception {
		// y = 2; x = y; x?
		final Expression x = new SimpleReference(0, 0, "x");
		final Expression y = new SimpleReference(0, 0, "y");
		final Expression num = new NumericLiteral(new DLTKToken());

		IGoalEvaluatorFactory factory = new IGoalEvaluatorFactory() {

			public GoalEvaluator createEvaluator(IGoal goal) {
				if (goal instanceof ExpressionGoal) {
					ExpressionGoal egoal = (ExpressionGoal) goal;
					Statement expr = egoal.getExpression();
					if (expr == x)
						return new SingleDependentGoalEvaluator(goal, new ExpressionGoal(null, y),
								new MyNum());
					if (expr == y)
						return new SingleDependentGoalEvaluator(goal, new ExpressionGoal(null, num),
								new MyNum());
					if (expr == num)
						return new FixedAnswerGoalEvaluator(goal, new MyNum());
				}
				return null;
			}

			public IGoal translateGoal(IGoal goal) {
				return goal;
			}

		};

		final TypeInferencer man = new TypeInferencer(factory);

		ExpressionGoal rootGoal = new ExpressionGoal(null, x);
		IEvaluatedType answer = man.evaluateGoal(rootGoal, 0);

		assertTrue(answer instanceof MyNum);
	}

	public void testCycles() throws Exception {
		final Expression x = new SimpleReference(0, 0, "x");
		final Expression y = new SimpleReference(0, 0, "y");
		final Expression z = new SimpleReference(0, 0, "z");
		final Expression num = new NumericLiteral(new DLTKToken());

		IGoalEvaluatorFactory factory = new IGoalEvaluatorFactory() {

			public GoalEvaluator createEvaluator(IGoal goal) {
				if (goal instanceof ExpressionGoal) {
					ExpressionGoal egoal = (ExpressionGoal) goal;
					Statement expr = egoal.getExpression();
					if (expr == x)
						return new SingleDependentGoalEvaluator(goal, new IGoal[] {
								new ExpressionGoal(null, y), new ExpressionGoal(null, z) }, new MyNum());
					if (expr == y)
						return new SingleDependentGoalEvaluator(goal,
								new IGoal[] { new ExpressionGoal(null, z) }, new MyNum());
					if (expr == z)
						return new SingleDependentGoalEvaluator(goal, new IGoal[] {
								new ExpressionGoal(null, num), new ExpressionGoal(null, y) }, new MyNum());
					if (expr == num)
						return new FixedAnswerGoalEvaluator(goal, new MyNum());
				}
				return null;
			}

			public IGoal translateGoal(IGoal goal) {
				return goal;
			}

		};

		final TypeInferencer man = new TypeInferencer(factory);

		ExpressionGoal rootGoal = new ExpressionGoal(null, x);
		IEvaluatedType answer = man.evaluateGoal(rootGoal, 0);

		assertTrue(answer instanceof MyNum);
	}

}
