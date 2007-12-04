package org.eclipse.dltk.python.launching;

import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.python.internal.debug.PythonDebugConstants;
import org.eclipse.dltk.python.internal.debug.PythonDebugPlugin;

/**
 * Python debugging engine id based selector
 */
public class PythonDebuggingEngineSelector extends DLTKIdContributionSelector {

	/*
	 * @see org.eclipse.dltk.core.DLTKIdContributionSelector#getContributionId()
	 */
	protected String getSavedContributionId() {
		return PythonDebugPlugin.getDefault().getPluginPreferences().getString(
				PythonDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}
}
