package org.eclipse.dltk.ruby.internal.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.launching.IInterpreterInstall;

public abstract class RubyDebuggingEngine implements IRubyDebuggingEngine {
		
	private static final String RUBY_HOST_VAR = "DBGP_RUBY_HOST";
	private static final String RUBY_PORT_VAR = "DBGP_RUBY_PORT";
	private static final String RUBY_KEY_VAR = "DBGP_RUBY_KEY";
	private static final String RUBY_SCRIPT_VAR = "DBGP_RUBY_SCRIPT";
	private static final String RUBY_LOG_VAR = "DBGP_RUBY_LOG";
	
	public void run(DbgpConnectParams params, IInterpreterInstall install)
			throws CoreException {
		List env = new ArrayList();		
		env.add(RUBY_HOST_VAR + "=" + params.getHost());
		env.add(RUBY_PORT_VAR + "=" + params.getPort());
		env.add(RUBY_KEY_VAR + "=" + params.getKey());
		env.add(RUBY_SCRIPT_VAR + "=" + "TODO");
				
		DebugPlugin.getDefault().exec(getCommandLine(install), null, null);
	}
	
	protected abstract String[] getCommandLine(IInterpreterInstall install) throws CoreException;
}
