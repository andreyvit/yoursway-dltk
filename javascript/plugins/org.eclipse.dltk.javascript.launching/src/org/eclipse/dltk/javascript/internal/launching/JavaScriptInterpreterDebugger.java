package org.eclipse.dltk.javascript.internal.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugPreferences;
import org.eclipse.dltk.javascript.launching.JavaScriptLaunchingPlugin;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IDLTKLaunchConfigurationConstants;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterRunnerConfiguration;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger.ScriptDebugTargetWaiter;


public class JavaScriptInterpreterDebugger extends AbstractInterpreterDebugger {
	public void run(InterpreterRunnerConfiguration configuration,
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

	private static final String HOST_KEY = "-host-ide";
	private static final String PORT_KEY = "-port-ide";
	private static final String SHELL_KEY = "-app-shell";
	private static final String IDE_KEY = "-ide-key";
	private static final String SCRIPT_KEY = "-app-file";
	private static final String ARGS_SEPARATOR = "--";

	protected String getPluginIdentifier() {
		return JavaScriptLaunchingPlugin.PLUGIN_ID;
	}

	protected File getEngine() throws CoreException {
		File engine = new File(JavaScriptDebugPreferences
				.getDebuggingEnginePath());

		// TODO: add more checks
		if (!engine.isFile()) {
			abort("Tcl debugging engine not configured", null, 180);
		}
		return engine;
	}

	public JavaScriptInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	protected String[] getCommandLine(String id, String host, int port,
			String script, String[] args, String shell) throws CoreException {

		List list = new ArrayList();

		list.add(getEngine().getAbsolutePath());
		list.add(HOST_KEY);
		list.add(host);
		list.add(PORT_KEY);
		list.add(Integer.toString(port));
		list.add(SHELL_KEY);
		list.add(shell);
		list.add(IDE_KEY);
		list.add(id);
		list.add(SCRIPT_KEY);
		list.add(script);
		list.add(ARGS_SEPARATOR);

		for (int i = 0; i < args.length; ++i) {
			list.add(args[i]);
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	protected File getWorkingDir() throws CoreException {
		return getEngine().getParentFile();
	}

	protected String[] getCommandLine(String sessionId, String host, int port,
			InterpreterRunnerConfiguration configuration) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	protected String getPluginId() {
		return JavaScriptLaunchingPlugin.PLUGIN_ID;
	}
}
