/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.IDbgpContinuationHandler;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public class ScriptThreadManager implements IScriptThreadManager {
	private static final boolean DEBUG = DLTKCore.DEBUG;
	
	// Helper methods
	private interface IThreadBoolean {
		boolean get(IThread thread);
	}

	private boolean getThreadBoolean(IThreadBoolean b) {
		synchronized (threads) {
			IThread[] ths = getThreads();

			if (ths.length == 0) {
				return false;
			}

			for (int i = 0; i < ths.length; ++i) {
				if (!b.get(ths[i])) {
					return false;
				}
			}

			return true;
		}
	}

	private final ListenerList listeners = new ListenerList(
			ListenerList.IDENTITY);

	private final List threads = new ArrayList();

	private volatile boolean waitingForThreads = true;

	private final ScriptDebugTarget target;

	private IDbgpContinuationHandler outputListener;

	protected void fireThreadAccepted(IScriptThread thread, boolean first) {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IScriptThreadManagerListener) list[i])
					.threadAccepted(thread, first);
		}
	}

	protected void fireAllThreadsTerminated() {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IScriptThreadManagerListener) list[i]).allThreadsTerminated();
		}
	}

	public void addListener(IScriptThreadManagerListener listener) {
		listeners.add(listener);
	}

	public void removeListener(IScriptThreadManagerListener listener) {
		listeners.remove(listener);
	}

	public boolean isWaitingForThreads() {
		return waitingForThreads;
	}

	public boolean hasThreads() {
		synchronized (threads) {
			return !threads.isEmpty();
		}
	}

	public IScriptThread[] getThreads() {
		synchronized (threads) {
			return (IScriptThread[]) threads.toArray(new IScriptThread[threads
					.size()]);
		}
	}

	public ScriptThreadManager(ScriptDebugTarget target) {
		if (target == null) {
			throw new IllegalArgumentException();
		}

		this.target = target;
		this.outputListener = new IDbgpContinuationHandler() {
			public void stderrReceived(String data) {
				final IScriptStreamProxy proxy = ScriptThreadManager.this.target.getStreamProxy();
				if (proxy != null) {
					try {
						final OutputStream err = proxy.getStderr();
						err.write(data.getBytes());
						err.flush();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (DEBUG) {
					System.out.println("Received (stderr): " + data); //$NON-NLS-1$
				}
			}

			public void stdoutReceived(String data) {
				final IScriptStreamProxy proxy = ScriptThreadManager.this.target.getStreamProxy();
				if (proxy != null) {
					try {
						final OutputStream out = proxy.getStdout();
						out.write(data.getBytes());
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (DEBUG) {
					System.out.println("Received (stdout): " + data); //$NON-NLS-1$
				}
			}			
		};
	}

	// IDbgpThreadAcceptor
	public void acceptDbgpThread(IDbgpSession session) {
		try {
			DbgpException error = session.getInfo().getError();
			if (error != null) {
				throw error;
			}
			
			session.getStreamManager().addListener(outputListener);
			ScriptThread thread = new ScriptThread(target, session, this);
			addThread(thread);

			boolean isFirstThread = waitingForThreads;
			waitingForThreads = false;

			fireThreadAccepted(thread, isFirstThread);

			DebugEventHelper.fireCreateEvent(thread);

			// Auto start
			thread.resume();
		} catch (Exception e) {
			try { target.terminate();} catch (DebugException e1) {}
			DLTKDebugPlugin.log(e);
		}
	}

	private void addThread(ScriptThread thread) {
		synchronized (threads) {
			threads.add(thread);			
		}
	}

	public void acceptDbgpThreadNotUnavailable() {

	}

	public void terminateThread(IScriptThread thread) {
		synchronized (threads) {
			threads.remove(thread);	
			DebugEventHelper.fireTerminateEvent(thread);			
			
			IDbgpSession session = ((ScriptThread) thread).getDbgpSession();
			session.getStreamManager().removeListener(outputListener);
			
			if (!hasThreads()) {
				fireAllThreadsTerminated();
			}
		}
	}

	// ITerminate
	public boolean canTerminate() {
		synchronized (threads) {
			IThread[] ths = getThreads();

			if (ths.length == 0) {
				if (waitingForThreads) {
					return true;
				} else {
					return false;
				}
			}

			for (int i = 0; i < ths.length; ++i) {
				if (!ths[i].canTerminate()) {
					return false;
				}
			}

			return true;
		}
	}

	public boolean isTerminated() {
		if (!hasThreads()) {
			return !isWaitingForThreads();
		}

		return getThreadBoolean(new IThreadBoolean() {
			public boolean get(IThread thread) {
				return thread.isTerminated();
			}
		});
	}

	public void terminate() throws DebugException {
		target.terminate();
	}

	public void sendTerminationRequest() throws DebugException {
		synchronized (threads) {
			IScriptThread[] threads = getThreads();
			for (int i = 0; i < threads.length; ++i) {
				threads[i].sendTerminationRequest();
			}
			waitingForThreads = false;
		}
	}
	
	public boolean canResume() {
		return getThreadBoolean(new IThreadBoolean() {
			public boolean get(IThread thread) {
				return thread.canResume();
			}
		});
	}

	public boolean canSuspend() {
		return getThreadBoolean(new IThreadBoolean() {
			public boolean get(IThread thread) {
				return thread.canSuspend();
			}
		});
	}

	public boolean isSuspended() {
		return getThreadBoolean(new IThreadBoolean() {
			public boolean get(IThread thread) {
				return thread.isSuspended();
			}
		});
	}

	public void resume() throws DebugException {
		synchronized (threads) {
			IThread[] threads = getThreads();
			for (int i = 0; i < threads.length; ++i) {
				threads[i].resume();
			}
		}
	}

	public void suspend() throws DebugException {
		synchronized (threads) {
			IThread[] threads = getThreads();
			for (int i = 0; i < threads.length; ++i) {
				threads[i].suspend();
			}
		}
	}
	
	public void refreshThreads() {
		synchronized(threads) {
			IThread[] threads = getThreads();
			for (int i = 0; i < threads.length; ++i) {
				((IScriptThread) threads[i]).updateStackFrames();
			}
		}
	}
}