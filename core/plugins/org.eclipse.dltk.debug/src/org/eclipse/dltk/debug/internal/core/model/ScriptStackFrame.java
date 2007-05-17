/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.internal.core.model;

import java.net.URI;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptStackFrame extends ScriptDebugElement implements
		IScriptStackFrame {

	private IScriptThread thread;

	private IDbgpStackLevel stackLevel;

	private IScriptVariable[] variables;

	protected ScriptVariable[] readVariables(int stackDepth,
			IDbgpCoreCommands core) throws DbgpException {

		IDbgpProperty[] properties = core.getContextProperties(stackDepth);

		ScriptVariable[] variables = new ScriptVariable[properties.length];

		for (int i = 0; i < properties.length; ++i) {
			variables[i] = new ScriptVariable(this, properties[i]);
		}

		return variables;
	}

	public ScriptStackFrame(IScriptThread thread, IDbgpStackLevel stackLevel)
			throws DbgpException {

		this.thread = thread;
		this.stackLevel = stackLevel;
		this.variables = readVariables(stackLevel.getLevel(), thread
				.getDbgpSession().getCoreCommands());
	}

	public URI getFileName() {
		return stackLevel.getFileURI();
	}

	public int getCharStart() throws DebugException {
		return -1;
	}

	public int getCharEnd() throws DebugException {
		return -1;
	}

	public int getLineNumber() throws DebugException {
		return stackLevel.getLineNumber();
	}

	public String getName() throws DebugException {
		String where = stackLevel.getWhere();

		if (where == null || where.length() == 0) {
			return "Stack Frame, level = " + stackLevel.getLevel();
		} else {
			return where + "(" + stackLevel.getFileURI() + ")";
		}
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
		if (variables == null) {
			return false;
		}

		return variables.length > 0;
	}

	public IVariable[] getVariables() throws DebugException {
		return (IVariable[])variables.clone();
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
		return thread.isTerminated();
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
		for (int i = 0; i < variables.length; i++) {
			if (variables[i].getName().equals(varName))
				return variables[i];
		}
		return null;
	}

	public int getLevel() {
		return stackLevel.getLevel();
	}

	public boolean equals(Object obj) {
		if (obj instanceof ScriptStackFrame) {
			return stackLevel.equals(((ScriptStackFrame) obj).stackLevel);
		}

		return false;
	}

	public int hashCode() {
		return stackLevel.hashCode();
	}

	public String toString() {
		return "Stack frame (level: " + stackLevel.getLevel() + ")";
	}
}
