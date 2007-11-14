/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.text.hover;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
public final class ScriptHoverMessages extends NLS {

	private static final String BUNDLE_NAME= ScriptHoverMessages.class.getName();

	private ScriptHoverMessages() {
		// Do not instantiate
	}

	public static String ScriptdocHover_noAttachedInformation;
	public static String ScriptTextHover_makeStickyHint;
	public static String NoBreakpointAnnotation_addBreakpoint;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ScriptHoverMessages.class);
	}
}
