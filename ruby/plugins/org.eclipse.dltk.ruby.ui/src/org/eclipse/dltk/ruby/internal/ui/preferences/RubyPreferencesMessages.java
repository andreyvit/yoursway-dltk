/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *     John Kaplan, johnkaplantech@gmail.com - 108071 [code templates] template for body of newly created class
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public final class RubyPreferencesMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.ui.preferences.PythonPreferencesMessages";//$NON-NLS-1$

	private RubyPreferencesMessages() {
		// Do not instantiate
	}
	
	
	public static String DLTKEditorPreferencePage_general;

	static {
		NLS.initializeMessages(BUNDLE_NAME, RubyPreferencesMessages.class);
	}
}
