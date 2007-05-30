package org.eclipse.dltk.javascript.internal.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IDLTKLaunchConfigurationConstants;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;


public class JavaScriptInterpreterDebugger extends AbstractInterpreterDebugger {
	public JavaScriptInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	public void run(InterpreterConfig configuration,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		IDbgpService dbgpService = null;
		final ILaunchConfiguration config = launch.getLaunchConfiguration();

		try {
			int port = config.getAttribute(
					IDLTKLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT, -1);

			if (port == -1) {
				dbgpService = DLTKDebugPlugin.getDefault().createDbgpService();
			} else {
				dbgpService = DLTKDebugPlugin.getDefault().creaeDbgpService(
						port);
			}
		} catch (Exception e) {
			abort(DLTKLaunchingPlugin.ID_PLUGIN, "Dbgp service not available",
					null, DLTKLaunchingPlugin.DBGP_SERVICE_NOT_AVAILABLE);
		}
		final String sessionId = addDebugTarget(launch, config, dbgpService);
		final String host = "localhost";
		final int port = dbgpService.getPort();
		JavaScriptInterpreterRunner.doRun(configuration, launch,host,""+port,sessionId);
		try {
			int waitingTimeout = config
					.getAttribute(
							IDLTKLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT,
							DEFAULT_WAITING_TIMEOUT);

			ScriptDebugTargetWaiter waiter = new ScriptDebugTargetWaiter(
					(IScriptDebugTarget) launch.getDebugTarget());

			if (!waiter.waitThread(waitingTimeout)) {
				abort(DLTKLaunchingPlugin.ID_PLUGIN,
						"Debugging engine not connected", null,
						DLTKLaunchingPlugin.DEBUGGING_ENGINE_NOT_CONNECTED);
			}
		} catch (CoreException e) {
			launch.terminate();
			throw e;
		}		
	}

	protected String getDebugModelIdentidier() {
		return "org.eclipse.dltk.debug.javascriptModel";
	}

	protected String[] getCommandLine(InterpreterConfig config,
			String sessionId, String host, int port) throws CoreException {
		return null; // TODO:
	}

	protected String getPluginId() {
		return null; // TODO:
	}
}
