/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.internal.ui.wizards.IBuildpathContainerPage;
import org.eclipse.dltk.internal.ui.wizards.IBuildpathContainerPageExtension;
import org.eclipse.dltk.internal.ui.wizards.IBuildpathContainerPageExtension2;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;



/**
  */
public class BuildpathContainerWizard extends Wizard {

	private BuildpathContainerDescriptor fPageDesc;
	private IBuildpathEntry fEntryToEdit;

	private IBuildpathEntry[] fNewEntries;
	private IBuildpathContainerPage fContainerPage;
	private IDLTKProject fCurrProject;
	private IBuildpathEntry[] fCurrBuildpath;
	
	private BuildpathContainerSelectionPage fSelectionWizardPage;

	/**
	 * Constructor for BuildpathContainerWizard.
	 */
	public BuildpathContainerWizard(IBuildpathEntry entryToEdit, IDLTKProject currProject, IBuildpathEntry[] currEntries) {
		this(entryToEdit, null, currProject, currEntries);
	}
	
	/**
	 * Constructor for BuildpathContainerWizard.
	 */
	public BuildpathContainerWizard(BuildpathContainerDescriptor pageDesc, IDLTKProject currProject, IBuildpathEntry[] currEntries) {
		this(null, pageDesc, currProject, currEntries);	
	}

	private BuildpathContainerWizard(IBuildpathEntry entryToEdit, BuildpathContainerDescriptor pageDesc, IDLTKProject currProject, IBuildpathEntry[] currEntries) {
		fEntryToEdit= entryToEdit;
		fPageDesc= pageDesc;
		fNewEntries= null;
		
		fCurrProject= currProject;
		fCurrBuildpath= currEntries;
		
		String title;
		if (entryToEdit == null) {
			title= NewWizardMessages.BuildpathContainerWizard_new_title; 
		} else {
			title= NewWizardMessages.BuildpathContainerWizard_edit_title; 
		}
		setWindowTitle(title);
	}
	
	/**
	 * @deprecated use getNewEntries()
	 */
	public IBuildpathEntry getNewEntry() {
		IBuildpathEntry[] entries= getNewEntries();
		if (entries != null) {
			return entries[0];
		}
		return null;
	}
	
	public IBuildpathEntry[] getNewEntries() {
		return fNewEntries;
	}

	/* (non-Javadoc)
	 * @see IWizard#performFinish()
	 */
	public boolean performFinish() {
		if (fContainerPage != null) {
			if (fContainerPage.finish()) {
				if (fEntryToEdit == null && fContainerPage instanceof IBuildpathContainerPageExtension2) {
					fNewEntries= ((IBuildpathContainerPageExtension2) fContainerPage).getNewContainers();
				} else {
					IBuildpathEntry entry= fContainerPage.getSelection();
					fNewEntries= (entry != null) ? new IBuildpathEntry[] { entry } : null;
				}
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see IWizard#addPages()
	 */
	public void addPages() {
		if (fPageDesc != null) {
			fContainerPage= getContainerPage(fPageDesc);
			addPage(fContainerPage);			
		} else if (fEntryToEdit == null) { // new entry: show selection page as first page
			BuildpathContainerDescriptor[] containers= BuildpathContainerDescriptor.getDescriptors(fCurrProject);

			fSelectionWizardPage= new BuildpathContainerSelectionPage(containers);
			addPage(fSelectionWizardPage);

			// add as dummy, will not be shown
			fContainerPage= new BuildpathContainerDefaultPage();
			addPage(fContainerPage);
		} else { // fPageDesc == null && fEntryToEdit != null
			BuildpathContainerDescriptor[] containers= BuildpathContainerDescriptor.getDescriptors();
			BuildpathContainerDescriptor descriptor= findDescriptorPage(containers, fEntryToEdit);
			fContainerPage= getContainerPage(descriptor);
			addPage(fContainerPage);				
		}
		super.addPages();
	}
	
	private IBuildpathContainerPage getContainerPage(BuildpathContainerDescriptor pageDesc) {
		IBuildpathContainerPage containerPage= null;
		if (pageDesc != null) {
			IBuildpathContainerPage page= pageDesc.getPage();
			if (page != null) {
				return page; // if page is already created, avoid double initialization
			}
			try {
				containerPage= pageDesc.createPage();
			} catch (CoreException e) {
				handlePageCreationFailed(e);
				containerPage= null;
			}
		}

		if (containerPage == null)	{
			containerPage= new BuildpathContainerDefaultPage();
			if (pageDesc != null) {
				pageDesc.setPage(containerPage); // avoid creation next time
			}
		}

		if (containerPage instanceof IBuildpathContainerPageExtension) {
			((IBuildpathContainerPageExtension) containerPage).initialize(fCurrProject, fCurrBuildpath);
		}

		containerPage.setSelection(fEntryToEdit);
		containerPage.setWizard(this);
		return containerPage;
	}
	
	/* (non-Javadoc)
	 * @see IWizard#getNextPage(IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == fSelectionWizardPage) {

			BuildpathContainerDescriptor selected= fSelectionWizardPage.getSelected();
			fContainerPage= getContainerPage(selected);
			
			return fContainerPage;
		}
		return super.getNextPage(page);
	}
	
	private void handlePageCreationFailed(CoreException e) {
		String title= NewWizardMessages.BuildpathContainerWizard_pagecreationerror_title; 
		String message= NewWizardMessages.BuildpathContainerWizard_pagecreationerror_message; 
		ExceptionHandler.handle(e, getShell(), title, message);
	}
	
	
	private BuildpathContainerDescriptor findDescriptorPage(BuildpathContainerDescriptor[] containers, IBuildpathEntry entry) {
		for (int i = 0; i < containers.length; i++) {
			if (containers[i].canEdit(entry)) {
				return containers[i];
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#dispose()
	 */
	public void dispose() {
		if (fSelectionWizardPage != null) {
			BuildpathContainerDescriptor[] descriptors= fSelectionWizardPage.getContainers();
			for (int i= 0; i < descriptors.length; i++) {
				descriptors[i].dispose();
			}
		}
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see IWizard#canFinish()
	 */
	public boolean canFinish() {
		if (fSelectionWizardPage != null) {
			if (!fContainerPage.isPageComplete()) {
				return false;
			}
		}
		if (fContainerPage != null) {
			return fContainerPage.isPageComplete();
		}
		return false;
	}
	
	public static int openWizard(Shell shell, BuildpathContainerWizard wizard) {
		WizardDialog dialog= new WizardDialog(shell, wizard);
		PixelConverter converter= new PixelConverter(shell);
		dialog.setMinimumPageSize(converter.convertWidthInCharsToPixels(70), converter.convertHeightInCharsToPixels(20));
		dialog.create();
		return dialog.open();
	}
	
}
