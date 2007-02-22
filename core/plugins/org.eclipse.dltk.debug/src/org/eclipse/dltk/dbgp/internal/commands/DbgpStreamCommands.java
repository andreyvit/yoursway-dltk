package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.commands.IDbgpStreamCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;

public class DbgpStreamCommands extends DbgpBaseCommands implements
		IDbgpStreamCommands {
	private static final String STDERR_COMMAND = "stderr";

	private static final String STDOUT_COMMAND = "stdout";

	protected boolean execCommand(String command, int value)
			throws DbgpException {
		DbgpRequest request = createRequest(command);
		request.addOption("-c", value);
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
