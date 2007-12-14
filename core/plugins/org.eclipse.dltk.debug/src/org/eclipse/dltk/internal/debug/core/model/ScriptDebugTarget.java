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
package org.eclipse.dltk.internal.debug.core.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.ExtendedDebugEventDetails;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTargetListener;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptDebugTarget extends ScriptDebugElement implements
		IScriptDebugTarget, IScriptThreadManagerListener {

	private static final String LAUNCH_CONFIGURATION_ATTR_PROJECT = "project";

	private static final int THREAD_TERMINATION_TIMEOUT = 5000; // 5 seconds

	private final ListenerList listeners;

	private IScriptStreamProxy streamProxy;

	private IProcess process;

	private final ILaunch launch;

	private String name;

	private boolean disconnected;

	private final IScriptThreadManager threadManager;

	private final ScriptBreakpointManager breakpointManager;

	private final IDbgpService dbgpService;
	private final String sessionId;

	private final String mondelId;

	static WeakHashMap targets = new WeakHashMap();
	private String[] stepFilters;

	private boolean useStepFilters;
	
	private final Object terminatedLock = new Object();
	private boolean terminated = false;

	private boolean initialized = false;
	
	public static List getAllTargets() {
		return new ArrayList(targets.keySet());
	}

	public ScriptDebugTarget(String modelId, IDbgpService dbgpService,
			String sessionId, ILaunch launch, IProcess process)
			throws CoreException {

		this.mondelId = modelId;

		this.listeners = new ListenerList();

		this.process = process;
		this.launch = launch;

		this.threadManager = new /* New */ScriptThreadManager(this);
		this.sessionId = sessionId;
		this.dbgpService = dbgpService;
		this.dbgpService.registerAcceptor(this.sessionId, this.threadManager);

		this.disconnected = false;

		this.breakpointManager = new ScriptBreakpointManager(this);

		this.threadManager.addListener(this);
		
		DebugEventHelper.fireCreateEvent(this);
		targets.put(this, "");
	}

	public void shutdown() {
		try {
			terminate();
		} catch (DebugException e) {
			DLTKDebugPlugin.log(e);
		}
	}

	public String getSessionId() {
		return sessionId;
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

	public void setProcess(IProcess process) {
		this.process = process; 
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

	// ITerminate
	public boolean canTerminate() {
		return !terminated;
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
		synchronized (terminatedLock) {
			if (terminated)
				return;
			terminated = true;
		}
		
		dbgpService.unregisterAcceptor(sessionId);
		
		threadManager.sendTerminationRequest();		
		if (!waitTermianted()) {
			// Debugging process is not answering, so terminating it
			if (process != null && process.canTerminate())
				process.terminate();
		}
		
		threadManager.removeListener(this);

		IBreakpointManager manager = DebugPlugin.getDefault()
				.getBreakpointManager();

		manager.removeBreakpointListener(this);
		manager.removeBreakpointManagerListener(breakpointManager);
		
		DebugEventHelper.fireTerminateEvent(this);
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
	public IScriptStreamProxy getStreamProxy() {
		return streamProxy;
	}

	public void setStreamProxy(IScriptStreamProxy proxy) {
		this.streamProxy = proxy;
	}

	// IDbgpThreadManagerListener
	public void threadAccepted(IScriptThread thread, boolean first) {
		if (first) {
			DebugEventHelper.fireExtendedEvent(this,
					ExtendedDebugEventDetails.BEFORE_CODE_LOADED);

			breakpointManager.setupDeferredBreakpoints();

			IBreakpointManager manager = DebugPlugin.getDefault()
					.getBreakpointManager();

			manager.addBreakpointListener(this);
			manager.addBreakpointManagerListener(breakpointManager);

			// DebugEventHelper.fireCreateEvent(this);
			initialized  = true;
			fireTargetInitialized();
		}
	}

	public void allThreadsTerminated() {
		try {
			if (streamProxy != null) {
				streamProxy.close();
			}
			terminate();
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return "Debugging engine (id = " + this.sessionId + ")";
	}

	// IScriptDebugTarget
	public void runToLine(URI uri, int lineNumber) throws DebugException {
		breakpointManager.setBreakpointUntilFirstSuspend(uri, lineNumber);
		resume();
	}

	public boolean isInitialized() {
		return initialized;
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

	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		return breakpointManager.supportsBreakpoint(breakpoint);
	}

	public void setFilters(String[] activeFilters) {
		this.stepFilters = activeFilters;
	}

	public String[] getFilters() {
		if (this.stepFilters != null) {
			return this.stepFilters;
		}
		return new String[0];
	}

	public boolean isUseStepFilters() {
		return useStepFilters;
	}

	public void setUseStepFilters(boolean useStepFilters) {
		this.useStepFilters = useStepFilters;
	}

	public IDLTKLanguageToolkit getLanguageToolkit() {
		ILaunchConfiguration configuration = this.getLaunch()
				.getLaunchConfiguration();
		String projectName = null;
		try {
			projectName = configuration.getAttribute(
					LAUNCH_CONFIGURATION_ATTR_PROJECT, (String) null);
			if (projectName != null) {
				projectName = projectName.trim();
				if (projectName.length() > 0) {
					IProject project = ResourcesPlugin.getWorkspace().getRoot()
							.getProject(projectName);
					IScriptProject scriptProject = DLTKCore.create(project);
					IDLTKLanguageToolkit toolkit = DLTKLanguageManager
							.getLanguageToolkit(scriptProject);
					return toolkit;
				}
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
