package org.eclipse.dltk.launching.sourcelookup;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.ScriptRuntime;

public class ScriptSourcePathComputer implements ISourcePathComputerDelegate {
	/*
	 * @see org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate#computeSourceContainers(org.eclipse.debug.core.ILaunchConfiguration,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public ISourceContainer[] computeSourceContainers(
			ILaunchConfiguration configuration, IProgressMonitor monitor)
			throws CoreException {
		IRuntimeBuildpathEntry[] entries = ScriptRuntime
				.computeUnresolvedSourceBuildpath(configuration);
		IRuntimeBuildpathEntry[] resolved = ScriptRuntime
				.resolveRuntimeBuildpath(entries, configuration);

		ISourceContainer[] containers = ScriptRuntime
				.getSourceContainers(resolved);

		/*
		 * some script languages don't like having project root on their 
		 * include path, but we still want it on the search path.
		 * 
		 * this is a cheat to include the project's root directory when 
		 * searching for source if it is not already in the list of searchable
		 * containers
		 *
		 * it should be noted that individual language implementations should
		 * provide the necessary safe guards in the event the project root
		 * *is* a part of their include path, and should not be.
		 */
		IProject project = LaunchConfigurationUtils.getProject(configuration);
		if (project != null) {
			FolderSourceContainer container = new FolderSourceContainer(
					project, false);

			ArrayList list = new ArrayList(Arrays.asList(containers));
			if (!list.contains(container)) {
				list.add(container);
			}

			return (ISourceContainer[]) list.toArray(new ISourceContainer[list
					.size()]);
		}

		return containers;
	}

}
