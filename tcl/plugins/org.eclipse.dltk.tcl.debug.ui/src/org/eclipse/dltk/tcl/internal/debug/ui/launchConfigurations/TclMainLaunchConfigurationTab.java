package org.eclipse.dltk.tcl.internal.debug.ui.launchConfigurations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.swt.graphics.Image;

public class TclMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

	protected boolean validateProject(IDLTKProject project) {
		if (project == null)
			return false;

		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager
					.getLangaugeToolkit(project);
			if (toolkit instanceof TclLanguageToolkit) {
				return true;
			}
		} catch (CoreException e) {
		}

		return false;
	}

	protected String getLanguageName() {
		return "TCL";
	}

	public Image getImage() {
		return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_CLASS);
	}
}
