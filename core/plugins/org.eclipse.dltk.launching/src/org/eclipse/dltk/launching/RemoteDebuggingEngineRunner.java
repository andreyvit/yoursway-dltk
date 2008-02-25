package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.PreferencesLookupDelegate;

public abstract class RemoteDebuggingEngineRunner extends DebuggingEngineRunner {

	public RemoteDebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getSessionId(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	protected String getSessionId(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(
				ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
				"");
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#addEngineConfig(org.eclipse.dltk.launching.InterpreterConfig,
	 *      org.eclipse.dltk.core.IScriptProject)
	 */
	protected InterpreterConfig addEngineConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) {
		return config;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#run(org.eclipse.dltk.launching.InterpreterConfig,
	 *      org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(InterpreterConfig config, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		try {
			initializeLaunch(launch, config,
					createPreferencesLookupDelegate(launch));
			waitDebuggerConnected(null, launch, monitor);
		} catch (CoreException e) {
			launch.terminate();
			throw e;
		}
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebuggingEngineId()
	 */
	protected String getDebuggingEngineId() {
		return null;
	}

}
