/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.debug.internal.core.model;

import java.net.URI;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTargetListener;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptDebugTarget extends ScriptDebugElement implements
		IScriptDebugTarget, IDbgpThreadManagerListener {

	private static final int THREAD_TERMINATION_TIMEOUT = 5000; // 5 seconds

	private ListenerList listeners;

	private IScriptDebugTargetStreamManager manager;

	private IProcess process;

	private ILaunch launch;

	private String name;

	private boolean disconnected;

	private ScriptThreadManager threadManager;

	private DbgpBreakpointManager breakpointManager;

	private IDbgpService dbgpService;
	private String dbgpId;
	
	private String mondelId;

	public ScriptDebugTarget(String modelId, IDbgpService dbgpService, String id,
			ILaunch launch, IProcess process) throws CoreException {
		
		this.mondelId = modelId;

		this.listeners = new ListenerList();

		this.process = process;
		this.launch = launch;

		this.threadManager = new ScriptThreadManager(this);
		this.dbgpId = id;
		this.dbgpService = dbgpService;
		this.dbgpService.registerAcceptor(this.dbgpId, this.threadManager);

		this.disconnected = false;

		breakpointManager = new DbgpBreakpointManager(this.threadManager);

		this.threadManager.addListener(this);

		DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(
				this);

		DebugEventHelper.fireCreateEvent(this);
	}

	public IDebugTarget getDebugTarget() {
		return this;
	}

	public String getModelIdentifier() {
		return mondelId;	
	}
	
	public ILaunch getLaunch() {
		return launch;
	}

	// IDebugTarget
	public IProcess getProcess() {
		return process;
	}

	public boolean hasThreads() throws DebugException {
		return threadManager.hasThreads();
	}

	public IThread[] getThreads() throws DebugException {		
		return threadManager.getThreads();
	}

	public String getName() throws DebugException {
		return name;
	}

	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		return DbgpBreakpointManager.supportsBreakpoint(breakpoint);
	}

	// ITerminate
	public boolean canTerminate() {
		return threadManager.canTerminate();
	}

	public boolean isTerminated() {
		return threadManager.isTerminated();
	}

	protected boolean waitTermianted() {
		final int WAIT_CHUNK = 400; // 200 ms

		int all = 0;
		while (all < THREAD_TERMINATION_TIMEOUT) {
			try {
				Thread.sleep(WAIT_CHUNK);
				all += WAIT_CHUNK;
			} catch (InterruptedException e) {

			}
			if (threadManager.isTerminated()) {
				return true;
			}			
		}
		return false;
	}

	public void terminate() throws DebugException {
		dbgpService.unregisterAcceptor(dbgpId);		
		dbgpService.shutdown();				
		threadManager.terminate();
		if (waitTermianted()) {
			
			threadManager.removeListener(this);
			DebugPlugin.getDefault().getBreakpointManager()
					.removeBreakpointListener(this);			
			DebugEventHelper.fireTerminateEvent(this);			
		}
		
		
	}

	// ISuspendResume
	public boolean canSuspend() {
		return threadManager.canSuspend();
	}

	public boolean isSuspended() {
		return threadManager.isSuspended();
	}

	public void suspend() throws DebugException {
		threadManager.suspend();
	}

	public boolean canResume() {
		return threadManager.canResume();
	}

	public void resume() throws DebugException {
		threadManager.resume();
	}

	// IDisconnect
	public boolean canDisconnect() {
		// Detach feature support!!!
		return false;
	}

	public void disconnect() throws DebugException {
		disconnected = true;
	}

	public boolean isDisconnected() {
		return disconnected;
	}

	// IMemoryBlockRetrieval
	public boolean supportsStorageRetrieval() {
		return false;
	}

	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		return null;
	}

	public IScriptVariable findVariable(String variableName)
			throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	// Request timeout
	public int getRequestTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setRequestTimeout(int timeout) {
		// TODO Auto-generated method stub

	}

	// IBreakpointListener
	public void breakpointAdded(IBreakpoint breakpoint) {
		breakpointManager.breakpointAdded(breakpoint);
	}

	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		breakpointManager.breakpointChanged(breakpoint, delta);
	}

	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		breakpointManager.breakpointRemoved(breakpoint, delta);
	}

	// Streams
	public IScriptDebugTargetStreamManager getStreamManager() {
		return manager;
	}

	public void setStreamManager(IScriptDebugTargetStreamManager manager) {
		this.manager = manager;
	}

	// IDbgpThreadManagerListener
	public void threadAccepted(IScriptThread thread, boolean first) {
		try {
			breakpointManager.setupDeferredBreakpoints(thread);
		} catch (CoreException e) {
			// TODO: log exception
			e.printStackTrace();
		}

		if (first) {
			// DebugEventHelper.fireCreateEvent(this);
			fireTargetInitialized();
		}
	}

	public void allThreadsTerminated() {
		try {
			terminate();
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return "Debugging engine (id = " + this.dbgpId + ")";
	}

	// IScriptDebugTarget
	public void runToLine(URI uri, int lineNumber) {
		try {
			breakpointManager.setupTemporaryBreakpoint(uri, lineNumber);
		} catch (DbgpException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public boolean isInitialized() {
		return !threadManager.isWaitingForThreads();
	}

	protected void fireTargetInitialized() {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IScriptDebugTargetListener) list[i]).targetInitialized();
		}
	}

	public void addListener(IScriptDebugTargetListener listener) {
		listeners.add(listener);
	}

	public void removeListener(IScriptDebugTargetListener listener) {
		listeners.remove(listener);
	}

	
}
