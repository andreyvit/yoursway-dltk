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
package org.eclipse.dltk.tcl.internal.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.internal.core.packages.PackagesManager;

/**
 * 
 */
public class TclPackageBuildpathContainer implements IBuildpathContainer {

	private String name;
	private IScriptProject project;
	private static IAccessRule[] EMPTY_RULES = new IAccessRule[0];


	public TclPackageBuildpathContainer(String name, IScriptProject project) {
		this.name = name;
		this.project = project;
	}

	public IBuildpathEntry[] getBuildpathEntries() {
		IInterpreterInstall install = null;
		try {
			install = ScriptRuntime.getInterpreterInstall(this.project);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		if (install != null) {
			IPath[] libs = PackagesManager.getInstance().getPathsForPackageWithDeps(
					install, this.name);
			
			List entries = new ArrayList(libs.length);
			Set rawEntries = new HashSet(libs.length);
			for (int i = 0; i < libs.length; i++) {
				IPath entryPath = libs[i];
			
				if (!entryPath.isEmpty()) {
					
					//	resolve symlink
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
					
					/*if (!entryPath.isAbsolute())
						Assert.isTrue(false, "Path for IBuildpathEntry must be absolute"); //$NON-NLS-1$*/
					IBuildpathAttribute[] attributes = new IBuildpathAttribute[0];
					ArrayList excluded = new ArrayList(); // paths to exclude
					for (int j = 0; j < libs.length; j++) {
						IPath otherPath = libs[j];
						if (otherPath.isEmpty())
							continue;
						//resolve symlink
						try {
							File f = entryPath.toFile();
							if (f == null)
								continue;
							entryPath = new Path(f.getCanonicalPath());
						} catch (IOException e) {
							continue;
						}
											
						// compare, if it contains some another					
						if (entryPath.isPrefixOf(otherPath) && !otherPath.equals(entryPath) ) {						
							IPath pattern = otherPath.removeFirstSegments(entryPath.segmentCount()).append("*");
							if( !excluded.contains(pattern ) ) {
								excluded.add(pattern);
							}
						}
					}

					entries.add(DLTKCore.newLibraryEntry(entryPath, EMPTY_RULES, attributes,
							BuildpathEntry.INCLUDE_ALL, (IPath[]) excluded.toArray(new IPath[excluded.size()]), false, true));
					rawEntries.add (entryPath);
				}
			}			
			return (IBuildpathEntry[]) entries.toArray(new IBuildpathEntry[entries.size()]);
		}
		return new IBuildpathEntry[0];
	}

	public String getDescription() {
		return this.name;
	}

	public int getKind() {
		return K_SYSTEM;
	}

	public IPath getPath() {
		return new Path(TclPackageBuildpathContainerInitializer.CONTAINER_PATH)
				.append(this.name);
	}

	public IBuiltinModuleProvider getBuiltinProvider() {
		return null;
	}
}
