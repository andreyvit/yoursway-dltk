/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.launching;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;
import org.eclipse.dltk.ruby.launching.IRubyLaunchConfigurationConstants;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;

public class RubyInterpreterDebugger extends AbstractInterpreterDebugger {
	private static final String DEBUGGER_DBGP_DIR = "dbgp";
	private static final String DEBUGGER_SCRIPT = "runner.rb";

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

	private void setupEnvironment(InterpreterConfig config, String host,
			int port, String sessionId, File scriptToDebug, boolean logging) {
		config.addEnvVar(RUBY_HOST_VAR, host);
		config.addEnvVar(RUBY_PORT_VAR, Integer.toString(port));
		config.addEnvVar(RUBY_KEY_VAR, sessionId);
		config.addEnvVar(RUBY_SCRIPT_VAR, scriptToDebug.toString());

		if (logging) {
			String logFile = RubyDebugPlugin.getDefault().getStateLocation()
					.append("debug_log.txt").toOSString();
			config.addEnvVar(RUBY_LOG_VAR, logFile);
		}
	}

	protected String[] getCommandLine(InterpreterConfig config,
			String sessionId, String host, int port) throws CoreException {
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

		InterpreterConfig newConfig = new InterpreterConfig(
				debuggerScript.toFile());
		newConfig.addEnvVars(config.getEnvVars());		
		newConfig.addInterpreterArg("-I" + debuggerLocation.toOSString());
		
		setupEnvironment(config, host, port, sessionId, config
				.getScriptFile(), true);

		return newConfig.renderCommandLine(constructProgramString());
	}

	protected String getDebugModelIdentidier() {
		return "org.eclipse.dltk.debug.rubyModel";
	}
}
