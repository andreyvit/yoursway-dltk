/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.text.folding;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
public final class FoldingMessages extends NLS {

	private static final String BUNDLE_NAME= FoldingMessages.class.getName();

	private FoldingMessages() {
		// Do not instantiate
	}

	public static String DefaultFoldingPreferenceBlock_title;
//	public static String DefaultFoldingPreferenceBlock_comments;
//	public static String DefaultFoldingPreferenceBlock_innerTypes;
//	public static String DefaultFoldingPreferenceBlock_methods;
//	public static String DefaultFoldingPreferenceBlock_imports;
//	public static String DefaultFoldingPreferenceBlock_headers;
//	public static String EmptyFoldingPreferenceBlock_emptyCaption;

	static {
		NLS.initializeMessages(BUNDLE_NAME, FoldingMessages.class);
	}
}
