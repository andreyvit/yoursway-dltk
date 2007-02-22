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
package org.eclipse.dltk.internal.ui.text.hover;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
final class DLTKHoverMessages extends NLS {

	private static final String BUNDLE_NAME= DLTKHoverMessages.class.getName();

	private DLTKHoverMessages() {
		// Do not instantiate
	}

	public static String ScriptdocHover_noAttachedInformation;
	public static String ScriptTextHover_createTextHover;
	public static String ScriptTextHover_makeStickyHint;
	public static String NoBreakpointAnnotation_addBreakpoint;
	public static String NLSStringHover_NLSStringHover_missingKeyWarning;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DLTKHoverMessages.class);
	}
}
