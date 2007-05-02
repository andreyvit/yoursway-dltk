/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.preferences;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

public class DebuggingPreferences extends
		AbstractConfigurationBlockPreferencePage {

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new DebuggingConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription("Common debugger parameters");
	}

	protected void setPreferenceStore() {
		setPreferenceStore(DLTKUIPlugin.getDefault().getPreferenceStore());
	}
}
