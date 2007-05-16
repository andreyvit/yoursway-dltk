package org.eclipse.dltk.ti.statistics;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;

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

	public void evaluatorInitialized(GoalEvaluator evaluator, IGoal subgoals,
			long time) {
		// TODO Auto-generated method stub

	}

	public void evaluatorProducedResult(GoalEvaluator evaluator, Object result,
			long time) {
		// TODO Auto-generated method stub

	}

	public void evaluatorReceivedResult(GoalEvaluator evaluator,
			IGoal finishedGoal, IGoal subgoals, long time) {
		// TODO Auto-generated method stub

	}

	public void goalEvaluatorAssigned(IGoal goal, GoalEvaluator evaluator) {
		// TODO Auto-generated method stub

	}

	public void goalStateChanged(IGoal goal, GoalState state, GoalState oldState) {
		// TODO Auto-generated method stub

	}

	public IGoal getRootRoal() {
		return rootRoal;
	}

}
