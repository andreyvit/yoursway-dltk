package org.eclipse.dltk.ti.statistics;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

/**
 * Records all evaluation tree including evaluation times
 *
 */
public class GoalEvalutationRecorder implements IEvaluationStatisticsRequestor {

	private IGoal rootRoal;
	private Map goalStats;

	public void evaluationStarted(IGoal rootGoal) {
		reset();
		this.rootRoal = rootGoal;
		this.goalStats.put(rootGoal, new GoalEvaluationStatistics(rootGoal));
	}

	private void reset() {
		this.rootRoal = null;
		this.goalStats = new HashMap();
	}

	private GoalEvaluationStatistics addGoalStatistics(
			GoalEvaluationStatistics parent, IGoal g) {
		GoalEvaluationStatistics s = new GoalEvaluationStatistics(g);
		s.setParentStat(parent);
		goalStats.put(g, s);
		return s;
	}

	private GoalEvaluationStatistics[] createEmptyGoalStatistics(
			GoalEvaluationStatistics parent, IGoal[] subgoals) {
		GoalEvaluationStatistics[] r = new GoalEvaluationStatistics[subgoals.length];
		for (int i = 0; i < subgoals.length; i++) {
			r[i] = addGoalStatistics(parent, subgoals[i]);
		}
		return r;
	}

	public void evaluatorInitialized(GoalEvaluator evaluator, IGoal[] subgoals,
			long time) {
		appendStep(evaluator, subgoals, null, time, GoalEvaluationStep.INIT);
	}

	public void evaluatorProducedResult(GoalEvaluator evaluator, Object result,
			long time) {
		GoalEvaluationStatistics s = appendStep(evaluator, null, result, time,
				GoalEvaluationStep.RESULT);
		if (s != null) {
			s.setTimeEnd(System.currentTimeMillis());
		}
	}

	public void evaluatorReceivedResult(GoalEvaluator evaluator,
			IGoal finishedGoal, IGoal[] subgoals, long time) {
		appendStep(evaluator, subgoals, null, time, GoalEvaluationStep.DEFAULT);
	}

	private GoalEvaluationStatistics appendStep(GoalEvaluator evaluator,
			IGoal[] subgoals, Object result, long time, int kind) {
		IGoal goal = evaluator.getGoal();
		GoalEvaluationStatistics stat = (GoalEvaluationStatistics) this.goalStats
				.get(goal);
		if (stat != null) {
			GoalEvaluationStep step = new GoalEvaluationStep(kind);
			step.setTime(time);
			if (subgoals != null)
				step
						.setSubgoalsStats(createEmptyGoalStatistics(stat,
								subgoals));
			step.setResult(result);
			stat.getSteps().add(step);
			return stat;
		} else {
			System.err.println("Unknown goal: " + goal);
		}
		return null;
	}

	public void goalEvaluatorAssigned(IGoal goal, GoalEvaluator evaluator) {
		GoalEvaluationStatistics stat = (GoalEvaluationStatistics) this.goalStats
				.get(goal);
		if (stat != null) {
			stat.setEvaluator(evaluator);
		} else {
			System.err.println("Unknown goal: " + goal);
		}
	}

	public void goalStateChanged(IGoal goal, GoalState state, GoalState oldState) {
		GoalEvaluationStatistics stat = (GoalEvaluationStatistics) this.goalStats
				.get(goal);
		if (stat != null) {
			stat.setState(state);
		} else {
			System.err.println("Unknown goal: " + goal);
		}
	}

	public IGoal getRootRoal() {
		return rootRoal;
	}

}
