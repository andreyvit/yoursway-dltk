package org.eclipse.dltk.ruby.internal.debug.ui.launchConfigurations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.debug.ui.launchConfigurations.RemoteLaunchConfigurationTab;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RubyRemoteLaunchConfigurationTab extends
		RemoteLaunchConfigurationTab {

	protected boolean validateProject(IScriptProject project) {
		if (project == null) {
			return false;
		}

		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager
					.getLanguageToolkit(project);
			if (toolkit instanceof RubyLanguageToolkit) {
				return true;
			}
		} catch (CoreException e) {

		}

		return false;
	}

	protected String getNatureID() {
		return RubyNature.NATURE_ID;
	}
}
