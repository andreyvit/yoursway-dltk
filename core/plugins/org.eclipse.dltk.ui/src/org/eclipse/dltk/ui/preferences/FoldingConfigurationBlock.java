/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ui.preferences;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


/**
 * Configures Script Editor folding preferences.
 * 
	 *
 */
public abstract class FoldingConfigurationBlock implements IPreferenceConfigurationBlock {
	
	private static class ErrorPreferences implements IFoldingPreferenceBlock {
		private String fMessage;
		
		protected ErrorPreferences(String message) {
			fMessage= message;
		}
				
		public Control createControl(Composite composite) {
			Composite inner= new Composite(composite, SWT.NONE);
			inner.setLayout(new FillLayout(SWT.VERTICAL));

			Label label= new Label(inner, SWT.CENTER);
			label.setText(fMessage);
			
			return inner;
		}

		public void initialize() {
		}

		public void performOk() {
		}

		public void performDefaults() {
		}

		public void dispose() {
		}
		
	}

	/** The overlay preference store. */
	protected final OverlayPreferenceStore fStore;
	
	/* The controls */
	private Button fFoldingCheckbox;
	private Button fCommentsFoldingCheckbox;
	private Composite fGroup;
	private StackLayout fStackLayout;
	PreferencePage fMainPage;
	
	/* the model */
	private Control fProviderControl;

	private IFoldingPreferenceBlock fPreferenceBlock;
	

	public FoldingConfigurationBlock(OverlayPreferenceStore store, PreferencePage prefPage) {
		Assert.isNotNull(store);	
		fMainPage = prefPage;
		fStore= store;		
		fStore.addKeys(createOverlayStoreKeys());
		fPreferenceBlock = createFoldingPreferenceBlock();
		fProviderControl =  null;		
	}
	
	protected PreferencePage getPreferencePage() {
		return fMainPage;
	}
	
	protected abstract IFoldingPreferenceBlock createFoldingPreferenceBlock ();
	
	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {
		
		ArrayList overlayKeys= new ArrayList();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN, PreferenceConstants.EDITOR_FOLDING_ENABLED));
		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN, PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED));
		
		OverlayPreferenceStore.OverlayKey[] keys= new OverlayPreferenceStore.OverlayKey[overlayKeys.size()];
		overlayKeys.toArray(keys);
		return keys;
	}

	/**
	 * Creates page for folding preferences.
	 * 
	 * @param parent the parent composite
	 * @return the control for the preference page
	 */
	public Control createControl(Composite parent) {

		Composite composite= new Composite(parent, SWT.NULL);
		// assume parent page uses griddata
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		composite.setLayoutData(gd);
		GridLayout layout= new GridLayout();
		layout.numColumns= 1;
		PixelConverter pc= new PixelConverter(composite);
		layout.verticalSpacing= pc.convertHeightInCharsToPixels(1) / 2;
		composite.setLayout(layout);
		
		
		/* check box for new editors */
		fFoldingCheckbox= new Button(composite, SWT.CHECK);
		fFoldingCheckbox.setText(PreferencesMessages.FoldingConfigurationBlock_enable); 
		gd= new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		fFoldingCheckbox.setLayoutData(gd);
		fFoldingCheckbox.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean enabled= fFoldingCheckbox.getSelection(); 
				fStore.setValue(PreferenceConstants.EDITOR_FOLDING_ENABLED, enabled);
				updateCheckboxDependencies();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		fCommentsFoldingCheckbox= new Button(composite, SWT.CHECK);
		fCommentsFoldingCheckbox.setText(PreferencesMessages.FoldingConfigurationBlock_commentsEnable); 
		gd= new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		fCommentsFoldingCheckbox.setLayoutData(gd);
		fCommentsFoldingCheckbox.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean enabled= fCommentsFoldingCheckbox.getSelection(); 
				fStore.setValue(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED, enabled);
				updateCheckboxDependencies();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		/*Label label= new Label(composite, SWT.CENTER);
		gd= new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		label.setLayoutData(gd);*/
		
		Composite groupComp= new Composite(composite, SWT.NONE);
		gd= new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan= 1;
		groupComp.setLayoutData(gd);
		GridLayout gridLayout= new GridLayout(1, false);
		gridLayout.marginWidth= 0;
		groupComp.setLayout(gridLayout);
		
		/* contributed provider preferences. */
		fGroup= new Composite(groupComp, SWT.NONE);
		gd= new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
		fGroup.setLayoutData(gd);
		fStackLayout= new StackLayout();
		fGroup.setLayout(fStackLayout);
		
		return composite;
	}

	

	private void updateCheckboxDependencies() {
		fCommentsFoldingCheckbox.setEnabled(fFoldingCheckbox.getSelection());
	}
	
	IFoldingPreferenceBlock getPreferenceBlock () {
		return fPreferenceBlock;
	}

	void updateListDependencies() {
		IFoldingPreferenceBlock prefs;
		
		prefs= getPreferenceBlock ();
		if (prefs == null) {
			prefs= new ErrorPreferences(PreferencesMessages.FoldingConfigurationBlock_noFoldingPreferenceBlock);			
		}
		
		Control control= fProviderControl;
		if (control == null) {
			control= prefs.createControl(fGroup);
			if (control == null) {
				String message= PreferencesMessages.FoldingConfigurationBlock_info_no_preferences; 
				control= new ErrorPreferences(message).createControl(fGroup);
			} else {
				fProviderControl = control;
			}
		}
		Dialog.applyDialogFont(control);
		fStackLayout.topControl= control;
		control.pack();
		fGroup.layout();
		fGroup.getParent().layout();
		
		prefs.initialize();
	}
	
	public void initialize() {
		restoreFromPreferences();
	}

	public void performOk() {
		IFoldingPreferenceBlock prefs= getPreferenceBlock();
		prefs.performOk();
	}
	
	public void performDefaults() {
		restoreFromPreferences();
		IFoldingPreferenceBlock prefs= getPreferenceBlock();
		prefs.performDefaults();
	}
	
	public void dispose() {
		IFoldingPreferenceBlock prefs = getPreferenceBlock();
		prefs.dispose();
	}

	private void restoreFromPreferences() {
		boolean enabled= fStore.getBoolean(PreferenceConstants.EDITOR_FOLDING_ENABLED);
		fFoldingCheckbox.setSelection(enabled);
		boolean commentsEnabled= fStore.getBoolean(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED);
		fCommentsFoldingCheckbox.setSelection(commentsEnabled);
		updateCheckboxDependencies();		
		updateListDependencies();
	}
}
