/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;

import org.eclipse.wst.javascript.core.internal.contenttype.ContentTypeIdForJavaScript;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;


/**
 * Help context ids for the Javascript Source Editor.
 * <p>
 * This interface contains constants only; it is not intended to be implemented.
 * </p>
 * 
 */
public interface IHelpContextIds {
	public static final String PREFIX = JSEditorPlugin.ID + "."; //$NON-NLS-1$

	// JavaScript Source page editor
	public static final String JS_SOURCEVIEW_HELPID = ContentTypeIdForJavaScript.ContentTypeID_JAVASCRIPT +"_source_HelpId"; //$NON-NLS-1$
	
	// JavaScript Files Preference page
	public static final String JS_PREFWEBX_FILES_HELPID = PREFIX + "webx0040"; //$NON-NLS-1$
	// JavaScript Source Preference page
	public static final String JS_PREFWEBX_SOURCE_HELPID = PREFIX + "webx0041"; //$NON-NLS-1$
	// JavaScript Styles Preference page
	public static final String JS_PREFWEBX_STYLES_HELPID = PREFIX + "webx0042"; //$NON-NLS-1$

	// Content Assist action
	public static final String CONTMNU_CONTENTASSIST_HELPID = PREFIX + "js0000"; //$NON-NLS-1$
}
