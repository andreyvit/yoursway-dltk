/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
