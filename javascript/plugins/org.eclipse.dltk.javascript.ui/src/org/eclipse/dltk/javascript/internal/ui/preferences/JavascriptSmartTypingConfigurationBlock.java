/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.javascript.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.javascript.ui.JavascriptPreferenceConstants;
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
*
 */
class JavascriptSmartTypingConfigurationBlock extends AbstractConfigurationBlock {

	public JavascriptSmartTypingConfigurationBlock(OverlayPreferenceStore store) {
		super(store);

		store.addKeys(createOverlayStoreKeys());
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {

		ArrayList overlayKeys = new ArrayList();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				JavascriptPreferenceConstants.EDITOR_CLOSE_STRINGS));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				JavascriptPreferenceConstants.EDITOR_CLOSE_BRACKETS));		
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				JavascriptPreferenceConstants.EDITOR_SMART_TAB));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				JavascriptPreferenceConstants.EDITOR_SMART_PASTE));

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
		label = JavaScriptPreferenceMessages.JavascriptSmartTypingConfigurationBlock_smartPaste;
		addCheckBox(composite, label,
				JavascriptPreferenceConstants.EDITOR_SMART_PASTE, 0);

	}

	private void addTabSection(Composite composite) {
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		String label;
		label = JavaScriptPreferenceMessages.JavascriptSmartTypingConfigurationBlock_typing_smartTab;
		addCheckBox(composite, label, JavascriptPreferenceConstants.EDITOR_SMART_TAB,
				0);

		// createMessage(composite);
	}

	private void addAutoclosingSection(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		String label;

		label = JavaScriptPreferenceMessages.JavascriptSmartTypingConfigurationBlock_closeStrings;
		addCheckBox(composite, label,
				JavascriptPreferenceConstants.EDITOR_CLOSE_STRINGS, 0);

		label = JavaScriptPreferenceMessages.JavascriptSmartTypingConfigurationBlock_closeBrackets;
		addCheckBox(composite, label,
				JavascriptPreferenceConstants.EDITOR_CLOSE_BRACKETS, 0);
		
	}

}
