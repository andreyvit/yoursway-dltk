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