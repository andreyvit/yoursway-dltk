package org.eclipse.dltk.ruby.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;

public class RubyRemoteDebuggerRunner extends RemoteDebuggingEngineRunner {

	public RubyRemoteDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
	 */
	protected String getDebugPreferenceQualifier() {
		return RubyDebugPlugin.PLUGIN_ID;
	}

}
