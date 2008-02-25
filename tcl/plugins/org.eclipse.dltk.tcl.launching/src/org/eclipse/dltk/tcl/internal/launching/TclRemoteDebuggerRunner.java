package org.eclipse.dltk.tcl.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPlugin;

public class TclRemoteDebuggerRunner extends RemoteDebuggingEngineRunner {

	public TclRemoteDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
	 */
	protected String getDebugPreferenceQualifier() {
		return TclDebugPlugin.PLUGIN_ID;		
	}
}
