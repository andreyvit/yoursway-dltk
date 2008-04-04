package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.ISuspendResume;
import org.eclipse.debug.core.model.ITerminate;
import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public interface IScriptThreadManager extends IDbgpThreadAcceptor, ITerminate,
		ISuspendResume {

	// Listener
	void addListener(IScriptThreadManagerListener listener);

	void removeListener(IScriptThreadManagerListener listener);

	// Thread management
	boolean hasThreads();

	IScriptThread[] getThreads();

	void terminateThread(IScriptThread thread);
	
	boolean isWaitingForThreads();

	void sendTerminationRequest() throws DebugException;

	public void refreshThreads();
}
