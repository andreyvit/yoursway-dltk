/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.PreferenceModifyListener;
import org.eclipse.dltk.core.DLTKCore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;


public class DLTKCorePreferenceModifyListener extends PreferenceModifyListener {
	
	static int PREFIX_LENGTH = ModelManager.BP_CONTAINER_PREFERENCES_PREFIX.length();
	Model scriptModel = ModelManager.getModelManager().getModel();

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.PreferenceModifyListener#preApply(org.eclipse.core.runtime.preferences.IEclipsePreferences)
	 */
	public IEclipsePreferences preApply(IEclipsePreferences node) {
		Preferences instance = node.node(InstanceScope.SCOPE);
		cleanScriptCore(instance.node(DLTKCore.PLUGIN_ID));
		return super.preApply(node);
	}
	
	/**
	 * Clean imported preferences from obsolete keys.
	 *
	 * @param preferences DLTKCore preferences.
	 */
	void cleanScriptCore(Preferences preferences) {
		try {
			String[] keys = preferences.keys();
			for (int k = 0, kl= keys.length; k<kl; k++) {
				String key = keys[k];
				if (key.startsWith(ModelManager.BP_CONTAINER_PREFERENCES_PREFIX) && !isScriptProjectAccessible(key)) {
					preferences.remove(key);
				}
			}
		} catch (BackingStoreException e) {
			// do nothing
		}
	}

	/**
	 * Returns whether ascriptproject referenced in property key
	 * is still longer accessible or not.
	 * 
	 * @param propertyName
	 * @return true if a project is referenced in given key and this project
	 * 	is still accessible, false otherwise.
	 */
	boolean isScriptProjectAccessible(String propertyName) {
		int index = propertyName.indexOf('|', PREFIX_LENGTH);
		if (index > 0) {
			final String projectName = propertyName.substring(PREFIX_LENGTH, index).trim();
			DLTKProject project = (DLTKProject) scriptModel.getScriptProject(projectName);
			if (project.getProject().isAccessible()) {
				return true;
			}
		}
		return false;
	}

}
