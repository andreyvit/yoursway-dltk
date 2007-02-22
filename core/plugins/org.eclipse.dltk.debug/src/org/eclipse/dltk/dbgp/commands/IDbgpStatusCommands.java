package org.eclipse.dltk.dbgp.commands;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpStatusCommands {
	IDbgpStatus getStatus() throws DbgpException;
}
