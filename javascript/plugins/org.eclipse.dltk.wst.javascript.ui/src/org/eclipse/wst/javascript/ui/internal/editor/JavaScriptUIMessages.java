/**********************************************************************
 * Copyright (c) 2005 IBM Corporation and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 * IBM - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.javascript.ui.internal.editor;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * Strings used by Javascript UI
 * 
 * @plannedfor 1.0
 */
public class JavaScriptUIMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.wst.javascript.ui.internal.editor.JSUIPluginResources";//$NON-NLS-1$
	private static ResourceBundle fResourceBundle;

	public static String Source;
	public static String Preview;
	public static String BrowseButtonText;
	public static String BrowseButtonToolTipText;
	public static String PreviewPageDropDownLabel;
	public static String DefaultFileName;
	public static String FilteredFileSelectionDialog_0;
	public static String FilteredFileSelectionDialog_1;
	public static String JSContentOutlinePage_0;
	public static String JSContentOutlinePage_1;
	public static String JSContentOutlinePage_2;
	public static String JSContentOutlinePage_3;
	public static String JSContentOutlinePage_4;
	public static String JSContentOutlinePage_5;
	public static String _UI_WIZARD_NEW_TITLE;
	public static String _UI_WIZARD_NEW_HEADING;
	public static String _UI_WIZARD_NEW_DESCRIPTION;
	public static String _ERROR_FILENAME_MUST_END_JS;
	public static String _WARNING_FOLDER_MUST_BE_INSIDE_WEB_CONTENT;
	public static String ResourceGroup_nameExists;
	public static String Editor_Cut_label; // Resource bundle
	public static String Editor_Cut_tooltip; // Resource bundle
	public static String Editor_Cut_description; // Resource bundle
	public static String Editor_Paste_label; // Resource bundle
	public static String Editor_Paste_tooltip; // Resource bundle
	public static String Editor_Paste_description; // Resource bundle
	public static String Editor_Delete_label; // Resource bundle
	public static String Editor_Delete_tooltip; // Resource bundle
	public static String Editor_Delete_description; // Resource bundle
	public static String ContentAssistProposal_label; // Resource bundle
	public static String ContentAssistProposal_tooltip; // Resource bundle
	public static String ContentAssistProposal_description; // Resource bundle
	public static String An_error_has_occurred_when_ERROR_;
	public static String SourceMenu_label;
	public static String Multiple_errors;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, JavaScriptUIMessages.class);
	}

	public static ResourceBundle getResourceBundle() {
		try {
			if (fResourceBundle == null)
				fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		catch (MissingResourceException x) {
			fResourceBundle = null;
		}
		return fResourceBundle;
	}

	private JavaScriptUIMessages() {
		// cannot create new instance
	}
}