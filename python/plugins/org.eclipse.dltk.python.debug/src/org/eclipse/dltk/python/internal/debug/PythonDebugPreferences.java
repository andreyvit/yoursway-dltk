/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.debug;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class PythonDebugPreferences {
	private static final String DEBUGGING_ENGINE_PATH = "debugging_engine";

	private static final String DEBUGGING_ENGINE_PATH_DEFAULT = "";

	private static Preferences getNode() {
		String id = PythonDebugPlugin.getDefault().getBundle().getSymbolicName();
		return Platform.getPreferencesService().getRootNode().node(
				InstanceScope.SCOPE).node(id);
	}

	public static void save() {
		try {
			getNode().flush();
		} catch (BackingStoreException e) {
			// TODO: add logging
		}
	}

	public static String getDebuggingEnginePath() {
		return getNode().get(DEBUGGING_ENGINE_PATH,
				DEBUGGING_ENGINE_PATH_DEFAULT);
	}

	public static void setDebuggingEnginePath(String path) {
		getNode().put(DEBUGGING_ENGINE_PATH, path);
	}
}
