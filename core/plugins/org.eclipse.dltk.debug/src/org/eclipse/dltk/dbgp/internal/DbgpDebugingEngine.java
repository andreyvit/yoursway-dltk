/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.eclipse.dltk.dbgp.internal.packets.DbgpLogger;
import org.eclipse.dltk.dbgp.internal.packets.DbgpNotifyPacket;
import org.eclipse.dltk.dbgp.internal.packets.DbgpPacketReceiver;
import org.eclipse.dltk.dbgp.internal.packets.DbgpPacketSender;
import org.eclipse.dltk.dbgp.internal.packets.DbgpResponsePacket;
import org.eclipse.dltk.dbgp.internal.packets.DbgpStreamPacket;
import org.eclipse.dltk.dbgp.internal.packets.IDbgpLogger;

public class DbgpDebugingEngine extends DbgpTermination implements
		IDbgpDebugingEngine, IDbgpTerminationListener {
	private Socket socket;

	private DbgpPacketReceiver receiver;

	private DbgpPacketSender sender;

	private Object terminatedLock = new Object();
	private boolean terminated = false;
	
	private IDbgpLogger logger = new DbgpLogger();

	public DbgpDebugingEngine(Socket socket) throws IOException {
		this.socket = socket;

		receiver = new DbgpPacketReceiver(new BufferedInputStream(socket
				.getInputStream()));
		
		receiver.setLogger(logger);

		receiver.addTerminationListener(this);

		receiver.start();

		sender = new DbgpPacketSender(new BufferedOutputStream(socket
				.getOutputStream()));
		
		sender.setLogger(logger);
	}

	public DbgpStreamPacket getStreamPacket() throws IOException,
			InterruptedException {
		return receiver.getStreamPacket();
	}

	public DbgpNotifyPacket getNotifyPacket() throws IOException,
			InterruptedException {
		return receiver.getNotifyPacket();
	}

	public DbgpResponsePacket getResponsePacket(int transactionId)
			throws IOException, InterruptedException {
		return receiver.getResponsePacket(transactionId);
	}

	public void sendCommand(String command) throws IOException {
		sender.sendCommand(command);
	}

	// IDbgpTerminataion
	public void requestTermination() {
		synchronized (terminatedLock) {
			if (terminated) {
				return;
			}

			try {
				socket.close();
			} catch (IOException e) {
				// TODO: log exception
				e.printStackTrace();
			}
		}
	}

	public void waitTerminated() throws InterruptedException {
		synchronized (terminatedLock) {
			if (terminated) {
				return;
			}

			receiver.waitTerminated();
		}
	}

	public void objectTerminated(Object object, Exception e) {
		synchronized (terminatedLock) {
			receiver.removeTerminationListener(this);
			try {
				receiver.waitTerminated();
			} catch (InterruptedException e1) {
				// OK, interrupted
			}

			terminated = true;
		}

		fireObjectTerminated(e);
	}
}
