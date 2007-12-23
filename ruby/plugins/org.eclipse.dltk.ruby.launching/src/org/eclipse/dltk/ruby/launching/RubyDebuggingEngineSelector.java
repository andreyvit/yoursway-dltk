package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;

/**
 * Ruby debugging engine id based selector
 */
public class RubyDebuggingEngineSelector extends DLTKIdContributionSelector {

	/*
	 * @see org.eclipse.dltk.core.DLTKIdContributionSelector#getSavedContributionId(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected String getSavedContributionId(PreferencesLookupDelegate delegate) {
		return delegate.getString(RubyDebugPlugin.PLUGIN_ID,
				RubyDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}
}
