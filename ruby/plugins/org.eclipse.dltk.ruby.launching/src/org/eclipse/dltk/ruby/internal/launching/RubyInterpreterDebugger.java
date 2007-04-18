package org.eclipse.dltk.ruby.internal.launching;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterRunnerConfiguration;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;
import org.eclipse.dltk.ruby.launching.IRubyLaunchConfigurationConstants;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;

public class RubyInterpreterDebugger extends AbstractInterpreterDebugger {
	private static final String DEBUGGER_DBGP_DIR = "dbgp";
	private static final String DEBUGGER_SCRIPT = "debugger.rb";

	private static final String RUBY_HOST_VAR = "DBGP_RUBY_HOST";
	private static final String RUBY_PORT_VAR = "DBGP_RUBY_PORT";
	private static final String RUBY_KEY_VAR = "DBGP_RUBY_KEY";
	private static final String RUBY_SCRIPT_VAR = "DBGP_RUBY_SCRIPT";
	private static final String RUBY_LOG_VAR = "DBGP_RUBY_LOG";

	public RubyInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	protected String getPluginId() {
		return RubyLaunchingPlugin.getUniqueIdentifier();
	}

	protected String getProcessType() {
		return IRubyLaunchConfigurationConstants.ID_RUBY_PROCESS_TYPE;
	}

	private void setupEnvironment(InterpreterRunnerConfiguration configuration,
			String host, int port, String sessionId) {
		List list = new ArrayList(Arrays.asList(configuration.getEnvironment()));

		list.add(RUBY_HOST_VAR + "=" + host);
		list.add(RUBY_PORT_VAR + "=" + Integer.toString(port));
		list.add(RUBY_KEY_VAR + "=" + sessionId);
		list.add(RUBY_SCRIPT_VAR + "=" + configuration.getScriptToLaunch());

		boolean logging = true;
		if (logging) {
			String logFile = RubyDebugPlugin.getDefault().getStateLocation()
					.append("debug_log.txt").toOSString();
			list.add(RUBY_LOG_VAR + "=" + logFile);
		}

		configuration.setEnvironment((String[]) list.toArray(new String[list
				.size()]));
	}

	protected String[] getCommandLine(String sessionId, String host, int port,
			InterpreterRunnerConfiguration configuration) throws CoreException {

		final String shell = constructProgramString(configuration);

		IPath debuggerLocation = null;

		try {
			debuggerLocation = RubyDebugPlugin.getDefault()
					.deployDebuggerSource();
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, getPluginId(),
					"Can't deploy debugger source", e));
		}

		IPath debuggerScript = debuggerLocation.append(DEBUGGER_DBGP_DIR)
				.append(DEBUGGER_SCRIPT);

		setupEnvironment(configuration, host, port, sessionId);

		return new String[] { shell, "-I" + debuggerLocation.toOSString(),
				debuggerScript.toOSString() };
	}
}
