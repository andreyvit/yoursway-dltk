/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.debug.model;

import org.eclipse.core.runtime.Assert;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.ISuspendResume;
import org.eclipse.debug.core.model.ITerminate;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;
import org.eclipse.dltk.debug.internal.core.model.DebugEventHelper;
import org.eclipse.dltk.debug.internal.core.model.ScriptDebugTarget;
import org.eclipse.dltk.debug.internal.core.model.ScriptThread;

public class RubyThreadManager implements ISuspendResume, ITerminate,
		IDbgpThreadAcceptor {

	private static final int WAITING = 0;
	private static final int ACCEPTING = 1;
	private static final int TERMINATING = 2;
	private static final int TERMINATED = 3;

	private Object lock = new Object();

	private int state;

	private ScriptThread[] threads;

	private ScriptDebugTarget debugTarget;

	private void destroy() {
		state = TERMINATED;
//		debugTarget.destroy();
	}

	private void addThread(ScriptThread thread) {
		ScriptThread[] newThreads = new ScriptThread[threads.length + 1];
		System.arraycopy(threads, 0, newThreads, 0, threads.length);
		newThreads[threads.length] = thread;
		threads = newThreads;
	}

	private void removeThread(ScriptThread thread) {
		ScriptThread[] newThreads = new ScriptThread[threads.length - 1];
		for (int i = 0, j = 0; i < threads.length; ++i) {
			if (threads[i] != thread) {
				newThreads[j++] = threads[i];
			}
		}
		threads = newThreads;
	}

	protected void createThread(IDbgpSession session) {
		ScriptThread thread = null; //new ScriptThread(this, "My Thread", 0, session);
		addThread(thread);
		DebugEventHelper.fireCreateEvent(thread);
	}

	public void terminateThread(ScriptThread thread) {
		synchronized (lock) {
			removeThread(thread);
			DebugEventHelper.fireTerminateEvent(thread);

			if (!hasThreads()) {
				destroy();
			}
		}
	}

	public RubyThreadManager(ScriptDebugTarget debugTarget) {
		this.debugTarget = debugTarget;
		this.state = WAITING;
		this.threads = new ScriptThread[0];
	}
	
	public ScriptDebugTarget getDebugTarget() {
		return debugTarget;
	}

	public ScriptThread[] getThreads() {
		synchronized (lock) {
			return (ScriptThread[]) threads.clone();
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
		synchronized (lock) {
			if (state == WAITING) {
				destroy();
			} else if (state == ACCEPTING) {
				state = TERMINATING;

				ScriptThread[] temp = getThreads();
				for (int i = 0; i < temp.length; ++i) {
					temp[i].terminate();
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

			createThread(session);

			if (state == WAITING) {
				state = ACCEPTING;
			}
		}
	}

	public void acceptDbgpThreadNotUnavailable() {
		// TODO: implement
     	}
}
