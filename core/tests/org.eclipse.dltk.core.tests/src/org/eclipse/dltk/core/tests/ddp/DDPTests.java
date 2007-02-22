package org.eclipse.dltk.core.tests.ddp;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ddp.GoalManager;
import org.eclipse.dltk.ddp.ICalculatedType;
import org.eclipse.dltk.ddp.IGoal;
import org.eclipse.dltk.ddp.IGoalsEvaluator;
import org.eclipse.dltk.ddp.TypeGoal;

public class DDPTests extends TestCase {
	
	class MyNum implements ICalculatedType {
		
		public String toString() {
			return "MyNum";
		}
		
	}
	
	public void testSimple () throws Exception {
		// y = 2; x = y; x?
		final Expression x = new SimpleReference(0,0,"x");
		final Expression y = new SimpleReference(0,0,"y");
		final Expression num = new NumericLiteral(new DLTKToken());
		
		final GoalManager man = new GoalManager();
		
		IGoalsEvaluator evaluator = new IGoalsEvaluator() {

			public boolean updateGoal(IGoal goal) {
				TypeGoal g = (TypeGoal)goal;
				
				Collection subgoals = g.getUpdatedSubgoals();
				for (Iterator iterator = subgoals.iterator(); iterator
						.hasNext();) {
					TypeGoal subgoal = (TypeGoal) iterator.next();
					g.setAnswer(subgoal.getAnswer());
					return true;
				}
				
				if (g.getExpr() == x) {
					IGoal ng = new TypeGoal(y, null, goal);
					man.postGoal(ng);
					g.getSubgoals().add(ng);
				}
				if (g.getExpr() == y) {
					IGoal ng = new TypeGoal(num, null, goal);
					man.postGoal(ng);
					g.getSubgoals().add(ng);
				}
				if (g.getExpr() == num) {
					g.setAnswer(new MyNum());
					return true;
				}
				return false;
			}
			
		};
		
		TypeGoal rootGoal = new TypeGoal(x, null, null);
		man.inferType(rootGoal, evaluator);
		
		System.out.println(rootGoal.toString());
		
		assertTrue (rootGoal.getAnswer() instanceof MyNum);		
	}
	
	
	public void testCycles () throws Exception {
		/*
		 * z = 2
		 * y = z
		 * z = y
		 * x = z
		 * x = y
		 */
		final Expression x = new SimpleReference(0,0,"x");
		final Expression y = new SimpleReference(0,0,"y");
		final Expression z = new SimpleReference(0,0,"z");
		final Expression num = new NumericLiteral(new DLTKToken());
		
		final GoalManager man = new GoalManager();
		
		IGoalsEvaluator evaluator = new IGoalsEvaluator() {
			
			private Expression[] getAssgns (Expression e) {
				if (e == x) 
					return new Expression[] {z, y};
				if (e == y) 
					return new Expression[] {z};
				if (e == z) 
					return new Expression[] {num, y};
				return new Expression[0];
			}
			
			private boolean invalidate(TypeGoal goal) {
				ICalculatedType oldAns = goal.getAnswer();
				Collection subgoals = goal.getUpdatedSubgoals();
				for (Iterator iterator = subgoals.iterator(); iterator
						.hasNext();) {
					TypeGoal subgoal = (TypeGoal) iterator.next();										
					ICalculatedType newAns = subgoal.getAnswer();
					if (newAns != null && newAns != oldAns) {					
						goal.setAnswer(newAns);
						return true;
					}
				}
				return false;
			}

			public boolean updateGoal(IGoal goal) {
				TypeGoal g = (TypeGoal)goal;
				
				if (g.getExpr() == num) {
					g.setAnswer(new MyNum());
					return true;
				}
				
				Expression[] ex = getAssgns(g.getExpr());
				
				for (int i = 0; i < ex.length; i++) {
					IGoal ng = new TypeGoal(ex[i], null, goal);
					man.postGoal(ng);
					//g.getSubgoals().add(ng);
				}				
				
				return false;
			}
			
		};
		
		TypeGoal rootGoal = new TypeGoal(x, null, null);
		man.inferType(rootGoal, evaluator);
		
		System.out.println(rootGoal.toString());
		
		assertTrue (rootGoal.getAnswer() instanceof MyNum);		
	}
	
}
