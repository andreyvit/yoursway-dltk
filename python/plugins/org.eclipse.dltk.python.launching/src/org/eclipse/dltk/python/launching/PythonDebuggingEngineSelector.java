package org.eclipse.dltk.python.launching;

import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.python.internal.debug.PythonDebugConstants;
import org.eclipse.dltk.python.internal.debug.PythonDebugPlugin;

/**
 * Python debugging engine id based selector
 */
public class PythonDebuggingEngineSelector extends DLTKIdContributionSelector {

	/*
	 * @see org.eclipse.dltk.core.DLTKIdContributionSelector#getSavedContributionId(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected String getSavedContributionId(PreferencesLookupDelegate delegate) {
		return delegate.getString(PythonDebugPlugin.PLUGIN_ID,
				PythonDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}
}
