package org.eclipse.dltk.dbgp.internal;

public interface IDbgpTerminationListener {
	void objectTerminated(Object object, Exception e);
}
