package org.eclipse.dltk.python.launching;

import org.eclipse.dltk.launching.debug.IdBasedDebuggingEngineSelector;
import org.eclipse.dltk.python.internal.debug.PythonDebugConstants;
import org.eclipse.dltk.python.internal.debug.PythonDebugPlugin;

/**
 * Python debugging engine selector
 */
public class PythonDebuggingEngineSelector extends
		IdBasedDebuggingEngineSelector {

	/*
	 * @see org.eclipse.dltk.launching.debug.IdBasedDebuggingEngineSelector#getDebuggingEngineId()
	 */
	protected String getDebuggingEngineId() {
		return PythonDebugPlugin.getDefault().getPluginPreferences()
				.getString(PythonDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}
}
