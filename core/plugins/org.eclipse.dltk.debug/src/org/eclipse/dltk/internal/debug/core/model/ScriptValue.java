/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.text.MessageFormat;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationCommand;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.internal.debug.core.eval.ScriptEvaluationCommand;

public class ScriptValue extends ScriptDebugElement implements IScriptValue {

	private final IScriptVariable variable;

	protected ScriptValue(IScriptVariable variable) {
		this.variable = variable;
	}

	public String getReferenceTypeName() throws DebugException {
		return getType().getName();
	}

	public String getValueString() throws DebugException {
		String value = variable.getValueString();

		if (value == null || value.length() == 0) {
			IScriptType type = getType();
			if (!type.isAtomic()) {
				StringBuffer sb = new StringBuffer();
				sb.append(type.getName());
				String id = getInstanceId();
				if (id != null) {
					sb.append(" (id = " + id + ")"); // TODO add constant
				}
				return sb.toString();
			}
		}

		return value;
	}

	public IVariable[] getVariables() throws DebugException {
		return variable.getChildren();
	}

	public boolean hasVariables() throws DebugException {
		return variable.hasChildren();
	}

	public boolean isAllocated() throws DebugException {
		return true;
	}

	public String toString() {
		return variable.getValueString();
	}

	public IDebugTarget getDebugTarget() {
		return variable.getDebugTarget();
	}

	public String getInstanceId() {
		return variable.getId();
	}

	public IScriptType getType() {
		return variable.getType();
	}

	public IScriptEvaluationCommand sendMessage(String messageTemplate,
			IScriptThread thread) {
		IScriptEvaluationEngine engine = thread.getEvaluationEngine();

		final String snippet = MessageFormat.format(messageTemplate,
				new Object[] { variable.getEvalName() });

		return new ScriptEvaluationCommand(engine, snippet, variable
				.getStackFrame());
	}
}
