package org.eclipse.dltk.debug.internal.core.model.operations;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.IDbgpContinuationHandler;
import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.internal.core.model.IThreadManagement;

public abstract class DbgpOperation {
	private static final boolean DEBUG = DLTKCore.DEBUG;

	public interface IResultHandler {
		void finish(IDbgpStatus status, DbgpException e);
	}
	
	private Job job;

	private IDbgpContinuationHandler continuationHandler;

	private IDbgpCommands commands;

	protected IDbgpCoreCommands getCore() {
		return commands.getCoreCommands();
	}

	protected IDbgpExtendedCommands getExtended() {
		return commands.getExtendedCommands();
	}

	protected IDbgpContinuationHandler getContinuationHandler() {
		return continuationHandler;
	}

	private IResultHandler resultHandler;

	protected void callFinish(IDbgpStatus status) {
		if (DEBUG) {
			System.out.println("Status: " + status);
		}

		resultHandler.finish(status, null);
	}

	protected DbgpOperation(IDbgpCommands commands, String name, IResultHandler handler) {
		if (commands == null) {
			throw new IllegalArgumentException();
		}

		this.resultHandler = handler;
		
		this.commands = commands;

		job = new Job(name) {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					process();
				} catch (DbgpException e) {
					if (DEBUG) {
						System.out.println("Exception: " + e.getMessage());
					}

					resultHandler.finish(null, e);
				}

				return Status.OK_STATUS;
			}
		};
		job.setSystem(true);
		job.setUser(false);

		this.continuationHandler = new IDbgpContinuationHandler() {
			public void stderrReceived(String data) {
				//try {
//					OutputStream err = getManagement().getStreamProxy()
//							.getStderr();
//					err.write(data.getBytes());
//					err.flush();
					System.out.println("Received (stderr): " + data);
					
				//} catch (IOException e) {
					//e.printStackTrace();
				//}
			}

			public void stdoutReceived(String data) {
				//try {
					//OutputStream out = getManagement().getStreamProxy()
						//	.getStdout();
					//out.write(data.getBytes());
					//out.flush();
				//} catch (IOException e) {
					//e.printStackTrace();
				//}
				
				System.out.println("Received (stdout): " + data);
			}
		};
	}

	public void schedule() {
		if (DEBUG) {
			System.out.println("Starting operation: " + job.getName());
		}

		job.schedule();
	}

	protected abstract void process() throws DbgpException;
}
