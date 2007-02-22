package org.eclipse.dltk.dbgp.commands;

public interface IDbgpCommands {
	IDbgpCoreCommands getCoreCommands();

	IDbgpExtendedCommands getExtendedCommands();
}
