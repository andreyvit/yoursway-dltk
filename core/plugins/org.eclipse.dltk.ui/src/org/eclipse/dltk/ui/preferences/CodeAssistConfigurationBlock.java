/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class CodeAssistConfigurationBlock extends AbstractConfigurationBlock {
	public CodeAssistConfigurationBlock(PreferencePage mainPreferencePage,
			OverlayPreferenceStore store) {
		super(store, mainPreferencePage);
		getPreferenceStore().addKeys(createOverlayStoreKeys());
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {
		ArrayList overlayKeys = new ArrayList();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY));
		
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.CODEASSIST_AUTOINSERT));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.CODEASSIST_AUTOINSERT));


		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;
	}

	/**
	 * Creates page for appearance preferences.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the control for the preference page
	 */
	public Control createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout());

		createTabsGroup(control);

		return control;
	}

	private void createTabsGroup(Composite composite) {
		Composite generalGroup = createSubsection(composite, null,
				PreferencesMessages.CodeAssistConfigurationBlock_insertionSection_title);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		generalGroup.setLayout(layout);

		addCheckBox(
				generalGroup,
				PreferencesMessages.DLTKEditorPreferencePage_insertSingleProposalsAutomatically,
				PreferenceConstants.CODEASSIST_AUTOINSERT, 2);
		
	}

	private Control createSettingsGroup(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		String label;

		label = PreferencesMessages.EditorPreferencePage_smartHomeEnd;
		addCheckBox(composite, label,
				PreferenceConstants.EDITOR_SMART_HOME_END, 0);

		label = PreferencesMessages.EditorPreferencePage_subWordNavigation;
		addCheckBox(composite, label,
				PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION, 0);

		label = PreferencesMessages.EditorPreferencePage_smartIndent;
		addCheckBox(composite, label, PreferenceConstants.EDITOR_SMART_INDENT,
				0);

		return composite;
	}

	public void initialize() {

		super.initialize();

	}

	public void performDefaults() {
		super.performDefaults();

	}
}
