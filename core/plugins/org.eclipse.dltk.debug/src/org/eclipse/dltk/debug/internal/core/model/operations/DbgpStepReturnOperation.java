package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.internal.core.model.IThreadManagement;

public class DbgpStepReturnOperation extends DbgpStepOperation {
	private static final String JOB_NAME = "StepReturn operation";

	public DbgpStepReturnOperation(IDbgpCommands commands, IResultHandler finish) {
		super(commands, JOB_NAME, finish);
	}

	protected IDbgpStatus step() throws DbgpException {
		return getCore().stepOut(getContinuationHandler());
	}
}
