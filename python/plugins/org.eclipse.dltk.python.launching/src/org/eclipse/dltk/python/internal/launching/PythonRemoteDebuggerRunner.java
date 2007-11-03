package org.eclipse.dltk.python.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;

public class PythonRemoteDebuggerRunner extends RemoteDebuggingEngineRunner {

	public PythonRemoteDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

}
