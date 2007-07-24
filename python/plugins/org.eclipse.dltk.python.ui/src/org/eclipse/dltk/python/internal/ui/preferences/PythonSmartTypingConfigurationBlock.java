/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.python.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.python.ui.PythonPreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Configures Editor typing preferences.
 * 
 */
class PythonSmartTypingConfigurationBlock extends AbstractConfigurationBlock {

	public PythonSmartTypingConfigurationBlock(OverlayPreferenceStore store) {
		super(store);

		store.addKeys(createOverlayStoreKeys());
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {

		ArrayList overlayKeys = new ArrayList();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PythonPreferenceConstants.EDITOR_CLOSE_STRINGS));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PythonPreferenceConstants.EDITOR_CLOSE_BRACKETS));		
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PythonPreferenceConstants.EDITOR_SMART_TAB));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PythonPreferenceConstants.EDITOR_SMART_PASTE));

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;

	}

	/**
	 * Creates page for mark occurrences preferences.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the control for the preference page
	 */
	public Control createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);// parent=scrolled
		GridLayout layout = new GridLayout();
		control.setLayout(layout);

		Composite composite;

		composite = createSubsection(
				control,
				null,
				PreferencesMessages.SmartTypingConfigurationBlock_autoclose_title);
		addAutoclosingSection(composite);

		composite = createSubsection(control, null,
				PreferencesMessages.SmartTypingConfigurationBlock_tabs_title);
		addTabSection(composite);

		composite= createSubsection(control, null,
		PreferencesMessages.SmartTypingConfigurationBlock_pasting_title);
		 addPasteSection(composite);

		return control;
	}

	private void addPasteSection(Composite composite) {
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		String label;
		label = PythonPreferencesMessages.PythonSmartTypingConfigurationBlock_smartPaste;
		addCheckBox(composite, label,
				PythonPreferenceConstants.EDITOR_SMART_PASTE, 0);

	}

	private void addTabSection(Composite composite) {
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		String label;
		label = PythonPreferencesMessages.PythonSmartTypingConfigurationBlock_typing_smartTab;
		addCheckBox(composite, label, PythonPreferenceConstants.EDITOR_SMART_TAB,
				0);

		// createMessage(composite);
	}

	private void addAutoclosingSection(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		String label;

		label = PythonPreferencesMessages.PythonSmartTypingConfigurationBlock_closeStrings;
		addCheckBox(composite, label,
				PythonPreferenceConstants.EDITOR_CLOSE_STRINGS, 0);

		label = PythonPreferencesMessages.PythonSmartTypingConfigurationBlock_closeBrackets;
		addCheckBox(composite, label,
				PythonPreferenceConstants.EDITOR_CLOSE_BRACKETS, 0);
		
	}
}
