/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.ti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.statistics.IEvaluationStatisticsRequestor;

/**
 * Main working class for type inference. Purpose of this class is simple:
 * evaluate goals and manage their dependencies of subgoals. Also this class
 * allows pruning: before evaluating every goal(except root goal) could be
 * pruned by provided prunner.
 *
 * This class isn't thread safe.
 */
public class GoalEngine {

	private final IGoalEvaluatorFactory evaluatorFactory;

	private final LinkedList workingQueue = new LinkedList();
	private final HashMap goalStates = new HashMap();
	private final HashMap evaluatorStates = new HashMap();

	private IEvaluationStatisticsRequestor statisticsRequestor;

	private class EvaluatorState {
		public long timeCreated;
		public int totalSubgoals;
		public int successfulSubgoals;
		public int subgoalsLeft;
		public List subgoals = new ArrayList();

		public EvaluatorState(int subgoalsLeft) {
			this.subgoalsLeft = subgoalsLeft;
			this.timeCreated = System.currentTimeMillis();
			totalSubgoals = subgoalsLeft;
		}

	}

	private class WorkingPair {
		private IGoal goal;
		private GoalEvaluator creator;

		public WorkingPair(IGoal goal, GoalEvaluator parent) {
			this.goal = goal;
			this.creator = parent;
		}

	}

	private class GoalEvaluationState {
		public GoalEvaluator creator;
		public GoalState state;
		public Object result;
	}

	public GoalEngine(IGoalEvaluatorFactory evaluatorFactory) {
		this.evaluatorFactory = evaluatorFactory;
	}

	private void storeGoal(IGoal goal, GoalState state, Object result,
			GoalEvaluator creator) {
		GoalEvaluationState es = new GoalEvaluationState();
		es.result = result;
		es.state = state;
		es.creator = creator;
		goalStates.put(goal, es);
		this.statisticsRequestor.goalStateChanged(goal, state, null); // TODO:
																		// add
																		// old
																		// state
	}

	private EvaluatorState getEvaluatorState(GoalEvaluator evaluator) {
		return (EvaluatorState) evaluatorStates.get(evaluator);
	}

	private void putEvaluatorState(GoalEvaluator evaluator, EvaluatorState state) {
		evaluatorStates.put(evaluator, state);
	}

	private void notifyEvaluator(GoalEvaluator evaluator, IGoal subGoal) {
		long t = 0;

		GoalEvaluationState subGoalState = (GoalEvaluationState) goalStates
				.get(subGoal);
		Object result = subGoalState.result;
		GoalState state = subGoalState.state;

		if (state == GoalState.WAITING) {
			state = GoalState.RECURSIVE;
		}

		t = System.currentTimeMillis();
		IGoal[] newGoals = evaluator.subGoalDone(subGoal, result, state);
		statisticsRequestor.evaluatorReceivedResult(evaluator, subGoal,
				newGoals, System.currentTimeMillis() - t);
		if (newGoals == null) {
			newGoals = IGoal.NO_GOALS;
		}
		for (int i = 0; i < newGoals.length; i++) {
			workingQueue.add(new WorkingPair(newGoals[i], evaluator));
		}
		EvaluatorState ev = getEvaluatorState(evaluator);
		ev.subgoalsLeft--;
		ev.subgoalsLeft += newGoals.length;
		ev.totalSubgoals += newGoals.length;
		ev.subgoals.addAll(Arrays.asList(newGoals));
		if (state == GoalState.DONE && result != null) {
			ev.successfulSubgoals++;
		}
		if (ev.subgoalsLeft == 0) {
			t = System.currentTimeMillis();
			Object newRes = evaluator.produceResult();
			statisticsRequestor.evaluatorProducedResult(evaluator, result,
					System.currentTimeMillis() - t);
			GoalEvaluationState st = (GoalEvaluationState) goalStates
					.get(evaluator.getGoal());
			Assert.isNotNull(st);
			st.state = GoalState.DONE;
			st.result = newRes;
			if (st.creator != null) {
				notifyEvaluator(st.creator, evaluator.getGoal());
			}
		}
	}

	private EvaluatorStatistics getEvaluatorStatistics(GoalEvaluator evaluator) {
		EvaluatorState ev = getEvaluatorState(evaluator);
		if (ev == null) {
			return null;
		}
		long currentTime = System.currentTimeMillis();
		return new EvaluatorStatistics(ev.totalSubgoals, currentTime
				- ev.timeCreated, ev.totalSubgoals - ev.subgoalsLeft,
				ev.successfulSubgoals);
	}

	public Object evaluateGoal(IGoal rootGoal, IPruner pruner) {
		return evaluateGoal(rootGoal, pruner, null);
	}

	public Object evaluateGoal(IGoal rootGoal, IPruner pruner,
			IEvaluationStatisticsRequestor statisticsRequestor) {
		long time = 0;

		if (statisticsRequestor == null) {
			statisticsRequestor = new IEvaluationStatisticsRequestor() {
				public void evaluationStarted(IGoal rootGoal) {
				}

				public void evaluatorInitialized(GoalEvaluator evaluator,
						IGoal[] subgoals, long time) {
				}

				public void evaluatorProducedResult(GoalEvaluator evaluator,
						Object result, long time) {
				}

				public void evaluatorReceivedResult(GoalEvaluator evaluator,
						IGoal finishedGoal, IGoal[] newSubgoals, long time) {
				}

				public void goalEvaluatorAssigned(IGoal goal,
						GoalEvaluator evaluator) {
				}

				public void goalStateChanged(IGoal goal, GoalState state,
						GoalState oldState) {
				}
			};
		}
		this.statisticsRequestor = statisticsRequestor;
		reset();
		if (pruner != null) {
			pruner.init();
		}
		workingQueue.add(new WorkingPair(rootGoal, null));
		statisticsRequestor.evaluationStarted(rootGoal);
		while (!workingQueue.isEmpty()) {
			WorkingPair pair = (WorkingPair) workingQueue.getFirst();
			workingQueue.removeFirst();
			GoalEvaluationState state = (GoalEvaluationState) goalStates
					.get(pair.goal);
			if (state != null && pair.creator != null) {
				notifyEvaluator(pair.creator, pair.goal);
			} else {
				boolean prune = false;
				if (pruner != null && pair.creator != null) {
					prune = pruner.prune(pair.goal,
							getEvaluatorStatistics(pair.creator));
				}
				if (prune) {
					storeGoal(pair.goal, GoalState.PRUNED, null, pair.creator);
					notifyEvaluator(pair.creator, pair.goal);
				} else {
					GoalEvaluator evaluator = evaluatorFactory
							.createEvaluator(pair.goal);
					Assert.isNotNull(evaluator);
					statisticsRequestor.goalEvaluatorAssigned(pair.goal,
							evaluator);
					time = System.currentTimeMillis();
					IGoal[] newGoals = evaluator.init();
					if (newGoals == null) {
						newGoals = IGoal.NO_GOALS;
					}
					statisticsRequestor.evaluatorInitialized(evaluator,
							newGoals, System.currentTimeMillis() - time);
					if (newGoals.length > 0) {
						for (int i = 0; i < newGoals.length; i++) {
							workingQueue.add(new WorkingPair(newGoals[i],
									evaluator));
						}
						EvaluatorState evaluatorState = new EvaluatorState(
								newGoals.length);
						evaluatorState.subgoals.add(Arrays.asList(newGoals));
						putEvaluatorState(evaluator, evaluatorState);
						storeGoal(pair.goal, GoalState.WAITING, null,
								pair.creator);
					} else {
						time = System.currentTimeMillis();
						Object result = evaluator.produceResult();
						statisticsRequestor.evaluatorProducedResult(evaluator,
								result, System.currentTimeMillis() - time);
						storeGoal(pair.goal, GoalState.DONE, result,
								pair.creator);
						if (pair.creator != null) {
							notifyEvaluator(pair.creator, pair.goal);
						}
					}
				}
			}
		}
		GoalEvaluationState s = (GoalEvaluationState) goalStates.get(rootGoal);

		Assert.isTrue(s.state == GoalState.DONE);
		return s.result;
	}

	private void reset() {
		workingQueue.clear();
		goalStates.clear();
		evaluatorStates.clear();
	}

}
