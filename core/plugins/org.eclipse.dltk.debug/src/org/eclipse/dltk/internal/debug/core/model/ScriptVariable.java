/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptVariable extends AbstractScriptVariable {

	private final IDbgpSession session;

	private IScriptStackFrame frame;

	private final IDbgpProperty property;

	private String assignedValue;

	protected ScriptVariable[] readChildrenVariables(IDbgpCoreCommands core)
			throws DbgpException {
		String key = property.getKey();

		IDbgpProperty p = null;

		if (key != null) {
			p = core
					.getPropertyByKey(property.getFullName(), property.getKey());
		} else if (frame != null) {
			p = core.getProperty(property.getFullName(), frame.getLevel());
		} else {
			p = core.getProperty(property.getFullName());
		}

		IDbgpProperty[] properties = p.getAvailableChildren();

		ScriptVariable[] variables = new ScriptVariable[properties.length];
		for (int i = 0; i < properties.length; ++i) {
			variables[i] = createChildVariable(properties[i]);
		}

		return variables;
	}

	protected ScriptVariable createChildVariable(IDbgpProperty property) {
		if (frame != null) {
			return new ScriptVariable(frame, property);
		} else {
			return new ScriptVariable(getDebugTarget(), session, property);
		}
	}

	public ScriptVariable(IScriptStackFrame frame, IDbgpProperty property) {
		super(frame.getDebugTarget());
		this.frame = frame;
		this.session = ((IScriptThread) frame.getThread()).getDbgpSession();
		this.property = property;
	}

	public ScriptVariable(IDebugTarget target, IDbgpSession session,
			IDbgpProperty property) {
		super(target);
		this.session = session;
		this.property = property;
	}

	public String getName() throws DebugException {
		return property.getName();
	}

	public String getReferenceTypeName() throws DebugException {
		return getType().getName();
	}

	public boolean hasValueChanged() throws DebugException {
		return false;
	}

	public void setValue(String expression) throws DebugException {
		try {
			if (frame != null) {
				if (session.getCoreCommands().setProperty(
						property.getFullName(), frame.getLevel(), expression)) {
					DebugEventHelper.fireChangeEvent(this);
				}

				assignedValue = expression;
			}

		} catch (DbgpException e) {
			throw wrapDbgpException("Can't assign variable", e);
		}
	}

	public void setValue(IValue value) throws DebugException {
		setValue(value.getValueString());
	}

	public boolean supportsValueModification() {
		return frame != null && !hasChildren() && !property.isConstant();
	}

	public boolean verifyValue(String expression) throws DebugException {
		return expression != null;
	}

	public boolean verifyValue(IValue value) throws DebugException {
		return verifyValue(value.getValueString());
	}

	public boolean shouldHasChildren() {
		return property.hasChildren();
	}

	public boolean hasChildren() {
		boolean shouldHas = shouldHasChildren();
		int count = property.getChildrenCount();

		return count == -1 ? shouldHas : shouldHas && count > 0;
	}

	public boolean isConstant() {
		return property.isConstant();
	}

	public synchronized IScriptVariable[] getChildren() throws DebugException {
		try {
			return readChildrenVariables(session.getCoreCommands());
		} catch (DbgpException e) {
			throw wrapDbgpException(
					"Exception during getting variable children", e);
		}
	}

	public String getValueString() {
		return assignedValue != null ? assignedValue : property.getValue();
	}

	public String toString() {
		return getFullName();
	}

	public String getFullName() {
		return property.getFullName();
	}

	public String getId() {
		return property.getKey();
	}

	public IScriptType getType() {
		return new ScriptType(property.getType());
	}
}
