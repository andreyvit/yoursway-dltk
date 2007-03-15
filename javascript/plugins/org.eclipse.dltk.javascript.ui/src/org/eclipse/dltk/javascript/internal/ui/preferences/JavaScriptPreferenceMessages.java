package org.eclipse.dltk.javascript.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class JavaScriptPreferenceMessages extends NLS{

	private static final String BUNDLE_NAME= "org.eclipse.dltk.javascript.internal.ui.preferences.JavaScriptPreferenceMessages";//$NON-NLS-1$
	
	public static String JavascriptEditorPreferencePage_general;
	public static String JavaScriptGlobalPreferencePage_description;
	public static String JavascriptSmartTypingConfigurationBlock_smartPaste;
	public static String JavascriptSmartTypingConfigurationBlock_typing_smartTab;
	public static String JavascriptSmartTypingConfigurationBlock_closeStrings;
	public static String JavascriptSmartTypingConfigurationBlock_closeBrackets;
	public static String JavascriptSmartTypingConfigurationBlock_typing_tabTitle;

	static {
		NLS.initializeMessages(BUNDLE_NAME, JavaScriptPreferenceMessages.class);
	}
}
