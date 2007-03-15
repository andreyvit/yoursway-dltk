package org.eclipse.dltk.dbgp;

public interface IDbgpStatus {
	// State
	boolean isStarting();

	boolean isStopping();

	boolean isStopped();

	boolean isRunning();

	boolean isBreak();

	// Reason
	boolean reasonOk();

	boolean reasonError();

	boolean reasonAborred();

	boolean reasonException();
}
