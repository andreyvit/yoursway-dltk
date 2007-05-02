/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.preferences;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

public class RubyGlobalPreferencesPage extends AbstractConfigurationBlockPreferencePage {

	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
		// TODO Auto-generated method stub
		return new RubyGlobalConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setDescription() {
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Move to messages");
		}
		String description = "General Ruby settings";
		setDescription(description);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(RubyUI.getDefault().getPreferenceStore());		
	}

}
