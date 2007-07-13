/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationCommand;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.ui.IEditorInput;

public class RubyDebugModelPresentation extends ScriptDebugModelPresentation {
	private static final String RUBY_EDITOR_ID = "org.eclipse.dltk.ruby.ui.editor.RubyEditor";

	private static final String CANNOT_EVALUATE = "Can't evaluate details.";

	private static final String TO_S = "({0}).to_s";

	public String getEditorId(IEditorInput input, Object element) {
		return RUBY_EDITOR_ID;
	}

	private String getValueString(IValue value) {
		try {
			return value.getValueString();
		} catch (DebugException e) {
			RubyDebugUIPlugin.log(e);
		}

		return value.toString();
	}

	public void computeDetail(final IValue value,
			final IValueDetailListener listener) {

		IScriptThread thread = getEvaluationThread((IScriptDebugTarget) value
				.getDebugTarget());

		if (thread != null) {
			final IScriptValue scriptValue = (IScriptValue) value;
			final IScriptEvaluationCommand command = scriptValue.sendMessage(
					TO_S, thread);

			command.asyncEvaluate(new IScriptEvaluationListener() {
				public void evaluationComplete(IScriptEvaluationResult result) {
					if (result != null) {
						final IScriptValue resultValue = result.getValue();
						if (resultValue != null) {
							listener.detailComputed(value,
									getValueString(resultValue));
						} else {
							listener.detailComputed(value, CANNOT_EVALUATE);
						}
					}
				}
			});
		} else {
			listener.detailComputed(value, getValueString(value));
		}
	}
}
