/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.console;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.launching.DLTKLaunchUtil;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class TclConsoleUtil {
	public static void runDefaultTclInterpreter(TclInterpreter interpreter)
			throws CoreException, IOException {
		IInterpreterInstall install = DLTKLaunchUtil
				.getDefaultInterpreterInstall(TclNature.NATURE_ID);

		ScriptConsoleServer server = ScriptConsoleServer.getInstance();

		String id = server.register(interpreter);
		String port = Integer.toString(server.getPort());

		String[] args = new String[] { "127.0.0.1", port, id };

		DLTKLaunchUtil.launchScript(install, TclLaunchingPlugin.getDefault()
				.getConsoleProxy().toOSString(), args, ILaunchManager.RUN_MODE);
	}
}
