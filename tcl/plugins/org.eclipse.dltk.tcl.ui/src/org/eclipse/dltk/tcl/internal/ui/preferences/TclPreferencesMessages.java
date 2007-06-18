package org.eclipse.dltk.tcl.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class TclPreferencesMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.tcl.internal.ui.preferences.TclPreferencesMessages";//$NON-NLS-1$	
//	public static String SmartTypingConfigurationBlock_pasting_title;

	private TclPreferencesMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME,org.eclipse.dltk.tcl.internal.ui.preferences.TclPreferencesMessages.class);
	}
	public static String TCLGlobalPreferencePage_description;
	public static String TCLEditorPreferencePage_general;
	public static String TCLSmartTypingConfigurationBlock_autoclose_title;
	public static String TCLSmartTypingConfigurationBlock_tabs_title;
	public static String TCLSmartTypingConfigurationBlock_smartPaste_simple;
	public static String TCLSmartTypingConfigurationBlock_smartPaste_full;
	public static String TCLSmartTypingConfigurationBlock_typing_smartTab;
	public static String TCLSmartTypingConfigurationBlock_closeBrackets;
	public static String TCLSmartTypingConfigurationBlock_closeBraces;
	public static String TCLSmartTypingConfigurationBlock_closeStrings;
	public static String TCLSmartTypingConfigurationBlock_typing_tabTitle;
}
