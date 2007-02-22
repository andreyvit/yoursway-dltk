package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpDebuggerFeedback {
	void endStepInto(DbgpException e, IDbgpStatus status);

	void endStepOver(DbgpException e, IDbgpStatus status);

	void endStepReturn(DbgpException e, IDbgpStatus status);

	void endSuspend(DbgpException e, IDbgpStatus status);

	void endResume(DbgpException e, IDbgpStatus status);

	void endTerminate(DbgpException e, IDbgpStatus status);
}
