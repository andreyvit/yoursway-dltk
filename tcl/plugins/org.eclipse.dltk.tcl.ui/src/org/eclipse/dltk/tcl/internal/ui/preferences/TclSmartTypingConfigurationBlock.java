/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.tcl.internal.ui.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * Configures Editor typing preferences.
 */
class TclSmartTypingConfigurationBlock extends AbstractConfigurationBlock {

	public TclSmartTypingConfigurationBlock(OverlayPreferenceStore store) {
		super(store);

		store.addKeys(createOverlayStoreKeys());
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {

		List keys = new ArrayList();

		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				TclPreferenceConstants.EDITOR_CLOSE_STRINGS));
		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				TclPreferenceConstants.EDITOR_CLOSE_BRACKETS));
		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				TclPreferenceConstants.EDITOR_CLOSE_BRACES));
		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				TclPreferenceConstants.EDITOR_SMART_TAB));
		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				TclPreferenceConstants.EDITOR_SMART_PASTE_MODE));

		return (OverlayPreferenceStore.OverlayKey[]) keys
				.toArray(new OverlayPreferenceStore.OverlayKey[keys
						.size()]);
	}
		
	public Control createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		control.setLayout(layout);

		Composite composite = createSubsection(
				control,
				null,
				TclPreferencesMessages.TCLSmartTypingConfigurationBlock_autoclose_title);
		addAutoclosingSection(composite);

		composite = createSubsection(control, null,
				TclPreferencesMessages.TCLSmartTypingConfigurationBlock_tabs_title);
		addTabSection(composite);

		composite = createSubsection(control, null,
				PreferencesMessages.SmartTypingConfigurationBlock_pasting_title);
		addPasteSection(composite);

		return control;
	}

	private void addPasteSection(Composite composite) {
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		String label = null;
		
		label = TclPreferencesMessages.TCLSmartTypingConfigurationBlock_smartPaste_simple;
		addRadioButton(composite, label,
				TclPreferenceConstants.EDITOR_SMART_PASTE_MODE,
				TclPreferenceConstants.EDITOR_SMART_PASTE_MODE_SIMPLE);
		
		label = TclPreferencesMessages.TCLSmartTypingConfigurationBlock_smartPaste_full;
		addRadioButton(composite, label,
				TclPreferenceConstants.EDITOR_SMART_PASTE_MODE,
				TclPreferenceConstants.EDITOR_SMART_PASTE_MODE_FULL);

	}

	private void addTabSection(Composite composite) {
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		String label;
		label = TclPreferencesMessages.TCLSmartTypingConfigurationBlock_typing_smartTab;
		addCheckBox(composite, label, TclPreferenceConstants.EDITOR_SMART_TAB,
				0);

		createMessage(composite);
	}

	private void addAutoclosingSection(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		String label;

		label = TclPreferencesMessages.TCLSmartTypingConfigurationBlock_closeStrings;
		addCheckBox(composite, label,
				TclPreferenceConstants.EDITOR_CLOSE_STRINGS, 0);

		label = TclPreferencesMessages.TCLSmartTypingConfigurationBlock_closeBrackets;
		addCheckBox(composite, label,
				TclPreferenceConstants.EDITOR_CLOSE_BRACKETS, 0);

		label = TclPreferencesMessages.TCLSmartTypingConfigurationBlock_closeBraces;
		addCheckBox(composite, label,
				TclPreferenceConstants.EDITOR_CLOSE_BRACES, 0);
	}

	private void createMessage(final Composite composite) {
		// TODO create a link with an argument, so the formatter preference page
		// can open the
		// current profile automatically.
		String linkTooltip = PreferencesMessages.SmartTypingConfigurationBlock_tabs_message_tooltip;
		String text;
		String indentMode = TclUI.getDefault().getPreferenceStore().getString(
				CodeFormatterConstants.FORMATTER_TAB_CHAR);
		if (CodeFormatterConstants.TAB.equals(indentMode))
			text = Messages
					.format(
							PreferencesMessages.SmartTypingConfigurationBlock_tabs_message_tab_text,
							new String[] { Integer
									.toString(getTabDisplaySize()) });
		else
			text = Messages
					.format(
							PreferencesMessages.SmartTypingConfigurationBlock_tabs_message_others_text,
							new String[] {
									Integer.toString(getTabDisplaySize()),
									getIndentMode() });

		final Link link = new Link(composite, SWT.NONE);
		link.setText(text);
		link.setToolTipText(linkTooltip);
		GridData gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		gd.widthHint = 300; // don't get wider initially
		link.setLayoutData(gd);
		link.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				PreferencesUtil.createPreferenceDialogOn(link.getShell(),
						"org.eclipse.dltk.tcl.ui.EditorPreferences", null, null); //$NON-NLS-1$
			}
		});

		final IPreferenceStore combinedStore = getPreferenceStore();
		final IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
			private boolean fHasRun = false;

			public void propertyChange(PropertyChangeEvent event) {
				if (fHasRun)
					return;
				if (composite.isDisposed())
					return;
				String property = event.getProperty();
				if (CodeFormatterConstants.FORMATTER_TAB_CHAR.equals(property)
						|| CodeFormatterConstants.FORMATTER_TAB_SIZE
								.equals(property)) {
					fHasRun = true;
					link.dispose();
					createMessage(composite);
					Dialog.applyDialogFont(composite);
					composite.redraw();
					composite.layout();
				}
			}
		};
		combinedStore.addPropertyChangeListener(propertyChangeListener);
		link.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(org.eclipse.swt.events.DisposeEvent e) {
				combinedStore
						.removePropertyChangeListener(propertyChangeListener);
			}
		});
	}

	private String getIndentMode() {
		String indentMode = TclUI.getDefault().getPreferenceStore().getString(
				CodeFormatterConstants.FORMATTER_TAB_CHAR);

		if (CodeFormatterConstants.SPACE.equals(indentMode))
			return PreferencesMessages.SmartTypingConfigurationBlock_tabs_message_spaces;

		if (CodeFormatterConstants.TAB.equals(indentMode))
			return PreferencesMessages.SmartTypingConfigurationBlock_tabs_message_tabs;

		if (CodeFormatterConstants.MIXED.equals(indentMode))
			return PreferencesMessages.SmartTypingConfigurationBlock_tabs_message_tabsAndSpaces;

		Assert.isTrue(false, "Illegal indent mode - must not happen"); //$NON-NLS-1$
		return null;
	}

	private int getTabDisplaySize() {
		return TclUI.getDefault().getPreferenceStore().getInt(
				CodeFormatterConstants.FORMATTER_TAB_SIZE);
	}

}
