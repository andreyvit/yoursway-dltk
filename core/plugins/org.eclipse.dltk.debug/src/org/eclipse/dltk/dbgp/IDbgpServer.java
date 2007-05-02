/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
