package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTargetListener;
import org.eclipse.dltk.debug.internal.core.model.ScriptDebugTarget;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;

public abstract class AbstractInterpreterDebugger extends
		AbstractInterpreterRunner {
	private static final boolean DEBUG = DLTKCore.DEBUG;

	private static final int DEFAULT_WAITING_TIMEOUT = 1000 * 30;

	private static final int DEFAULT_PAUSE = 500;

	protected static class ScriptDebugTargetWaiter implements
			IScriptDebugTargetListener {
		private IScriptDebugTarget target;

		public ScriptDebugTargetWaiter(IScriptDebugTarget target) {
			if (target == null) {
				throw new IllegalArgumentException();
			}

			this.target = target;
		}

		public synchronized void targetInitialized() {
			notifyAll();
		}

		public synchronized boolean waitThread(int timeout) {
			target.addListener(this);

			try {
				wait(timeout);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}

			target.removeListener(this);
			return target.isInitialized();
		}
	}

	private String generateSessionId() {
		return "dbgp" + System.currentTimeMillis();
	}

	protected static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public AbstractInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	protected String addDebugTarget(ILaunch launch,
			ILaunchConfiguration configuration) throws CoreException {
		String sessionId = configuration.getAttribute(
				IDLTKLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
				(String) null);
		if (sessionId == null) {
			sessionId = generateSessionId();
		}

		IScriptDebugTarget target = new ScriptDebugTarget(sessionId, launch,
				null);
		launch.addDebugTarget(target);

		return sessionId;
	}

	public void run(InterpreterRunnerConfiguration configuration,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		final ILaunchConfiguration config = launch.getLaunchConfiguration();
		final String sessionId = addDebugTarget(launch, config);

		try {
			boolean remoteDebugging = config.getAttribute(
					IDLTKLaunchConfigurationConstants.ATTR_DLTK_DBGP_REMOTE,
					false);

			// Port
			IDbgpService dbgpService = null;
			int port = config.getAttribute(
					IDLTKLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT, -1);
			if (port == -1) {
				dbgpService = DLTKDebugPlugin.getDefault().getDbgpService();
			} else {
				dbgpService = DLTKDebugPlugin.getDefault().getDbgpService(port);
			}

			// Checking if dbgp service started
			if (!dbgpService.available()) {
				abort(DLTKLaunchingPlugin.ID_PLUGIN,
						"Dbgp service not available", null,
						DLTKLaunchingPlugin.DBGP_SERVICE_NOT_AVAILABLE);
			}

			// Starting debugging
			final String scriptFile = configuration.getScriptToLaunch();
			final String shell = constructProgramString(configuration);
			final String host = "localhost";

			final String[] args = configuration.getProgramArguments();

			port = dbgpService.getPort();

			String[] commandLine = getCommandLine(sessionId, host, port,
					scriptFile, args, shell);

			if (DEBUG) {
				System.out.println(getCmdLineAsString(commandLine));
			}

			File workingDir = getWorkingDir();

			if (!remoteDebugging) {
				// Start local debugging engine
				sleep(DEFAULT_PAUSE);

				try {
					exec(commandLine, workingDir, configuration
							.getEnvironment());
					// Testing for two threads
					// sleep(2000);
					// exec(commandLine,
					// workingDir,configuration.getEnvironment());
				} catch (CoreException e) {
					abort(DLTKLaunchingPlugin.ID_PLUGIN,
							"Debugging engine not started", null,
							DLTKLaunchingPlugin.DEBUGGING_ENGINE_NOT_STARTED);
				}
			}

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

		// Happy debugging :)
	}

	protected abstract File getWorkingDir() throws CoreException;

	protected abstract String[] getCommandLine(String id, String host,
			int port, String script, String args[], String shell)
			throws CoreException;
}
