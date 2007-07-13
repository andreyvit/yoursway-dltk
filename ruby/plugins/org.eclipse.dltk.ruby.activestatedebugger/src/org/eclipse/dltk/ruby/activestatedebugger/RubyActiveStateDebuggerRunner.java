/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.activestatedebugger;

import java.io.File;

import org.eclipse.dltk.launching.ExternalDebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConstants;

/**
 * Debugging engine implementation for ActiveState's ruby debugging engine.
 * 
 * <p>
 * see: <a
 * href="http://aspn.activestate.com/ASPN/docs/Komodo/4.1/komodo-doc-debugruby.html">
 * http://aspn.activestate.com/ASPN/docs/Komodo/4.1/komodo-doc-debugruby.html</a>
 * </p>
 */
public class RubyActiveStateDebuggerRunner extends
		ExternalDebuggingEngineRunner {

	public RubyActiveStateDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	protected File getDebuggingEnginePath() {
		String path = RubyActiveStateDebuggerPlugin
				.getDefault()
				.getPreferenceStore()
				.getString(
						RubyActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY);
		return new File(path);
	}

	protected InterpreterConfig alterConfig(String exe,
			InterpreterConfig config, String debugEnginePath) {

		final String host = (String) config.getProperty(DbgpConstants.HOST_PROP);
		final String port = (String) config.getProperty(DbgpConstants.PORT_PROP);
		final String sessionId = (String) config
				.getProperty(DbgpConstants.SESSION_ID_PROP);

		String dir = debugEnginePath.substring(0, debugEnginePath
				.lastIndexOf('/'));

		/*
		 * TODO: handle RUBYOPT support for rubygems
		 * 
		 * unset if not explicity set to a value by user? see:
		 * http://aspn.activestate.com/ASPN/docs/Komodo/4.1/komodo-doc-debugruby.html
		 */
		config.addEnvVar("RUBYDB_OPTS", "RemotePort=" + host + ":" + port);
		config.addEnvVar("DBGP_IDEKEY", sessionId);
		// ruby -I"$dbgdir" -r "$dbgdir"/rdbgp.rb <Program_To_Debug.rb>
		config.addInterpreterArg("-I");
		config.addInterpreterArg(dir);

		config.addInterpreterArg("-r");
		config.addInterpreterArg(debugEnginePath);

		return config;
	}
}
