/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *     istvan@benedek-home.de - 103706 [formatter] indent empty lines
 *     Aaron Luchko, aluchko@redhat.com - 105926 [Formatter] Exporting Unnamed profile fails silently
 *******************************************************************************/
package org.eclipse.dltk.ui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
public final class FormatterMessages extends NLS {

	private static final String BUNDLE_NAME= FormatterMessages.class.getName(); 

	private FormatterMessages() {
		// Do not instantiate
	}
	public static String IndentationTabPage_preview_header;

	public static String IndentationTabPage_general_group_option_tab_policy;
	public static String IndentationTabPage_general_group_option_tab_policy_SPACE;
	public static String IndentationTabPage_general_group_option_tab_policy_TAB;
	public static String IndentationTabPage_general_group_option_tab_policy_MIXED;
	public static String IndentationTabPage_general_group_option_tab_size;

	public static String IndentationTabPage_general_group_option_indent_size;

	static {
		NLS.initializeMessages(BUNDLE_NAME, FormatterMessages.class);
	}
}
