/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import java.io.IOException;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.exceptions.DbgpIOException;
import org.eclipse.dltk.dbgp.exceptions.DbgpOpertionCanceledException;
import org.eclipse.dltk.dbgp.exceptions.DbgpTimeoutException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.IDbgpDebugingEngine;
import org.eclipse.dltk.dbgp.internal.packets.DbgpResponsePacket;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;
import org.w3c.dom.Element;

public class DbgpDebuggingEngineCommunicator implements IDbgpCommunicator {
	private static final int TIMEOUT = 500000;

	private final IDbgpDebugingEngine engine;

	private void sendRequest(String command) throws IOException {
		engine.sendCommand(command);
	}

	private DbgpResponsePacket receiveResponse(int transactionId)
			throws IOException, InterruptedException {
		return engine.getResponsePacket(transactionId, TIMEOUT);
	}

	public DbgpDebuggingEngineCommunicator(IDbgpDebugingEngine engine) {
		if (engine == null) {
			throw new IllegalArgumentException();
		}

		this.engine = engine;
	}

	public Element communicate(DbgpRequest request) throws DbgpException {
		try {
			sendRequest(request.toString());

			DbgpResponsePacket packet = receiveResponse(Integer
					.parseInt(request.getOption("-i"))); //$NON-NLS-1$

			if (packet == null) {
				throw new DbgpTimeoutException();
			}

			Element response = packet.getContent();

			DbgpException e = DbgpXmlParser.checkError(response);
			if (e != null) {
				throw e;
			}

			return response;
		} catch (InterruptedException e) {
			throw new DbgpOpertionCanceledException();
		} catch (IOException e) {
			throw new DbgpIOException(e);
		}
	}

	public void send(DbgpRequest request) throws DbgpException {
		try {
			sendRequest(request.toString());
		} catch (IOException e) {
			throw new DbgpIOException(e);
		}
	}
}
