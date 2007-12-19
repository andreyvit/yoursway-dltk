/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model.operations;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.exceptions.DbgpOpertionCanceledException;
import org.eclipse.dltk.dbgp.exceptions.DbgpTimeoutException;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public abstract class DbgpOperation {
	private static final boolean DEBUG = DLTKCore.DEBUG;

	public interface IResultHandler {
		void finish(IDbgpStatus status, DbgpException e);
	}

	private final Job job;
	private final IDbgpCommands commands;

	protected IDbgpCoreCommands getCore() {
		return commands.getCoreCommands();
	}

	protected IDbgpExtendedCommands getExtended() {
		return commands.getExtendedCommands();
	}

	private final IResultHandler resultHandler;

	protected void callFinish(IDbgpStatus status) {
		if (DEBUG) {
			System.out.println("Status: " + status);
		}

		resultHandler.finish(status, null);
	}

	protected DbgpOperation(IScriptThread thread, String name,
			IResultHandler handler) {
		this.resultHandler = handler;

		this.commands = thread.getDbgpSession();

		job = new Job(name) {
			protected IStatus run(IProgressMonitor monitor) {
				// TODO: improve
				try {
					process();
				} catch (DbgpOpertionCanceledException e) {
					// Operation was canceled cause debugger is shutting down
				} catch (DbgpTimeoutException e) {
					System.err.println("Timeout exception!!!");
					e.printStackTrace();
				} catch (DbgpException e) {
					if (DLTKCore.DEBUG) {
						System.out.println("Exception: " + e.getMessage());
						System.out.println(e.getClass());
						e.printStackTrace();
					}

					resultHandler.finish(null, e);
				}

				return Status.OK_STATUS;
			}
		};
		job.setSystem(false);
		job.setUser(false);
	}

	public void schedule() {
		if (DEBUG) {
			System.out.println("Starting operation: " + job.getName());
		}

		job.schedule();
	}

	protected abstract void process() throws DbgpException;
}
