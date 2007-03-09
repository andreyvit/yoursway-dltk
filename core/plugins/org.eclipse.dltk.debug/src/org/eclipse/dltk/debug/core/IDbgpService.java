package org.eclipse.dltk.debug.core;

import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;

public interface IDbgpService {
	int getPort();
	
	void shutdown();
		
	void registerAcceptor(String id, IDbgpThreadAcceptor acceptor);
	IDbgpThreadAcceptor unregisterAcceptor(String id);
}
