package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.launching.RubyRemoteDebuggerRunner;

public class RubyRemoteLaunchConfigurationDelegate extends
		AbstractRemoteLaunchConfigurationDelegate {

	/*
	 * @see org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate#getDebuggingRunner(org.eclipse.dltk.launching.IInterpreterInstall)
	 */
	protected RemoteDebuggingEngineRunner getDebuggingRunner(
			IInterpreterInstall install) {
		return new RubyRemoteDebuggerRunner(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getLanguageId()
	 */
	public String getLanguageId() {
		return RubyNature.NATURE_ID;
	}

}
