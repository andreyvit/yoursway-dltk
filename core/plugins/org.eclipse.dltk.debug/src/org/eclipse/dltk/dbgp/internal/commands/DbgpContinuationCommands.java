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
import org.eclipse.dltk.dbgp.commands.IDbgpContinuationCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;

public class DbgpContinuationCommands extends DbgpBaseCommands implements
		IDbgpContinuationCommands {
	private static final String RUN_COMMAND = "run";

	private static final String STEP_INTO_COMMAND = "step_into";

	private static final String STEP_OVER_COMMAND = "step_over";

	private static final String STEP_OUT_COMMAND = "step_out";

	private static final String STOP_COMMAND = "stop";

	private static final String DETACH_COMMAND = "detach";

	protected IDbgpStatus execCommand(String command) throws DbgpException {
		return DbgpXmlEntityParser
				.parseStatus(communicate(createRequest(command)));
	}

	public DbgpContinuationCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public IDbgpStatus run()
			throws DbgpException {
		return execCommand(RUN_COMMAND);
	}

	public IDbgpStatus stepInto()
			throws DbgpException {
		return execCommand(STEP_INTO_COMMAND);
	}

	public IDbgpStatus stepOut()
			throws DbgpException {
		return execCommand(STEP_OUT_COMMAND);
	}

	public IDbgpStatus stepOver()
			throws DbgpException {
		return execCommand(STEP_OVER_COMMAND);
	}

	public IDbgpStatus stop() throws DbgpException {
		return execCommand(STOP_COMMAND);
	}

	public IDbgpStatus detach() throws DbgpException {
		return execCommand(DETACH_COMMAND);
	}
}
