package org.eclipse.dltk.python.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;
import org.eclipse.dltk.python.internal.debug.PythonDebugPlugin;

public class PythonRemoteDebuggerRunner extends RemoteDebuggingEngineRunner {

	public PythonRemoteDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
	 */
	protected String getDebugPreferenceQualifier() {
		return PythonDebugPlugin.PLUGIN_ID;
	}

}
