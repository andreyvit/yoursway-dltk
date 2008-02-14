/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.commands.IDbgpContextCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpDebuggingEngineException;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.core.model.IScriptStack;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptStackFrame extends ScriptDebugElement implements
		IScriptStackFrame {

	private static final String STACK_FRAME_LABEL = "Stack frame #{0}";

	private IScriptStack stack;

	private IScriptThread thread;

	private final IDbgpStackLevel level;

	private IScriptVariable[] variables;

	protected static IScriptVariable[] readVariables(
			ScriptStackFrame parentFrame, int contextId,
			IDbgpContextCommands commands) throws DbgpException {

		try {
			IDbgpProperty[] properties = commands.getContextProperties(
					parentFrame.getLevel(), contextId);

			IScriptVariable[] variables = new IScriptVariable[properties.length];

			// Workaround for bug 215215 https://bugs.eclipse.org/bugs/show_bug.cgi?id=215215
			// Remove this code when Tcl active state debugger fixed
			Set useFullName = new HashSet();
			Set alreadyExsisting = new HashSet();
			for (int i = 0; i < properties.length; ++i) {
				IDbgpProperty property = properties[i];
				String name = property.getName();
				if (alreadyExsisting.contains(name)) {
					useFullName.add(name);
				} else {
					alreadyExsisting.add(name);
				}
			}

			for (int i = 0; i < properties.length; ++i) {
				IDbgpProperty property = properties[i];
				String name = property.getName();
				if (useFullName.contains(name)) {
					name = property.getEvalName();
				}
				variables[i] = new ScriptVariable(parentFrame, property, name);
			}

			return variables;
		} catch (DbgpDebuggingEngineException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return new IScriptVariable[0];
		}
	}

	protected IScriptVariable[] readAllVariables() throws DbgpException {
		IDbgpContextCommands commands = thread.getDbgpSession()
				.getCoreCommands();

		Map names = commands.getContextNames(getLevel());

		final Integer localId = new Integer(
				IDbgpContextCommands.LOCAL_CONTEXT_ID);
		final Integer globalId = new Integer(
				IDbgpContextCommands.GLOBAL_CONTEXT_ID);
		final Integer classId = new Integer(
				IDbgpContextCommands.CLASS_CONTEXT_ID);

		Preferences prefs = DLTKDebugPlugin.getDefault().getPluginPreferences();
		boolean showLocal = prefs
				.getBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL);
		boolean showGlobal = prefs
				.getBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL);
		boolean showClass = prefs
				.getBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS);

		ScriptVariableContainer all = new ScriptVariableContainer();

		if (showLocal && names.containsKey(localId)) {
			all.add(readVariables(this, localId.intValue(), commands));
		}

		if (showGlobal && names.containsKey(globalId)) {
			all.add(new ScriptVariableWrapper(getDebugTarget(),
					"Global Variables", readVariables(this,
							globalId.intValue(), commands)));
		}

		if (showClass && names.containsKey(classId)) {
			all.add(new ScriptVariableWrapper(getDebugTarget(),
					"Class Variables", readVariables(this, classId.intValue(),
							commands)));
		}

		return all.get();
	}

	private static class ScriptVariableContainer {
		private final List list = new ArrayList();

		public void add(IScriptVariable variable) {
			list.add(variable);
		}

		public void add(IScriptVariable[] variables) {
			for (int i = 0; i < variables.length; ++i) {
				list.add(variables[i]);
			}
		}

		public IScriptVariable[] get() {
			return (IScriptVariable[]) list.toArray(new IScriptVariable[list
					.size()]);
		}
	}

	public ScriptStackFrame(IScriptStack stack, IDbgpStackLevel stackLevel) {
		this.stack = stack;
		this.thread = stack.getThread();
		this.level = stackLevel;
		updateVariables();
	}

	public void updateVariables() {
		this.variables = null;
	}

	public IScriptStack getStack() {
		return stack;
	}

	public URI getFileName() {
		return level.getFileURI();
	}

	public int getCharStart() throws DebugException {
		return -1;
	}

	public int getCharEnd() throws DebugException {
		return -1;
	}

	public int getLineNumber() throws DebugException {
		return level.getLineNumber();
	}

	public String getName() throws DebugException {
		String name = level.getWhere().trim();

		if (name == null || name.length() == 0) {
			name = toString();
		}

		name += " (" + level.getFileURI().getPath() + ")";

		return name;
	}

	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return new IRegisterGroup[0];
	}

	public IThread getThread() {
		return thread;
	}

	public boolean hasVariables() throws DebugException {
		checkVariablesAvailable();
		return variables.length > 0;
	}

	private synchronized void checkVariablesAvailable() throws DebugException {
		if (variables == null) {
			try {
				variables = readAllVariables();
			} catch (DbgpException e) {
				variables = new IScriptVariable[0];
				Status status = new Status(IStatus.ERROR,
						DLTKDebugPlugin.PLUGIN_ID, "Unable to load variables",
						e);
				throw new DebugException(status);
			}
		}
	}

	public IVariable[] getVariables() throws DebugException {
		checkVariablesAvailable();
		return (IVariable[]) variables.clone();
	}

	// IStep
	public boolean canStepInto() {
		return thread.canStepInto();
	}

	public boolean canStepOver() {
		return thread.canStepOver();
	}

	public boolean canStepReturn() {
		return thread.canStepReturn();
	}

	public boolean isStepping() {
		return thread.isStepping();
	}

	public void stepInto() throws DebugException {
		thread.stepInto();
	}

	public void stepOver() throws DebugException {
		thread.stepOver();
	}

	public void stepReturn() throws DebugException {
		thread.stepReturn();
	}

	// ISuspenResume
	public boolean canResume() {
		return thread.canResume();
	}

	public boolean canSuspend() {
		return thread.canSuspend();
	}

	public boolean isSuspended() {
		return thread.isSuspended();
	}

	public void resume() throws DebugException {
		thread.resume();
	}

	public void suspend() throws DebugException {
		thread.suspend();
	}

	// ITerminate
	public boolean canTerminate() {
		return thread.canTerminate();
	}

	public boolean isTerminated() {
		return thread.isTerminated();
	}

	public void terminate() throws DebugException {
		thread.terminate();
	}

	// IDebugElement
	public IDebugTarget getDebugTarget() {
		return thread.getDebugTarget();
	}

	public IScriptVariable findVariable(String varName) throws DebugException {
		checkVariablesAvailable();
		for (int i = 0; i < variables.length; i++) {
			if (variables[i].getName().equals(varName)) {
				return variables[i];
			}
		}
		return null;
	}

	public int getLevel() {
		return level.getLevel();
	}

	public boolean equals(Object obj) {
		if (obj instanceof ScriptStackFrame) {
			return level.equals(((ScriptStackFrame) obj).level);
		}

		return false;
	}

	public int hashCode() {
		return level.hashCode();
	}

	public String toString() {
		return MessageFormat.format(STACK_FRAME_LABEL,
				new Object[] { new Integer(level.getLevel()) });
	}

	public String getSourceLine() {
		return level.getWhere();
	}

	public URI getSourceURI() {
		return level.getFileURI();
	}

	public IScriptThread getScriptThread() {
		return (IScriptThread) getThread();
	}
}
