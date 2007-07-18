package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.dbgp.DbgpSessionIdGenerator;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTargetListener;
import org.eclipse.dltk.internal.debug.core.model.ScriptDebugTarget;
import org.eclipse.dltk.internal.launching.InterpreterMessages;
import org.eclipse.dltk.launching.debug.DbgpConstants;
import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;

public abstract class DebuggingEngineRunner extends AbstractInterpreterRunner {
	public static class DbgpInterpreterConfig {
		private InterpreterConfig config;

		public DbgpInterpreterConfig(InterpreterConfig config) {
			this.config = config;
		}

		public void setHost(String host) {
			config.setProperty(DbgpConstants.HOST_PROP, host);
		}

		public String getHost() {
			return (String) config.getProperty(DbgpConstants.HOST_PROP);
		}

		public void setPort(int port) {
			config.setProperty(DbgpConstants.PORT_PROP, Integer.toString(port));
		}

		public int getPort() {
			return ((Integer) config.getProperty(DbgpConstants.PORT_PROP))
					.intValue();
		}

		public void setSessionId(String sessionId) {
			config.setProperty(DbgpConstants.SESSION_ID_PROP, sessionId);
		}

		public String getSessionId() {
			return (String) config.getProperty(DbgpConstants.SESSION_ID_PROP);
		}
	}

	public static class ScriptDebugTargetWaiter implements
			IScriptDebugTargetListener {

		private IScriptDebugTarget target;

		public ScriptDebugTargetWaiter(IScriptDebugTarget target) {
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

	// Launch attributes
	public static final String LAUNCH_ATTR_DEBUGGING_ENGINE_ID = "debugging_engine_id";

	private static final String LOCALHOST = "127.0.0.1";

	protected static final int DEFAULT_PAUSE = 500;

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	protected String getSessionId(ILaunchConfiguration configuration)
			throws CoreException {
		String id = configuration.getAttribute(
				ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
				(String) null);

		if (id == null) {
			id = DbgpSessionIdGenerator.generate();
		}

		return id;
	}

	protected void addDebugTarget(ILaunch launch,
			ILaunchConfiguration configuration, IDbgpService dbgpService,
			String sessionId) throws CoreException {

		final IScriptDebugTarget target = new ScriptDebugTarget(
				getDebugModelId(), dbgpService, sessionId, launch, null);
		launch.addDebugTarget(target);
	}

	public DebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected InterpreterConfig alterConfig(String exe, InterpreterConfig config)
			throws CoreException {
		return config;
	}

	protected void initialize(InterpreterConfig config, ILaunch launch,
			ILaunchConfiguration configuration) throws CoreException {
		final IDbgpService service = DLTKDebugPlugin.getDefault()
				.getDbgpService();
		final String sessionId = getSessionId(configuration);

		addDebugTarget(launch, configuration, service, sessionId);

		// Disable the output of the debugging engine process
		launch.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, Boolean.FALSE
				.toString());

		// Debugging engine id
		launch.setAttribute(LAUNCH_ATTR_DEBUGGING_ENGINE_ID,
				getDebuggingEngineId());

		// Config
		final int port = service.getPort();
		final String host = LOCALHOST;

		config.setProperty(DbgpConstants.HOST_PROP, host);
		config.setProperty(DbgpConstants.PORT_PROP, Integer.toString(port));
		config.setProperty(DbgpConstants.SESSION_ID_PROP, sessionId);
	}

	protected void checkConfig(InterpreterConfig config) throws CoreException {
		File dir = new File(config.getWorkingDirectoryPath().toOSString());
		if (!dir.exists()) {
			abort("Working directory doesn't exist: " + dir.toString(), null);
		}

		File script = new File(config.getScriptFilePath().toOSString());
		if (!script.exists()) {
			abort("Script to run doesn't exist: " + script.toString(), null);
		}
	}

	public void run(InterpreterConfig config, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {

		try {
			// Configuration
			final ILaunchConfiguration configuration = launch
					.getLaunchConfiguration();

			initialize(config, launch, configuration);

			final InterpreterConfig newConfig = alterConfig(
					constructProgramString(), config);

			checkConfig(newConfig);

			// TODO: remove later
			sleep(DEFAULT_PAUSE);

			// Starting debugging engine
			try {
				String exe = (String) newConfig.getProperty("OVERRIDE_EXE");

				if (exe != null) {
					String[] cmdLine = newConfig.renderCommandLine(exe);
					rawRun(launch, cmdLine, newConfig.getWorkingDirectoryPath()
							.toFile(), newConfig.getEnvironmentAsStrings());
				} else {
					super.run(newConfig, launch, monitor);
				}
			} catch (CoreException e) {
				abort(InterpreterMessages.errDebuggingEngineNotStarted, null);
			}

			// Waiting for debugging engine connect
			int waitingTimeout = configuration
					.getAttribute(
							ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT,
							0);

			ScriptDebugTargetWaiter waiter = new ScriptDebugTargetWaiter(
					(IScriptDebugTarget) launch.getDebugTarget());

			if (!waiter.waitThread(waitingTimeout)) {
				abort(InterpreterMessages.errDebuggingEngineNotConnected, null);
			}
		} catch (CoreException e) {
			launch.terminate();
			throw e;
		}

		// Happy debugging :)
	}

	public String getDebugModelId() {
		return ScriptDebugManager.getInstance().getDebugModelByNature(
				getInstall().getNatureId());
	}

	public IDebuggingEngine getDebuggingEngine() {
		return DebuggingEngineManager.getInstance().getDebuggingEngine(
				getDebuggingEngineId());
	}

	protected abstract String getDebuggingEngineId();
}
