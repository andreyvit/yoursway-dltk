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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.javascript.launching.JavaScriptLaunchConfigurationConstants;
import org.eclipse.dltk.javascript.launching.JavaScriptLaunchingPlugin;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConstants;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.osgi.framework.Bundle;

public class JavaScriptInterpreterRunner extends AbstractInterpreterRunner {

	public void run(InterpreterConfig config, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		doRunImpl(config, launch);
	}

	public static void doRunImpl(InterpreterConfig config, ILaunch launch)
			throws CoreException {
		
		String host = (String) config.getProperty(DbgpConstants.HOST_PROP);
		if (host == null) {
			host = "";
		}
		
		String port = (String) config.getProperty(DbgpConstants.PORT_PROP);
		if (port == null) {
			port = "";
		}
		
		String sessionId = (String) config
				.getProperty(DbgpConstants.SESSION_ID_PROP);
		
		if (sessionId == null) {
			sessionId = "";
		}
		
		IDLTKProject proj = AbstractScriptLaunchConfigurationDelegate
				.getScriptProject(launch.getLaunchConfiguration());
		IJavaProject myJavaProject = JavaCore.create(proj.getProject());
		IVMInstall vmInstall = JavaRuntime.getVMInstall(myJavaProject);
		if (vmInstall == null)
			vmInstall = JavaRuntime.getDefaultVMInstall();
		if (vmInstall != null) {
			IVMRunner vmRunner = vmInstall.getVMRunner(ILaunchManager.DEBUG_MODE);
			if (vmRunner != null) {
				String[] classPath = null;
				try {
					classPath = JavaRuntime
							.computeDefaultRuntimeClassPath(myJavaProject);
				} catch (CoreException e) {
				}
				if (classPath != null) {

					Bundle bundle = Platform
							.getBundle(GenericJavaScriptInstallType.EMBEDDED_RHINO_BUNDLE_ID);
					
					Bundle bundle1 = Platform.getBundle(GenericJavaScriptInstallType.DBGP_FOR_RHINO_BUNDLE_ID);
					try {
						URL resolve = FileLocator.toFileURL(bundle1
								.getResource("RhinoRunner.class"));
						try {
							File fl = new File(new URI(resolve.toString())).getParentFile();
							URL fileURL = FileLocator.toFileURL(bundle.getResource("org/mozilla/classfile/ByteCode.class"));
							File fl1 = new File(new URI(fileURL.toString())).getParentFile().getParentFile().getParentFile().getParentFile();
							String[] newClassPath = new String[classPath.length + 2];
							System.arraycopy(classPath, 0, newClassPath, 0,
									classPath.length);
							newClassPath[classPath.length] = fl
									.getAbsolutePath();
							newClassPath[classPath.length+1] = fl1.getAbsolutePath();
							VMRunnerConfiguration vmConfig = new VMRunnerConfiguration(
									"RhinoRunner", newClassPath);
							vmConfig.setProgramArguments(new String[] { config
									.getScriptFile().toString(),host,""+port,sessionId });
							ILaunch launchr = new Launch(launch.getLaunchConfiguration(),
									ILaunchManager.DEBUG_MODE, null);
							vmRunner.run(vmConfig, launchr, null);
							IDebugTarget[] debugTargets = launchr.getDebugTargets();
							for (int a=0;a<debugTargets.length;a++){
								launch.addDebugTarget(debugTargets[a]);
							}
							IProcess[] processes = launchr.getProcesses();
							for (int a = 0; a < processes.length; a++)
								launch.addProcess(processes[a]);
							return;
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		throw new CoreException(new Status(IStatus.ERROR, "", ""));
	}

	protected String constructProgramString(
			InterpreterConfig config) throws CoreException {

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
		return JavaScriptLaunchConfigurationConstants.ID_JAVASCRIPT_PROCESS_TYPE;
	}

	protected String getPluginId() {
		return JavaScriptLaunchingPlugin.PLUGIN_ID;
	}
}
