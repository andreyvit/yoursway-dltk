/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.commands.IDbgpStreamCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;

public class DbgpStreamCommands extends DbgpBaseCommands implements
		IDbgpStreamCommands {
	private static final String STDERR_COMMAND = "stderr"; //$NON-NLS-1$

	private static final String STDOUT_COMMAND = "stdout"; //$NON-NLS-1$

	protected boolean execCommand(String command, int value)
			throws DbgpException {
		DbgpRequest request = createRequest(command);
		request.addOption("-c", value); //$NON-NLS-1$
		return DbgpXmlParser.parseSuccess(communicate(request));
	}

	public DbgpStreamCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public boolean configureStdout(int value) throws DbgpException {
		return execCommand(STDOUT_COMMAND, value);
	}

	public boolean configureStderr(int value) throws DbgpException {
		return execCommand(STDERR_COMMAND, value);
	}
}
