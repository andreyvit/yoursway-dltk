package org.eclipse.dltk.dbgp;

public interface IDbgpContinuationHandler {
	void stdoutReceived(String data);

	void stderrReceived(String data);
}
