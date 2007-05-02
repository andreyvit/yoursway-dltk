/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.console.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.tcl.console.TclConsoleConstants;
import org.eclipse.dltk.tcl.internal.debug.ui.TclDebugUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


public class TclConsolePreferenceInitializer extends AbstractPreferenceInitializer {

	public TclConsolePreferenceInitializer() {
	}

	public void initializeDefaultPreferences() {
		IPreferenceStore store = TclDebugUIPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(TclConsoleConstants.PREF_NEW_PROMPT,
				TclConsoleConstants.DEFAULT_NEW_PROMPT);
		store.setDefault(TclConsoleConstants.PREF_CONTINUE_PROMPT,
				TclConsoleConstants.DEFAULT_CONTINUE_PROMPT);
	}

}
