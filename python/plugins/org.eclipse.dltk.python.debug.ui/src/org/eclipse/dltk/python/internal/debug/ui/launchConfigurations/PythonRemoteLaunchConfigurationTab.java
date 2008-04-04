package org.eclipse.dltk.python.internal.debug.ui.launchConfigurations;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.launchConfigurations.RemoteLaunchConfigurationTab;
import org.eclipse.dltk.python.core.PythonLanguageToolkit;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.debug.PythonDebugPlugin;

/**
 * 'Connect' launch configuration tab for remote python scripts
 */
public class PythonRemoteLaunchConfigurationTab extends
		RemoteLaunchConfigurationTab {

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#breakOnFirstLinePrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected boolean breakOnFirstLinePrefEnabled(
			PreferencesLookupDelegate delegate) {
		return delegate.getBoolean(PythonDebugPlugin.PLUGIN_ID,
				DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#dbpgLoggingPrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
		return delegate.getBoolean(PythonDebugPlugin.PLUGIN_ID,
				DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#getNatureID()
	 */
	protected String getNatureID() {
		return PythonNature.NATURE_ID;
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#isValidToolkit(org.eclipse.dltk.core.IDLTKLanguageToolkit)
	 */
	protected boolean isValidToolkit(IDLTKLanguageToolkit toolkit) {
		return (toolkit instanceof PythonLanguageToolkit) ? true : false;
	}
}
