/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.preferences;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.IDLTKDebugUIPreferenceConstants;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.preferences.FieldValidators;
import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class ScriptDebugConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {

	private PreferencePage preferencePage;
	private Preferences fUIPreferences;

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {

		ArrayList overlayKeys = new ArrayList();

		// Connection
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				DLTKDebugPreferenceConstants.PREF_DBGP_PORT));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				DLTKDebugPreferenceConstants.PREF_DBGP_CONNECTION_TIMEOUT));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				DLTKDebugPreferenceConstants.PREF_DBGP_RESPONSE_TIMEOUT));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED));

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
		this.fUIPreferences = DLTKDebugUIPlugin.getDefault()
				.getPluginPreferences();
	}

	private static final int AUTO_SELECT_PORT_INDEX = 0;
	private static final int CUSTOM_PORT_INDEX = 1;

	private Combo portCombo;
	private Text portText;
	private Button fAlertHCRFailed;
	private Button fAlertHCRNotSupported;

	private Control createDbgpGroup(Composite parent) {
		final Group group = SWTFactory.createGroup(parent,
				ScriptDebugPreferencesMessages.CommunicationLabel, 2, 1,
				GridData.FILL_HORIZONTAL);

		// Port
		SWTFactory.createLabel(group, ScriptDebugPreferencesMessages.PortLabel,
				1);

		Composite portCompsite = SWTFactory.createComposite(group, group
				.getFont(), 2, 0, GridData.FILL_HORIZONTAL);

		portCombo = SWTFactory.createCombo(portCompsite, SWT.READ_ONLY
				| SWT.BORDER, 0, new String[] {});

		portCombo.add(ScriptDebugPreferencesMessages.AutoSelectLabel,
				AUTO_SELECT_PORT_INDEX);
		portCombo.add(ScriptDebugPreferencesMessages.CustomLabel,
				CUSTOM_PORT_INDEX);

		portText = SWTFactory.createText(portCompsite, SWT.BORDER, 0, "");
		bindControl(portText, FieldValidators.PORT_VALIDATOR);

		portCombo.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				boolean isCustom = portCombo.getSelectionIndex() == CUSTOM_PORT_INDEX;

				portText.setEnabled(isCustom);

				if (!isCustom) {
					portText.setText("");
				} else {
					portText.setText(portText.getText());
				}
			}
		});

		// Connection timeout
		SWTFactory.createLabel(group,
				ScriptDebugPreferencesMessages.ConnectionTimeoutLabel, 1);
		final Text connectionTimeout = SWTFactory.createText(group, SWT.BORDER,
				1, "");
		bindControl(connectionTimeout,
				DLTKDebugPreferenceConstants.PREF_DBGP_CONNECTION_TIMEOUT,
				FieldValidators.POSITIVE_NUMBER_VALIDATOR);

		// Response timeout
		SWTFactory.createLabel(group,
				ScriptDebugPreferencesMessages.ResponseTimeoutLabel, 1);
		final Text responseTimeout = SWTFactory.createText(group, SWT.BORDER,
				1, "");
		bindControl(responseTimeout,
				DLTKDebugPreferenceConstants.PREF_DBGP_RESPONSE_TIMEOUT,
				FieldValidators.POSITIVE_NUMBER_VALIDATOR);

		return group;
	}

	private Control createHotCodeReplaceGroup(Composite parent) {
		final Group group = SWTFactory.createGroup(parent,
				ScriptDebugPreferencesMessages.HCRLabel, 1, 1,
				GridData.FILL_HORIZONTAL);

		boolean alertHcrFailed = fUIPreferences
				.getBoolean(IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED);

		fAlertHCRFailed = SWTFactory.createCheckButton(group,
				ScriptDebugPreferencesMessages.HCRFailedLabel, null,
				alertHcrFailed, 1);

		boolean alertHcrNotSupported = fUIPreferences
				.getBoolean(IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED);

		fAlertHCRNotSupported = SWTFactory.createCheckButton(group,
				ScriptDebugPreferencesMessages.HCRNotSupportedLabel, null,
				alertHcrNotSupported, 1);

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

				/*
				 * meh, is there a better way to allow this string to be
				 * externalized and still support the preference link?
				 */
				String message = MessageFormat
						.format(
								ScriptDebugPreferencesMessages.LinkToLanguageDebugOptions,
								new Object[] { "{0}", languageName });

				PreferenceLinkArea area = new PreferenceLinkArea(composite,
						SWT.NONE, pageId, message,
						(IWorkbenchPreferenceContainer) preferencePage
								.getContainer(), null);

				area.getControl().setLayoutData(
						new GridData(SWT.FILL, SWT.FILL, false, false));
			}
		}
	}

	public Control createControl(Composite parent) {
		final Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL_HORIZONTAL);

		createDbgpGroup(composite);
		createHotCodeReplaceGroup(composite);
		createScriptLanguagesLinks(composite);

		return composite;
	}

	public void initialize() {
		super.initialize();

		final IPreferenceStore store = getPreferenceStore();

		int port = store.getInt(DLTKDebugPreferenceConstants.PREF_DBGP_PORT);
		if (port == DLTKDebugPreferenceConstants.DBGP_AVAILABLE_PORT) {
			portText.setEnabled(false);
			portText.setText("");
			portCombo.select(AUTO_SELECT_PORT_INDEX);
		} else {
			portText.setEnabled(true);
			portText.setText(Integer.toString(port));
			portCombo.select(CUSTOM_PORT_INDEX);
		}
	}

	public void performDefaults() {
		fAlertHCRFailed.setSelection(true);
		fAlertHCRNotSupported.setSelection(true);
		super.performDefaults();
	}

	public void performOk() {
		super.performOk();

		fUIPreferences.setValue(
				IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED,
				fAlertHCRFailed.getSelection());

		fUIPreferences.setValue(
				IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED,
				fAlertHCRNotSupported.getSelection());

		final IPreferenceStore store = getPreferenceStore();

		if (portCombo.getSelectionIndex() == AUTO_SELECT_PORT_INDEX) {
			store.setValue(DLTKDebugPreferenceConstants.PREF_DBGP_PORT,
					DLTKDebugPreferenceConstants.DBGP_AVAILABLE_PORT);
		} else {
			store.setValue(DLTKDebugPreferenceConstants.PREF_DBGP_PORT, Integer
					.parseInt(portText.getText()));
		}
	}
}