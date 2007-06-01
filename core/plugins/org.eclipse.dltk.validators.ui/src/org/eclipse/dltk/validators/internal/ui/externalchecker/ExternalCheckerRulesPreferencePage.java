/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.ui.externalchecker;

import org.eclipse.dltk.validators.internal.core.ValidatorsCore;
import org.eclipse.dltk.validators.internal.core.externalchecker.CustomWildcard;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalCheckerWildcardManager;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ExternalCheckerRulesPreferencePage extends PreferencePage
		implements IWorkbenchPreferencePage {

// private static final String EXTERNALCHECKER_PREFERENCE_PAGE =
// ValidatorsUI.PLUGIN_ID + ".ExternalCheckerPreferencePage";

	private ExternalCheckerRulesBlock fRulesBlock;

	public ExternalCheckerRulesPreferencePage() {
		super();
		setTitle("External checker rules");
		setDescription("External checker rules");
	}

	public void init(IWorkbench workbench) {
	}

	protected Control createContents(Composite ancestor) {
		initializeDialogUnits(ancestor);

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		ancestor.setLayout(layout);

		fRulesBlock = createRulesBlock();
		fRulesBlock.createControl(ancestor);
		Control control = fRulesBlock.getControl();
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 1;
		control.setLayoutData(data);

		applyDialogFont(ancestor);
		return ancestor;
	}

	private ExternalCheckerRulesBlock createRulesBlock() {
		return new ExternalCheckerRulesBlock();
	}

	public boolean performOk() {

		CustomWildcardsList wlist = fRulesBlock.getWlist();

		CustomWildcard[] customWildcards = wlist.getWildcards();
		ExternalCheckerWildcardManager.storeWildcards(customWildcards);
		return true;
	}

	protected void performDefaults() {
// System.out.println("Defaults");
		String xmlString = ExternalCheckerWildcardManager.getDefaultWildcards();
// ValidatorsCore.getDefault().getPluginPreferences().setDefault("wildcards",
// xmlString);
		ValidatorsCore.getDefault().getPluginPreferences().setValue(
				ExternalCheckerWildcardManager.WILDCARDS, xmlString);
		ValidatorsCore.getDefault().savePluginPreferences();
		fRulesBlock.removeAll();
		fRulesBlock.loadWildcards();
	}
}
