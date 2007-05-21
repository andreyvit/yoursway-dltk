/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.internal.core.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IWatchExpressionListener;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.IDbgpNotification;
import org.eclipse.dltk.dbgp.IDbgpNotificationListener;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.debug.internal.core.model.operations.DbgpDebugger;
import org.eclipse.dltk.debug.internal.core.model.operations.IDbgpDebuggerFeedback;

public class ScriptThread extends ScriptDebugElement implements IScriptThread,
		IThreadManagement, IDbgpDebuggerFeedback {

	private static final IStackFrame[] NO_STACK_FRAMES = new IStackFrame[0];

	private boolean canSuspend;

	private ScriptThreadManager manager;

	private IScriptThreadStreamProxy streamProxy;

	private ScriptStack stack;

	private DbgpDebugger engine;

	// Session
	private IDbgpSession session;

	// State variables
	private volatile boolean stepping;

	private volatile boolean suspended;

	private volatile boolean terminated;

	private IScriptDebugTarget target;

	// Stop
	private IDbgpStatus stopDebugger() throws DbgpException {
		return session.getCoreCommands().stop();
	}

	protected void processOperationEnd(DbgpException exception,
			IDbgpStatus status, int suspendDetail) {
		try {
			if (exception != null) {
				throw exception;
			}

			if (status.isBreak()) {
				stack.update();

				setSuspended(true, suspendDetail);
			} else if (status.isStopping()) {
				// Stop
				if (stopDebugger().isStopped()) {
					setTerminated();
				}
			} else if (status.isStopped()) {
				setTerminated();
			}
		} catch (DbgpException e) {
			try {
				streamProxy.getStdout().write(e.getMessage().getBytes());
				setTerminated();
			} catch (IOException e1) {
				// TODO: log properly
				e1.printStackTrace();
			}
		}
	}

	// Steps
	protected boolean canStep() {
		return !isTerminated() && isSuspended();
	}

	protected void beginStep(int detail) {
		stepping = true;
		setSuspended(false, detail);
	}

	protected void endStep(DbgpException execption, IDbgpStatus status) {
		stepping = false;
		processOperationEnd(execption, status, DebugEvent.STEP_END);
	}

	public ScriptThread(IScriptDebugTarget target, IDbgpSession session,
			ScriptThreadManager manager) throws DbgpException, CoreException {

		this.target = target;

		this.manager = manager;

		this.streamProxy = target.getStreamManager().makeThreadStreamProxy();

		this.session = session;

		// Initial states
		this.suspended = true;
		this.terminated = false;

		// Stepping
		this.stepping = this.suspended;

		engine = new DbgpDebugger(this, this);

		if (DLTKCore.DEBUG) {
			DbgpDebugger.printEngineInfo(engine);
		}

		final IDbgpExtendedCommands extended = session.getExtendedCommands();

		canSuspend = true; // engine.isSupportsAsync();

		engine.setMaxChildren(256);
		engine.setMaxDepth(2);
		engine.setMaxData(8192);

		engine.redirectStdin();
		engine.setNotifyOk(true);

		engine.redirectStdout();
		engine.redirectStderr();

		session.getNotificationManager().addNotificationListener(
				new IDbgpNotificationListener() {
					private BufferedReader reader = new BufferedReader(
							new InputStreamReader(getStreamProxy().getStdin()));

					public void dbgpNotify(IDbgpNotification notification) {
						try {
							extended.sendStdin(reader.readLine() + "\n");
						} catch (IOException e) {
							// TODO: log exception
							e.printStackTrace();
						} catch (DbgpException e) {
							// TODO: log exception
							e.printStackTrace();
						}
					}
				});

		this.stack = new ScriptStack(this);
	}

	public boolean hasStackFrames() throws DebugException {
		return stack.hasFrames();
	}

	// IThread
	public IStackFrame[] getStackFrames() throws DebugException {
		if (!isSuspended()) {
			return NO_STACK_FRAMES;
		}

		return stack.getFrames();
	}

	public int getPriority() throws DebugException {
		return 0;
	}

	public IStackFrame getTopStackFrame() throws DebugException {
		if (hasStackFrames()) {
			return getStackFrames()[0];
		}

		return null;
	}

	public String getName() throws DebugException {
		return session.getInfo().getThreadId();
	}

	public IBreakpoint[] getBreakpoints() {
		return new IBreakpoint[0];
	}

	protected void setSuspended(boolean value, int detail) {
		suspended = value;
		if (value) {
			DebugEventHelper.fireSuspendEvent(this, detail);

		} else {
			DebugEventHelper.fireResumeEvent(this, detail);
			DebugEventHelper.fireChangeEvent(this);
		}
	}

	protected void setTerminated() {
		terminated = true;
		session.requestTermination();
		manager.terminateThread(this);
	}

	// ISuspendResume

	// Suspend
	public boolean isSuspended() {
		return suspended;
	}

	public boolean canSuspend() {
		return canSuspend && !isTerminated() && !isSuspended();
	}

	public void endSuspend(DbgpException e, IDbgpStatus status) {
		processOperationEnd(e, status, DebugEvent.CLIENT_REQUEST);
	}

	public void suspend() throws DebugException {
		setSuspended(true, DebugEvent.CLIENT_REQUEST);
		engine.suspend();
	}

	// Resume
	public boolean canResume() {
		return !isTerminated() && isSuspended();
	}

	public void endResume(DbgpException e, IDbgpStatus status) {
		processOperationEnd(e, status, DebugEvent.BREAKPOINT);
	}

	public void resume() throws DebugException {
		setSuspended(false, DebugEvent.CLIENT_REQUEST);

		engine.resume();
	}

	// IStep
	public boolean isStepping() {
		return !isTerminated() && stepping;
	}

	// Step into
	public boolean canStepInto() {
		return canStep();
	}

	public void endStepInto(DbgpException e, IDbgpStatus status) {
		endStep(e, status);
	}

	public void stepInto() throws DebugException {
		beginStep(DebugEvent.STEP_INTO);
		engine.stepInto();
	}

	// Step over
	public boolean canStepOver() {
		return canStep();
	}

	public void endStepOver(DbgpException e, IDbgpStatus status) {
		endStep(e, status);
	}

	public void stepOver() throws DebugException {
		beginStep(DebugEvent.STEP_OVER);
		engine.stepOver();
	}

	// Step return
	public boolean canStepReturn() {
		return canStep();
	}

	public void endStepReturn(DbgpException e, IDbgpStatus status) {
		endStep(e, status);
	}

	public void stepReturn() throws DebugException {
		beginStep(DebugEvent.STEP_RETURN);
		engine.stepReturn();
	}

	// ITerminate
	public boolean isTerminated() {
		return terminated;
	}

	public boolean canTerminate() {
		return !isTerminated();
	}

	public void endTerminate(DbgpException e, IDbgpStatus status) {
		processOperationEnd(e, status, DebugEvent.CLIENT_REQUEST);
	}

	public void terminate() throws DebugException {
		engine.terminate();
	}

	public boolean canTerminateEvaluation() {
		// TODO: imlement
		return false;
	}

	public IDbgpSession getDbgpSession() {
		return session;
	}

	public IScriptThreadStreamProxy getStreamProxy() {
		return streamProxy;
	}

	public String toString() {
		return "Thread (" + session.getInfo().getThreadId() + ")";
	}

	public IDebugTarget getDebugTarget() {
		return target.getDebugTarget();
	}

	public void evaluateExpression(final String expression,
			final IWatchExpressionListener listener) {

		// DebugEventHelper.fireResumeEvent(this, DebugEvent.EVALUATION);

		Job job = new Job("Evaluation of \"" + expression + "\"") {
			protected IStatus run(IProgressMonitor monitor) {
				if (getDebugTarget().isTerminated()) {
					return Status.OK_STATUS;
				}

				ScriptWatchExpressionResult result = null;

				try {
					IDbgpProperty property = session.getExtendedCommands()
							.evaluate(expression);

					// DebugEventHelper.fireSuspendEvent(ScriptThread.this,
					// DebugEvent.EVALUATION);

					if (property != null) {
						IScriptVariable variable = new ScriptVariable(
								getDebugTarget(), session, property);
						result = new ScriptWatchExpressionResult(expression,
								variable);
					}

				} catch (DbgpException e) {
					result = new ScriptWatchExpressionResult(expression,
							new String[] { e.getMessage() });
				}

				listener.watchEvaluationFinished(result);

				return Status.OK_STATUS;
			}
		};

		job.setSystem(true);
		job.setUser(false);
		job.schedule();
	}
}