package org.eclipse.dltk.debug.internal.core.model;

import org.eclipse.dltk.debug.core.model.IScriptThread;

public interface IDbgpThreadManagerListener {
	void threadAccepted(IScriptThread thread, boolean first);

	void allThreadsTerminated();
}
