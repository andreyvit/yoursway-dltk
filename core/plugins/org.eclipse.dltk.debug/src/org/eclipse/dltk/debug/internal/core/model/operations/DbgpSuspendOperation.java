package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpStatus;
import org.eclipse.dltk.debug.internal.core.model.IThreadManagement;

public class DbgpSuspendOperation extends DbgpOperation {
	private static final String JOB_NAME = "Suspend operation";

	public DbgpSuspendOperation(IThreadManagement management,
			IDbgpCommands commands, IResultHandler finish) throws DbgpException {
		super(management, commands, JOB_NAME, finish);
	}

	protected void process() throws DbgpException {
		boolean success = getExtended().makeBreak();

		IDbgpStatus status = new DbgpStatus(DbgpStatus.STATUS_BREAK,
				DbgpStatus.REASON_OK);
		if (!success) {
			status = new DbgpStatus(DbgpStatus.STATUS_RUNNING,
					DbgpStatus.REASON_ERROR);
		}

		callFinish(status);
	}
}
