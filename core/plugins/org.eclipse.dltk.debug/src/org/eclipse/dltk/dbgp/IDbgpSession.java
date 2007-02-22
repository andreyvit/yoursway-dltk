package org.eclipse.dltk.dbgp;

import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.internal.IDbgpTermination;

public interface IDbgpSession extends IDbgpCommands, IDbgpTermination {
	IDbgpSessionInfo getInfo();

	IDbgpNotificationManager getNotificationManager();
}
