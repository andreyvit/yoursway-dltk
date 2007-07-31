package org.eclipse.dltk.tcl.launching;

import org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.launching.TclRemoteDebuggerRunner;

/**
 * Remote launch configuration delegate for Tcl applications
 */
public class TclRemoteLaunchConfigurationDelegate extends
		AbstractRemoteLaunchConfigurationDelegate {

	/*
	 * @see org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate#getDebuggingRunner(org.eclipse.dltk.launching.IInterpreterInstall)
	 */
	protected RemoteDebuggingEngineRunner getDebuggingRunner(
			IInterpreterInstall install) {
		return new TclRemoteDebuggerRunner(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getLanguageId()
	 */
	public String getLanguageId() {
		return TclNature.NATURE_ID;
	}

}
