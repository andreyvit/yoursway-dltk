package org.eclipse.dltk.tcl.activestatedebugger.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerConstants;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerPlugin;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 */
public class TclActiveStateDebuggerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	// ~ Methods

	public void initializeDefaultPreferences() {
		IPreferenceStore store = TclActiveStateDebuggerPlugin.getDefault()
				.getPreferenceStore();

		TclActiveStateDebuggerConstants.initalizeDefaults(store);
	}

}
