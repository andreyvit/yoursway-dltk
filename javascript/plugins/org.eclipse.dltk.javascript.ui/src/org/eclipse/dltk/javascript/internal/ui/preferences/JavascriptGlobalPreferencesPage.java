/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.preferences;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

public class JavascriptGlobalPreferencesPage extends AbstractConfigurationBlockPreferencePage {

	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
		// TODO Auto-generated method stub
		return new JavascriptGlobalConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setDescription() {
		String description = JavaScriptPreferenceMessages.JavaScriptGlobalPreferencePage_description;
		setDescription(description);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(JavaScriptUI.getDefault().getPreferenceStore());		
	}

}
