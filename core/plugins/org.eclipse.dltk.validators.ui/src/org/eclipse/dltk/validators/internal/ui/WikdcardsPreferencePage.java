package org.eclipse.dltk.validators.internal.ui;

import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class WikdcardsPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	
	StringDialogField testField;


	protected Control createContents(Composite parent) {
		testField = new StringDialogField();
		testField.doFillIntoGrid(parent,3);
		
		// TODO Auto-generated method stub
		return parent;
	}


	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
