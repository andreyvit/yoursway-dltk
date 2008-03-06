/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.basicdebugger;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.DebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpInterpreterConfig;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;
import org.eclipse.dltk.ruby.internal.launching.JRubyInstallType;

public class RubyBasicDebuggerRunner extends DebuggingEngineRunner {
	public static final String ENGINE_ID = "org.eclipse.dltk.ruby.basicdebugger";

	private static final String RUBY_HOST_VAR = "DBGP_RUBY_HOST";
	private static final String RUBY_PORT_VAR = "DBGP_RUBY_PORT";
	private static final String RUBY_KEY_VAR = "DBGP_RUBY_KEY";
	private static final String RUBY_LOG_VAR = "DBGP_RUBY_LOG";

	private static final String DEBUGGER_SCRIPT = "BasicRunner.rb";

	protected IPath deploy() throws CoreException {
		try {
			return RubyBasicDebuggerPlugin.getDefault().deployDebuggerSource();
		} catch (IOException e) {
			abort("Can't deploy debugger source", e);
		}

		return null;
	}

	public RubyBasicDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	protected InterpreterConfig addEngineConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) throws CoreException {
		// Get debugger source location
		final IPath sourceLocation = deploy();

		final IPath scriptFile = sourceLocation.append(DEBUGGER_SCRIPT);

		// Creating new config
		InterpreterConfig newConfig = (InterpreterConfig) config.clone();

		if (getInstall().getInterpreterInstallType() instanceof JRubyInstallType) {
			newConfig.addEnvVar("JAVA_OPTS", "-Djruby.jit.enabled=false");
			newConfig.addInterpreterArg("-X-C");
		}

		newConfig.addInterpreterArg("-r" + scriptFile.toPortableString());
		newConfig.addInterpreterArg("-I" + sourceLocation.toPortableString());

		// Environment
		final DbgpInterpreterConfig dbgpConfig = new DbgpInterpreterConfig(
				config);

		String sessionId = dbgpConfig.getSessionId();

		newConfig.addEnvVar(RUBY_HOST_VAR, dbgpConfig.getHost());
		newConfig.addEnvVar(RUBY_PORT_VAR, Integer.toString(dbgpConfig
				.getPort()));
		newConfig.addEnvVar(RUBY_KEY_VAR, sessionId);

		if (isLoggingEnabled(delegate)) {
			newConfig.addEnvVar(RUBY_LOG_VAR, getLogFileName(delegate,
					sessionId).getAbsolutePath());
		}

		return newConfig;
	}

	protected String getDebuggingEngineId() {
		return ENGINE_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
	 */
	protected String getDebugPreferenceQualifier() {
		return RubyDebugPlugin.PLUGIN_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebuggingEnginePreferenceQualifier()
	 */
	protected String getDebuggingEnginePreferenceQualifier() {
		return RubyBasicDebuggerPlugin.PLUGIN_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLoggingEnabledPreferenceKey()
	 */
	protected String getLoggingEnabledPreferenceKey() {
		return RubyBasicDebuggerConstants.ENABLE_LOGGING;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLogFileNamePreferenceKey()
	 */
	protected String getLogFileNamePreferenceKey() {
		return RubyBasicDebuggerConstants.LOG_FILE_NAME;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLogFilePathPreferenceKey()
	 */
	protected String getLogFilePathPreferenceKey() {
		return RubyBasicDebuggerConstants.LOG_FILE_PATH;
	}
}
