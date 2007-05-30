/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPreferences;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class TclInterpreterDebugger extends AbstractInterpreterDebugger {
	private static final String HOST_KEY = "-host-ide";
	private static final String PORT_KEY = "-port-ide";
	private static final String SHELL_KEY = "-app-shell";
	private static final String IDE_KEY = "-ide-key";
	private static final String SCRIPT_KEY = "-app-file";
	private static final String ARGS_SEPARATOR = "--";

	protected String getPluginId() {
		return TclLaunchingPlugin.PLUGIN_ID;
	}

	protected File getEngine() throws CoreException {
		File engine = new File(TclDebugPreferences.getDebuggingEnginePath());

		// TODO: add more checks
		if (!engine.isFile()) {
			abort("Tcl debugging engine not configured", null, 180);
		}
		return engine;
	}

	public TclInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	protected String[] getCommandLine(InterpreterConfig configuration,
			String sessionId, String host, int port) throws CoreException {
		final String shell = constructProgramString();

		List list = new ArrayList();

		list.add(getEngine().getAbsolutePath());
		list.add(HOST_KEY);
		list.add(host);
		list.add(PORT_KEY);
		list.add(Integer.toString(port));
		list.add(SHELL_KEY);
		list.add(shell);
		list.add(IDE_KEY);
		list.add(sessionId);
		list.add(SCRIPT_KEY);
		list.add(configuration.getScriptFile().toString());
		list.add(ARGS_SEPARATOR);
		list.addAll(configuration.getScriptArgs());

		return (String[]) list.toArray(new String[list.size()]);
	}

	protected String getDebugModelIdentidier() {
		return "org.eclipse.dltk.debug.tclModel";
	}
}
