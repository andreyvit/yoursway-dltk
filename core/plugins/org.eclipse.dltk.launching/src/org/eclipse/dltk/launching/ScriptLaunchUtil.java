/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.internal.launching.EnvironmentResolver;

public class ScriptLaunchUtil {
	// Create file with script content
	public static File createScriptFileWithContent(String scriptContent)
			throws IOException {
		File file = File.createTempFile("script", null);

		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(scriptContent);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

		return file;
	}

	// Creating of InterpreterConfig
	public static InterpreterConfig createInterpreterConfig(File scriptFile,
			File workingDirectory) {
		return createInterpreterConfig(scriptFile, workingDirectory, null);
	}

	public static InterpreterConfig createInterpreterConfig(File scriptFile,
			File workingDirectory, EnvironmentVariable[] env) {
		InterpreterConfig config = new InterpreterConfig(scriptFile,
				workingDirectory);

		Map envVars = DebugPlugin.getDefault().getLaunchManager()
				.getNativeEnvironmentCasePreserved();
		config.addEnvVars(envVars);
		EnvironmentVariable[] resVars = EnvironmentResolver.resolve(envVars,
				env);
		if (resVars != null) {
			for (int i = 0; i < resVars.length; i++) {
				config.addEnvVar(resVars[i].getName(), resVars[i].getValue());
			}
		}

		return config;
	}

	// Useful run methods
	public static Process runScriptWithInterpreter(String interpreter,
			InterpreterConfig config) throws CoreException {
		String[] cmdLine = config.renderCommandLine(interpreter);

		String[] environmentAsStrings = config.getEnvironmentAsStrings();
		IPath workingDirectoryPath = config.getWorkingDirectoryPath();
		File file = null;
		if (workingDirectoryPath != null) {
			file = workingDirectoryPath.toFile();
		}
		if (DLTKLaunchingPlugin.TRACE_EXECUTION) {
			traceExecution("runScript with interpreter", cmdLine,
					environmentAsStrings);
		}
		return DebugPlugin.exec(cmdLine, file, environmentAsStrings);
	}

	private static void traceExecution(String processLabel,
			String[] cmdLineLabel, String[] environment) {
		StringBuffer sb = new StringBuffer();
		sb.append("-----------------------------------------------\n");
		sb.append("Running ").append(processLabel).append('\n');
		// sb.append("Command line: ").append(cmdLineLabel).append('\n');
		sb.append("Command line: ");
		for (int i = 0; i < cmdLineLabel.length; i++) {
			sb.append(" " + cmdLineLabel[i]);
		}
		sb.append("\n");
		sb.append("Environment:\n");
		for (int i = 0; i < environment.length; i++) {
			sb.append('\t').append(environment[i]).append('\n');
		}
		sb.append("-----------------------------------------------\n");
		System.out.println(sb);
	}

	public static Process runScriptWithInterpreter(String interpreter,
			File scriptFile, File workingDirectory, String[] interpreterArgs,
			String[] scriptArgs, EnvironmentVariable[] environment)
			throws CoreException {
		InterpreterConfig config = createInterpreterConfig(scriptFile,
				workingDirectory, environment);

		if (scriptArgs != null) {
			config.addScriptArgs(scriptArgs);
		}

		if (interpreterArgs != null) {
			config.addInterpreterArgs(interpreterArgs);
		}

		return runScriptWithInterpreter(interpreter, config);
	}

	public static Process runScriptWithInterpreter(String interpreter,
			String scriptContent, File workingDirectory,
			String[] interpreterArgs, String[] scriptArgs,
			EnvironmentVariable[] environment) throws CoreException,
			IOException {
		return runScriptWithInterpreter(interpreter,
				createScriptFileWithContent(scriptContent), workingDirectory,
				interpreterArgs, scriptArgs, environment);
	}

	// 
	public static IInterpreterInstall getDefaultInterpreterInstall(
			String natureId) {
		return ScriptRuntime.getDefaultInterpreterInstall(natureId);
	}

	public static IInterpreterInstall getProjectInterpreterInstall(
			IScriptProject project) throws CoreException {
		return ScriptRuntime.getInterpreterInstall(project);
	}

	// General run method
	public static ILaunch runScript(IInterpreterInstall install,
			InterpreterConfig config, IProgressMonitor monitor)
			throws CoreException {

		if (install == null) {
			// TODO: Handle this error!!!
		}

		ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);

		// will use 'instance scoped' interpreter here
		IInterpreterRunner runner = install.getInterpreterRunner(launch
				.getLaunchMode());

		runner.run(config, launch, monitor);

		return launch;
	}

	// Run by interpreter form project
	public static ILaunch runScript(IScriptProject project,
			InterpreterConfig config, IProgressMonitor monitor)
			throws CoreException {
		return runScript(getProjectInterpreterInstall(project), config, monitor);
	}

	// Run by default interpreter
	public static ILaunch runScript(String natureId, InterpreterConfig config,
			IProgressMonitor monitor) throws CoreException {
		IInterpreterInstall install = getDefaultInterpreterInstall(natureId);
		EnvironmentVariable[] variables = EnvironmentResolver.resolve(config
				.getEnvVars(), install.getEnvironmentVariables());
		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				config.addEnvVar(variables[i].getName(), variables[i]
						.getValue());
			}
		}
		return runScript(install, config, monitor);
	}

	// Script file
	public static ILaunch runScript(String natureId, File scriptFile,
			File workingDirectory, String[] interpreterArgs,
			String[] scriptArgs, IProgressMonitor monitor) throws CoreException {
		InterpreterConfig config = createInterpreterConfig(scriptFile,
				workingDirectory, null);

		if (interpreterArgs != null) {
			config.addInterpreterArgs(interpreterArgs);
		}

		if (scriptArgs != null) {
			config.addScriptArgs(scriptArgs);
		}

		return runScript(natureId, config, monitor);
	}

	public static ILaunch runScript(String natureId, File scriptFile)
			throws CoreException {
		return runScript(natureId, scriptFile, null, null, null, null);
	}

	// Script content
	public static ILaunch runScript(String natureId, String scriptContent,
			File workingDirectory, String[] interpreterArgs,
			String[] scriptArgs, IProgressMonitor monitor)
			throws CoreException, IOException {
		return runScript(natureId, createScriptFileWithContent(scriptContent),
				workingDirectory, interpreterArgs, scriptArgs, monitor);
	}

	public static ILaunch runScript(String natureId, String scriptContent)
			throws CoreException, IOException {
		return runScript(natureId, createScriptFileWithContent(scriptContent));
	}
}
