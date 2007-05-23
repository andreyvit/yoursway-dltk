/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.debug.core.DebugPreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

public class ScriptDebugConfigurationBlock extends AbstractConfigurationBlock {

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {

		ArrayList overlayKeys = new ArrayList();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS));

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;

	}

	public ScriptDebugConfigurationBlock(OverlayPreferenceStore store,
			PreferencePage mainPreferencePage) {
		super(store, mainPreferencePage);

		store.addKeys(createOverlayStoreKeys());
	}

	private Control createSettingsGroup(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setText("General options");
		group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		GridLayout layout = new GridLayout(2, false);
		group.setLayout(layout);

		String label = "Break on first line";
		addCheckBox(group, label,
				DebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE, 0);

		label = "Enable DBGP logging";
		addCheckBox(group, label,
				DebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING, 0);

		return group;
	}

	private Control createVariablesGroup(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		group.setText("Variables");

		GridLayout layout = new GridLayout(2, false);
		group.setLayout(layout);

		String label = "Show Local Variables";
		addCheckBox(group, label,
				DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL, 0);

		label = "Show Global Variables";
		addCheckBox(group, label,
				DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL, 0);

		label = "Show Class Variables";
		addCheckBox(group, label,
				DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS, 0);

		return group;
	}

	public Control createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite control = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout(1, false);

		control.setLayout(layout);

		createSettingsGroup(control);
		createVariablesGroup(control);

		return control;
	}
}