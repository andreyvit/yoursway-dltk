/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.debug.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpStackLevel;

public class RubyStackFrame extends PlatformObject implements IStackFrame {

	private RubyThread thread;

	private String name;

	private IDbgpStackLevel level;

	private boolean dirty;

	private RubyVariable[] variables;

	protected RubyVariable[] readVariables() throws Exception {
		IDbgpSession session = thread.getSession();

		synchronized (session) {

			List properties = session.getCoreCommands().getContextProperties(
					level.getLevel());
			List variables = new ArrayList();
			Iterator it = properties.iterator();
			while (it.hasNext()) {
				IDbgpProperty property = (IDbgpProperty) it.next();
				variables.add(new RubyVariable(this, property));
			}

			return (RubyVariable[]) variables
					.toArray(new RubyVariable[variables.size()]);
		}
	}

	protected void checkVariables() {
		try {
			//if (dirty) {
				System.out.println("== Updating variables ==");
				RubyVariable[] newVariables = readVariables();
				//RubyVariableUpdater.update(variables, newVariables);
				
				List list = Arrays.asList(newVariables);
				
				
				//Collections.shuffle(list);
								
				variables = (RubyVariable[])list.toArray(new RubyVariable[list.size()]);
				//dirty = false;
			//}
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RubyStackFrame(RubyThread thread, IDbgpStackLevel level) {
		this.thread = thread;
		this.level = level;
		this.name = "RubyStackFrame";
		this.dirty = true;
		this.variables = new RubyVariable[0];
	}

	public void setLevel(IDbgpStackLevel level) {
		this.level = level;
	}

	public void setDirty() {
		dirty = true;
	}

	public URI getFile() {
		return level.getFileURI();
	}

	// Source location
	public int getCharEnd() throws DebugException {
		return -1;
	}

	public int getCharStart() throws DebugException {
		return -1;
	}

	public int getLineNumber() throws DebugException {
		return level.getLineNumber();
	}

	public String getName() throws DebugException {
		return name;
	}

	public IThread getThread() {
		return thread;
	}

	// Variables
	public boolean hasVariables() throws DebugException {
		checkVariables();

		return variables.length > 0;
	}

	public IVariable[] getVariables() throws DebugException {
		System.out.println("@@@@@@@@@@@@@@@@@ RubyStackFrame.getVariables() @@@@@@@@@@@@@@@@@@");
		
		checkVariables();

		return (RubyVariable[]) variables.clone();
	}

	// Register groups
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return new IRegisterGroup[0];
	}

	public IDebugTarget getDebugTarget() {
		return getThread().getDebugTarget();
	}

	public ILaunch getLaunch() {
		return getThread().getLaunch();
	}

	public String getModelIdentifier() {
		return getThread().getModelIdentifier();
	}

	// =========================================================================
	// ============================ IStep ======================================
	// =========================================================================
	public boolean canStepInto() {
		return getThread().canStepInto();
	}

	public boolean canStepOver() {
		return getThread().canStepOver();
	}

	public boolean canStepReturn() {
		return getThread().canStepReturn();
	}

	public boolean isStepping() {
		return getThread().isStepping();
	}

	public void stepInto() throws DebugException {
		getThread().stepInto();
	}

	public void stepOver() throws DebugException {
		getThread().stepOver();
	}

	public void stepReturn() throws DebugException {
		getThread().stepReturn();
	}

	// =========================================================================
	// ========================== ISuspendResume ===============================
	// =========================================================================
	public boolean canResume() {
		return getThread().canResume();
	}

	public boolean canSuspend() {
		return getThread().canSuspend();
	}

	public boolean isSuspended() {
		return getThread().isSuspended();
	}

	public void resume() throws DebugException {
		getThread().resume();
	}

	public void suspend() throws DebugException {
		getThread().suspend();
	}

	// =========================================================================
	// ========================== ITerminate ===================================
	// =========================================================================
	public boolean canTerminate() {
		return getThread().canTerminate();
	}

	public boolean isTerminated() {
		return getThread().isTerminated();
	}

	public void terminate() throws DebugException {
		getThread().terminate();
	}

	public int hashCode() {
		return level.getFileURI().hashCode() ^ level.getLineNumber();
	}

	public boolean equals(Object o) {
		if (o instanceof RubyStackFrame) {
			return equals(((RubyStackFrame) o).level);
		}

		return false;
	}

	public boolean equals(IDbgpStackLevel level) {
		return this.level.getFileURI().equals(level.getFileURI())
				&& this.level.getLineNumber() == level.getLineNumber();
	}

	public String toString() {
		return name + " [line: " + Integer.toString(level.getLineNumber())
				+ "; hash: " + super.hashCode() + "]" ;
	}
}
