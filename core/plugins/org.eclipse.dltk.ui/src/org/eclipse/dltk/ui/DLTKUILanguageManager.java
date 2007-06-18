/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.core.ClassBasedDLTKExtensionManager;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;

public class DLTKUILanguageManager extends ClassBasedDLTKExtensionManager {
	private static DLTKUILanguageManager instance = new DLTKUILanguageManager();
	private final static String LANGUAGE_EXTPOINT = DLTKUIPlugin.PLUGIN_ID
			+ ".language";

	private DLTKUILanguageManager() {
		super(LANGUAGE_EXTPOINT);
	}

	public static IDLTKUILanguageToolkit getLanguageToolkit(String natureId) {
		return (IDLTKUILanguageToolkit) instance.getObject(natureId);
	}

	public static IDLTKUILanguageToolkit getLanguageToolkit(
			IModelElement element) {
		return (IDLTKUILanguageToolkit) instance.getObject(element);
	}

	public static ScriptUILabelProvider createLabelProvider(
			IModelElement element) {
		IDLTKUILanguageToolkit languageToolkit = getLanguageToolkit(element);
		if (languageToolkit != null) {
			ScriptUILabelProvider provider = languageToolkit
					.createScripUILabelProvider();
			if (provider != null) {
				return provider;
			}
		}
		return new ScriptUILabelProvider();
	}

	public static ScriptUILabelProvider createLabelProvider(String nature) {
		IDLTKUILanguageToolkit languageToolkit = getLanguageToolkit(nature);
		if (languageToolkit != null) {
			ScriptUILabelProvider provider = languageToolkit
					.createScripUILabelProvider();
			if (provider != null) {
				return provider;
			}
		}
		return new ScriptUILabelProvider();
	}

	public static IDLTKUILanguageToolkit getLanguageToolkitLower(String natureId) {
		return (IDLTKUILanguageToolkit) instance.getObjectLower(natureId);
	}
}
