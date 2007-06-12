/**
 *
 */
package org.eclipse.dltk.ti.statistics;

public class GoalEvaluationStep {
	public final static int INIT = 0;
	public final static int DEFAULT = 1;
	public final static int RESULT = 2;

	private final int kind;
	private long time;
	private GoalEvaluationStatistics[] subgoalsStats;
	private Object result;

	public GoalEvaluationStep(int kind) {
		super();
		this.kind = kind;
	}

	public GoalEvaluationStep(int kind, long time,
			GoalEvaluationStatistics[] subgoalsStats, Object result) {
		super();
		this.kind = kind;
		this.time = time;
		this.subgoalsStats = subgoalsStats;
		this.result = result;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public GoalEvaluationStatistics[] getSubgoalsStats() {
		return subgoalsStats;
	}

	public void setSubgoalsStats(GoalEvaluationStatistics[] subgoalsStats) {
		this.subgoalsStats = subgoalsStats;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getKind() {
		return kind;
	}

}