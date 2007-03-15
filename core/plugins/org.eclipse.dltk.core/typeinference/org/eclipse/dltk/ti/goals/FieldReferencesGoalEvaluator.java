package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchPattern;

public class FieldReferencesGoalEvaluator extends SearchBasedGoalEvaluator {

	public FieldReferencesGoalEvaluator(IGoal goal) {
		super(goal);
	}

	protected SearchPattern createSearchPattern() {
		FieldReferencesGoal goal = (FieldReferencesGoal) getGoal();
		String name = goal.getName();
		return SearchPattern.createPattern(name, IDLTKSearchConstants.FIELD,
				IDLTKSearchConstants.REFERENCES, SearchPattern.R_EXACT_MATCH);
	}

	protected IGoal createVerificationGoal(PossiblePosition pos) {
		FieldPositionVerificationGoal g = new FieldPositionVerificationGoal(
				this.getGoal().getContext(), (FieldReferencesGoal) this
						.getGoal(), pos);
		return g;
	}

}
