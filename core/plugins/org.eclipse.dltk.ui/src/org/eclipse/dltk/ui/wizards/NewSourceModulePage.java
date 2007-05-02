/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


public abstract class NewSourceModulePage extends NewContainerWizardPage {

	private static final String FILE = "NewSourceModulePage.file";

	private IStatus sourceMoudleStatus;
	
	private IScriptFolder currScriptFolder;
	
	// Label + 'filename'
	private StringDialogField fileDialogField;

	private IStatus fileChanged() {
		StatusInfo status = new StatusInfo();

		String fileName = getFileName();
		
		if (fileName.length() == 0){
			status.setError("Cannot be empty"); 
		} else if (currScriptFolder != null){
			ISourceModule module = currScriptFolder.getSourceModule(fileName);
			if (module.exists()){
				status.setError("Module already exists");
			}			
		}
				
		return status;
	}
	
	/**
	 * The wizard owning this page is responsible for calling this method with
	 * the current selection. The selection is used to initialize the fields of
	 * the wizard page.
	 * 
	 * @param selection
	 *            used to initialize the fields
	 */
	public void init(IStructuredSelection selection) {
		IModelElement element = getInitialScriptElement(selection);

		initContainerPage(element);
			
		updateStatus(new IStatus[] { containerStatus, fileChanged() });
	}

	protected void createFileControls(Composite parent, int nColumns) {

		// fileDialogField
		fileDialogField.doFillIntoGrid(parent, nColumns - 1);
		Text text = fileDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		LayoutUtil.setHorizontalGrabbing(text);
		DialogField.createEmptySpace(parent);
	}

	/**
	 * @return page's title
	 */
	protected abstract String getPageTitle ();
	
	/**
	 * @return pages's description
	 */
	protected abstract String getPageDescription ();
	
	public NewSourceModulePage() {
		super("wizardPage");
		setTitle(getPageTitle());
		setDescription(getPageDescription());

		sourceMoudleStatus = new StatusInfo();

		// fileDialogField
		fileDialogField = new StringDialogField();
		fileDialogField.setLabelText("File: ");
		fileDialogField.setDialogFieldListener(new IDialogFieldListener() {
			public void dialogFieldChanged(DialogField field) {
				sourceMoudleStatus = fileChanged();
				handleFieldChanged(FILE);
			}
		});
	}

	protected void handleFieldChanged(String fieldName) {
		super.handleFieldChanged(fieldName);
		if (fieldName == CONTAINER) {
			IProjectFragment fragment = getProjectFragment(); 
			if (fragment != null)
				currScriptFolder = fragment.getScriptFolder("");			
			else
				currScriptFolder = null;
			sourceMoudleStatus = fileChanged();
		}
		// do status line update
		updateStatus(new IStatus[] { containerStatus, sourceMoudleStatus});
	}
	
	

	public void createFile(IProgressMonitor monitor) throws CoreException,
			InterruptedException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		String fileName = getFileName();
				
		ISourceModule module = currScriptFolder.createSourceModule(fileName, "", true, monitor);

		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}
		
		if (module != null)
			EditorUtility.openInEditor(module);
	}

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		final int nColumns = 3;

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		GridLayout layout = new GridLayout();
		layout.numColumns = nColumns;
		composite.setLayout(layout);

		createContainerControls(composite, nColumns);
		// createPackageControls(composite, nColumns);
		createFileControls(composite, nColumns);

		setControl(composite);

		Dialog.applyDialogFont(composite);

		// TODO: Add help support here
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
		// IScriptHelpContextIds.NEW_PACKAGE_WIZARD_PAGE);
	}

	public String getFileText() {
		return fileDialogField.getText();
	}
	
	protected String getFileExtension () {
		String[] exts;
		try {
			exts = DLTKLanguageManager.getLanguageToolkit(getRequiredNature()).getLanguageFileExtensions();
			if (exts != null && exts.length > 0)
				return exts[0];
		} catch (CoreException e) {
		}		
		return null;
	}
	
	public String getFileName() {
		String str = getFileText();
		if (str.indexOf('.') == -1)
			str += "." + getFileExtension ();
		return str;
	}
	
	protected abstract String getRequiredNature();
	
	protected IScriptFolder chooseScriptFolder() {
		ILabelProvider labelProvider = new ModelElementLabelProvider(
				ModelElementLabelProvider.SHOW_DEFAULT);
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				getShell(), labelProvider);

		dialog.setIgnoreCase(false);
		dialog.setTitle("Select Script Folder title");
		dialog.setMessage("Select Script Folder message");
		dialog.setEmptyListMessage("Empty List message");

		IProjectFragment f = getProjectFragment();
		if (f != null) {
			try {
				dialog.setElements(f.getChildren());
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		dialog.setHelpAvailable(false);

		if (currScriptFolder != null) {
			dialog.setInitialSelections(new Object[] { currScriptFolder });
		}

		if (dialog.open() == Window.OK) {
			Object element = dialog.getFirstResult();
			if (element instanceof IScriptFolder) {
				return (IScriptFolder) element;
			}
		}

		return null;
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			setFocus();
		}
	}
	
	protected void setFocus() {
		fileDialogField.setFocus();
	}
}
