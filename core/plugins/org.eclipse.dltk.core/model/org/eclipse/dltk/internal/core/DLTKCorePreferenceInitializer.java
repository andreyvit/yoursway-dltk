/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.dltk.core.DLTKCore;


/**
 * DLTKCore eclipse preferences initializer.
 * Initially done in DLTKCore.initializeDefaultPreferences which was deprecated
 * with new eclipse preferences mechanism.
 */
public class DLTKCorePreferenceInitializer extends AbstractPreferenceInitializer {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		// If modified, also modify the method ModelManager#getDefaultOptionsNoInitialization()
		// Get options names set
		HashSet optionNames = ModelManager.getModelManager().optionNames;
		
		// Compiler settings
		Map defaultOptionsMap = new HashMap(); // compiler defaults		
		

		// DLTKCore settings		
		defaultOptionsMap.put(DLTKCore.CORE_INCOMPLETE_BUILDPATH, DLTKCore.ERROR); 
		defaultOptionsMap.put(DLTKCore.CORE_CIRCULAR_BUILDPATH, DLTKCore.ERROR);  
		defaultOptionsMap.put(DLTKCore.CORE_ENABLE_BUILDPATH_EXCLUSION_PATTERNS, DLTKCore.ENABLED);  

		// encoding setting comes from resource plug-in
		optionNames.add(DLTKCore.CORE_ENCODING);

		// Store default values to default preferences
	 	IEclipsePreferences defaultPreferences = ((IScopeContext) new DefaultScope()).getNode(DLTKCore.PLUGIN_ID);
		for (Iterator iter = defaultOptionsMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			String optionName = (String) entry.getKey();
			defaultPreferences.put(optionName, (String)entry.getValue());
			optionNames.add(optionName);
		}
	}
}
