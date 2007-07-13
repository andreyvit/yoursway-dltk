package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;

public class NoDebuggingEngineStatusHandler extends
		AbstractOpenPreferencePageStatusHandler {
	
	protected String getTitle() {
		return "Debugging engine not configured";
	}

	protected String getMessage() {
		return "Debugging engine not configured";
	}

	protected String getPreferencePageId(IDLTKUILanguageToolkit toolkit) {
		return toolkit.getDebugPreferencePage();
	}
}
