package org.eclipse.dltk.dbgp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.internal.DbgpDebugingEngine;
import org.eclipse.dltk.dbgp.internal.DbgpSession;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;

public class DbgpServer extends DbgpWorkingThread implements IDbgpServer {
	private int clientTimeout;
	private int serverTimeout;

	private Map acceptors;

	private ServerSocket server;

	protected void workingCycle() throws Exception, IOException {
		try {
			while (true) {
				if (DLTKCore.DEBUG) {
					System.out.println("Wating for connections...");
				}

				Socket client = server.accept();
				if (DLTKCore.DEBUG) {
					System.out.println("Client connected!");
				}

				client.setSoTimeout(clientTimeout);

				DbgpSession session = new DbgpSession(new DbgpDebugingEngine(
						client));

				String id = session.getInfo().getIdeKey();

				IDbgpThreadAcceptor acceptor = (IDbgpThreadAcceptor) acceptors
						.get(id);

				if (acceptor != null) {
					acceptor.acceptDbgpThread(session);
				} else {
					session.requestTermination();
				}
			}
		} finally {
			if (!server.isClosed()) {
				server.close();
			}
		}
	}

	protected void tryStart(int port) throws IOException {
		server = new ServerSocket(port);
		server.setSoTimeout(serverTimeout);

		super.start();
	}

	public DbgpServer(int clientTimeout, int serverTimeout) {
		this.clientTimeout = clientTimeout;
		this.serverTimeout = serverTimeout;

		this.acceptors = Collections.synchronizedMap(new HashMap());
	}

	public void start(int port) throws IOException {
		tryStart(port);
	}

	public void start(int startPort, int endPort) throws IOException {
		if (startPort > endPort) {
			throw new IllegalArgumentException(
					"startPort should be less or equal than endPort");
		}

		int port = startPort;
		while (port <= endPort) {
			try {
				tryStart(port);
				if (DLTKCore.DEBUG) {
					System.out.println("Listening on port: " + port);
				}
				return;
			} catch (IOException e) {
				port++;
			}
		}

		throw new IOException("Can't find available port in rage " + startPort
				+ " ... " + endPort);
	}

	// Stop
	public void stop() throws IOException {
		server.close();
	}

	// Port
	public int getPort() {
		return server.getLocalPort();
	}

	// Timeout
	public int getClientTimeout() {
		return clientTimeout;
	}

	public int getServerTimeout() {
		return serverTimeout;
	}

	// Accpetors
	public void registerAcceptor(String id, IDbgpThreadAcceptor acceptor) {
		acceptors.put(id, acceptor);
	}

	public IDbgpThreadAcceptor unregisterAcceptor(String id) {
		return (IDbgpThreadAcceptor) acceptors.remove(id);
	}

	public boolean avaialble() {
		return !server.isClosed();
	}
}
