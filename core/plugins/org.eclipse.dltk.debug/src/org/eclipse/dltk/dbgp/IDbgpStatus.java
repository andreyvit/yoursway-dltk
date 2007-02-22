package org.eclipse.dltk.dbgp;

public interface IDbgpStatus {
	boolean isStarting();

	boolean isStopping();

	boolean isStopped();

	boolean isRunning();

	boolean isBreak();

	boolean reasonOk();

	boolean reasonError();

	boolean reasonAborred();

	boolean reasonException();
}
