package org.eclipse.dltk.dbgp;

public interface IDbgpNotificationManager {
	void addNotificationListener(IDbgpNotificationListener listener);

	void removeNotificationListener(IDbgpNotificationListener listener);
}
