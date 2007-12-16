/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.activestatedebugger;

import org.eclipse.jface.preference.IPreferenceStore;

public final class RubyActiveStateDebuggerConstants {

	public static final String DEBUGGING_ENGINE_PATH_KEY = "debugging_engine_path";

	public static void initializeDefaults(IPreferenceStore store) {
		store.setDefault(DEBUGGING_ENGINE_PATH_KEY, "");
	}
	
	private RubyActiveStateDebuggerConstants() {
		// private constructor
	}

}
