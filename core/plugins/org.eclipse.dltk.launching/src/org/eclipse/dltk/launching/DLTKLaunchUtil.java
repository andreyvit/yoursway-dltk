/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.dltk.core.IDLTKProject;

public class DLTKLaunchUtil {
	public static IInterpreterInstall getDefaultInterpreterInstall(String nature) {
		return ScriptRuntime.getDefaultInterpreterInstall(nature);
	}

	public static IInterpreterInstall getProjectInterpreterInstall(
			IDLTKProject project, String nature) throws CoreException {
		IInterpreterInstall interp = ScriptRuntime
				.getInterpreterInstall(project);
		if (interp == null) {
			interp = ScriptRuntime.getDefaultInterpreterInstall(nature);
		}
		return interp;
	}
	
	public static ILaunch launchScript(IInterpreterInstall install,
			String scriptPath, String[] args, String mode) throws CoreException {
		ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);

		InterpreterRunnerConfiguration config = new InterpreterRunnerConfiguration(
				scriptPath);
		config.setProgramArguments(args);

		IInterpreterRunner runner = install.getInterpreterRunner(mode);
		runner.run(config, launch, null);

		return launch;
	}
}
