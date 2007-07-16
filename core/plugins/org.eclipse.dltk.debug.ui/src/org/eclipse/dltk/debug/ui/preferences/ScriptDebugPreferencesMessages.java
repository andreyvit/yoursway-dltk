package org.eclipse.dltk.debug.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class ScriptDebugPreferencesMessages {
	private ScriptDebugPreferencesMessages() {

	}

	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.preferences.ScriptDebugPreferencesMessages"; //$NON-NLS-1$

	static {
		NLS.initializeMessages(BUNDLE_NAME,
				ScriptDebugPreferencesMessages.class);
	}

	public static String GeneralPreferencesDescription;

	public static String LinkToGeneralPreferenses;

	public static String LingToDebuggingEnginePreferences;

	public static String DebuggingEngine;

	public static String NameLabel;

	public static String DescriptionLabel;

	public static String PathLabel;

	public static String BrowseEnginePath;

	public static String ExternalEngineGroup;
	
	//
	public static String BreakOnFirstLineLabel;
	
	public static String EnableDbgpLoggingLabel;
	
	
}
