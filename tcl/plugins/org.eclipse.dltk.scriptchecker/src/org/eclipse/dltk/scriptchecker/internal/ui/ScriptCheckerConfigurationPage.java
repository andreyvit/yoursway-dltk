package org.eclipse.dltk.scriptchecker.internal.ui;

import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.scriptchecker.internal.core.ScriptChecker;
import org.eclipse.dltk.validators.ValidatorConfigurationPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ScriptCheckerConfigurationPage extends ValidatorConfigurationPage {
	
	private static final String[] SEVERITY = ScriptChecker.SEVERITY_VALUES;
	private StringDialogField fPath;
	private StringDialogField fArguments;
	private SelectionButtonDialogField fStyle;
	private SelectionButtonDialogField fSyntax;
	private ComboDialogField fSeverity;
	
	public ScriptCheckerConfigurationPage() {
	}

	public void applyChanges() {
		ScriptChecker scriptChecker = getScriptChecker();
		scriptChecker.setPath(this.fPath.getText());
		scriptChecker.setArguments(this.fArguments.getText());
		scriptChecker.setSeverity(this.fSeverity.getText());
		scriptChecker.setStyle(this.fStyle.isSelected());
		scriptChecker.setSyntax(this.fSyntax.isSelected());
	}

	public void createControl(final Composite parent, int columns ) {
		createFields();
	
		createBrowseButton(parent, columns);
		this.fArguments.doFillIntoGrid(parent, columns);
		createLabel( parent, "(%f will be replaced to filename, if no -- are specified, then it added before all arguments)", columns);
		this.fSeverity.doFillIntoGrid(parent, columns);
		this.fStyle.doFillIntoGrid(parent, columns);
		this.fSyntax.doFillIntoGrid(parent, columns);
		
		updateValuesFrom();
	}
	private ScriptChecker getScriptChecker() {
		return (ScriptChecker)getValidator();
	}
	private void updateValuesFrom() {
		ScriptChecker scriptChecker = getScriptChecker();
		this.fPath.setText(scriptChecker.getPath());
		this.fArguments.setText(scriptChecker.getArguments());
		this.fStyle.setSelection(scriptChecker.isStyle());
		this.fSyntax.setSelection(scriptChecker.isSyntax());
		this.fSeverity.selectItem(scriptChecker.getSeverity());
	}

	private void createLabel(Composite parent, String content, int columns) {
		Label l = new Label( parent, SWT.None );
		l.setText(content);
		GridData gd= new GridData();
		gd.horizontalAlignment= GridData.FILL;
		gd.grabExcessHorizontalSpace= true;
		gd.horizontalSpan= columns;
		l.setLayoutData(gd);
	}

	private void createBrowseButton(final Composite parent, int columns ) {
		this.fPath.doFillIntoGrid(parent, columns - 1);
		Text path = this.fPath.getTextControl(parent);
		GridData gd= new GridData();
		gd.horizontalAlignment= GridData.FILL;
		gd.grabExcessHorizontalSpace= true;
		gd.horizontalSpan= columns - 2;
		path.setLayoutData(gd);
		// Browse
		Button browse = new Button(parent, SWT.PUSH);
		browse.setText("Browse...");
		gd= new GridData(GridData.END);
		gd.horizontalSpan = 1;
		browse.setLayoutData(gd);

		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					fPath.setText(file);
				}
			}
		});
	}

	private void createFields() {
		this.fPath = new StringDialogField();
		this.fPath.setLabelText("Script Checker Path:");
		
		this.fPath.setDialogFieldListener(new IDialogFieldListener() {
			public void dialogFieldChanged(DialogField field) {
				validateScriptCheckerPath();
			}
		});
		
		this.fArguments = new StringDialogField();
		this.fArguments.setLabelText("Arguments");
		
		this.fStyle = new SelectionButtonDialogField(SWT.CHECK);
		this.fStyle.setLabelText("Check Style Guidelines");
		
		this.fSyntax = new SelectionButtonDialogField(SWT.CHECK);
		this.fSyntax.setLabelText("Check Syntax");
		
		this.fSeverity = new ComboDialogField(SWT.DROP_DOWN | SWT.READ_ONLY);
		this.fSeverity.setLabelText("Severity");
		this.fSeverity.setItems(SEVERITY);
	}

	protected void validateScriptCheckerPath() {
	}
}
