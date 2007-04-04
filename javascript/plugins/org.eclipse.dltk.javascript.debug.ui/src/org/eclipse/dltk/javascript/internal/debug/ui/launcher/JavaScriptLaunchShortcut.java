package org.eclipse.dltk.javascript.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.ScriptLaunchShortcut;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.launching.IJavaScriptLaunchConfigurationConstants;

public class JavaScriptLaunchShortcut extends ScriptLaunchShortcut {

	// TODO: abstract in future
	protected ILaunchConfigurationType getConfigurationType() {
		return getLaunchManager().getLaunchConfigurationType(
				IJavaScriptLaunchConfigurationConstants.ID_JAVASCRIPT_SCRIPT);
	}

	protected String getNature() {
		return JavaScriptNature.NATURE_ID;
	}

}
