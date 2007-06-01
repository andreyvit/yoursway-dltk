/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.scriptchecker.internal.ui;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.transform.TransformerException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.scriptchecker.internal.ui.ATSGuideManager.GuideNode;
import org.eclipse.dltk.validators.core.IValidatorType;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AddGuideDialog extends StatusDialog {

// private IAddValidatorDialogRequestor fRequestor

	private IValidatorType fSelectedValidatorType;

	private GuideNode fNode;

	private StringDialogField fPattern;
	private StringDialogField fURI;
	private SelectionButtonDialogField fRegularExpression;

	private IStatus[] fStati;
	private int fPrevIndex = -1;

	public AddGuideDialog(Shell shell, GuideNode node) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		// fRequestor= requestor;
		fStati = new IStatus[5];
		for (int i = 0; i < fStati.length; i++) {
			fStati[i] = new StatusInfo();
		}

		fNode = node;
	}

	/**
	 * @see Windows#configureShell
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(newShell,
		// IScriptDebugHelpContextIds.EDIT_ValidatorEnvironment_DIALOG);
	}

	protected void createDialogFields() {

		fPattern = new StringDialogField();
		fPattern.setLabelText("Pattern");

		fURI = new StringDialogField();
		fURI.setLabelText("URI");
		
		fRegularExpression = new SelectionButtonDialogField(SWT.CHECK);
		fRegularExpression.setLabelText("Regular expression");
	}

	protected void createFieldListeners() {

		fPattern.setDialogFieldListener(new IDialogFieldListener() {
			public void dialogFieldChanged(DialogField field) {
				setPatternStatus(validate());
				updateStatusLine();
			}
		});
		fURI.setDialogFieldListener(new IDialogFieldListener() {
			public void dialogFieldChanged(DialogField field) {
				setPatternStatus(validate());
				updateStatusLine();
			}
		});
	}

	protected String getPattern() {
		return fPattern.getText();
	}

	private void createLabel(Composite parent, String content, int columns) {
		Label l = new Label(parent, SWT.None);
		l.setText(content);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = columns;
		l.setLayoutData(gd);
	}

	protected Control createDialogArea(Composite ancestor) {
		createDialogFields();
		Composite parent = (Composite) super.createDialogArea(ancestor);
		((GridLayout) parent.getLayout()).numColumns = 3;

		fPattern.doFillIntoGrid(parent, 3);
		fURI.doFillIntoGrid(parent, 3);
		fRegularExpression.doFillIntoGrid(parent, 3);
		createLabel(
				parent,
				"Then regular expression is used you can specify %s in uri. It will be replaced to guideline value",
				3);

		((GridData) fPattern.getTextControl(null).getLayoutData()).grabExcessHorizontalSpace = true;

		initializeFields();
		createFieldListeners();
		applyDialogFont(parent);
		return parent;
	}

	public void create() {
		super.create();
		fPattern.setFocus();
	}

	private void initializeFields() {
		if (fNode == null) {
			fPattern.setText(""); //$NON-NLS-1$
			fURI.setText("");
			fRegularExpression.setSelection(false);
		} else {
			fPattern.setText(fNode.getPattern());
			fURI.setText(fNode.getUri());
			fRegularExpression.setSelection(fNode.isRegexp());
		}
		setPatternStatus(validate());
		updateStatusLine();
	}

	private IStatus validate() {
		StatusInfo status = new StatusInfo();
		String pattern = fPattern.getText();
		String uri = fURI.getText();
		if (pattern == null || pattern.trim().length() == 0 || uri == null
				|| uri.trim().length() == 0) {
			status.setInfo("Please enter pattern and URI");
		} else {
			try {
				URI u = new URI(uri.replaceAll("%s", "s"));
			} catch (URISyntaxException e) {
				status.setError("Please specify correct URI");
			}
			if (ATSGuideManager.getInstance().isDuplicateName(pattern)
					&& (fNode == null || !pattern.equals(fNode.getPattern()))) {
				status.setError("Duplicate pattern");
			}
		}
		return status;
	}

	public void updateStatusLine() {
		IStatus max = null;
		for (int i = 0; i < fStati.length; i++) {
			IStatus curr = fStati[i];
			if (curr.matches(IStatus.ERROR)) {
				updateStatus(curr);
				return;
			}
			if (max == null || curr.getSeverity() > max.getSeverity()) {
				max = curr;
			}
		}
		updateStatus(max);
	}

	protected void okPressed() {
		doOkPressed();
		super.okPressed();
	}

	private void doOkPressed() {
		if (fNode == null) {
// fRequestor.validatorAdded(Validator);
			ATSGuideManager.getInstance().addGuide(fPattern.getText(),
					fURI.getText(), fRegularExpression.isSelected());
		}
		else {
			this.fNode.setPattern(this.fPattern.getText());
			this.fNode.setUri(this.fURI.getText());
			this.fNode.setRegexp(this.fRegularExpression.isSelected());
		}
		try {
			ATSGuideManager.getInstance().save();
		} catch (TransformerException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	private void setPatternStatus(IStatus status) {
		fStati[0] = status;
	}

	protected IStatus getSystemLibraryStatus() {
		return fStati[3];
	}

	public void setSystemLibraryStatus(IStatus status) {
		fStati[3] = status;
	}

	/**
	 * Updates the status of the ok button to reflect the given status.
	 * Subclasses may override this method to update additional buttons.
	 * 
	 * @param status
	 *            the status.
	 */
	protected void updateButtonsEnableState(IStatus status) {
		Button ok = getButton(IDialogConstants.OK_ID);
		if (ok != null && !ok.isDisposed())
			ok.setEnabled(status.getSeverity() == IStatus.OK);
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#setButtonLayoutData(org.eclipse.swt.widgets.Button)
	 */
	public void setButtonLayoutData(Button button) {
		super.setButtonLayoutData(button);
	}

	/**
	 * Returns the name of the section that this dialog stores its settings in
	 * 
	 * @return String
	 */
	protected String getDialogSettingsSectionName() {
		return "ADD_Guide_DIALOG_SECTION"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#getDialogBoundsSettings()
	 */
// protected IDialogSettings getDialogBoundsSettings() {
// IDialogSettings settings = ValidatorsUI.getDefault().getDialogSettings();
// IDialogSettings section =
// settings.getSection(getDialogSettingsSectionName());
// if (section == null) {
// section = settings.addNewSection(getDialogSettingsSectionName());
// }
// return section;
// }
}
