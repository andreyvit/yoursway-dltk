/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.refactoring;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;


public class RefactoringSavePreferences {

	public static final String PREF_SAVE_ALL_EDITORS= PreferenceConstants.REFACTOR_SAVE_ALL_EDITORS;
	
	public static boolean getSaveAllEditors() {
		IPreferenceStore store= DLTKUIPlugin.getDefault().getPreferenceStore();
		return store.getBoolean(PREF_SAVE_ALL_EDITORS);
	}
	
	public static void setSaveAllEditors(boolean save) {
		IPreferenceStore store= DLTKUIPlugin.getDefault().getPreferenceStore();
		store.setValue(RefactoringSavePreferences.PREF_SAVE_ALL_EDITORS, save);
	}	
}
