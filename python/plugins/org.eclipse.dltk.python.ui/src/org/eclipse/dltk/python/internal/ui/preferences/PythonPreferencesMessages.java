package org.eclipse.dltk.python.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class PythonPreferencesMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.python.internal.ui.preferences.PythonPreferencesMessages";//$NON-NLS-1$	

	private PythonPreferencesMessages() {
		// Do not instantiate
	}
	static {
		NLS.initializeMessages(BUNDLE_NAME, org.eclipse.dltk.python.internal.ui.preferences.PythonPreferencesMessages.class);
	}

	public static String PythonGlobalPreferencePage_description;

	public static String PythonEditorPreferencePage_general;

	public static String PythonSmartTypingConfigurationBlock_smartPaste;
	public static String PythonSmartTypingConfigurationBlock_typing_smartTab;
	public static String PythonSmartTypingConfigurationBlock_closeBrackets;
	public static String PythonSmartTypingConfigurationBlock_closeStrings;
	public static String PythonSmartTypingConfigurationBlock_typing_tabTitle;
}
