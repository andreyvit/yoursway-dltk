package org.eclipse.dltk.ti.goals;

import java.util.List;
import java.util.Stack;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.ti.BasicContext;

public abstract class SearchBasedGoalEvaluator extends GoalEvaluator {
	private Stack possiblePositions;
	private List references;
	
	private SearchRequestor requestor = new SearchRequestor() {

		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			PossiblePosition pos = new PossiblePosition(match.getResource(),
					match.getOffset(), match.getLength());
			possiblePositions.add(pos);
			references = null;
		}
		
	};
		
	public SearchBasedGoalEvaluator(IGoal goal) {
		super(goal);
		possiblePositions = null;
	}

	private void initialize () throws CoreException {
		possiblePositions = new Stack();
		
		FieldReferencesGoal goal = (FieldReferencesGoal)getGoal();
		BasicContext basicContext = (BasicContext) goal.getContext();
		IDLTKProject project = basicContext.getSourceModule().getScriptProject();
		IDLTKSearchScope scope = SearchEngine.createSearchScope(new IModelElement[] {project});
		SearchPattern pattern = createSearchPattern();
		SearchEngine engine = new SearchEngine();
	
		engine.search(pattern, new SearchParticipant[] { SearchEngine
				.getDefaultSearchParticipant() }, scope, requestor, null);
	}
	
	public IGoal produceNextSubgoal(IGoal previousGoal, Object previousResult) {
		if (possiblePositions == null) {
			try {
				initialize();
			} catch (CoreException e) {
				return null;
			}
		}
		
		if (previousResult != null && previousResult instanceof ItemReference) {
			references.add(previousResult);
		}
		
		if (possiblePositions.isEmpty())
			return null;
		
		PossiblePosition pos = (PossiblePosition)possiblePositions.pop();

		
		return createVerificationGoal(pos);
	}

	public Object produceResult() {
		return references.toArray(new ItemReference[references.size()]);
	}
	
	protected abstract SearchPattern createSearchPattern ();
	protected abstract IGoal createVerificationGoal (PossiblePosition pos);

}
