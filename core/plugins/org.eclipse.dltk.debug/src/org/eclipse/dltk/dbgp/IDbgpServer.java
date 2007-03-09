package org.eclipse.dltk.dbgp;

public interface IDbgpServer {
	final int DEFAULT_DBGP_PORT = 9000;
	
	// Timeout
	int getClientTimeout();

	int getServerTimeout();

	// Start
	void start(int port) throws DbgpServerException;

	void start(int startPort, int endPort) throws DbgpServerException;

	// Stop
	void stop() throws DbgpServerException;

	// Port
	int getPort() throws DbgpServerException;

	// Acceptors
	void registerAcceptor(String id, IDbgpThreadAcceptor acceptor) throws DbgpServerException;

	IDbgpThreadAcceptor unregisterAcceptor(String id);
}
