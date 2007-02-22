package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpStatusCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;

public class DbgpStatusCommands extends DbgpBaseCommands implements
		IDbgpStatusCommands {
	private static final String STATUS_COMMAND = "status";

	public DbgpStatusCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public IDbgpStatus getStatus() throws DbgpException {
		return DbgpXmlEntityParser
				.parseStatus(communicate(createRequest(STATUS_COMMAND)));
	}
}
