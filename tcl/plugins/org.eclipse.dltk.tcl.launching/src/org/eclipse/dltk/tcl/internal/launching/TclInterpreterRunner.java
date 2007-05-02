/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.launching;

import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.launching.ITclLaunchConfigurationConstants;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class TclInterpreterRunner extends AbstractInterpreterRunner {
	public TclInterpreterRunner(IInterpreterInstall install) {
		super(install);
	}

	protected String[] alterCommandLine(String[] cmdLine, String id) {
		ScriptConsoleServer server = ScriptConsoleServer.getInstance();
		String port = Integer.toString(server.getPort());
		String[] newCmdLine = new String[cmdLine.length + 4];

		newCmdLine[0] = cmdLine[0];
		newCmdLine[1] = DLTKCore.getDefault().getStateLocation().append(
				"tcl_proxy").toOSString();

		newCmdLine[2] = "localhost";
		newCmdLine[3] = port;
		newCmdLine[4] = id;

		for (int i = 1; i < cmdLine.length; ++i) {
			newCmdLine[i + 4] = cmdLine[i];
		}

		return newCmdLine;
	}

	protected String getPluginId() {
		return TclLaunchingPlugin.getUniqueIdentifier();
	}

	protected String getProcessType() {
		return ITclLaunchConfigurationConstants.ID_TCL_PROCESS_TYPE;
	}
}
