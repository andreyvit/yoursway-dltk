/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
public final class DLTKEditorMessages extends NLS {

	private static final String BUNDLE_FOR_CONSTRUCTED_KEYS= "org.eclipse.dltk.internal.ui.editor.ConstructedDLTKEditorMessages";//$NON-NLS-1$
	private static ResourceBundle fgBundleForConstructedKeys= ResourceBundle.getBundle(BUNDLE_FOR_CONSTRUCTED_KEYS);

	/**
	 * Returns the message bundle which contains constructed keys.
	 *
	 *
	 * @return the message bundle
	 */
	public static ResourceBundle getBundleForConstructedKeys() {
		return fgBundleForConstructedKeys;
	}

	private static final String BUNDLE_NAME= DLTKEditorMessages.class.getName();


	private DLTKEditorMessages() {
		// Do not instantiate
	}

	public static String SourceModuleEditor_error_saving_message1;
	public static String SourceModuleEditor_error_saving_title1;
	public static String ScriptOutlinePage_Sort_label;
	public static String ScriptOutlinePage_Sort_tooltip;
	public static String ScriptOutlinePage_Sort_description;
	public static String ScriptOutlinePage_error_NoTopLevelType;
	public static String ToggleComment_error_title;
	public static String ToggleComment_error_message;
	public static String ShowScriptDoc_label;
	public static String Editor_FoldingMenu_name;
	public static String GotoMatchingBracket_label;
	public static String EditorUtility_concatModifierStrings;
	public static String SemanticHighlighting_job;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DLTKEditorMessages.class);
	}
}
