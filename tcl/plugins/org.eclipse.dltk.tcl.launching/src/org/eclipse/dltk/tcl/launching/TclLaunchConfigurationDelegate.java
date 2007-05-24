/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.launching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {

	public String getInterpreterArguments(ILaunchConfiguration configuration) {
		return "";
	}

	public String getLanguageId() {
		return TclNature.NATURE_ID;
	}

	protected String[] buildRunEnvironment(ILaunchConfiguration configuration)
			throws CoreException {
		String buildPath = createBuildPath(configuration);
		Map systemEnv = DebugPlugin.getDefault().getLaunchManager()
				.getNativeEnvironmentCasePreserved();

		boolean displayFixed = false; // for linux: preserve $DISPLAY
		boolean systemRootFixed = false; // for windows: preserve //
											// SystemRoot

		String[] envp = getEnvironment(configuration);
		String envLibName = "TCLLIBPATH";

		ArrayList envList = new ArrayList();

		// require all envirinment variables to run correctly on linux.
		Iterator sysI = systemEnv.keySet().iterator();
		while (sysI.hasNext()) {
			String key = (String) sysI.next();
			// System.out.println("Adding:" + key + " environment variable...");
			envList.add(key + "=" + systemEnv.get(key));
			if (key.equals(envLibName)) {
				buildPath += "" + Path.DEVICE_SEPARATOR + systemEnv.get(key);
			}
		}

		// append = so checks below will pass
		envLibName += "=";

		if (envp != null) {
			// exclude TCLLIBPATH from environment for future insertion
			for (int i = 0; i < envp.length; i++) {
				if (envp[i].startsWith("SystemRoot="))
					systemRootFixed = true;
				if (envp[i].startsWith("DISPLAY="))
					displayFixed = true;
				if (envp[i].startsWith(envLibName))
					continue;
				envList.add(envp[i]);
			}
		}

		if (!systemRootFixed && systemEnv.get("SystemRoot") != null)
			envList.add("SystemRoot=" + systemEnv.get("SystemRoot"));

		if (!displayFixed && systemEnv.get("DISPLAY") != null)
			envList.add("DISPLAY=" + systemEnv.get("DISPLAY"));

		envList.add(envLibName + buildPath);

		envp = (String[]) envList.toArray(new String[envList.size()]);

		return envp;
	}
	
	protected  String createNativeBuildPath(IPath[] paths) {
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < paths.length; ++i) {
			IPath path = paths[i];
			
			String osString = path.toPortableString();
			if (osString.indexOf(" ") != -1) {
				osString = "{" + osString + "}";
			}
			
			sb.append(osString);
			
			if (i < paths.length - 1) {
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
}
