/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.core.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.wst.javascript.core.internal.JavaScriptCorePlugin;

/**
 * Sets default values for JavaScript Core preferences
 */
public class JavaScriptCorePreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IEclipsePreferences node = new DefaultScope().getNode(JavaScriptCorePlugin.getDefault().getBundle().getSymbolicName());

		// this could be made smarter by actually looking up the content
		// type's valid extensions
		node.put(JavaScriptCorePreferenceNames.DEFAULT_EXTENSION, "js"); //$NON-NLS-1$
	}

}
