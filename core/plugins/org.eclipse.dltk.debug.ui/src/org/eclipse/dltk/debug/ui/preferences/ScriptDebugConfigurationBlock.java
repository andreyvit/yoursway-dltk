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
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class ScriptDebugConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {

	private PreferencePage preferencePage;

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

		this.preferencePage = mainPreferencePage;
	}

	private Control createSettingsGroup(Composite parent) {
		final Group group = SWTFactory.createGroup(parent, "General options",
				1, 1, GridData.FILL_HORIZONTAL);

		// Break on first line
		Button b = SWTFactory.createCheckButton(group,
				ScriptDebugPreferencesMessages.BreakOnFirstLineLabel, null,
				false, 1);
		bindControl(b, DebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);

		// Enable dbpg logging
		b = SWTFactory.createCheckButton(group,
				ScriptDebugPreferencesMessages.EnableDbgpLoggingLabel, null,
				false, 1);
		bindControl(b, DebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);

		return group;
	}

	private Control createVariablesGroup(Composite parent) {
		final Group group = SWTFactory.createGroup(parent, "Variables", 1, 1,
				GridData.FILL_HORIZONTAL);

		Button b = SWTFactory.createCheckButton(group, "Show Local Variables",
				null, false, 1);
		bindControl(b, DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL);

		b = SWTFactory.createCheckButton(group, "Show Global Variables", null,
				false, 1);
		bindControl(b, DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL);

		b = SWTFactory.createCheckButton(group, "Show Class Variables", null,
				false, 1);
		bindControl(b, DebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS);

		return group;
	}

	private void createScriptLanguagesLinks(Composite parent) {
		IDLTKUILanguageToolkit[] toolkits = DLTKUILanguageManager
				.getLanguageToolkits();

		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL_HORIZONTAL);

		for (int i = 0; i < toolkits.length; ++i) {
			final IDLTKUILanguageToolkit toolkit = toolkits[i];

			final String pageId = toolkit.getDebugPreferencePage();

			if (pageId != null) {
				final String languageName = toolkit.getCoreToolkit()
						.getLanguageName();

				// Link
				PreferenceLinkArea area = new PreferenceLinkArea(composite,
						SWT.NONE, pageId, "See <a>''{0}''</a> for "
								+ languageName + " debug settings",
						(IWorkbenchPreferenceContainer) preferencePage
								.getContainer(), null);

				area.getControl().setLayoutData(
						new GridData(SWT.FILL, SWT.FILL, false, false));
			}
		}
	}

	public Control createControl(Composite parent) {
		final Composite control = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL_HORIZONTAL);

		createSettingsGroup(control);
		createVariablesGroup(control);
		createScriptLanguagesLinks(control);

		return control;
	}
}