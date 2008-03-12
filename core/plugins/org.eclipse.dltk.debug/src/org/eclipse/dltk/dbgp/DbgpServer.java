package org.eclipse.dltk.dbgp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.dbgp.internal.DbgpDebugingEngine;
import org.eclipse.dltk.dbgp.internal.DbgpSession;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;

public class DbgpServer extends DbgpWorkingThread {
	private final int port;
	private ServerSocket server;

	private final int serverTimeout;
	private final int clientTimeout;

	public static int findAvailablePort(int fromPort, int toPort) {
		if (fromPort > toPort) {
			throw new IllegalArgumentException(
					Messages.DbgpServer_startPortShouldBeLessThanOrEqualToEndPort);
		}

		int port = fromPort;
		ServerSocket socket = null;
		while (port <= toPort) {
			try {
				socket = new ServerSocket(port);
				return port;
			} catch (IOException e) {
				++port;
			} finally {
				if (socket != null)
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}

		return -1;
	}

	protected void workingCycle() throws Exception, IOException {
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(serverTimeout);

			while (!Thread.interrupted()) {
				Socket client = acceptClient();
				client.setSoTimeout(clientTimeout);
				createSession(client);
			}
		} finally {
			if (server != null && !server.isClosed()) {
				server.close();
			}
		}
	}

	private void createSession(final Socket client) {
		Job job = new Job(Messages.DbgpServer_acceptingDebuggingEngineConnection) {
			protected IStatus run(IProgressMonitor monitor) {
				if (listener != null) {
					DbgpDebugingEngine dbgpDebugingEngine = null;
					DbgpSession session = null;
					
					try {
						dbgpDebugingEngine = new DbgpDebugingEngine(client);
						session = new DbgpSession(dbgpDebugingEngine);
						listener.clientConnected(session);
					}
					catch (Exception e) {
						DLTKDebugPlugin.log(e);
						if (dbgpDebugingEngine != null)
							dbgpDebugingEngine.requestTermination();
					}
				}
				return Status.OK_STATUS;				
			} 
		};
		job.schedule();
	}

	private Socket acceptClient() throws IOException {
		Socket client = null; 
		while (client == null) {
			try {
				client = server.accept();
			}
			catch (SocketTimeoutException e) {}
		}
		return client;
	}

	public DbgpServer(int port, int serverTimeout, int clientTimeout) {
		super("DbgpServer"); //$NON-NLS-1$

		this.port = port;
		this.serverTimeout = serverTimeout;
		this.clientTimeout = clientTimeout;
	}

	public void requestTermination() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.requestTermination();
	}

	private IDbgpServerListener listener;

	public void setListener(IDbgpServerListener listener) {
		this.listener = listener;
	}
}
