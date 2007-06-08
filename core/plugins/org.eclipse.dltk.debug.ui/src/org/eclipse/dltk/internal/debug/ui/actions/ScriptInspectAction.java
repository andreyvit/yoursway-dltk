package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.eval.InsepctEvaluatedScriptExpression;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.swt.widgets.Display;

/**
 * Places the result of an evaluation in the debug expression view.
 */
public class ScriptInspectAction extends ScriptEvaluationAction {
	protected void displayResult(final IScriptEvaluationResult result) {
		final Display display = DLTKDebugUIPlugin.getStandardDisplay();
		display.asyncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed()) {
					showExpressionView();
					InsepctEvaluatedScriptExpression expression = new InsepctEvaluatedScriptExpression(
							result);
					DebugPlugin.getDefault().getExpressionManager()
							.addExpression(expression);
				}
				evaluationCleanup();
			}
		});
	}
}
