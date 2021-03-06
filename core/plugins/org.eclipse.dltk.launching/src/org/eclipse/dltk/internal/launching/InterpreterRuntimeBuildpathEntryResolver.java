/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IAccessRule;
import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntryResolver;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntryResolver2;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.launching.ScriptRuntime;

/**
 * Resolves INTERPRETER_CONTAINER
 */
public class InterpreterRuntimeBuildpathEntryResolver implements
		IRuntimeBuildpathEntryResolver2 {

	private static IAccessRule[] EMPTY_RULES = new IAccessRule[0];

	/**
	 * @see IRuntimeBuildpathEntryResolver#resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry,
	 *      ILaunchConfiguration)
	 */
	public IRuntimeBuildpathEntry[] resolveRuntimeBuildpathEntry(
			IRuntimeBuildpathEntry entry, ILaunchConfiguration configuration)
			throws CoreException {
		String nature = configuration.getAttribute(
				ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE,
				(String) null);
		IInterpreterInstall InterpreterEnvironment = null;
		if (entry.getType() == IRuntimeBuildpathEntry.CONTAINER
				&& entry.getPath().segmentCount() > 1) {
			// a specific Interpreter
			InterpreterEnvironment = InterpreterContainerInitializer
					.resolveInterpreter(nature, entry.getPath());
		} else {
			// default Interpreter for config
			InterpreterEnvironment = ScriptRuntime
					.computeInterpreterInstall(configuration);
		}
		if (InterpreterEnvironment == null) {
			// cannot resolve InterpreterEnvironment
			return new IRuntimeBuildpathEntry[0];
		}
		return resolveLibraryLocations(InterpreterEnvironment, entry
				.getBuildpathProperty());
	}

	private String getNatureFromProject(IScriptProject project) {
		return DLTKLanguageManager.getLanguageToolkit(project)
				.getNatureId();
	}

	/**
	 * @see IRuntimeBuildpathEntryResolver#resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry,
	 *      IScriptProject)
	 */
	public IRuntimeBuildpathEntry[] resolveRuntimeBuildpathEntry(
			IRuntimeBuildpathEntry entry, IScriptProject project)
			throws CoreException {
		IInterpreterInstall InterpreterEnvironment = null;
		if (entry.getType() == IRuntimeBuildpathEntry.CONTAINER
				&& entry.getPath().segmentCount() > 1) {
			// a specific Interpreter
			InterpreterEnvironment = InterpreterContainerInitializer
					.resolveInterpreter(getNatureFromProject(project), entry
							.getPath());
		} else {
			// default Interpreter for project
			InterpreterEnvironment = ScriptRuntime
					.getInterpreterInstall(project);
		}
		if (InterpreterEnvironment == null) {
			// cannot resolve InterpreterEnvironment
			return new IRuntimeBuildpathEntry[0];
		}
		return resolveLibraryLocations(InterpreterEnvironment, entry
				.getBuildpathProperty());
	}

	/**
	 * Resolves library locations for the given Interpreter install
	 */
	protected IRuntimeBuildpathEntry[] resolveLibraryLocations(
			IInterpreterInstall interpreter, int kind) {
		LibraryLocation[] libs = interpreter.getLibraryLocations();
		LibraryLocation[] defaultLibs = interpreter.getInterpreterInstallType()
				.getDefaultLibraryLocations(interpreter.getInstallLocation(),
						interpreter.getEnvironmentVariables(), null);
		if (libs == null) {
			// default system libs
			libs = defaultLibs;
		}
		else if (!isSamePaths(libs, defaultLibs)) {
			// determine if bootpath should be explicit
			kind = IRuntimeBuildpathEntry.BOOTSTRAP_ENTRY;
		}
		List resolvedEntries = new ArrayList(libs.length);
		for (int i = 0; i < libs.length; i++) {
			IPath systemLibraryPath = libs[i].getLibraryPath();
			if (systemLibraryPath.toFile().exists()) {
				resolvedEntries.add(resolveLibraryLocation(interpreter,
						libs[i], kind));
			}
		}
		return (IRuntimeBuildpathEntry[]) resolvedEntries
				.toArray(new IRuntimeBuildpathEntry[resolvedEntries.size()]);
	}
	public static boolean isSamePaths(LibraryLocation[] libs, LibraryLocation[] defaultLibs) {
		if (libs.length != defaultLibs.length) {
			return false;
		}
		IPath dpath = null, lpath = null;
		for (int i = 0; i < defaultLibs.length; i++) {
			dpath = defaultLibs[i].getLibraryPath();
			lpath = libs[i].getLibraryPath();
			if(Platform.getOS().equals(Platform.OS_WIN32)) {
				//the .equals method of IPath ignores trailing seperators so we must as well
				if (!dpath.removeTrailingSeparator().toOSString().equalsIgnoreCase(lpath.removeTrailingSeparator().toOSString())) {
					return false;
				}
			} else if (!dpath.equals(lpath)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Return whether the given list of libraries refer to the same archives in
	 * the same order. Only considers the binary archive (not source or javadoc
	 * locations).
	 * 
	 * @param libs
	 * @param defaultLibs
	 * @return whether the given list of libraries refer to the same archives in
	 *         the same order
	 */
	public static boolean isSameArchives(LibraryLocation[] libs,
			LibraryLocation[] defaultLibs) {
		if (libs.length != defaultLibs.length) {
			return false;
		}
		for (int i = 0; i < defaultLibs.length; i++) {
			LibraryLocation def = defaultLibs[i];
			LibraryLocation lib = libs[i];
			if (!def.getLibraryPath().equals(lib.getLibraryPath())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @see IRuntimeBuildpathEntryResolver#resolveInterpreterInstall(IBuildpathEntry)
	 */
	public IInterpreterInstall resolveInterpreterInstall(String lang,
			IBuildpathEntry entry) {
		if (entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER)
			if (entry.getPath().segment(0).equals(
					ScriptRuntime.INTERPRETER_CONTAINER)) {
				try {
					return InterpreterContainerInitializer.resolveInterpreter(
							lang, entry.getPath());
				} catch (CoreException e) {
					return null;
				}
			}
		return null;
	}

	public boolean isInterpreterInstallReference(String lang,
			IBuildpathEntry entry) {
		if (entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER)
			if (entry.getPath().segment(0).equals(
					ScriptRuntime.INTERPRETER_CONTAINER)) {
				return true;
			}
		return false;
	}

	/**
	 * Returns a runtime buildpath entry for the given library in the specified
	 * Interpreter.
	 * 
	 * @param Interpreter
	 * @param location
	 * @param kind
	 * @return runtime buildpath entry
	 * 
	 */
	private IRuntimeBuildpathEntry resolveLibraryLocation(
			IInterpreterInstall Interpreter, LibraryLocation location, int kind) {
		IPath libraryPath = location.getLibraryPath();
		IBuildpathAttribute[] attributes = new IBuildpathAttribute[0];
		IBuildpathEntry bpe = DLTKCore.newLibraryEntry(libraryPath,
				EMPTY_RULES, attributes, false, false);
		IRuntimeBuildpathEntry resolved = new RuntimeBuildpathEntry(bpe);
		resolved.setBuildpathProperty(kind);
		return resolved;
	}

}
