/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.DbgpTransactionManager;
import org.w3c.dom.Element;

public class DbgpBaseCommands {

	private IDbgpCommunicator communicator;

	public DbgpBaseCommands(IDbgpCommunicator communicator) {
		this.communicator = communicator;
	}

	protected DbgpRequest createRequest(String command) {
		DbgpRequest request = new DbgpRequest(command);
		request.addOption("-i", DbgpTransactionManager.getInstance()
				.generateId());
		return request;
	}

	protected Element communicate(DbgpRequest request) throws DbgpException {
		return communicator.communicate(request);
	}
	
	protected void send(DbgpRequest request) throws DbgpException {
		communicator.send(request);
	}
}
