package org.eclipse.dltk.internal.launching;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;

public final class LaunchConfigurationUtils {

	/*
	 * does a class like this exist elsewhere?
	 */

	/**
	 * Returns the project associated with the launch configuration
	 * 
	 * @param configuration
	 *            launch configuration
	 * 
	 * @return project instance associated with the configuration, or
	 *         <code>null</code> if the project can not be found
	 */
	public static IProject getProject(ILaunchConfiguration configuration)
			throws CoreException {
		String projectName = configuration.getAttribute(
				ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME,
				(String) null);

		IProject project = null;
		if (projectName != null) {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		}

		return project;
	}

}
