/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.dltk.tcl.activestatedebugger;

import org.eclipse.dltk.launching.ExternalDebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConstants;

/**
 * Debugging engine implementation for ActiveState's tcl debugging engine.
 * 
 * <p>
 * see: <a
 * href="http://aspn.activestate.com/ASPN/docs/Komodo/komodo-doc-debugtcl.html">
 * http://aspn.activestate.com/ASPN/docs/Komodo/komodo-doc-debugtcl.html</a>
 * </p>
 */
public class TclActiveStateDebuggerRunner extends ExternalDebuggingEngineRunner {
	public static final String ENGINE_ID = "org.eclipse.dltk.tcl.avtivestatedebugger";

	private static final String HOST_KEY = "-host-ide";
	private static final String PORT_KEY = "-port-ide";
	private static final String SHELL_KEY = "-app-shell";
	private static final String IDE_KEY = "-ide-key";
	private static final String SCRIPT_KEY = "-app-file";
	private static final String ARGS_SEPARATOR = "--";

	public TclActiveStateDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.ExternalDebuggingEngineRunner#alterConfig(org.eclipse.dltk.launching.InterpreterConfig,
	 *      java.lang.String)
	 */
	protected InterpreterConfig alterConfig(InterpreterConfig config,
			String debuggingEnginePath) {
		final String exe = getInstall().getInstallLocation().getAbsolutePath();
		final String host = (String) config
				.getProperty(DbgpConstants.HOST_PROP);
		final String port = (String) config
				.getProperty(DbgpConstants.PORT_PROP);
		final String sessionId = (String) config
				.getProperty(DbgpConstants.SESSION_ID_PROP);

		InterpreterConfig newConfig = (InterpreterConfig) config.clone();

		// Additional property
		newConfig.setProperty(OVERRIDE_EXE, debuggingEnginePath);

		// Interpreter arguments
		newConfig.addInterpreterArg(HOST_KEY);
		newConfig.addInterpreterArg(host);

		newConfig.addInterpreterArg(PORT_KEY);
		newConfig.addInterpreterArg(port);

		newConfig.addInterpreterArg(SHELL_KEY);
		newConfig.addInterpreterArg(exe);

		newConfig.addInterpreterArg(IDE_KEY);
		newConfig.addInterpreterArg(sessionId);

		newConfig.addInterpreterArg(SCRIPT_KEY);

		// Script arguments
		newConfig.addScriptArg(ARGS_SEPARATOR);
		newConfig.addScriptArgs(config.getScriptArgs());

		return newConfig;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebuggingEngineId()
	 */
	protected String getDebuggingEngineId() {
		return ENGINE_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.ExternalDebuggingEngineRunner#getDebuggingEnginePreferenceKey()
	 */
	protected String getDebuggingEnginePreferenceKey() {
		return TclActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY;
	}

	/*
	 * @see org.eclipse.dltk.launching.ExternalDebuggingEngineRunner#getDebuggingEnginePreferenceQualifier()
	 */
	protected String getDebuggingEnginePreferenceQualifier() {
		return TclActiveStateDebuggerPlugin.PLUGIN_ID;
	}
}
