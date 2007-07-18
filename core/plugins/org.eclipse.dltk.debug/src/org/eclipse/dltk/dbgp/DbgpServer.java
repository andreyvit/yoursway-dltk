package org.eclipse.dltk.dbgp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.dltk.dbgp.internal.DbgpDebugingEngine;
import org.eclipse.dltk.dbgp.internal.DbgpSession;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;

public class DbgpServer extends DbgpWorkingThread {
	private final int port;
	private ServerSocket server;

	private final int serverTimeout;
	private final int clientTimeout;

	public static int findAvailablePort(int fromPort, int toPort) {
		if (fromPort > toPort) {
			throw new IllegalArgumentException(
					"startPort should be less or equal than endPort");
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
				final Socket client = server.accept();
				client.setSoTimeout(clientTimeout);

				if (listener != null) {
					final DbgpDebugingEngine dbgpDebugingEngine = new DbgpDebugingEngine(
							client);
					final DbgpSession session = new DbgpSession(
							dbgpDebugingEngine);
					listener.clientConnected(session);
				}
			}
		} finally {
			server.close();
		}
	}

	public DbgpServer(int port, int serverTimeout, int clientTimeout) {
		super("DbgpServer");

		this.port = port;
		this.serverTimeout = serverTimeout;
		this.clientTimeout = clientTimeout;
	}

	private IDbgpServerListener listener;

	public void setListener(IDbgpServerListener listener) {
		this.listener = listener;
	}
}
