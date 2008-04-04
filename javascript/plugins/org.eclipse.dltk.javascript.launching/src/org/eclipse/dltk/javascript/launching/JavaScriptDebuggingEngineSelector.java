package org.eclipse.dltk.javascript.launching;

import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugConstants;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugPlugin;

/**
 * JavaScript debugging engine id based selector
 */
public class JavaScriptDebuggingEngineSelector extends
		DLTKIdContributionSelector {
	/*
	 * @see org.eclipse.dltk.core.DLTKIdContributionSelector#getSavedContributionId(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected String getSavedContributionId(PreferencesLookupDelegate delegate) {
		return delegate.getString(JavaScriptDebugPlugin.PLUGIN_ID,
				JavaScriptDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}

}
