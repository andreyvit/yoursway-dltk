/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpStatusCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;

public class DbgpStatusCommands extends DbgpBaseCommands implements
		IDbgpStatusCommands {
	private static final String STATUS_COMMAND = "status"; //$NON-NLS-1$

	public DbgpStatusCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public IDbgpStatus getStatus() throws DbgpException {
		return DbgpXmlEntityParser
				.parseStatus(communicate(createRequest(STATUS_COMMAND)));
	}
}
