package org.eclipse.dltk.debug.internal.core.model;

import java.io.IOException;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.DbgpServer;
import org.eclipse.dltk.dbgp.IDbgpServer;
import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;

public class DbgpService implements IDbgpService {
	private static final int PORT_SEARCH_BEGIN = 10000;
	private static final int PORT_SEARCH_END = 50000;

	private static final int SERVER_TIMEOUT = 1000 * (DLTKCore.DEBUG ? 30
			: 60 * 60); // 30 seconds or 1 hour

	private static final int CLIENT_TIMEOUT = 1000 * (DLTKCore.DEBUG ? 30
			: 120 * 60); // // 30 seconds or 1 hour

	private static DbgpService instance;

	public static IDbgpService getInstance() {
		if (instance == null) {
			instance = new DbgpService(PORT_SEARCH_BEGIN, PORT_SEARCH_END);
		} else if (!instance.available()) {
			instance = new DbgpService(PORT_SEARCH_BEGIN, PORT_SEARCH_END);
		}

		return instance;
	}

	public static IDbgpService getInstance(int port) {
		if (instance == null) {
			instance = new DbgpService(port);
		} else if (!instance.available()) {
			instance = new DbgpService(port);
		} else if (instance.getPort() != port) {
			try {
				instance.stop();
			} catch (IOException e) {
				DLTKDebugPlugin.log(e);
			}
			instance = new DbgpService(port);
		}

		return instance;
	}

	public static void shutdown() {
		if (instance != null) {
			try {
				instance.stop();
				instance = null;
			} catch (IOException e) {
				DLTKDebugPlugin.log(e);
			}
		}
	}

	// Instance
	private IDbgpServer server;

	private void stop() throws IOException {
		try {
			server.stop();
		} catch (IOException e) {
			DLTKDebugPlugin.log(e);
		}
	}

	protected void checkAvailable() {
		if (!available()) {
			throw new IllegalStateException("DbgpService is unavailable");
		}
	}

	protected DbgpService(int port) {
		this.server = new DbgpServer(CLIENT_TIMEOUT, SERVER_TIMEOUT);
		try {
			this.server.start(port);
		} catch (IOException e) {
			DLTKDebugPlugin.log(e);
		}
	}

	protected DbgpService(int startPort, int endPort) {
		this.server = new DbgpServer(CLIENT_TIMEOUT, SERVER_TIMEOUT);
		try {
			this.server.start(startPort, endPort);
		} catch (IOException e) {
			DLTKDebugPlugin.log(e);
		}
	}

	public boolean available() {
		return server.avaialble();
	}

	public int getPort() {
		checkAvailable();
		return server.getPort();
	}

	public void registerAcceptor(String id, IDbgpThreadAcceptor acceptor) {
		checkAvailable();
		server.registerAcceptor(id, acceptor);
	}

	public IDbgpThreadAcceptor unregisterAcceptor(String id) {
		checkAvailable();
		return server.unregisterAcceptor(id);
	}
}
