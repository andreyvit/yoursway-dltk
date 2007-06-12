/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.IDLTKProject;


/**
 * Default implementation for buildpath provider.
 * <p>
 * This class may be subclassed.
 * </p>
 */
public class StandardBuildpathProvider implements IRuntimeBuildpathProvider {
	public IRuntimeBuildpathEntry[] computeUnresolvedBuildpath(ILaunchConfiguration configuration) throws CoreException {
		boolean useDefault = configuration.getAttribute(ScriptLaunchConfigurationConstants.ATTR_DEFAULT_BUILDPATH, true);
		if (useDefault) {
			IDLTKProject proj = ScriptRuntime.getDLTKProject(configuration);
			IRuntimeBuildpathEntry InterpreterEnvironmentEntry = ScriptRuntime.computeInterpreterEntry(configuration);
			if (proj == null) {
				//no project - use default libraries
				if (InterpreterEnvironmentEntry == null) {
					return new IRuntimeBuildpathEntry[0];
				}
				return new IRuntimeBuildpathEntry[]{InterpreterEnvironmentEntry};				
			}
			IRuntimeBuildpathEntry[] entries = ScriptRuntime.computeUnresolvedRuntimeBuildpath(proj);
			// replace project interpreter with config's interpreter
			IRuntimeBuildpathEntry projEntry = ScriptRuntime.computeInterpreterEntry(proj);
			if (InterpreterEnvironmentEntry != null && projEntry != null) {
				if (!InterpreterEnvironmentEntry.equals(projEntry)) {
					for (int i = 0; i < entries.length; i++) {
						IRuntimeBuildpathEntry entry = entries[i];
						if (entry.equals(projEntry)) {
							entries[i] = InterpreterEnvironmentEntry;
							return entries;
						}
					}
				}
			}
			return entries;
		}
		// recover persisted buildpath
		return recoverRuntimePath(configuration, ScriptLaunchConfigurationConstants.ATTR_BUILDPATH);
	}
	
	public IRuntimeBuildpathEntry[] resolveBuildpath(IRuntimeBuildpathEntry[] entries, ILaunchConfiguration configuration) throws CoreException {
		List all = new ArrayList(entries.length);
		for (int i = 0; i < entries.length; i++) {
			IRuntimeBuildpathEntry[] resolved =ScriptRuntime.resolveRuntimeBuildpathEntry(entries[i], configuration);
			for (int j = 0; j < resolved.length; j++) {
				all.add(resolved[j]);
			}
		}
		return (IRuntimeBuildpathEntry[])all.toArray(new IRuntimeBuildpathEntry[all.size()]);
	}
	
	/**
	 * Returns a collection of runtime buildpath entries that are defined in the
	 * specified attribute of the given launch configuration. When present,
	 * the attribute must contain a list of runtime buildpath entry mementos.
	 * 
	 * @param configuration launch configuration
	 * @param attribute attribute name containing the list of entries
	 * @return collection of runtime buildpath entries that are defined in the
	 *  specified attribute of the given launch configuration
	 * @exception CoreException if unable to retrieve the list
	 */
	protected IRuntimeBuildpathEntry[] recoverRuntimePath(ILaunchConfiguration configuration, String attribute) throws CoreException {
		List entries = configuration.getAttribute(attribute, Collections.EMPTY_LIST);
		IRuntimeBuildpathEntry[] rtes = new IRuntimeBuildpathEntry[entries.size()];
		Iterator iter = entries.iterator();
		int i = 0;
		while (iter.hasNext()) {
			rtes[i] = ScriptRuntime.newRuntimeBuildpathEntry((String)iter.next());
			i++;
		}
		return rtes;		
	}	

}
