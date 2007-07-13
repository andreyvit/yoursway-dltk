package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTargetListener;
import org.eclipse.dltk.internal.debug.core.model.ScriptDebugTarget;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.internal.launching.InterpreterMessages;
import org.eclipse.dltk.launching.debug.DbgpConstants;

public abstract class DebuggingEngineRunner extends AbstractInterpreterRunner {
	private static final String LOCALHOST = "127.0.0.1";

	protected static final int DEFAULT_WAITING_TIMEOUT = 1000 * 1000;

	protected static final int DEFAULT_PAUSE = 500;

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	protected String getPluginId() {
		return DLTKLaunchingPlugin.PLUGIN_ID;
	}

	public static class ScriptDebugTargetWaiter implements
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

	protected String generateSessionId() {
		return "dbgp" + System.currentTimeMillis();
	}

	protected String getSessionId(ILaunchConfiguration configuration)
			throws CoreException {
		String id = configuration.getAttribute(
				ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
				(String) null);

		if (id == null) {
			id = generateSessionId();
		}

		return id;
	}

	protected void addDebugTarget(ILaunch launch,
			ILaunchConfiguration configuration, IDbgpService dbgpService,
			String sessionId) throws CoreException {

		IScriptDebugTarget target = new ScriptDebugTarget(getDebugModelId(),
				dbgpService, sessionId, launch, null);
		launch.addDebugTarget(target);
	}

	public DebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected IDbgpService createDebuggingService(
			ILaunchConfiguration configuration) throws CoreException {
		try {
			int port = configuration.getAttribute(
					ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT, -1);

			if (port == -1) {
				return DLTKDebugPlugin.getDefault().createDbgpService();
			} else {
				return DLTKDebugPlugin.getDefault().creaeDbgpService(port);
			}
		} catch (Exception e) {
			abort(DLTKLaunchingPlugin.PLUGIN_ID,
					InterpreterMessages.errDbgpServiceNotStarted, null,
					ScriptLaunchConfigurationConstants.ERR_INTERNAL_ERROR);
		}

		return null;
	}

	protected InterpreterConfig alterConfig(String exe, InterpreterConfig config)
			throws CoreException {
		return config;
	}

	protected void initialize(InterpreterConfig config, ILaunch launch,
			ILaunchConfiguration configuration) throws CoreException {
		final IDbgpService service = createDebuggingService(configuration);
		final String sessionId = getSessionId(configuration);
		addDebugTarget(launch, configuration, service, sessionId);

		final int port = service.getPort();
		final String host = LOCALHOST;

		config.setProperty(DbgpConstants.HOST_PROP, host);
		config.setProperty(DbgpConstants.PORT_PROP, Integer.toString(port));
		config.setProperty(DbgpConstants.SESSION_ID_PROP, sessionId);
	}

	public void run(InterpreterConfig config, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {

		// Disable the output of the debugging engine process
		launch.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, "false");

		try {
			// Configuration
			final ILaunchConfiguration configuration = launch
					.getLaunchConfiguration();

			initialize(config, launch, configuration);

			InterpreterConfig newConfig = alterConfig(constructProgramString(),
					config);

			// TODO: remove later
			sleep(DEFAULT_PAUSE);

			// Starting debugging engine
			try {
				String exe = (String) newConfig.getProperty("OVERRIDE_EXE");

				if (exe != null) {
					String[] cmdLine = newConfig.renderCommandLine(exe);
					rawRun(launch, cmdLine, newConfig.getWorkingDirectoryPath().toFile(),
							newConfig.getEnvironmentAsStrings());
				} else {
					super.run(newConfig, launch, monitor);
				}
			} catch (CoreException e) {
				abort(
						DLTKLaunchingPlugin.PLUGIN_ID,
						InterpreterMessages.errDebuggingEngineNotStarted,
						null,
						ScriptLaunchConfigurationConstants.ERR_DEBUGGING_ENGINE_NOT_STARTED);
			}

			// Waiting for debugging engine connect
			int waitingTimeout = configuration
					.getAttribute(
							ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT,
							DEFAULT_WAITING_TIMEOUT);

			ScriptDebugTargetWaiter waiter = new ScriptDebugTargetWaiter(
					(IScriptDebugTarget) launch.getDebugTarget());

			if (!waiter.waitThread(waitingTimeout)) {
				abort(
						DLTKLaunchingPlugin.PLUGIN_ID,
						InterpreterMessages.errDebuggingEngineNotConnected,
						null,
						ScriptLaunchConfigurationConstants.ERR_DEBUGGING_ENGINE_NOT_CONNECTED);
			}
		} catch (CoreException e) {
			launch.terminate();
			throw e;
		}

		// Happy debugging :)
	}

	protected String getDebugModelId() {
		return ScriptDebugManager.getInstance().getDebugModelByNature(
				getInstall().getNatureId());
	}
}
