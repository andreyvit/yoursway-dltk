/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.tcl.activestatedebugger;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.launching.ExternalDebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConstants;

public class TclActiveStateDebuggerRunner extends ExternalDebuggingEngineRunner {
	private static final String HOST_KEY = "-host-ide";
	private static final String PORT_KEY = "-port-ide";
	private static final String SHELL_KEY = "-app-shell";
	private static final String IDE_KEY = "-ide-key";
	private static final String SCRIPT_KEY = "-app-file";
	private static final String ARGS_SEPARATOR = "--";

	public TclActiveStateDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	protected File getDebuggingEnginePath() {
		final String path = TclActiveStateDebuggerPlugin
				.getDefault()
				.getPreferenceStore()
				.getString(
						TclActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY);

		if (path != null) {
			return new File(path);
		}

		return null;
	}

	protected InterpreterConfig alterConfig(String exe,
			InterpreterConfig config, String debuggingEnginePath)
			throws CoreException {
		final String host = (String) config
				.getProperty(DbgpConstants.HOST_PROP);
		final String port = (String) config
				.getProperty(DbgpConstants.PORT_PROP);
		final String sessionId = (String) config
				.getProperty(DbgpConstants.SESSION_ID_PROP);

		InterpreterConfig newConfig = (InterpreterConfig) config.clone();

		// Additional property
		newConfig.setProperty("OVERRIDE_EXE", debuggingEnginePath);

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
}
