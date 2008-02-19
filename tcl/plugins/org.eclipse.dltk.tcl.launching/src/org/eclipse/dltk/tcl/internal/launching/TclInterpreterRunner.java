/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.launching;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.tcl.launching.TclLaunchConfigurationConstants;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class TclInterpreterRunner extends AbstractInterpreterRunner {
	public TclInterpreterRunner(IInterpreterInstall install) {
		super(install);
	}

	protected String getProcessType() {
		return TclLaunchConfigurationConstants.ID_TCL_PROCESS_TYPE;
	}

	protected void alterConfig(ILaunch launch, InterpreterConfig config) {
		super.alterConfig(launch, config);
		ILaunchConfiguration configuration = launch.getLaunchConfiguration();

		boolean useTclConsole = false;
		try {
			useTclConsole = configuration.getAttribute(
					ScriptLaunchConfigurationConstants.ATTR_USE_DLTK_OUTPUT,
					false);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		if (useTclConsole) {
			ScriptConsoleServer server = ScriptConsoleServer.getInstance();
			String port = Integer.toString(server.getPort());

			try {
				File scriptFile = TclLaunchingPlugin.getDefault()
						.getConsoleProxy().toFile();
				String id = configuration
						.getAttribute(
								ScriptLaunchConfigurationConstants.ATTR_DLTK_CONSOLE_ID,
								(String) null);
				config.addInterpreterArg(scriptFile.getAbsolutePath());
				config.addInterpreterArg("127.0.0.1");
				config.addInterpreterArg(port);
				config.addInterpreterArg(id);
			} catch (IOException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}
}
