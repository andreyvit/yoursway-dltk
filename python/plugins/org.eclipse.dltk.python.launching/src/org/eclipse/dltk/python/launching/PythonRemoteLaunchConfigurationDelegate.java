package org.eclipse.dltk.python.launching;

import org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.launching.PythonRemoteDebuggerRunner;

public class PythonRemoteLaunchConfigurationDelegate extends
		AbstractRemoteLaunchConfigurationDelegate {

	/*
	 * @see org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate#getDebuggingRunner(org.eclipse.dltk.launching.IInterpreterInstall)
	 */
	protected RemoteDebuggingEngineRunner getDebuggingRunner(
			IInterpreterInstall install) {
		return new PythonRemoteDebuggerRunner(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getLanguageId()
	 */
	public String getLanguageId() {
		return PythonNature.NATURE_ID;
	}

}
