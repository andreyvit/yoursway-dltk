/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.activestatedebugger.preferences;

import org.eclipse.dltk.debug.ui.preferences.ExternalDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.ruby.activestatedebugger.RubyActiveStateDebuggerConstants;
import org.eclipse.dltk.ruby.activestatedebugger.RubyActiveStateDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;


public class RubyActiveStateDebuggerPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.ruby.preferences.debug.activestatedebugger";

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore store) {
		return new ExternalDebuggingEngineConfigurationBlock(store, this) {
			protected String getDebuggingEnginePathKey() {
				return RubyActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY;
			}
		};
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(PreferenceMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(RubyActiveStateDebuggerPlugin.getDefault()
				.getPreferenceStore());
	}

	public boolean performOk() {
		super.performOk();
		RubyActiveStateDebuggerPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
