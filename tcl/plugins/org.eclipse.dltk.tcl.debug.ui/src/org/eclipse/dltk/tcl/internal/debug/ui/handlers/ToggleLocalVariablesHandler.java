package org.eclipse.dltk.tcl.internal.debug.ui.handlers;

import org.eclipse.dltk.debug.ui.handlers.AbstractToggleLocalVariableHandler;
import org.eclipse.dltk.tcl.internal.debug.TclDebugConstants;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPlugin;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Toggles the display of tcl local variables in the debug 'Variables' view
 */
public class ToggleLocalVariablesHandler extends
		AbstractToggleLocalVariableHandler {

	/*
	 * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getModelId()
	 */
	protected String getModelId() {
		return TclDebugConstants.DEBUG_MODEL_ID;
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getPreferenceStore()
	 */
	protected IPreferenceStore getPreferenceStore() {
		return new PreferencesAdapter(TclDebugPlugin.getDefault()
				.getPluginPreferences());
	}
}
