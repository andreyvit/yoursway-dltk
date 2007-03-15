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

/**
 * Bundle of most images used by the JS Common plug-in.
 */
public class JSCommonUIPluginImages {
	private static final String IMG_OBJ = "icons/full/obj16/"; //$NON-NLS-1$
	private static final String DOT_GIF = ".gif"; //$NON-NLS-1$

	public static final String IMG_OBJ_HTML_TAG = IMG_OBJ + "html_tag_obj" + DOT_GIF; //$NON-NLS-1$
	public static final String IMG_OBJ_JDOC_TAG = IMG_OBJ + "jdoc_tag_obj" + DOT_GIF; //$NON-NLS-1$
	public static final String IMG_OBJ_GENERIC_TAG = IMG_OBJ + "tag-generic" + DOT_GIF; //$NON-NLS-1$
	public static final String IMG_OBJ_METHPUB = IMG_OBJ + "methpub_obj" + DOT_GIF; //$NON-NLS-1$

	/**
	 * Helper method used by CompletionStringNode to build the javascript
	 * content assist icons' filenames (no_no, unknown_yes, etc)
	 * 
	 * @param name icon to retrieve
	 * @return String complete filename of icon
	 * @deprecated deprecated in WTP 2.0 to be removed in next release
	 */
	public static String buildObjName(String name) {
		return IMG_OBJ + name + DOT_GIF;
	}
}
