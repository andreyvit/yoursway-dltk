/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.preferences;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class DLTKPreferencesPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public DLTKPreferencesPage() {
		super(GRID);
		setPreferenceStore(DLTKUIPlugin.getDefault().getPreferenceStore());
		setDescription("DLTK Core Preferences");
	}

	public void createFieldEditors() {
	}

	public void init(IWorkbench workbench) {
	}
}