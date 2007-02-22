package org.eclipse.dltk.tcl.internal.console.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.tcl.console.TclConsoleConstants;
import org.eclipse.dltk.tcl.internal.debug.ui.TclDebugUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


public class TclConsolePreferenceInitializer extends AbstractPreferenceInitializer {

	public TclConsolePreferenceInitializer() {
	}

	public void initializeDefaultPreferences() {
		IPreferenceStore store = TclDebugUIPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(TclConsoleConstants.PREF_NEW_PROMPT,
				TclConsoleConstants.DEFAULT_NEW_PROMPT);
		store.setDefault(TclConsoleConstants.PREF_CONTINUE_PROMPT,
				TclConsoleConstants.DEFAULT_CONTINUE_PROMPT);
	}

}
