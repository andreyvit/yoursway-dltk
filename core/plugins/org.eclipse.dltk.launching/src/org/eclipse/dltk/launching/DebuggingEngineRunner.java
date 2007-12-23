package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.dbgp.DbgpSessionIdGenerator;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.ExtendedDebugEventDetails;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.internal.debug.core.model.DebugEventHelper;
import org.eclipse.dltk.internal.debug.core.model.ScriptDebugTarget;
import org.eclipse.dltk.internal.launching.InterpreterMessages;
import org.eclipse.dltk.launching.debug.DbgpInterpreterConfig;
import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;

public abstract class DebuggingEngineRunner extends AbstractInterpreterRunner {
	// Launch attributes
	public static final String LAUNCH_ATTR_DEBUGGING_ENGINE_ID = "debugging_engine_id";

	private static final String LOCALHOST = "127.0.0.1";

	public static final String OVERRIDE_EXE = "OVERRIDE_EXE";

	protected String getSessionId(ILaunchConfiguration configuration)
			throws CoreException {
		return DbgpSessionIdGenerator.generate();
	}

	protected IScriptDebugTarget addDebugTarget(ILaunch launch,
			IDbgpService dbgpService) throws CoreException {

		final IScriptDebugTarget target = new ScriptDebugTarget(
				getDebugModelId(), dbgpService, getSessionId(launch
						.getLaunchConfiguration()), launch, null);

		launch.addDebugTarget(target);
		return target;
	}

	public DebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected void initializeLaunch(ILaunch launch, InterpreterConfig config)
			throws CoreException {
		final IDbgpService service = DLTKDebugPlugin.getDefault()
				.getDbgpService();

		if (!service.available()) {
			abort(InterpreterMessages.errDbgpServiceNotAvailable, null);
		}

		final IScriptDebugTarget target = addDebugTarget(launch, service);

		// Disable the output of the debugging engine process
		launch.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, Boolean.FALSE
				.toString());

		// Debugging engine id
		launch.setAttribute(LAUNCH_ATTR_DEBUGGING_ENGINE_ID,
				getDebuggingEngineId());

		// Configuration
		final DbgpInterpreterConfig dbgpConfig = new DbgpInterpreterConfig(
				config);

		dbgpConfig.setSessionId(target.getSessionId());
		dbgpConfig.setPort(service.getPort());
		dbgpConfig.setHost(LOCALHOST);
	}

	/**
	 * Add the debugging engine configuration.
	 */
	protected abstract InterpreterConfig addEngineConfig(
			InterpreterConfig config, PreferencesLookupDelegate delegate)
			throws CoreException;

	public void run(InterpreterConfig config, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		monitor.beginTask("Launching...", 5);
		if (monitor.isCanceled()) {
			return;
		}
		try {
			IScriptProject sProject = ScriptRuntime.getScriptProject(launch
					.getLaunchConfiguration());
			PreferencesLookupDelegate prefDelegate = new PreferencesLookupDelegate(sProject
					.getProject());

			initializeLaunch(launch, config);
			InterpreterConfig newConfig = addEngineConfig(config, prefDelegate);

			// Starting debugging engine
			IProcess process = null;
			try {
				DebugEventHelper.fireExtendedEvent(newConfig,
						ExtendedDebugEventDetails.BEFORE_VM_STARTED);

				// Running
				monitor.subTask("Running");
				process = rawRun(launch, newConfig);
			} catch (CoreException e) {
				abort(InterpreterMessages.errDebuggingEngineNotStarted, e);
			}
			monitor.worked(4);

			// Waiting for debugging engine connect
			waitDebuggerConnected(process, launch, monitor);
		} catch (CoreException e) {
			launch.terminate();
			throw e;
		} finally {
			monitor.done();
		}
		// Happy debugging :)
	}

	protected String[] renderCommandLine(InterpreterConfig config) {
		String exe = (String) config.getProperty(OVERRIDE_EXE);
		if (exe != null) {
			return config.renderCommandLine(exe);
		}

		return config.renderCommandLine(getInstall());
	}

	/**
	 * Waiting debugging process to connect to current launch
	 * 
	 * @param debuggingProcess
	 *            process that will connect to current launch or null if handle
	 *            to process is not available (remote debugging)
	 * @param launch
	 *            launch to connect to
	 * @param monitor
	 *            progress monitor
	 * @throws CoreException
	 *             if debuggingProcess terminated, monitor is canceled or // *
	 *             timeout
	 */
	protected void waitDebuggerConnected(IProcess debuggingProcess,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		final int WAIT_CHUNK = 100;

		ILaunchConfiguration configuration = launch.getLaunchConfiguration();
		int timeout = configuration
				.getAttribute(
						ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT,
						0);

		ScriptDebugTarget target = (ScriptDebugTarget) launch.getDebugTarget();
		target.setProcess(debuggingProcess);

		try {
			int all = 0;
			while (timeout == 0 || all < timeout) {
				if (target.isInitialized()
						|| target.isTerminated()
						|| monitor.isCanceled()
						|| (debuggingProcess != null && debuggingProcess
								.isTerminated()))
					break;

				Thread.sleep(WAIT_CHUNK);
				all += WAIT_CHUNK;
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
		}

		if (!target.isInitialized()) {
			if (debuggingProcess != null && debuggingProcess.canTerminate()) {
				debuggingProcess.terminate();
			}
			abort(InterpreterMessages.errDebuggingEngineNotConnected, null);
		}
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
