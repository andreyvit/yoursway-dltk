package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.internal.core.model.IThreadManagement;

public class DbgpTerminateOperation extends DbgpOperation {
	private static final String JOB_NAME = "Terminate operation";

	public DbgpTerminateOperation(IThreadManagement management,
			IDbgpCommands commands, IResultHandler finish) {
		super(management, commands, JOB_NAME, finish);
	}

	protected void process() throws DbgpException {
		callFinish(getCore().stop());
	}
}
