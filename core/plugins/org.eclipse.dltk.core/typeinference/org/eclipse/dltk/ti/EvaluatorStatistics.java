package org.eclipse.dltk.ti;

public class EvaluatorStatistics {

	private int totalSubGoalsRequested;
	private long timeRunning;
	private int subGoalsDone;
	private int subGoalsDoneSuccessful;
	public EvaluatorStatistics(int totalSubGoalsRequested, long timeRunning,
			int subGoalsDone, int subGoalsDoneSuccessful) {
		super();
		this.totalSubGoalsRequested = totalSubGoalsRequested;
		this.timeRunning = timeRunning;
		this.subGoalsDone = subGoalsDone;
		this.subGoalsDoneSuccessful = subGoalsDoneSuccessful;
	}
	public int getTotalSubGoalsRequested() {
		return totalSubGoalsRequested;
	}
	public long getTimeRunning() {
		return timeRunning;
	}
	public int getSubGoalsDone() {
		return subGoalsDone;
	}
	public int getSubGoalsDoneSuccessful() {
		return subGoalsDoneSuccessful;
	}

	

}
