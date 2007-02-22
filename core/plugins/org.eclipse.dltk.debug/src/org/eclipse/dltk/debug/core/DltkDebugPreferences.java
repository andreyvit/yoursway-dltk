package org.eclipse.dltk.debug.core;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class DltkDebugPreferences {
	private static final String DBGP_PORT = "dbgp_port";

	private static final int DEFAULT_DBGP_PORT = 9000;

	private static final String DBGP_TIMEOUT = "dbgp_timeout";

	private static final int DEFAULT_DBGP_TIMEOUT = 5000;

	private static Preferences getNode() {
		String id = DLTKDebugPlugin.getDefault().getBundle().getSymbolicName();
		return Platform.getPreferencesService().getRootNode().node(
				InstanceScope.SCOPE).node(id);
	}

	public static void save() {
		try {
			getNode().flush();
		} catch (BackingStoreException e) {
			// TODO: add logging
		}
	}

	public static int getDbgpPort() {
		return getNode().getInt(DBGP_PORT, DEFAULT_DBGP_PORT);
	}

	public static void setDbgpPort(int port) {
		getNode().getInt(DBGP_PORT, port);
	}

	public static int getDbgpTimeout() {
		return getNode().getInt(DBGP_TIMEOUT, DEFAULT_DBGP_TIMEOUT);
	}

	public static void setDbgpTimeout(int timeout) {
		getNode().getInt(DBGP_TIMEOUT, timeout);
	}
}
