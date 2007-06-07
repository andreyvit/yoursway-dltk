package org.eclipse.dltk.internal.debug.ui.actions;

import java.text.MessageFormat;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Displays the result of an evaluation in the display view
 */
public abstract class ScriptDisplayAction extends CommonEvaluationAction {
	public static String trimDisplayResult(String result) {
		int max = DebugUITools.getPreferenceStore().getInt(
				IDebugUIConstants.PREF_MAX_DETAIL_LENGTH);
		if (max > 0 && result.length() > max) {
			result = result.substring(0, max) + "..."; //$NON-NLS-1$
		}
		return result;
	}

	protected void displayResult(final IScriptEvaluationResult evaluationResult) {
		// Errors
		if (evaluationResult.hasErrors()) {
			final Display display = DLTKDebugUIPlugin.getStandardDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					if (display.isDisposed()) {
						return;
					}

					reportErrors(evaluationResult);
					evaluationCleanup();
				}
			});
			return;
		}

		final String snippet = evaluationResult.getSnippet();
		IScriptValue resultValue = evaluationResult.getValue();


		try {
			final String resultString = resultValue.getValueString();
			getDebugModelPresentation().computeDetail(resultValue,
					new IValueDetailListener() {
						public void detailComputed(IValue value, String result) {
							displayStringResult(snippet, MessageFormat.format(
									"{0} {1}", new Object[] { resultString,
											trimDisplayResult(result) }));
						}
					});
		} catch (DebugException x) {
			displayStringResult(snippet, getExceptionMessage(x));
		}
	}

	// Real display of results
	protected abstract void displayStringResult(final String snippet,
			final String resultString);
}
