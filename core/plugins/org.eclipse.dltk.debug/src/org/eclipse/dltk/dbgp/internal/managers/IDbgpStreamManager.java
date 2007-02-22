package org.eclipse.dltk.dbgp.internal.managers;

import org.eclipse.dltk.dbgp.IDbgpContinuationHandler;

public interface IDbgpStreamManager {
	void addListener(IDbgpContinuationHandler listener);

	void removeListener(IDbgpContinuationHandler listener);
}
