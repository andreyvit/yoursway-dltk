package org.eclipse.dltk.dbgp.internal;

import java.io.IOException;

import org.eclipse.dltk.dbgp.internal.packets.DbgpNotifyPacket;
import org.eclipse.dltk.dbgp.internal.packets.DbgpResponsePacket;
import org.eclipse.dltk.dbgp.internal.packets.DbgpStreamPacket;

public interface IDbgpDebugingEngine extends IDbgpTermination {
	// Non-blocking method
	void sendCommand(String command) throws IOException;

	// Blocking methods
	DbgpResponsePacket getResponsePacket(int transactionId) throws IOException,
			InterruptedException;

	DbgpNotifyPacket getNotifyPacket() throws IOException, InterruptedException;

	DbgpStreamPacket getStreamPacket() throws IOException, InterruptedException;
}
