package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.launching.debug.IdBasedDebuggingEngineSelector;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;

public class RubyDebuggingEngineSelector extends IdBasedDebuggingEngineSelector {
	protected String getDebuggingEngineId() {
		return  RubyDebugPlugin.getDefault().getPluginPreferences().getString(
				RubyDebugConstants.DEBUGGING_ENGINE_ID_KEY);
	}
}
