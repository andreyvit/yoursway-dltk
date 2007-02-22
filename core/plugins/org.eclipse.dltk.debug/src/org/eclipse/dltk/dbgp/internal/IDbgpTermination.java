package org.eclipse.dltk.dbgp.internal;

public interface IDbgpTermination {
	void addTerminationListener(IDbgpTerminationListener listener);

	void removeTerminationListener(IDbgpTerminationListener listener);

	void requestTermination();

	void waitTerminated() throws InterruptedException;
}
