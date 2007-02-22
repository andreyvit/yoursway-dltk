package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.internal.core.model.IThreadManagement;

public class DbgpSuspendOperation extends DbgpOperation {
	private static final String JOB_NAME = "Suspend operation";

	public DbgpSuspendOperation(IThreadManagement management,
			IDbgpCommands commands, IResultHandler finish) throws DbgpException {
		super(management, commands, JOB_NAME, finish);
	}

	protected void process() throws DbgpException {
		// TODO: fix this
		/* boolean success = */getExtended().makeBreak();

		callFinish(null);
	}
}
