package org.eclipse.dltk.javascript.internal.console.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.javascript.console.JavaScriptConsoleConstants;
import org.eclipse.dltk.javascript.internal.debug.ui.JavaScriptDebugUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


public class JavaScriptConsolePreferenceInitializer extends AbstractPreferenceInitializer {

	public JavaScriptConsolePreferenceInitializer() {
	}

	public void initializeDefaultPreferences() {
		IPreferenceStore store = JavaScriptDebugUIPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(JavaScriptConsoleConstants.PREF_NEW_PROMPT,
				JavaScriptConsoleConstants.DEFAULT_NEW_PROMPT);
		store.setDefault(JavaScriptConsoleConstants.PREF_CONTINUE_PROMPT,
				JavaScriptConsoleConstants.DEFAULT_CONTINUE_PROMPT);
	}

}
