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
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public abstract class NewSourceModulePage extends NewContainerWizardPage {

	private static final String FILE = "NewSourceModulePage.file"; //$NON-NLS-1$

	private IStatus sourceMoudleStatus;

	private IScriptFolder currentScriptFolder;

	private StringDialogField fileDialogField;

	private IStatus fileChanged() {
		StatusInfo status = new StatusInfo();

		if (getFileText().length() == 0) {
			status.setError(Messages.NewSourceModulePage_pathCannotBeEmpty);
		} else {
			if (currentScriptFolder != null) {
				ISourceModule module = currentScriptFolder
						.getSourceModule(getFileName());
				if (module.exists()) {
					status.setError(Messages.NewSourceModulePage_fileAlreadyExists);
				}
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
		fileDialogField.doFillIntoGrid(parent, nColumns - 1);
		Text text = fileDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		LayoutUtil.setHorizontalGrabbing(text);
		DialogField.createEmptySpace(parent);
	}

	public NewSourceModulePage() {
		super("wizardPage"); //$NON-NLS-1$
		setTitle(getPageTitle());
		setDescription(getPageDescription());

		sourceMoudleStatus = new StatusInfo();

		// fileDialogField
		fileDialogField = new StringDialogField();
		fileDialogField.setLabelText(Messages.NewSourceModulePage_file);
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
				currentScriptFolder = fragment.getScriptFolder(""); //$NON-NLS-1$
			else
				currentScriptFolder = null;
			sourceMoudleStatus = fileChanged();
		}

		updateStatus(new IStatus[] { containerStatus, sourceMoudleStatus });
	}

	public ISourceModule createFile(IProgressMonitor monitor)
			throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		String fileName = getFileName();

		final ISourceModule module = currentScriptFolder.createSourceModule(
				fileName, getFileContent(), true, monitor);

		if (module != null) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					try {
						EditorUtility.openInEditor(module);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

		return module;
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
	}

	protected String getFileText() {
		return fileDialogField.getText();
	}

	protected String getFileName() {
		final String fileText = getFileText();

		String[] extensions = getFileExtensions();
		for (int i = 0; i < extensions.length; ++i) {
			String extension = extensions[i];
			if (extension.length() > 0 && fileText.endsWith("." + extension)) { //$NON-NLS-1$
				return fileText;
			}
		}

		return fileText + "." + extensions[0]; //$NON-NLS-1$
	}

	// TODO: correct this
	protected String[] getFileExtensions() {
		try {
			String requiredNature = getRequiredNature();

			IDLTKLanguageToolkit toolkit = DLTKLanguageManager
					.getLanguageToolkit(requiredNature);
			String contentType = toolkit.getLanguageContentType();
			IContentTypeManager manager = Platform.getContentTypeManager();
			IContentType type = manager.getContentType(contentType);
			if (type != null) {
				String[] extensions = type.getFileSpecs(IContentType.FILE_EXTENSION_SPEC);
				return extensions;
			}
		} catch (CoreException e) {
		}

		return new String[] { "" }; //$NON-NLS-1$
	}

	protected IScriptFolder chooseScriptFolder() {
		ILabelProvider labelProvider = new ModelElementLabelProvider(
				ModelElementLabelProvider.SHOW_DEFAULT);

		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				getShell(), labelProvider);

		dialog.setIgnoreCase(false);
		dialog.setTitle(Messages.NewSourceModulePage_selectScriptFolder);
		dialog.setMessage(Messages.NewSourceModulePage_selectScriptFolder);
		dialog.setEmptyListMessage(Messages.NewSourceModulePage_noFoldersAvailable);

		IProjectFragment projectFragment = getProjectFragment();
		if (projectFragment != null) {
			try {
				dialog.setElements(projectFragment.getChildren());
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}

		dialog.setHelpAvailable(false);

		if (currentScriptFolder != null) {
			dialog.setInitialSelections(new Object[] { currentScriptFolder });
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

	protected abstract String getPageTitle();

	protected abstract String getPageDescription();

	protected String getFileContent() {
		return ""; //$NON-NLS-1$
	}
}
