/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.browsing;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
public final class ScriptBrowsingMessages extends NLS {

	private static final String BUNDLE_NAME= ScriptBrowsingMessages.class.getName();

	private ScriptBrowsingMessages() {
		// Do not instantiate
	}

	public static String JavaBrowsingPart_toolTip;
	public static String JavaBrowsingPart_toolTip2;
	public static String LexicalSortingAction_label;
	public static String LexicalSortingAction_tooltip;
	public static String LexicalSortingAction_description;
	public static String PackagesView_flatLayoutAction_label;
	public static String PackagesView_HierarchicalLayoutAction_label;
	public static String PackagesView_LayoutActionGroup_layout_label;
	public static String ScriptBrowsingPart_natureAttributeMustBeSpecifiedAndCorrect;
	public static String StatusBar_concat;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ScriptBrowsingMessages.class);
	}
}