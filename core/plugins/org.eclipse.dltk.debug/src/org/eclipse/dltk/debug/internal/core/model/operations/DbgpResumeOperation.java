package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public class DbgpResumeOperation extends DbgpOperation {
	private static final String JOB_NAME = "Resume operation";

	public DbgpResumeOperation(IDbgpCommands commands, IResultHandler finish) {
		super(commands, JOB_NAME, finish);
	}

	protected void process() throws DbgpException {
		callFinish(getCore().run(getContinuationHandler()));
	}
}
