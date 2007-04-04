/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.launching;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.debug.core.Launch;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.javascript.launching.IJavaScriptLaunchConfigurationConstants;
import org.eclipse.dltk.javascript.launching.JavaScriptLaunchingPlugin;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IDLTKLaunchConfigurationConstants;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterRunnerConfiguration;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.osgi.framework.Bundle;

public class JavaScriptInterpreterRunner extends AbstractInterpreterRunner {

	@Override
	public void run(InterpreterRunnerConfiguration config, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		IDLTKProject proj = AbstractScriptLaunchConfigurationDelegate
				.getScriptProject(launch.getLaunchConfiguration());
		IJavaProject myJavaProject = JavaCore.create(proj.getProject());
		IVMInstall vmInstall = JavaRuntime.getVMInstall(myJavaProject);
		if (vmInstall == null)
			vmInstall = JavaRuntime.getDefaultVMInstall();
		if (vmInstall != null) {
			IVMRunner vmRunner = vmInstall.getVMRunner(ILaunchManager.RUN_MODE);
			if (vmRunner != null) {
				String[] classPath = null;
				try {
					classPath = JavaRuntime
							.computeDefaultRuntimeClassPath(myJavaProject);
				} catch (CoreException e) {
				}
				if (classPath != null) {
					
					VMRunnerConfiguration vmConfig = new VMRunnerConfiguration(
							"MyClass", classPath);
					ILaunch launchr = new Launch(null, ILaunchManager.RUN_MODE,
							null);
					vmRunner.run(vmConfig, launchr, null);
					Bundle bundle = Platform.getBundle("org.eclipse.dltk.javascript.rhino");
					FileLocator.resolve(bundle.getResource(""));
				}
			}
		}
		throw new CoreException(new Status(IStatus.ERROR, "", ""));
	}

	@Override
	protected String constructProgramString(
			InterpreterRunnerConfiguration config) throws CoreException {

		return "";
	}

	public JavaScriptInterpreterRunner(IInterpreterInstall install) {
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

	protected String getPluginIdentifier() {
		return JavaScriptLaunchingPlugin.getUniqueIdentifier();
	}

	protected String getProcessType() {
		return IJavaScriptLaunchConfigurationConstants.ID_JAVASCRIPT_PROCESS_TYPE;
	}
}