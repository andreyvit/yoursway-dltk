package org.eclipse.dltk.ruby.debug.model;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.internal.core.model.DebugEventHelper;
import org.eclipse.dltk.debug.internal.core.model.operations.DbgpDebugger;
import org.eclipse.dltk.debug.internal.core.model.operations.IDbgpDebuggerFeedback;

public class RubyThread extends PlatformObject implements IThread,
		IDbgpDebuggerFeedback {

	private volatile boolean stepping;

	private volatile boolean suspended;

	private volatile boolean terminated;

	private RubyThreadManager threadManager;

	private DbgpDebugger debugger;

	private IDbgpSession session;
	private int priority;
	private String name;
	private RubyStack stack;
	
	private int suspendCount = 0;
	
	public int getSuspendCount() {
		return suspendCount; 
	}
	

	protected void setSuspended(boolean value, int detail) {
		suspended = value;
		if (value) {
			++suspendCount;
			DebugEventHelper.fireSuspendEvent(this, detail);
		} else {
			DebugEventHelper.fireResumeEvent(this, detail);
		}
	}

	protected void processOperationEnd(DbgpException exception,
			IDbgpStatus status, int suspendDetail) {
		try {
			if (exception != null) {
				throw exception;
			}

			if (status.isBreak()) {			
				stack.setDirty();
				setSuspended(true, suspendDetail);				
			} else if (status.isStopping()) {
				// Stop
				//if (stopDebugger().isStopped()) {
					//setTerminated();
				//}
			} else if (status.isStopped()) {
				//setTerminated();
			}
		} catch (DbgpException e) {
			//try {
				//streamProxy.getStdout().write(e.getMessage().getBytes());
				//setTerminated();
			//} catch (IOException e1) {
				// TODO: log properly
				//e1.printStackTrace();
			//}
			
			e.printStackTrace();
		}
	}

	public RubyThread(RubyThreadManager threadManager, String name,
			int priority, IDbgpSession session) {
		this.threadManager = threadManager;
		this.name = name;
		this.priority = priority;
		this.session = session;
		this.stack = new RubyStack(this);
		this.debugger = new DbgpDebugger(session, this);

		this.stepping = false; // true during steps
		this.suspended = true;
		this.terminated = false;
	}

	public IDbgpSession getSession() {
		return session;
	}

	public IBreakpoint[] getBreakpoints() {
		return new IBreakpoint[0];
	}

	public String getName() throws DebugException {
		return name;
	}

	public int getPriority() throws DebugException {
		return priority;
	}

	public IStackFrame[] getStackFrames() throws DebugException {
		if (!isSuspended()) {
			return new IStackFrame[0];
		}

		return stack.getFrames();
	}

	public IStackFrame getTopStackFrame() throws DebugException {
		if (!suspended) {
			return null;
		}

		return stack.getTopFrame();
	}

	public boolean hasStackFrames() throws DebugException {
		if (!suspended) {
			return false;
		}

		return stack.hasFrames();
	}

	// =========================================================================
	// ========================== IDebugElement ================================
	// =========================================================================
	public IDebugTarget getDebugTarget() {
		return threadManager.getDebugTarget();
	}

	public ILaunch getLaunch() {
		return getDebugTarget().getLaunch();
	}

	public String getModelIdentifier() {
		return getDebugTarget().getModelIdentifier();
	}

	// =========================================================================
	// ========================== ISuspendResume ===============================
	// =========================================================================
	public boolean canResume() {
		return false;
	}

	public void resume() throws DebugException {
	}

	public boolean canSuspend() {
		return false;
	}

	public void suspend() throws DebugException {
	}

	public boolean isSuspended() {
		return suspended;
	}

	// =========================================================================
	// ============================ IStep ======================================
	// =========================================================================
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

	public boolean isStepping() {
		return stepping;
	}

	public boolean canStepInto() {
		return canStep();
	}

	public void stepInto() throws DebugException {
		beginStep(DebugEvent.STEP_INTO);
		debugger.stepInto();
	}

	public boolean canStepOver() {
		return canStep();
	}

	public void stepOver() throws DebugException {
		beginStep(DebugEvent.STEP_OVER);
		debugger.stepOver();
	}

	public boolean canStepReturn() {
		return canStep();
	}

	public void stepReturn() throws DebugException {
		beginStep(DebugEvent.STEP_RETURN);
		debugger.stepReturn();
	}

	// =========================================================================
	// ========================== ITerminate ===================================
	// =========================================================================
	public boolean canTerminate() {
		return true;
	}

	public boolean isTerminated() {
		return false;
	}

	public void terminate() throws DebugException {
		threadManager.terminateThread(this);
	}

	// ===================================================================================
	// ========================= IDbgpDebuggerFeedback
	// ===================================
	// ===================================================================================
	public void endResume(DbgpException e, IDbgpStatus status) {
	}

	public void endStepInto(DbgpException e, IDbgpStatus status) {
		endStep(e, status);
	}

	public void endStepOver(DbgpException e, IDbgpStatus status) {
		endStep(e, status);
	}

	public void endStepReturn(DbgpException e, IDbgpStatus status) {
		endStep(e, status);
	}

	public void endSuspend(DbgpException e, IDbgpStatus status) {
	}

	public void endTerminate(DbgpException e, IDbgpStatus status) {
	}
}
