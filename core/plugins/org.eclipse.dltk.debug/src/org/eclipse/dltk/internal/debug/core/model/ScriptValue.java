/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IIndexedValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.commands.IDbgpPropertyCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationCommand;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.model.AtomicScriptType;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.internal.debug.core.eval.ScriptEvaluationCommand;

public class ScriptValue extends ScriptDebugElement implements IScriptValue,
		IIndexedValue {
	final private IScriptType type;
	final private IVariable[] variables;
	private IScriptStackFrame frame;
	private int pageSize;
	private String name;
	private String fullname;
	private String value;
	private boolean hasChildren;
	private String key;
	private String rawValue;

	public static IScriptValue createValue(IScriptStackFrame frame,
			IDbgpProperty property) {
		IScriptType type = createType(frame.getDebugTarget(), property);
		return new ScriptValue(frame, property, type);
	}

	private static IScriptType createType(IDebugTarget target,
			IDbgpProperty property) {
		IScriptType type = null;
		final String rawType = property.getType();

		final IScriptTypeFactory factory = ScriptDebugManager.getInstance()
				.getTypeFactoryByDebugModel(target.getModelIdentifier());
		if (factory != null) {
			type = factory.buildType(rawType);
		} else {
			type = new AtomicScriptType(rawType);
		}
		return type;
	}

	protected ScriptValue(IScriptStackFrame frame, IDbgpProperty property,
			IScriptType type) {
		this.frame = frame;
		this.type = type;

		this.key = property.getKey();
		this.name = property.getName();
		this.fullname = property.getEvalName();
		this.rawValue = property.getValue();
		this.value = null;
		this.hasChildren = property.hasChildren();
		this.variables = new ScriptVariable[property.getChildrenCount()];
		
		this.pageSize = property.getPageSize();		
		fillVariables(property.getPage(), property);
	}

	private void loadPage(int page) throws DbgpException {
		IDbgpPropertyCommands commands = frame.getScriptThread()
				.getDbgpSession().getCoreCommands();
		IDbgpProperty pageProperty = commands.getProperty(page, fullname, frame
				.getLevel());
		fillVariables(page, pageProperty);
	}

	private void fillVariables(int page, IDbgpProperty pageProperty) {
		int offset = getPageOffset(page);
		IDbgpProperty[] properties = pageProperty.getAvailableChildren();
		for (int i = 0; i < properties.length; ++i) {
			IDbgpProperty p = properties[i];
			variables[offset + i] = new ScriptVariable(frame, p, p.getName());
		}
		Assert.isLegal(pageSize > 0 || properties.length == variables.length);
	}

	private int getPageOffset(int page) {
		if (pageSize <= 0)
			pageSize = frame.getScriptThread().getPropertyPageSize();
		
		if (pageSize <= 0)
			return 0;
		return page * pageSize;
	}

	private int getPageForOffset(int offset) {
		Assert.isLegal(pageSize > 0);
		return offset / pageSize;
	}

	public String getReferenceTypeName() throws DebugException {
		return getType().getName();
	}

	public String getValueString() {
		if (value == null) {
			if (type.isString()) {
				value = prepareString(rawValue);
			}
			else if (!type.isAtomic()) {
				StringBuffer sb = new StringBuffer();
				sb.append(type.getName());
				String id = getInstanceId();
				if (id != null) {
					sb.append(" (id = " + id + ")"); // TODO add constant
				}
				value = sb.toString();
			}
			else {
				value = rawValue;
			}
		}
		return value;
	}

	public String getRawValue() {
		return rawValue;
	}
	
	private String prepareString(String string) {
		if (string == null) {
			return null;
		}
		StringBuffer escaped = new StringBuffer();
		escaped.append('"');
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			switch (c) {
				case '"':
					escaped.append("\\\""); //$NON-NLS-1$
					break;					
				default:
					escaped.append(c);
					break;
			}
		}
		escaped.append('"');
		return escaped.toString();
	}

	public String getEvalName() {
		return fullname;
	}

	public boolean hasVariables() throws DebugException {
		return hasChildren;
	}

	public boolean isAllocated() throws DebugException {
		return true;
	}

	public String toString() {
		return getValueString();
	}

	public IDebugTarget getDebugTarget() {
		return frame.getDebugTarget();
	}

	public String getInstanceId() {
		return key;
	}

	public IScriptType getType() {
		return type;
	}

	public IScriptEvaluationCommand createEvaluationCommand(
			String messageTemplate, IScriptThread thread) {
		IScriptEvaluationEngine engine = thread.getEvaluationEngine();

		String pattern = "(%variable%)";
		String evalName = getEvalName();
		if (messageTemplate.indexOf(pattern) != -1) {
			String snippet = replacePattern(messageTemplate, pattern, evalName);
			return new ScriptEvaluationCommand(engine, snippet, frame);
		} else {
			DLTKDebugPlugin.log(new Status(IStatus.WARNING,
					DLTKDebugPlugin.PLUGIN_ID,
					"Detail formatter required to contain " + pattern
							+ " identifier."));
			return new ScriptEvaluationCommand(engine, evalName, frame);
		}
	}

	private static String replacePattern(String messageTemplate,
			String pattern, String evalName) {
		String result = messageTemplate;
		while (result.indexOf(pattern) != -1) {
			int pos = result.indexOf(pattern);
			result = result.substring(0, pos) + evalName
					+ result.substring(pos + pattern.length(), result.length());
		}
		return result;
	}

	public int getInitialOffset() {
		return 0;
	}

	public int getSize() throws DebugException {
		return variables.length;
	}

	public IVariable getVariable(int offset) throws DebugException {
		try {
			if (variables[offset] == null) {
				loadPage(getPageForOffset(offset));
			}
			return variables[offset];
		} catch (DbgpException e) {
			throw wrapDbgpException("Unable to load children of " + name, e);
		}
	}

	public IVariable[] getVariables() throws DebugException {
		return getVariables(0, getSize());
	}

	public IVariable[] getVariables(int offset, int length)
			throws DebugException {
		IVariable[] variables = new IVariable[length];
		for (int i = 0; i < length; i++) {
			variables[i] = getVariable(offset + i);
		}
		return variables;
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IIndexedValue.class && type.isCollection()) {
			return this;
		}
		return super.getAdapter(adapter);
	}

}
