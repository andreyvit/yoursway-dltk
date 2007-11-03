package org.eclipse.dltk.python.internal.debug.ui.launchConfigurations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.debug.ui.launchConfigurations.RemoteLaunchConfigurationTab;
import org.eclipse.dltk.python.core.PythonLanguageToolkit;
import org.eclipse.dltk.python.core.PythonNature;

public class PythonRemoteLaunchConfigurationTab extends
		RemoteLaunchConfigurationTab {
	
	protected String getNatureID() {
		return PythonNature.NATURE_ID;
	}

	protected boolean validateProject(IScriptProject project) {
		if (project == null) {
			return false;
		}

		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager
					.getLanguageToolkit(project);

			if (toolkit instanceof PythonLanguageToolkit) {
				return true;
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	protected void doPerformApply(ILaunchConfigurationWorkingCopy config) {
		super.doPerformApply(config);

		config.setAttribute("suspendOnEntry", false);
		config.setAttribute("suspendOnExit", false);
	}
}
