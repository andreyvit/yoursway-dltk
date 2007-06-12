/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.dbgp.DbgpServer;
import org.eclipse.dltk.dbgp.DbgpServerException;
import org.eclipse.dltk.dbgp.IDbgpServer;
import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;
import org.eclipse.dltk.debug.core.IDbgpService;

public class DbgpService implements IDbgpService {
	private static final boolean DEBUG = DLTKCore.DEBUG;

	private static final int PORT_SEARCH_BEGIN = 10000;

	private static final int PORT_SEARCH_END = 50000;

	// 30 seconds or 1 hour
	private static final int SERVER_TIMEOUT = 1000 * (DEBUG ? 30 : 60 * 60);

	// 30 seconds or 2 hours
	private static final int CLIENT_TIMEOUT = 1000 * (DEBUG ? 30 : 2 * 60 * 60);

	public static IDbgpService getService() throws Exception {
		return new DbgpService(PORT_SEARCH_BEGIN, PORT_SEARCH_END);
	}

	public static IDbgpService getService(int port) throws Exception {
		return new DbgpService(port);
	}

	public static IDbgpService getService(int portBegin, int portEnd)
			throws Exception {
		return new DbgpService(portBegin, portEnd);
	}

	// Instance
	private final IDbgpServer server;

	protected DbgpService(int port) throws DbgpServerException {
		this.server = new DbgpServer(CLIENT_TIMEOUT, SERVER_TIMEOUT);
		this.server.start(port);
	}

	protected DbgpService(int startPort, int endPort)
			throws DbgpServerException {
		this.server = new DbgpServer(CLIENT_TIMEOUT, SERVER_TIMEOUT);
		this.server.start(startPort, endPort);
	}

	public int getPort() {
		try {
			return server.getPort();
		} catch (DbgpServerException e) {
			DebugPlugin.log(e);
		}

		return -1;
	}

	public void registerAcceptor(String id, IDbgpThreadAcceptor acceptor) {
		try {
			server.registerAcceptor(id, acceptor);
		} catch (DbgpServerException e) {
			DebugPlugin.log(e);
		}
	}

	public IDbgpThreadAcceptor unregisterAcceptor(String id) {
		return server.unregisterAcceptor(id);
	}

	public void shutdown() {
		try {
			server.stop();
		} catch (DbgpServerException e) {
			DebugPlugin.log(e);
		}
	}
}
