package org.eclipse.dltk.tcl.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.ScriptLaunchShortcut;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.launching.ITclLaunchConfigurationConstants;

public class TclLaunchShortcut extends ScriptLaunchShortcut {

	// TODO: abstract in future
	protected ILaunchConfigurationType getConfigurationType() {
		return getLaunchManager().getLaunchConfigurationType(
				ITclLaunchConfigurationConstants.ID_TCL_SCRIPT);
	}

	protected String getNature() {
		return TclNature.NATURE_ID;
	}

}
