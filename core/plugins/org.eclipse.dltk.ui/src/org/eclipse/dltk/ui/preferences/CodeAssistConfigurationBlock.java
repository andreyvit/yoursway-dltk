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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class CodeAssistConfigurationBlock extends AbstractConfigurationBlock {
	private Button fCompletionInsertsRadioButton;
	private Button fCompletionOverwritesRadioButton;

	public CodeAssistConfigurationBlock(PreferencePage mainPreferencePage,
			OverlayPreferenceStore store) {
		super(store, mainPreferencePage);
		getPreferenceStore().addKeys(createOverlayStoreKeys());
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {
		ArrayList overlayKeys = new ArrayList();

		getOverlayKeys(overlayKeys);

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;
	}

	protected void getOverlayKeys(ArrayList overlayKeys) {
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
				PreferenceConstants.CODEASSIST_INSERT_COMPLETION));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.CODEASSIST_SORTER));
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

		Composite composite;

		composite = createSubsection(
				control,
				null,
				PreferencesMessages.CodeAssistConfigurationBlock_insertionSection_title);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		addInsertionSection(composite);

		composite = createSubsection(
				control,
				null,
				PreferencesMessages.CodeAssistConfigurationBlock_autoactivationSection_title);
		composite.setLayout(layout);
		addAutoActivationSection(composite);

		// createTabsGroup(control);

		return control;
	}
	
//	protected void addSortingSection(Composite composite) {
//		String label;
//
//		label= PreferencesMessages.DLTKEditorPreferencePage_presentProposalsInAlphabeticalOrder;
//		ProposalSorterHandle[] sorters= ProposalSorterRegistry.getDefault().getSorters();
//		String[] labels= new String[sorters.length];
//		String[] values= new String[sorters.length];
//		for (int i= 0; i < sorters.length; i++) {
//			ProposalSorterHandle handle= sorters[i];
//			labels[i]= handle.getName();
//			values[i]= handle.getId();
//		}
//		
//		addComboBox(composite, label, PreferenceConstants.CODEASSIST_SORTER, values, labels);
//	}


	Control autoActivation;

	protected void addAutoActivationSection(Composite composite) {
		String label;
		label = PreferencesMessages.DLTKEditorPreferencePage_enableAutoActivation;
		final Button autoactivation = addCheckBox(composite, label,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION, 2);
		autoactivation.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateAutoactivationControls();
			}
		});

		label = PreferencesMessages.DLTKEditorPreferencePage_autoActivationDelay;
		Control[] ctrl = addLabelledTextField(composite, label,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY, 4, 2, true);
		autoActivation = ctrl[1];
	}

	private void updateAutoactivationControls() {
		boolean autoactivation = getPreferenceStore().getBoolean(
				PreferenceConstants.CODEASSIST_AUTOACTIVATION);
		if (autoActivation != null) {
			autoActivation.setEnabled(autoactivation);
		}
	}

	protected void addInsertionSection(Composite composite) {
		addCompletionRadioButtons(composite);

		String label;
		label = PreferencesMessages.DLTKEditorPreferencePage_insertSingleProposalsAutomatically;
		addCheckBox(composite, label,
				PreferenceConstants.CODEASSIST_AUTOINSERT, 2);
	}

	protected void addCompletionRadioButtons(Composite contentAssistComposite) {
		Composite completionComposite = new Composite(contentAssistComposite,
				SWT.NONE);
		GridData ccgd = new GridData();
		ccgd.horizontalSpan = 2;
		completionComposite.setLayoutData(ccgd);
		GridLayout ccgl = new GridLayout();
		ccgl.marginWidth = 0;
		ccgl.numColumns = 2;
		completionComposite.setLayout(ccgl);

		SelectionListener completionSelectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean insert = fCompletionInsertsRadioButton.getSelection();
				getPreferenceStore().setValue(
						PreferenceConstants.CODEASSIST_INSERT_COMPLETION,
						insert);
			}
		};

		fCompletionInsertsRadioButton = new Button(completionComposite,
				SWT.RADIO | SWT.LEFT);
		fCompletionInsertsRadioButton
				.setText(PreferencesMessages.DLTKEditorPreferencePage_completionInserts);
		fCompletionInsertsRadioButton.setLayoutData(new GridData());
		fCompletionInsertsRadioButton
				.addSelectionListener(completionSelectionListener);

		fCompletionOverwritesRadioButton = new Button(completionComposite,
				SWT.RADIO | SWT.LEFT);
		fCompletionOverwritesRadioButton
				.setText(PreferencesMessages.DLTKEditorPreferencePage_completionOverwrites);
		fCompletionOverwritesRadioButton.setLayoutData(new GridData());
		fCompletionOverwritesRadioButton
				.addSelectionListener(completionSelectionListener);

		Label label = new Label(completionComposite, SWT.NONE);
		label
				.setText(PreferencesMessages.DLTKEditorPreferencePage_completionToggleHint);
		GridData gd = new GridData();
		gd.horizontalIndent = 20;
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
	}

	protected void initializeFields() {
		super.initializeFields();
		boolean completionInserts = getPreferenceStore().getBoolean(
				PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
		fCompletionInsertsRadioButton.setSelection(completionInserts);
		fCompletionOverwritesRadioButton.setSelection(!completionInserts);

		updateAutoactivationControls();
	}
}
