/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {
	
	private static final String TCLLIBPATH_ENV_VAR = "TCLLIBPATH";

	public String getLanguageId() {
		return TclNature.NATURE_ID;
	}

	protected void addLibpathEnvVar(InterpreterConfig config,
			ILaunchConfiguration configuration) throws CoreException {
		config.removeEnvVar(TCLLIBPATH_ENV_VAR);

		IPath[] paths = createBuildPath(configuration);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < paths.length; ++i) {
			sb.append('{');
			sb.append(paths[i].toOSString());
			sb.append('}');
			if (i < paths.length - 1) {
				sb.append(' ');
			}
		}

		config.addEnvVar(TCLLIBPATH_ENV_VAR, sb.toString());
	}

	protected void checkEnvVars(InterpreterConfig config,
			ILaunchConfiguration configuration) {
		// TODO
		/*
		 * if (envp != null) { // exclude TCLLIBPATH from environment for future
		 * insertion for (int i = 0; i < envp.length; i++) { if
		 * (envp[i].startsWith("SystemRoot=")) systemRootFixed = true; if
		 * (envp[i].startsWith("DISPLAY=")) displayFixed = true; if
		 * (envp[i].startsWith(envLibName)) continue; envList.add(envp[i]); } }
		 * 
		 * if (!systemRootFixed && systemEnv.get("SystemRoot") != null)
		 * envList.add("SystemRoot=" + systemEnv.get("SystemRoot"));
		 * 
		 * if (!displayFixed && systemEnv.get("DISPLAY") != null)
		 * envList.add("DISPLAY=" + systemEnv.get("DISPLAY"));
		 */
	}

	protected InterpreterConfig createInterpreterConfiguration(
			ILaunchConfiguration configuration, ILaunch launch)
			throws CoreException {
		InterpreterConfig config = super.createInterpreterConfiguration(
				configuration, launch);

		addLibpathEnvVar(config, configuration);
		checkEnvVars(config, configuration);

		return config;
	}
}
