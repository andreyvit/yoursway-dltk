/**********************************************************************
 * Copyright (c) 2005 2006 IBM Corporation and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 * IBM - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;

import org.eclipse.osgi.util.NLS;

/**
 * Strings used by Javascript Common UI
 * 
 * @plannedfor 1.0
 */
public class JSCommonUIMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.wst.javascript.ui.internal.common.JSCommonUIPluginResources";//$NON-NLS-1$

	public static String Default_Code_UI_;
	public static String Keywords_UI_;
	public static String Literal_Strings_UI_;
	public static String Comments_UI_;
	public static String Unfinished_Strings_and_Comments_UI_;
	public static String sample_javascript_UI_;
	public static String Convert_to_a_JavaDoc_comment;
	public static String internal_error_1;
	public static String internal_error_2;
	public static String javadocinfo_author;
	public static String javadocinfo_param;
	public static String javadocinfo_return;
	public static String javadocinfo_exception;
	public static String javadocinfo_throws;
	public static String javadocinfo_see;
	public static String javadocinfo_since;
	public static String javadocinfo_version;
	public static String javadocinfo_deprecated;
	public static String End_with;
	public static String Creating_files;
	public static String JavaScriptFilesPreferencePage_ExtensionLabel;
	public static String JavaScriptFilesPreferencePage_ExtensionError;
	public static String Creating_or_saving_files;
	public static String End_of_line_code_desc;
	public static String End_of_line_code;
	public static String EOL_Windows;
	public static String EOL_Unix;
	public static String EOL_Mac;
	public static String EOL_NoTranslation;
	public static String Content_assist_UI_;
	public static String Automatically_make_suggest_UI_;
	public static String Prompt_when_these_characte_UI_;
	public static String Formatting_UI_;
	public static String Indent_using_tabs;
	public static String Indent_using_spaces;
	public static String Indentation_size;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, JSCommonUIMessages.class);
	}
	
	private JSCommonUIMessages() {
		// cannot create new instance
	}
}