/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.actions;

import java.text.MessageFormat;

import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.swt.widgets.Display;

public abstract class ScriptDisplayAction extends ScriptEvaluationAction {
	public static String trimDisplayResult(String result) {
		int max = DebugUITools.getPreferenceStore().getInt(
				IDebugUIConstants.PREF_MAX_DETAIL_LENGTH);
		if (max > 0 && result.length() > max) {
			result = result.substring(0, max) + "..."; //$NON-NLS-1$
		}
		return result;
	}

	protected void displayResult(final IScriptEvaluationResult result) {
		// Errors
		if (result.hasErrors()) {
			final Display display = DLTKDebugUIPlugin.getStandardDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					if (display.isDisposed()) {
						return;
					}

					reportErrors(result);
					evaluationCleanup();
				}
			});
			return;
		}

		final String snippet = result.getSnippet();
		IScriptValue resultValue = result.getValue();

		final String typeName = resultValue.getType().getName();

		IDebugModelPresentation presentation = getDebugModelPresentation(result
				.getThread().getModelIdentifier());
		presentation.computeDetail(resultValue, new IValueDetailListener() {
			public void detailComputed(IValue value, String result) {
				displayStringResult(snippet, MessageFormat.format("({0}) {1}",
						new Object[] { typeName, trimDisplayResult(result) }));
			}
		});

		presentation.dispose();
		// displayStringResult(snippet, getExceptionMessage(x));
	}

	// Real display of results
	protected abstract void displayStringResult(final String snippet,
			final String resultString);
}
