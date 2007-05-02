/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.tcl.internal.ui.preferences;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.DLTKEditorHoverConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;




/**
 * Tcl Editor Hovers preferences
 * <p>
 * Note: Must be public since it is referenced from plugin.xml
 * </p>
 * 
	 *
 */
public class TclEditorHoverPreferencePage extends AbstractConfigurationBlockPreferencePage {

	/*
	 * @see org.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#getHelpId()
	 */
	protected String getHelpId() {
		//return ITclHelpContextIds.TCL_EDITOR_PREFERENCE_PAGE;
		
		return null;
	}

	/*
	 * @see org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setDescription()
	 */
	protected void setDescription() {
		String description= PreferencesMessages.DLTKEditorPreferencePage_hoverTab_title; 
		setDescription(description);
	}
	
	/*
	 * @see org.org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setPreferenceStore()
	 */
	protected void setPreferenceStore() {
		setPreferenceStore(TclUI.getDefault().getPreferenceStore());
	}
	
	
	protected Label createDescriptionLabel(Composite parent) {
		return null; // no description for new look.
	}

	/*
	 * @see org.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#createConfigurationBlock(org.eclipse.ui.internal.editors.text.OverlayPreferenceStore)
	 */
	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
		return new DLTKEditorHoverConfigurationBlock(this, overlayPreferenceStore);
	}
}
