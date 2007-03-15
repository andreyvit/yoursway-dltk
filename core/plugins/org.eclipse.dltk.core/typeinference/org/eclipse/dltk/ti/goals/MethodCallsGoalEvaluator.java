package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchPattern;

public class MethodCallsGoalEvaluator extends SearchBasedGoalEvaluator {

	public MethodCallsGoalEvaluator(IGoal goal) {
		super(goal);
	}

	protected SearchPattern createSearchPattern() {
		MethodCallsGoal goal = (MethodCallsGoal) getGoal();
		String name = goal.getName();
		return SearchPattern.createPattern(name, IDLTKSearchConstants.METHOD,
				IDLTKSearchConstants.REFERENCES, SearchPattern.R_EXACT_MATCH);
	}

	protected IGoal createVerificationGoal(PossiblePosition pos) {
		MethodCallVerificationGoal g = new MethodCallVerificationGoal(
				this.getGoal().getContext(), (MethodCallsGoal) this
						.getGoal(), pos);
		return g;
	}

}
