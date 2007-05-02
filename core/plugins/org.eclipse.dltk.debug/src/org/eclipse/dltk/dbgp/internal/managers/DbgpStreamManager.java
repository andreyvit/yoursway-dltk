/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.managers;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.dbgp.IDbgpContinuationHandler;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.dbgp.internal.IDbgpDebugingEngine;
import org.eclipse.dltk.dbgp.internal.packets.DbgpStreamPacket;

public class DbgpStreamManager extends DbgpWorkingThread implements
		IDbgpStreamManager {
	private ListenerList listeners = new ListenerList();

	private IDbgpDebugingEngine engine;

	protected void fireStderrReceived(String data) {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IDbgpContinuationHandler) list[i]).stderrReceived(data);
		}
	}

	protected void fireStdoutReceived(String data) {
		Object[] list = listeners.getListeners();
		for (int i = 0; i < list.length; ++i) {
			((IDbgpContinuationHandler) list[i]).stdoutReceived(data);
		}
	}

	protected void workingCycle() throws Exception {
		try {
			while (!Thread.interrupted()) {
				DbgpStreamPacket packet = engine.getStreamPacket();

				if (packet.isStderr()) {
					fireStderrReceived(packet.getContent());
				} else if (packet.isStdout()) {
					fireStdoutReceived(packet.getContent());
				}
			}
		} catch (InterruptedException e) {
			// OK, interrupted
		}
	}

	public DbgpStreamManager(IDbgpDebugingEngine engine) {
		if (engine == null) {
			throw new IllegalArgumentException();
		}

		this.engine = engine;
	}

	public void addListener(IDbgpContinuationHandler listener) {
		listeners.add(listener);
	}

	public void removeListener(IDbgpContinuationHandler listener) {
		listeners.remove(listener);
	}
}
