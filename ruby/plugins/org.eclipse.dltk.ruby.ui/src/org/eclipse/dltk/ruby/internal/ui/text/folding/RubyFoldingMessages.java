/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.folding;

import org.eclipse.osgi.util.NLS;

public final class RubyFoldingMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.ruby.internal.ui.text.folding.RubyFoldingMessages";//$NON-NLS-1$

	private RubyFoldingMessages() {
		// Do not instantiate
	}

	
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, RubyFoldingMessages.class);
	}

	public static String DLTKEditorPreferencePage_empty_input;
	public static String RubyFoldingPreferenceBlock_0;
	public static String RubyFoldingPreferenceBlock_10;
	public static String RubyFoldingPreferenceBlock_14;
	public static String RubyFoldingPreferenceBlock_15;
	public static String RubyFoldingPreferenceBlock_16;
	public static String RubyFoldingPreferenceBlock_2;
	public static String RubyFoldingPreferenceBlock_3;
	public static String RubyFoldingPreferenceBlock_4;
	public static String RubyFoldingPreferenceBlock_6;
	public static String RubyFoldingPreferenceBlock_inputIsNotANumber;
	public static String RubyFoldingPreferenceBlock_minimalAmountOfLinesToBeFolded;
	public static String RubyFoldingPreferenceBlock_youMayInputNumbers;

}
