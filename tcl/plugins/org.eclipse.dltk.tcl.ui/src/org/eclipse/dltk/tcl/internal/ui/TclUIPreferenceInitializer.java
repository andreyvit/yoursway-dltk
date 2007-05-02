/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.editors.text.EditorsUI;


public class TclUIPreferenceInitializer extends AbstractPreferenceInitializer {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = TclUI.getDefault().getPreferenceStore();
		
		EditorsUI.useAnnotationsPreferencePage(store);
		EditorsUI.useQuickDiffPreferencePage(store);
		TclPreferenceConstants.initializeDefaultValues(store);
	}	
	
}
