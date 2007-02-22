package org.eclipse.dltk.debug.core;

import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;

public interface IDbgpService {
	boolean available();

	int getPort();

	void registerAcceptor(String id, IDbgpThreadAcceptor acceptor);

	IDbgpThreadAcceptor unregisterAcceptor(String id);
}
