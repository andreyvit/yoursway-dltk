package org.eclipse.dltk.internal.debug.ui.actions;

public interface IScriptEvaluationListener {
	/**
	 * Notifies this listener that an evaluation has completed, with the
	 * given result.
	 * 
	 * @param result The result from the evaluation
	 * @see IEvaluationResult
	 */
	public void evaluationComplete(IScriptEvaluationResult result);
}
