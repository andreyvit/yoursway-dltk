/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.preferences;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DebuggingConfigurationBlock extends AbstractConfigurationBlock {

	public DebuggingConfigurationBlock(OverlayPreferenceStore store,
			PreferencePage mainPreferencePage) {
		super(store, mainPreferencePage);
	}

	private Control createSettingsGroup(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		String label = "Break on first line";
		addCheckBox(composite, label,
				PreferenceConstants.EDITOR_SMART_HOME_END, 0);

		return composite;
	}

	public Control createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout());

		createSettingsGroup(control);

		return control;
	}
	
	public void performOk() {
		// TODO Auto-generated method stub
		super.performOk();
	}
}