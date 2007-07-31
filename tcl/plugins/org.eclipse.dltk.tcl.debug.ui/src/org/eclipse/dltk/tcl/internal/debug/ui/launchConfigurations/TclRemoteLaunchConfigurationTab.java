package org.eclipse.dltk.tcl.internal.debug.ui.launchConfigurations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.debug.ui.launchConfigurations.RemoteLaunchConfigurationTab;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclRemoteLaunchConfigurationTab extends
		RemoteLaunchConfigurationTab {

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#validateProject(org.eclipse.dltk.core.IScriptProject)
	 */
	protected boolean validateProject(IScriptProject project) {
		if (project == null)
			return false;

		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager
					.getLanguageToolkit(project);
			if (toolkit instanceof TclLanguageToolkit) {
				return true;
			}
		} catch (CoreException e) {
		}

		return false;
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#getNatureID()
	 */
	protected String getNatureID() {
		return TclNature.NATURE_ID;
	}

}
