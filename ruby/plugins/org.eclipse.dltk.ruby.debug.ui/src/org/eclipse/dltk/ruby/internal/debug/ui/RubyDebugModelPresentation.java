/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui;

import java.text.MessageFormat;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.ui.IEditorInput;

public class RubyDebugModelPresentation extends ScriptDebugModelPresentation {
	private static final String RUBY_EDITOR_ID = "org.eclipse.dltk.ruby.ui.editor.RubyEditor";

	private static final String EVAL_PATTERN = "({0}).to_s";
	private static final String CANNOT_EVALUATE = "Can't evaluate details.";

	public String getEditorId(IEditorInput input, Object element) {
		return RUBY_EDITOR_ID;
	}

	public void computeDetail(final IValue value,
			final IValueDetailListener listener) {

		IScriptThread thread = getEvaluationThread((IScriptDebugTarget) value
				.getDebugTarget());

		if (thread != null) {
			final String snippet = MessageFormat.format(EVAL_PATTERN,
					new Object[] { ((IScriptValue) value).getEvalName() });

			final IScriptEvaluationEngine engine = thread.getEvaluationEngine();
			engine.asyncEvaluate(snippet, null,
					new IScriptEvaluationListener() {
						public void evaluationComplete(
								IScriptEvaluationResult result) {
							if (result != null) {
								IScriptValue value = result.getValue();
								final String details = value == null ? CANNOT_EVALUATE
										: value.toString();
								listener.detailComputed(value, details);
							}
						}
					});
		} else {
			try {
				listener.detailComputed(value, value.getValueString());
			} catch (DebugException e) {
				RubyDebugUIPlugin.log(e);
			}
		}
	}
}
