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
package org.eclipse.dltk.tcl.internal.core.packages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IAccessRule;
import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.launching.ScriptRuntime;

/**
 * 
 */
public class TclPackagesBuildpathContainer implements IBuildpathContainer {

	private IPath containerPath;
	private IScriptProject project;
	private static IAccessRule[] EMPTY_RULES = new IAccessRule[0];

	public TclPackagesBuildpathContainer(IPath containerPath,
			IScriptProject project) {
		this.containerPath = containerPath;
		this.project = project;
	}

	public IBuildpathEntry[] getBuildpathEntries() {
		IInterpreterInstall install = null;
		LibraryLocation[] locations = null;
		try {
			install = ScriptRuntime.getInterpreterInstall(this.project);
			if (install != null) {
				locations = ScriptRuntime.getLibraryLocations(install);
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		if (install != null) {
			Set packages = PackagesContainerHelper
					.getPackageContainerPackageNames(this.project);

			Set allPaths = new HashSet();
			for (Iterator iterator = packages.iterator(); iterator.hasNext();) {
				String pkgName = (String) iterator.next();
				IPath[] libs = PackagesManager.getInstance()
						.getPathsForPackageWithDeps(install, pkgName);
				if (libs != null) {
					allPaths.addAll(Arrays.asList(libs));
				}
			}

			List entries = new ArrayList(allPaths.size());
			Set rawEntries = new HashSet(allPaths.size());
			for (Iterator iterator = allPaths.iterator(); iterator.hasNext();) {
				IPath entryPath = (IPath) iterator.next();

				if (!entryPath.isEmpty()) {

					// resolve symlink
					try {
						File f = entryPath.toFile();
						if (f == null)
							continue;
						entryPath = new Path(f.getCanonicalPath());
					} catch (IOException e) {
						continue;
					}

					if (rawEntries.contains(entryPath))
						continue;

					/*
					 * if (!entryPath.isAbsolute()) Assert.isTrue(false, "Path
					 * for IBuildpathEntry must be absolute"); //$NON-NLS-1$
					 */
					IBuildpathAttribute[] attributes = new IBuildpathAttribute[0];
					ArrayList excluded = new ArrayList(); // paths to exclude
					for (Iterator iterator2 = allPaths.iterator(); iterator2
							.hasNext();) {
						IPath otherPath = (IPath) iterator2.next();
						if (otherPath.isEmpty())
							continue;
						// resolve symlink
						try {
							File f = entryPath.toFile();
							if (f == null)
								continue;
							entryPath = new Path(f.getCanonicalPath());
						} catch (IOException e) {
							continue;
						}

						// compare, if it contains some another
						if (entryPath.isPrefixOf(otherPath)
								&& !otherPath.equals(entryPath)) {
							IPath pattern = otherPath.removeFirstSegments(
									entryPath.segmentCount()).append("*");
							if (!excluded.contains(pattern)) {
								excluded.add(pattern);
							}
						}
					}
					boolean inInterpreter = false;
					if (locations != null) {
						for (int i = 0; i < locations.length; i++) {
							IPath path = locations[i].getLibraryPath();
							if (path.isPrefixOf(entryPath)) {
								inInterpreter = true;
								break;
							}
						}
					}
					if (!inInterpreter) {
						// Check for interpreter container libraries.
						entries.add(DLTKCore.newLibraryEntry(entryPath,
								EMPTY_RULES, attributes,
								BuildpathEntry.INCLUDE_ALL, (IPath[]) excluded
										.toArray(new IPath[excluded.size()]),
								false, true));
						rawEntries.add(entryPath);
					}
				}
			}
			return (IBuildpathEntry[]) entries
					.toArray(new IBuildpathEntry[entries.size()]);
		}
		return new IBuildpathEntry[0];
	}

	public String getDescription() {
		return "Libraries";
	}

	public int getKind() {
		return K_SYSTEM;
	}

	public IPath getPath() {
		return containerPath;
	}

	public IBuiltinModuleProvider getBuiltinProvider() {
		return null;
	}
}
