package org.eclipse.dltk.internal.debug.core.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.dltk.dbgp.DbgpServer;
import org.eclipse.dltk.dbgp.IDbgpServerListener;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpThreadAcceptor;
import org.eclipse.dltk.dbgp.internal.IDbgpTerminationListener;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.core.IDbgpService;

public class DbgpService implements IDbgpService, IPropertyChangeListener,
		IDbgpTerminationListener, IDbgpServerListener {
	private static final int FROM_PORT = 10000;
	private static final int TO_PORT = 50000;

	private static final int SERVER_SOCKET_TIMEOUT = 500;
	private static final int CLIENT_SOCKET_TIMEOUT = 10000000;

	private DbgpServer server;

	private Map acceptors;

	private int serverPort;

	private int getPreferencePort() {
		return DLTKDebugPlugin.getDefault().getPluginPreferences().getInt(
				DLTKDebugPreferenceConstants.PREF_DBGP_PORT);
	}

	protected void restartServer(int port) {
		if (server != null) {
			server.removeTerminationListener(this);
			server.setListener(null);

			server.requestTermination();
			try {
				server.waitTerminated();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		serverPort = port;

		server = new DbgpServer(port, SERVER_SOCKET_TIMEOUT,
				CLIENT_SOCKET_TIMEOUT);
		server.addTerminationListener(this);
		server.setListener(this);
		server.start();
	}

	public DbgpService() {
		this.acceptors = Collections.synchronizedMap(new HashMap());

		DLTKDebugPlugin.getDefault().getPluginPreferences()
				.addPropertyChangeListener(this);

		int port = getPreferencePort();
		if (port == DLTKDebugPreferenceConstants.DBGP_AVAILABLE_PORT) {
			port = DbgpServer.findAvailablePort(FROM_PORT, TO_PORT);
		}

		restartServer(port);
	}

	public void shutdown() {
		if (server != null) {
			server.removeTerminationListener(this);
			server.setListener(null);

			server.requestTermination();
			try {
				server.waitTerminated();
			} catch (InterruptedException e) {
				DLTKDebugPlugin.log(e);
			}
		}
	}

	public int getPort() {
		return serverPort;
	}

	// Acceptors
	public void registerAcceptor(String id, IDbgpThreadAcceptor acceptor) {
		acceptors.put(id, acceptor);
	}

	public IDbgpThreadAcceptor unregisterAcceptor(String id) {
		return (IDbgpThreadAcceptor) acceptors.remove(id);
	}

	// IPropertyChangeListener
	public void propertyChange(PropertyChangeEvent event) {
		final String property = event.getProperty();

		if (DLTKDebugPreferenceConstants.PREF_DBGP_PORT.equals(property)) {
			final int port = getPreferencePort();
			if (port != DLTKDebugPreferenceConstants.DBGP_AVAILABLE_PORT) {
				// Only restart if concrete port specified
				restartServer(port);
			}
		}
	}

	// IDbgpTerminationListener
	public void objectTerminated(Object object, Exception e) {
		if (e != null) {
			DLTKDebugPlugin.log(e);
			restartServer(serverPort);
		}
	}

	public boolean available() {
		return true;
	}

	// INewDbgpServerListener
	public void clientConnected(IDbgpSession session) {
		final String id = session.getInfo().getIdeKey();

		final IDbgpThreadAcceptor acceptor = (IDbgpThreadAcceptor) acceptors
				.get(id);

		if (acceptor != null) {
			acceptor.acceptDbgpThread(session);
		} else {
			session.requestTermination();
		}
	}
}
