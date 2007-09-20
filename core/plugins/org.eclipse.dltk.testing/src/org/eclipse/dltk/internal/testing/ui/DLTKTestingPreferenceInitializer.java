/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sebastian Davids <sdavids@gmx.de> - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.ui;

import java.util.List;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.eclipse.dltk.testing.DLTKTestingPlugin;

/**
 * Default preference value initialization for the
 * <code>org.eclipse.jdt.junit</code> plug-in.
 */
public class DLTKTestingPreferenceInitializer extends AbstractPreferenceInitializer {

	/** {@inheritDoc} */
	public void initializeDefaultPreferences() {
		Preferences prefs= DLTKTestingPlugin.getDefault().getPluginPreferences();
		prefs.setDefault(DLTKTestingPreferencesConstants.DO_FILTER_STACK, true);
		prefs.setDefault(DLTKTestingPreferencesConstants.SHOW_ON_ERROR_ONLY, false);
		prefs.setDefault(DLTKTestingPreferencesConstants.ENABLE_ASSERTIONS, false);

		List defaults= DLTKTestingPreferencesConstants.createDefaultStackFiltersList();
		String[] filters= (String[]) defaults.toArray(new String[defaults.size()]);
		String active= DLTKTestingPreferencesConstants.serializeList(filters);
		prefs.setDefault(DLTKTestingPreferencesConstants.PREF_ACTIVE_FILTERS_LIST, active);
		prefs.setDefault(DLTKTestingPreferencesConstants.PREF_INACTIVE_FILTERS_LIST, ""); //$NON-NLS-1$
		prefs.setDefault(DLTKTestingPreferencesConstants.MAX_TEST_RUNS, 10);
	}
}
