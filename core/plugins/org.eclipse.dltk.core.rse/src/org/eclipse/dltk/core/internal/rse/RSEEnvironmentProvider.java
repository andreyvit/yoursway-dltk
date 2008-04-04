package org.eclipse.dltk.core.internal.rse;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IEnvironmentProvider;
import org.eclipse.rse.core.IRSESystemType;
import org.eclipse.rse.core.model.IHost;
import org.eclipse.rse.core.model.SystemStartHere;
import org.eclipse.rse.subsystems.files.core.model.RemoteFileUtility;
import org.eclipse.rse.subsystems.files.core.subsystems.IRemoteFileSubSystem;

public class RSEEnvironmentProvider implements IEnvironmentProvider {
	public static final String RSE_ENVIRONMENT_PREFIX = DLTKRSEPlugin.PLUGIN_ID
			+ ".rseEnvironment.";

	public RSEEnvironmentProvider() {
	}

	boolean fakeRSEInitialized = false;

	public IEnvironment getEnvironment(String envId) {
		initializeRSE();
		if (envId.startsWith(RSE_ENVIRONMENT_PREFIX)) {
			String name = envId.substring(RSE_ENVIRONMENT_PREFIX.length());
			IHost connection = getRSEConnection(name);
			IRemoteFileSubSystem fs = RemoteFileUtility
					.getFileSubSystem(connection);
			if (fs != null)
				return new RSEEnvironment(fs);
		}
		return null;
	}

	private IHost getRSEConnection(String name) {
		initializeRSE();
		IHost[] connections = SystemStartHere.getConnections();
		for (int i = 0; i < connections.length; i++) {
			IHost connection = connections[i];
			if (name.equals(connection.getAliasName())) {
				return connection;
			}
		}
		return null;
	}

	public IEnvironment[] getEnvironments() {
		initializeRSE();
		IHost[] connections = SystemStartHere.getConnections();
		List environments = new LinkedList();
		for (int i = 0; i < connections.length; i++) {
			IHost connection = connections[i];
			if (IRSESystemType.SYSTEMTYPE_LOCAL_ID.equals(connection
					.getSystemType().getId())) {
				continue;
			}

			IRemoteFileSubSystem fs = RemoteFileUtility
					.getFileSubSystem(connection);
			if (fs != null)
				environments.add(new RSEEnvironment(fs));
		}
		return (IEnvironment[]) environments
				.toArray(new IEnvironment[environments.size()]);
	}

	private void initializeRSE() {
		if (!fakeRSEInitialized) {
			fakeRSEInitialized = true;
//			ILog log = RSEUIPlugin.getDefault().getLog();
//			log.log(new Status(IStatus.INFO, DLTKRSEPlugin.PLUGIN_ID,
//					"Starting RSE..."));
		}
	}
}
