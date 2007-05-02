/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.debug.model;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.internal.core.model.DebugEventHelper;

public class RubyDebugTarget extends PlatformObject implements IDebugTarget {

	private final String DEBUG_MODEL_IDENTIFIER = "org.eclipse.dltk.debug.rubyModel";

	private ILaunch launch;
	private IProcess process;
	private RubyThreadManager threadManager;

	
	// Specific for thread manager
	public void destroy() {
		// Unregister dbgp acceptor
		
		DebugPlugin.getDefault().getBreakpointManager()
				.removeBreakpointListener(this);

		DebugEventHelper.fireTerminateEvent(this);
	}

	public RubyDebugTarget(ILaunch launch, IProcess process, String id,
			IDbgpService dbgpService) {
		//if (launch == null || process == null) {
			//throw new IllegalArgumentException();
		//}

		this.launch = launch;
		this.process = process;

		this.threadManager = new RubyThreadManager(this);

		dbgpService.registerAcceptor(id, this.threadManager);

		DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(
				this);

		DebugEventHelper.fireCreateEvent(this);
	}

	public String getName() throws DebugException {
		return "Ruby Debug Target";
	}

	public IProcess getProcess() {
		return process;
	}

	public IThread[] getThreads() throws DebugException {
		return threadManager.getThreads();
	}

	public boolean hasThreads() throws DebugException {
		return threadManager.hasThreads();
	}

	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	public IDebugTarget getDebugTarget() {
		return this;
	}

	public ILaunch getLaunch() {
		return launch;
	}

	public String getModelIdentifier() {
		return DEBUG_MODEL_IDENTIFIER;
	}

	// =========================================================================
	// ========================== ITerminate ===================================
	// =========================================================================
	public boolean canTerminate() {
		return threadManager.canTerminate();
	}

	public boolean isTerminated() {
		return threadManager.isTerminated();
	}

	public void terminate() throws DebugException {
		threadManager.terminate();
	}

	// =========================================================================
	// ========================== ISuspendResume ===============================
	// =========================================================================
	public boolean canResume() {
		return threadManager.canResume();
	}

	public boolean canSuspend() {
		return threadManager.canSuspend();
	}

	public boolean isSuspended() {
		return threadManager.isSuspended();
	}

	public void resume() throws DebugException {
		threadManager.resume();
	}

	public void suspend() throws DebugException {
		threadManager.suspend();
	}

	// =========================================================================
	// ========================== IBreakpointListener ==========================
	// =========================================================================
	public void breakpointAdded(IBreakpoint breakpoint) {
		// TODO Add breakpoint

	}

	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Change breakpoint

	}

	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Remove breakpoint
	}

	// =========================================================================
	// ============================= IDisconnect ===============================
	// =========================================================================
	public boolean canDisconnect() {
		return false;
	}

	public void disconnect() throws DebugException {

	}

	public boolean isDisconnected() {
		return false;
	}

	// =========================================================================
	// ============================= IMemoryBlockRetrieval =====================
	// =========================================================================
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		return null;
	}

	public boolean supportsStorageRetrieval() {
		return false;
	}

	public RubyThreadManager getThreadManager() {
		return threadManager;
	}	
}
