/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.python.internal.ui.preferences;


import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;



/**
 * The page for setting the editor options.
 */
public final class PythonSmartTypingPreferencePage extends AbstractConfigurationBlockPreferencePage {
	

	/*
	 * @see org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setDescription()
	 */
	protected void setDescription() {
		String description= org.eclipse.dltk.python.internal.ui.preferences.PythonPreferencesMessages.PythonSmartTypingConfigurationBlock_typing_tabTitle; 
		setDescription(description);
	}
	
	/*
	 * @see org.org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setPreferenceStore()
	 */
	protected void setPreferenceStore() {		
		setPreferenceStore(PythonUI.getDefault().getPreferenceStore());
	}
	
	
	protected Label createDescriptionLabel(Composite parent) {
		return null; // no description for new look.
	}
	
	/*
	 * @see org.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#createConfigurationBlock(org.eclipse.ui.internal.editors.text.OverlayPreferenceStore)
	 */
	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
		return new PythonSmartTypingConfigurationBlock(overlayPreferenceStore);
	}

	protected String getHelpId() {
		// TODO Auto-generated method stub
		return "";
	}

	
}
