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
package org.eclipse.dltk.validators.internal.ui;


import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.validators.core.IValidator;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The Installed InterpreterEnvironments preference page.
 * 
 *
 */
public class ValidatorPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
							
	private static final String VALIDATOR_PREFERENCE_PAGE = ValidatorsUI.PLUGIN_ID + ".ValidatorPreferencePage";
	// InterpreterEnvironment Block
	private ValidatorBlock fInterpretersBlock;
	
	public ValidatorPreferencePage() {
		super();
		
		// only used when page is shown programatically
		setTitle(ValidatorMessages.ValidatorPreferencePage_1);	 
		
		setDescription(ValidatorMessages.ValidatorPreferencePage_2); 
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite ancestor) {
		initializeDialogUnits(ancestor);
		
		noDefaultAndApplyButton();
		
		GridLayout layout= new GridLayout();
		layout.numColumns= 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		ancestor.setLayout(layout);
					
		fInterpretersBlock = createValidatorsBlock();
		fInterpretersBlock.createControl(ancestor);
		Control control = fInterpretersBlock.getControl();
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 1;
		control.setLayoutData(data);
		
		fInterpretersBlock.restoreColumnSettings(ValidatorsUI.getDefault().getDialogSettings(), 
				VALIDATOR_PREFERENCE_PAGE);
						
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(ancestor, IDLTKDebugHelpContextIds.INTERPRETER_PREFERENCE_PAGE);		
//		initDefaultInterpreter();
		fInterpretersBlock.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
//				IValidator install = getCurrentDefaultInterpreter();
//				if (install == null) {
//					if (fInterpretersBlock.getInterpreters().length > 0)
//						setErrorMessage(ValidatorMessages.InterpreterPreferencePage_pleaseSetDefaultInterpreter);
//					else
//						setErrorMessage(ValidatorMessages.InterpreterPreferencePage_addInterpreter);
//				} else {
//					setErrorMessage(null);
//				}
			}
		});	
		applyDialogFont(ancestor);
		return ancestor;
	}
			
	private ValidatorBlock createValidatorsBlock() {
		return new ValidatorBlock(); 
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		final boolean[] canceled = new boolean[] {false};
		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				IValidator[] vnterpreters = fInterpretersBlock.getValidator();
				ValidatorUpdater updater = new ValidatorUpdater();
				if (!updater.updateValidatorSettings(vnterpreters)) {
					canceled[0] = true;
				}
			}
		});
		
		if(canceled[0]) {
			return false;
		}
		
		// save column widths
		IDialogSettings settings = ValidatorsUI.getDefault().getDialogSettings();
		fInterpretersBlock.saveColumnSettings(settings, VALIDATOR_PREFERENCE_PAGE);
		
		return super.performOk();
	}	
	
	protected IScriptModel getScriptModel() {
		return DLTKCore.create(ResourcesPlugin.getWorkspace().getRoot());
	}
}
