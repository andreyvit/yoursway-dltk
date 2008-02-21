/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class TclCheckerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public TclCheckerPreferenceInitializer() {
	}

	public void initializeDefaultPreferences() {
		IPreferenceStore store = TclCheckerPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(TclCheckerConstants.PREF_MODE,
				TclCheckerConstants.MODE_DEFAULT);

		List problems = TclCheckerProblemDescription.getProblemIdentifiers();
		Iterator it = problems.iterator();
		while (it.hasNext()) {
			store.setDefault((String) it.next(), false);
		}
		store.setDefault("warnUndefinedUpvar", true);
		store.setDefault("warnUndefinedVar", true);
		store.setDefault("warnUndefFunc", true);
		store.setDefault("warnUndefProc", true);
	}
}
