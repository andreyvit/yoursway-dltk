package org.eclipse.dltk.dbgp;

public interface IDbgpRawListener {
	void dbgpPacketReceived(String content);

	void dbgpPacketSent(String content);
}
