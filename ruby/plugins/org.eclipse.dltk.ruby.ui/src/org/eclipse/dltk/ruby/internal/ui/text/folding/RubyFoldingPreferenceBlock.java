/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.folding;

import java.util.ArrayList;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Script default folding preferences.
 * 
	 *
 */
public class RubyFoldingPreferenceBlock extends AbstractConfigurationBlock implements IFoldingPreferenceBlock {
	private OverlayPreferenceStore fOverlayStore;
	private OverlayKey[] fKeys;

	public RubyFoldingPreferenceBlock(OverlayPreferenceStore store, PreferencePage mainPreferencePage) {
		super(store, mainPreferencePage);
		fOverlayStore = store;
		fKeys = createKeys();
		fOverlayStore.addKeys(fKeys);
	}

	private OverlayKey[] createKeys() {
		ArrayList overlayKeys = new ArrayList();
		
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.INT, 
				PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT));

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED));
		
		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys.size()];
		overlayKeys.toArray(keys);
		return keys;
	}	
	private class ListBlock {
		private ListViewer fList;
		String fKey;
		private Button fAddButton;
		private Button fRemoveButton;

		public ListBlock(Composite parent, String key) {
			fKey = key;
			createControl(parent);
		}

		private Control createControl(Composite parent) {
			Font font = parent.getFont();
			Composite comp = new Composite(parent, SWT.NONE);
			GridLayout topLayout = new GridLayout();
			topLayout.numColumns = 2;
			topLayout.marginHeight = 0;
			topLayout.marginWidth = 0;
			comp.setLayout(topLayout);
			GridData gd = new GridData(GridData.FILL_BOTH);
			comp.setLayoutData(gd);
			fList = new ListViewer(comp);
			gd = new GridData(GridData.FILL_BOTH);
			gd.heightHint = 6;
			fList.getControl().setLayoutData(gd);
			Composite pathButtonComp = new Composite(comp, SWT.NONE);
			GridLayout pathButtonLayout = new GridLayout();
			pathButtonLayout.marginHeight = 0;
			pathButtonLayout.marginWidth = 0;
			pathButtonComp.setLayout(pathButtonLayout);
			gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
			pathButtonComp.setLayoutData(gd);
			pathButtonComp.setFont(font);
			fAddButton = createPushButton(pathButtonComp, RubyFoldingMessages.TclFoldingPreferenceBlock_0);
			fAddButton.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {}

				public void widgetSelected(SelectionEvent e) {
					IInputValidator validator = new IInputValidator() {
						public String isValid(String newText) {
							if (newText.trim().length() > 0 && newText.matches("[_a-zA-Z]*")) //$NON-NLS-1$
								return null;							
							return RubyFoldingMessages.TclFoldingPreferenceBlock_2;
						}
					};
					InputDialog dlg = new InputDialog(null, RubyFoldingMessages.TclFoldingPreferenceBlock_3, RubyFoldingMessages.TclFoldingPreferenceBlock_4, "", validator); //$NON-NLS-3$
					if (dlg.open() == InputDialog.OK) {
						fList.add(dlg.getValue());
						save();
					}
				}
			});
			fRemoveButton = createPushButton(pathButtonComp, RubyFoldingMessages.TclFoldingPreferenceBlock_6);
			fRemoveButton.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {}

				public void widgetSelected(SelectionEvent e) {
					ISelection s = fList.getSelection();
					if (s instanceof IStructuredSelection) {
						IStructuredSelection sel = (IStructuredSelection) s;
						fList.remove(sel.toArray());
						save();
					}
				}
			});
			return comp;
		}

		protected Button createPushButton(Composite parent, String label) {
			Button button = new Button(parent, SWT.PUSH);
			button.setFont(parent.getFont());
			if (label != null) {
				button.setText(label);
			}
			GridData gd = new GridData();
			button.setLayoutData(gd);
			gd.widthHint = getButtonWidthHint(button);
			gd.horizontalAlignment = GridData.FILL;
			return button;
		}

		/**
		 * Returns a width hint for a button control.
		 */
		public int getButtonWidthHint(Button button) {
			button.setFont(JFaceResources.getDialogFont());
			PixelConverter converter = new PixelConverter(button);
			int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
			return Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		}

		private String[] getEntries() {
			return fList.getList().getItems();
		}
		
		private void setEntries (String items[]) {
			fList.remove(fList.getList().getItems());
			for (int i = 0; i < items.length; i++) {
				if (items[i].trim().length() > 0)
					fList.add(items[i]);
			}
		}

		public void save() {
			String items[] = getEntries();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < items.length; i++) {
				buf.append(items[i]);
				if (i != items.length - 1)
					buf.append(","); //$NON-NLS-1$
			}
			getPreferenceStore().setValue(fKey, buf.toString());
		}
		
		public void initialize () {
			String val = getPreferenceStore().getString(fKey);
			if (val != null) {
				String items[] = val.split(","); //$NON-NLS-1$
				setEntries (items);
			}
			
		}

		public void performDefault() {
			String val = getPreferenceStore().getDefaultString(fKey);
			if (val != null) {
				String items[] = val.split(","); //$NON-NLS-1$
				setEntries (items);
			}
		}
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.text.folding.IScriptFoldingPreferences#createControl(org.eclipse.swt.widgets.Group)
	 */
	public Control createControl(Composite composite) {
		Composite inner = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		inner.setLayout(layout);
		
		
		Composite blockFolding = createSubsection(inner, null, RubyFoldingMessages.TclFoldingPreferenceBlock_10);
		blockFolding.setLayout(new GridLayout());		
		
		IInputValidator val = new IInputValidator () {

			public String isValid(String number) {
				if (number.length() == 0) {
					return PreferencesMessages.DLTKEditorPreferencePage_empty_input; 
				} else {
					try {
						int value= Integer.parseInt(number);
						if (value < 2)
							return "You may input numbers >= 2."; 
					} catch (NumberFormatException e) {
						return "Input is not a number"; 
					}
				}
				return null;
			}
			
		};
		
		addLabelledTextField(blockFolding, "Minimal amount of lines to be folded(>=2):", 
				PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 3, 1, true, val);
		
		Composite commentFolding = createSubsection(inner, null, RubyFoldingMessages.TclFoldingPreferenceBlock_14);
		commentFolding.setLayout(new GridLayout());
		
		addCheckBox(commentFolding, RubyFoldingMessages.TclFoldingPreferenceBlock_15,
				PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED, 0);
		
		Composite initialFolding = createSubsection(inner, null, RubyFoldingMessages.TclFoldingPreferenceBlock_16);
		initialFolding.setLayout(new GridLayout());
				
		return inner;
	}
	
	public void initialize() {
		super.initialize();		
	}
	
	public void performDefaults() {
		super.performDefaults();		
	}
}
