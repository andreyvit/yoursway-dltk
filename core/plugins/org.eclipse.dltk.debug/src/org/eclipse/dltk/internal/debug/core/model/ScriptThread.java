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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.IDbgpTerminationListener;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.internal.debug.core.eval.ScriptEvaluationEngine;
import org.eclipse.dltk.internal.debug.core.model.operations.DbgpDebugger;

public class ScriptThread extends ScriptDebugElement implements IScriptThread,
		IThreadManagement, IDbgpTerminationListener,
		ScriptThreadStateManager.IStateChangeHandler {

	private ScriptThreadStateManager stateManager;

	private final IScriptThreadManager manager;

	private IScriptThreadStreamProxy streamProxy;

	private final ScriptStack stack;

	// Session
	private final IDbgpSession session;

	// State variables
	private final IScriptDebugTarget target;

	private IScriptEvaluationEngine evalEngine;

	// ScriptThreadStateManager.IStateChangeHandler
	public void handleSuspend(int detail) {
		stack.update();

		DebugEventHelper.fireSuspendEvent(this, detail);
	}

	public void handleResume(int detail) {
		DebugEventHelper.fireResumeEvent(this, detail);
		DebugEventHelper.fireChangeEvent(this);
	}

	public void handleTermination(DbgpException e) {
		if (e != null) {
			DLTKDebugPlugin.log(e);
		}

		session.requestTermination();
		try {
			session.waitTerminated();
		} catch (InterruptedException ee) {
			ee.printStackTrace();
		}
		manager.terminateThread(this);
	}

	public ScriptThread(IScriptDebugTarget target, IDbgpSession session,
			IScriptThreadManager manager) throws DbgpException, CoreException {

		this.target = target;

		this.manager = manager;

		this.session = session;
		this.session.addTerminationListener(this);

		this.stateManager = new ScriptThreadStateManager(this);

		final DbgpDebugger engine = this.stateManager.getEngine();

		if (DLTKCore.DEBUG) {
			DbgpDebugger.printEngineInfo(engine);
		}

		final IDbgpExtendedCommands extended = session.getExtendedCommands();

		engine.setMaxChildren(256);
		engine.setMaxDepth(2);
		engine.setMaxData(8192);

		if (engine.isFeatureSupported(IDbgpExtendedCommands.STDIN_COMMAND)) {
			engine.redirectStdin();
		}

		engine.setNotifyOk(true);

		engine.redirectStdout();
		engine.redirectStderr();

		// session.getNotificationManager().addNotificationListener(
		// new IDbgpNotificationListener() {
		// private final BufferedReader reader = new BufferedReader(
		// new InputStreamReader(getStreamProxy().getStdin()));
		//
		// public void dbgpNotify(IDbgpNotification notification) {
		// try {
		// extended.sendStdin(reader.readLine() + "\n");
		// } catch (IOException e) {
		// // TODO: log exception
		// e.printStackTrace();
		// } catch (DbgpException e) {
		// // TODO: log exception
		// e.printStackTrace();
		// }
		// }
		// });

		this.stack = new ScriptStack(this);
	}

	public boolean hasStackFrames() throws DebugException {
		return stack.hasFrames();
	}

	// IThread
	public IStackFrame[] getStackFrames() throws DebugException {
		if (!isSuspended()) {
			return ScriptStack.NO_STACK_FRAMES;
		}

		return stack.getFrames();
	}

	public int getPriority() throws DebugException {
		return 0;
	}

	public IStackFrame getTopStackFrame() throws DebugException {
		return stack.getTopFrame();
	}

	public String getName() throws DebugException {
		return session.getInfo().getThreadId();
	}

	public IBreakpoint[] getBreakpoints() {
		return DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(
				getModelIdentifier());
	}

	// ISuspendResume

	// Suspend
	public int getSuspendCount() {
		return stateManager.getSuspendCount();
	}

	public boolean isSuspended() {
		return stateManager.isSuspended();
	}

	public boolean canSuspend() {
		return stateManager.canSuspend();
	}

	public void suspend() throws DebugException {
		stateManager.suspend();
	}

	// Resume
	public boolean canResume() {
		return stateManager.canResume();
	}

	public void resume() throws DebugException {
		stateManager.resume();
	}

	// IStep
	public boolean isStepping() {
		return stateManager.isStepping();
	}

	// Step into
	public boolean canStepInto() {
		return stateManager.canStepInto();
	}

	public void stepInto() throws DebugException {
		stateManager.stepInto();
	}

	// Step over
	public boolean canStepOver() {
		return stateManager.canStepOver();
	}

	public void stepOver() throws DebugException {
		stateManager.stepOver();
	}

	// Step return
	public boolean canStepReturn() {
		return stateManager.canStepReturn();
	}

	public void stepReturn() throws DebugException {
		stateManager.stepReturn();
	}

	// ITerminate
	public boolean isTerminated() {
		return stateManager.isTerminated();
	}

	public boolean canTerminate() {
		return !isTerminated();
	}

	public void terminate() throws DebugException {
		stateManager.terminate();
	}

	public IDbgpSession getDbgpSession() {
		return session;
	}

	public IDbgpBreakpoint getDbgpBreakpoint(String id) {
		try {
			return session.getCoreCommands().getBreakpoint(id);
		} catch (DbgpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public IScriptThreadStreamProxy getStreamProxy() {
		if (streamProxy == null) {
			IScriptDebugTargetStreamManager manager = target.getStreamManager();
			if (manager != null) {
				streamProxy = manager.makeThreadStreamProxy();
			}
		}

		return streamProxy;
	}

	public IDebugTarget getDebugTarget() {
		return target.getDebugTarget();
	}

	public IScriptEvaluationEngine getEvaluationEngine() {
		if (evalEngine == null) {
			evalEngine = new ScriptEvaluationEngine(this);
		}

		return evalEngine;
	}

	// IDbgpTerminationListener
	public void objectTerminated(Object object, Exception e) {
		Assert.isTrue(object == session);
		manager.terminateThread(this);
	}

	// Object
	public String toString() {
		return "Thread (" + session.getInfo().getThreadId() + ")";
	}
}