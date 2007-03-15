/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.editor;

import org.eclipse.osgi.util.NLS;

public final class ActionMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.javascript.internal.ui.editor.ActionMessages";//$NON-NLS-1$

	private ActionMessages() {
		// Do not instantiate
	}

	public static String MemberFilterActionGroup_hide_variables_label;
	public static String MemberFilterActionGroup_hide_variables_tooltip;
	public static String MemberFilterActionGroup_hide_variables_description;
	public static String MemberFilterActionGroup_hide_functions_label;
	public static String MemberFilterActionGroup_hide_functions_tooltip;
	public static String MemberFilterActionGroup_hide_functions_description;
	public static String MemberFilterActionGroup_hide_classes_label;
	public static String MemberFilterActionGroup_hide_classes_tooltip;
	public static String MemberFilterActionGroup_hide_classes_description;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ActionMessages.class);
	}

}
