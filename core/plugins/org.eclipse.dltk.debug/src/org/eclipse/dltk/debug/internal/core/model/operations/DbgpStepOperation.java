package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public abstract class DbgpStepOperation extends DbgpOperation {
	public DbgpStepOperation(IDbgpCommands commands, String name, IResultHandler finish) {
		super(commands, name, finish);
	}

	protected void process() throws DbgpException {
		callFinish(step());
	}

	protected abstract IDbgpStatus step() throws DbgpException;
}
