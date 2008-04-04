/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * Wizard page to create a new package.
 * 
 * <p>
 * Note: This class is not intended to be subclassed, but clients can
 * instantiate. To implement a different kind of a new package wizard page,
 * extend <code>NewContainerWizardPage</code>.
 * </p>
 * 
	 *
 */
public abstract class NewPackageWizardPage extends NewContainerWizardPage {

	private static final String PAGE_NAME = "NewPackageWizardPage"; //$NON-NLS-1$
	private static final String PACKAGE = "NewPackageWizardPage.package"; //$NON-NLS-1$

	private StringDialogField fPackageDialogField;

	// Status of last validation of the package field
	private IStatus fPackageStatus;

	protected IScriptFolder fCreatedScriptFolder;

	public NewPackageWizardPage() {
		super(PAGE_NAME);

		setTitle(NewWizardMessages.NewPackageWizardPage_title);
		setDescription(NewWizardMessages.NewPackageWizardPage_description);

		fCreatedScriptFolder = null;

		fPackageDialogField = new StringDialogField();
		fPackageDialogField.setDialogFieldListener(new IDialogFieldListener() {
			public void dialogFieldChanged(DialogField field) {
				fPackageStatus = packageChanged();
				// tell all others
				handleFieldChanged(PACKAGE);
			}
		});
		fPackageDialogField
				.setLabelText(NewWizardMessages.NewPackageWizardPage_package_label);

		fPackageStatus = new StatusInfo();
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
		String name = ""; //$NON-NLS-1$
		if (element != null) {
			IScriptFolder sf = (IScriptFolder) element
					.getAncestor(IModelElement.SCRIPT_FOLDER);
			if (sf != null && !sf.isRootFolder())
				name = sf.getElementName();
		}
		setPackageText(name, true);
		updateStatus(new IStatus[] { containerStatus, fPackageStatus });
	}

	/*
	 * @see WizardPage#createControl
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		final int nColumns = 3;
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		GridLayout layout = new GridLayout();
		layout.numColumns = nColumns;
		composite.setLayout(layout);

		Label label = new Label(composite, SWT.WRAP);
		label.setText(NewWizardMessages.NewPackageWizardPage_info);		
		GridData gd = new GridData();		
		gd.widthHint = convertWidthInCharsToPixels(60);
		gd.horizontalSpan = 3;
		label.setLayoutData(gd);

		createContainerControls(composite, nColumns);
		createPackageControls(composite, nColumns);

		setControl(composite);
		
		Dialog.applyDialogFont(composite);
		
		//TODO: Add help support here
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
		// IScriptHelpContextIds.NEW_PACKAGE_WIZARD_PAGE);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			setFocus();
		}
	}

	/**
	 * Sets the focus to the package name input field.
	 */
	protected void setFocus() {
		fPackageDialogField.setFocus();
	}

	private void createPackageControls(Composite composite, int nColumns) {
		fPackageDialogField.doFillIntoGrid(composite, nColumns - 1);
		Text text = fPackageDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		LayoutUtil.setHorizontalGrabbing(text);
		DialogField.createEmptySpace(composite);

		TextFieldNavigationHandler.install(text);
	}
	
	protected void handleFieldChanged(String fieldName) {
		super.handleFieldChanged(fieldName);
		if (fieldName == CONTAINER) {
			fPackageStatus = packageChanged();
		}
		// do status line update
		updateStatus(new IStatus[] { containerStatus, fPackageStatus });
	}

	// ----------- validation ----------

	/*
	 * Verifies the input for the package field.
	 */
	private IStatus packageChanged() {
		StatusInfo status = new StatusInfo();
		String packName = getPackageText();
		if (packName.length() > 0) {
			if (DLTKCore.DEBUG) {
				System.err
						.println("TODO: Add correct package name checking here..."); //$NON-NLS-1$
			}
			// IStatus val= ScriptConventions.validatePackageName(packName);
			// if( packName.indexOf("\\") != -1 || packName.indexOf("/") != -1 )
			// {
			// //if (val.getSeverity() == IStatus.ERROR) {
			// status.setError(Messages.format(NewWizardMessages.NewPackageWizardPage_error_InvalidPackageName,
			// "Consist / of \\ symbols..." ));
			// return status;
			// }
			// else if (val.getSeverity() == IStatus.WARNING) {
			// status.setWarning(Messages.format(NewWizardMessages.NewPackageWizardPage_warning_DiscouragedPackageName,
			// val.getMessage()));
			// }
		} else {
			status
					.setError(NewWizardMessages.NewPackageWizardPage_error_EnterName);
			return status;
		}

		IProjectFragment root = getProjectFragment();
		if (root != null && root.getScriptProject().exists()) {
			IScriptFolder pack = root.getScriptFolder(packName);
			try {
//				IPath rootPath = root.getPath();
				if (pack.exists()) {
					if (pack.containsScriptResources() || !pack.hasSubfolders()) {
						status
								.setError(NewWizardMessages.NewPackageWizardPage_error_PackageExists);
					} else {
						status
								.setError(NewWizardMessages.NewPackageWizardPage_error_PackageNotShown);
					}
				} else {
					URI location = pack.getResource().getLocationURI();
					if (location != null) {
						IFileStore store = EFS.getStore(location);
						if (store.fetchInfo().exists()) {
							status
									.setError(NewWizardMessages.NewPackageWizardPage_error_PackageExistsDifferentCase);
						}
					}
				}
			} catch (CoreException e) {
				DLTKUIPlugin.log(e);
			}
		}
		return status;
	}

	/**
	 * Returns the content of the package input field.
	 * 
	 * @return the content of the package input field
	 */
	public String getPackageText() {
		return fPackageDialogField.getText();
	}

	/**
	 * Sets the content of the package input field to the given value.
	 * 
	 * @param str
	 *            the new package input field text
	 * @param canBeModified
	 *            if <code>true</code> the package input field can be
	 *            modified; otherwise it is read-only.
	 */
	public void setPackageText(String str, boolean canBeModified) {
		fPackageDialogField.setText(str);

		fPackageDialogField.setEnabled(canBeModified);
	}

	/**
	 * Returns the resource handle that corresponds to the element to was
	 * created or will be created.
	 * 
	 * @return A resource or null if the page contains illegal values.
	 *
	 */
	public IResource getModifiedResource() {
		IProjectFragment root = getProjectFragment();
		if (root != null) {
			return root.getScriptFolder(getPackageText()).getResource();
		}
		return null;
	}

	// ---- creation ----------------

	/**
	 * Returns a runnable that creates a package using the current settings.
	 * 
	 * @return the runnable that creates the new package
	 */
//	public IRunnableWithProgress getRunnable() {
//		return new IRunnableWithProgress() {
//			public void run(IProgressMonitor monitor)
//					throws InvocationTargetException, InterruptedException {
//				try {
//					createPackage(monitor);
//				} catch (CoreException e) {
//					throw new InvocationTargetException(e);
//				}
//			}
//		};
//	}

	/**
	 * Returns the created package fragment. This method only returns a valid
	 * value after <code>getRunnable</code> or <code>createPackage</code>
	 * have been executed.
	 * 
	 * @return the created package fragment
	 */
	public IScriptFolder getNewScriptFolder() {
		return fCreatedScriptFolder;
	}

	/**
	 * Creates the new package using the entered field values.
	 * 
	 * @param monitor
	 *            a progress monitor to report progress. The progress monitor
	 *            must not be <code>null</code>
	 * @throws CoreException
	 *             Thrown if creating the package failed.
	 * @throws InterruptedException
	 *             Thrown when the operation has been canceled.
	 *
	 */

	public void createPackage(IProgressMonitor monitor) throws CoreException,
			InterruptedException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		
		IProjectFragment root = getProjectFragment();
		String packName = getPackageText();
		fCreatedScriptFolder = root.createScriptFolder(packName, true,
				monitor);

		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}
	}

}
