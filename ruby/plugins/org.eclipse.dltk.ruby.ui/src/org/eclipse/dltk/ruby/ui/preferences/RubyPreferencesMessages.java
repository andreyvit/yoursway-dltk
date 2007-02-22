/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     John Kaplan, johnkaplantech@gmail.com - 108071 [code templates] template for body of newly created class
 *******************************************************************************/
package org.eclipse.dltk.ruby.ui.preferences;

import org.eclipse.osgi.util.NLS;

public final class RubyPreferencesMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.ruby.ui.preferences.RubyPreferencesMessages";//$NON-NLS-1$
	
	public static String RubyEditorPreferencePage_rubyDoc;
	public static String RubyEditorPreferencePage_rubyDocTopic;

	public static String RubyVariablesCategory;
	
	public static String RubyClassVariable;
	public static String RubyInstanceVariable;
	public static String RubyGlobalVariable;
	public static String RubyPseudoVariable;
	public static String RubySymbols;
		
	private RubyPreferencesMessages() {
		// Do not instantiate
	}	
	static {
		NLS.initializeMessages(BUNDLE_NAME, RubyPreferencesMessages.class);
	}	
}