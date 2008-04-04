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
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public class NewScriptThreadManager implements IScriptThreadManager {

	private static final int WAITING = 0;
	private static final int ACCEPTING = 1;
	private static final int TERMINATING = 2;
	private static final int TERMINATED = 3;

	private final ListenerList listeners = new ListenerList(
			ListenerList.IDENTITY);

	private Object lock = new Object();

	private int state;

	private ScriptThread[] threads;

	private IScriptDebugTarget debugTarget;

	private void destroy() {
		state = TERMINATED;
		// debugTarget.destroy();
	}

	private void addThread(ScriptThread thread) {
		ScriptThread[] newThreads = new ScriptThread[threads.length + 1];
		System.arraycopy(threads, 0, newThreads, 0, threads.length);
		newThreads[threads.length] = thread;
		threads = newThreads;
	}

	private void removeThread(IScriptThread thread) {
		ScriptThread[] newThreads = new ScriptThread[threads.length - 1];
		for (int i = 0, j = 0; i < threads.length; ++i) {
			if (threads[i] != thread) {
				newThreads[j++] = threads[i];
			}
		}
		threads = newThreads;
	}

	protected IScriptThread createThread(IDbgpSession session) throws DbgpException, CoreException {
		ScriptThread thread = new ScriptThread(debugTarget, session, this);
		
		addThread(thread);
		DebugEventHelper.fireCreateEvent(thread);
		
		return thread;
	}

	public void terminateThread(IScriptThread thread) {
		synchronized (lock) {
			removeThread(thread);
			DebugEventHelper.fireTerminateEvent(thread);

			if (!hasThreads()) {
				destroy();
				fireAllThreadsTerminated();
			}
		}
	}

	public NewScriptThreadManager(IScriptDebugTarget debugTarget) {
		this.debugTarget = debugTarget;
		this.state = WAITING;
		this.threads = new ScriptThread[0];
	}

	public IScriptDebugTarget getDebugTarget() {
		return debugTarget;
	}

	public IScriptThread[] getThreads() {
		synchronized (lock) {
			return (IScriptThread[]) threads.clone();
		}
	}

	public boolean hasThreads() {
		synchronized (lock) {
			return threads.length > 0;
		}
	}

	// =========================================================================
	// ======================== ISuspendResume =================================
	// =========================================================================
	public boolean canResume() {
		synchronized (lock) {
			if (state == ACCEPTING) {
				Assert.isTrue(hasThreads());

				for (int i = 0; i < threads.length; ++i) {
					if (threads[i].canResume()) {
						return true;
					}
				}
			}

			return false;
		}
	}

	public boolean canSuspend() {
		synchronized (lock) {
			if (state == ACCEPTING) {
				Assert.isTrue(hasThreads());

				for (int i = 0; i < threads.length; ++i) {
					if (threads[i].canSuspend()) {
						return true;
					}
				}
			}

			return false;
		}
	}

	public boolean isSuspended() {
		synchronized (lock) {
			if (state != ACCEPTING) {
				return false;
			}

			Assert.isTrue(hasThreads());

			for (int i = 0; i < threads.length; ++i) {
				if (!threads[i].isSuspended()) {
					return false;
				}
			}

			return true;
		}
	}

	public void resume() throws DebugException {
		synchronized (lock) {
			if (state == ACCEPTING) {
				Assert.isTrue(hasThreads());

				for (int i = 0; i < threads.length; ++i) {
					ScriptThread thread = threads[i];
					if (thread.canResume()) {
						thread.resume();
					}
				}
			}
		}
	}

	public void suspend() throws DebugException {
		synchronized (lock) {
			if (state == ACCEPTING) {
				Assert.isTrue(hasThreads());

				for (int i = 0; i < threads.length; ++i) {
					ScriptThread thread = threads[i];
					if (thread.canSuspend()) {
						thread.suspend();
					}
				}
			}
		}
	}

	public void refreshThreads() {
		synchronized(lock) {
			for (int i = 0; i < threads.length; ++i) {
				ScriptThread thread = threads[i];
				thread.updateStackFrames();
			}
		}
	}
	
	// =========================================================================
	// ============================ ITerminate =================================
	// =========================================================================
	public boolean canTerminate() {
		synchronized (lock) {
			return state == WAITING || state == ACCEPTING;
		}
	}

	public boolean isTerminated() {
		synchronized (lock) {
			return state == TERMINATED;
		}
	}

	public void terminate() throws DebugException {
		debugTarget.terminate();
	}

	public void sendTerminationRequest() throws DebugException {
		synchronized (lock) {
			if (state == WAITING) {
				destroy();
			} else if (state == ACCEPTING) {
				state = TERMINATING;

				IScriptThread[] temp = getThreads();
				for (int i = 0; i < temp.length; ++i) {
					temp[i].sendTerminationRequest();
				}
			}
		}
	}
	
	// =========================================================================
	// ======================= IDbgpThreadAcceptor =============================
	// =========================================================================
	public void acceptDbgpThread(IDbgpSession session) {
		synchronized (lock) {
			if (state == TERMINATED || state == TERMINATING) {
				return;
			}

			try {
				final IScriptThread thread = createThread(session);

				boolean first = false;
				if (state == WAITING) {
					first = true;
					state = ACCEPTING;
				}
				
				fireThreadAccepted(thread, first);
			} catch (DbgpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void acceptDbgpThreadNotUnavailable() {
		// TODO: implement
	}

	// ===================================
	// Listeners
	public void addListener(IScriptThreadManagerListener listener) {
		listeners.add(listener);
	}

	public void removeListener(IScriptThreadManagerListener listener) {
		listeners.remove(listener);
	}

	protected void fireThreadAccepted(IScriptThread thread, boolean first) {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IScriptThreadManagerListener) list[i]).threadAccepted(thread,
					first);
		}
	}

	protected void fireAllThreadsTerminated() {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IScriptThreadManagerListener) list[i]).allThreadsTerminated();
		}
	}

	public boolean isWaitingForThreads() {
		return state == WAITING;
	}
}
