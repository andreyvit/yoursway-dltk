package org.eclipse.dltk.dbgp;

public interface IDbgpSessionListener {
	void packetReceived(String content);

	void packetSent(String content);
}
