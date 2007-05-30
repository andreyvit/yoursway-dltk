package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class AddRuleDialog extends StatusDialog {
	
	private StringDialogField fRule;
	
	public AddRuleDialog(Shell shell){
		super(shell);
	}
	
	public void create(){
		super.create();
		fRule.setFocus();
	}
	
	protected Control createDialogArea(Composite ancestor){
		Composite parent = (Composite)super.createDialogArea(ancestor);
		((GridLayout)parent.getLayout()).numColumns= 3;
		
		fRule = new StringDialogField();
		fRule.doFillIntoGrid(parent, 3);
		
		return parent;
	}
	
	

	

}
