package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;

/**
 * Ruby debugging engine id based selector
 */
public class RubyDebuggingEngineSelector extends DLTKIdContributionSelector {

    /*
     * @see org.eclipse.dltk.core.DLTKIdContributionSelector#getContributionId()
     */
	protected String getSavedContributionId() {
		return  RubyDebugPlugin.getDefault().getPluginPreferences().getString(
				RubyDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}
}
