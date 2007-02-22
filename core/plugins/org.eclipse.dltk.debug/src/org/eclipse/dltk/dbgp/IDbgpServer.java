package org.eclipse.dltk.dbgp;

import java.io.IOException;

public interface IDbgpServer {
	final int DEFAULT_DBGP_PORT = 9000;

	// Start
	void start(int port) throws IOException;

	void start(int startPort, int endPort) throws IOException;

	// Stop
	void stop() throws IOException;

	// Available
	boolean avaialble();

	// Port
	int getPort();

	// Timeout
	int getClientTimeout();

	int getServerTimeout();

	// Acceptors
	void registerAcceptor(String id, IDbgpThreadAcceptor acceptor);

	IDbgpThreadAcceptor unregisterAcceptor(String id);
}
