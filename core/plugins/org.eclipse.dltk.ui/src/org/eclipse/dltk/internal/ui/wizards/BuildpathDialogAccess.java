/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards;

import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.internal.ui.IUIConstants;
import org.eclipse.dltk.internal.ui.wizards.buildpath.ArchiveFileFilter;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BuildpathContainerWizard;
import org.eclipse.dltk.internal.ui.wizards.buildpath.MultipleFolderSelectionDialog;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceSorter;


/**
 * Class that gives access to dialogs used by the script build path page to configure buildpath entries
 * and properties of buildpath entries.
 * Static methods are provided to show dialogs for:
 * <ul>
 *  <li> configuration of source attachments</li>
 *  <li> configuration of Javadoc locations</li>
 *  <li> configuration and selection of buildpath variable entries</li>
 *  <li> configuration and selection of buildpath container entries</li>
 *  <li> configuration and selection of JAR and external JAR entries</li>
 *  <li> selection of class and source folders</li>
 * </ul>
 * <p>
 * This class is not intended to be instantiated or subclassed by clients.
 * </p>
	 *
 */
public final class BuildpathDialogAccess {

	private BuildpathDialogAccess() {
		// do not instantiate
	}	
	
	/**
	 * Shows the UI to configure a buildpath container buildpath entry. See {@link IBuildpathEntry#BPE_CONTAINER} for
	 * details about container buildpath entries.
	 * The dialog returns the configured buildpath entry or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @param initialEntry The initial buildpath container entry.
	 * @param project The project the entry belongs to. The project does not have to exist and can also be <code>null</code>.
	 * @param currentBuildpath The class path entries currently selected to be set as the projects buildpath. This can also
	 * include the entry to be edited. The dialog uses these entries as information only (e.g. to avoid duplicate entries); The user still can make changes after the
	 * the buildpath container dialog has been closed. See {@link IBuildpathContainerPageExtension} for
	 * more information.
	 * @return Returns the configured buildpath container entry or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IBuildpathEntry configureContainerEntry(Shell shell, IBuildpathEntry initialEntry, IDLTKProject project, IBuildpathEntry[] currentBuildpath) {
		if (initialEntry == null || currentBuildpath == null) {
			throw new IllegalArgumentException();
		}
		
		BuildpathContainerWizard wizard= new BuildpathContainerWizard(initialEntry, project, currentBuildpath);
		if (BuildpathContainerWizard.openWizard(shell, wizard) == Window.OK) {
			IBuildpathEntry[] created= wizard.getNewEntries();
			if (created != null && created.length == 1) {
				return created[0];
			}
		}
		return null;
	}
	
	/**
	 * Shows the UI to choose new buildpath container buildpath entries. See {@link IBuildpathEntry#BPE_CONTAINER} for
	 * details about container buildpath entries.
	 * The dialog returns the selected buildpath entries or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @param project The project the entry belongs to. The project does not have to exist and
	 * can also be <code>null</code>.
	 * @param currentBuildpath The class path entries currently selected to be set as the projects buildpath. This can also
	 * include the entry to be edited. The dialog uses these entries as information only; The user still can make changes after the
	 * the buildpath container dialog has been closed. See {@link IBuildpathContainerPageExtension} for
	 * more information.
	 * @return Returns the selected buildpath container entries or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IBuildpathEntry[] chooseContainerEntries(Shell shell, IDLTKProject project, IBuildpathEntry[] currentBuildpath) {
		if (currentBuildpath == null) {
			throw new IllegalArgumentException();
		}
		
		BuildpathContainerWizard wizard= new BuildpathContainerWizard((IBuildpathEntry) null, project, currentBuildpath);
		if (BuildpathContainerWizard.openWizard(shell, wizard) == Window.OK) {
			return wizard.getNewEntries();
		}
		return null;
	}
	
	
	/**
	 * Shows the UI to configure a JAR or ZIP archive located in the workspace.
	 * The dialog returns the configured buildpath entry path or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @param initialEntry The path of the initial archive entry 
	 * @param usedEntries An array of paths that are already on the buildpath and therefore should not be
	 * selected again.
	 * @return Returns the configured buildpath container entry path or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IPath configureArchiveEntry(Shell shell, IPath initialEntry, IPath[] usedEntries) {
		if (initialEntry == null || usedEntries == null) {
			throw new IllegalArgumentException();
		}
		
		Class[] acceptedClasses= new Class[] { IFile.class };
		TypedElementSelectionValidator validator= new TypedElementSelectionValidator(acceptedClasses, false);
		
		ArrayList usedJars= new ArrayList(usedEntries.length);
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		for (int i= 0; i < usedEntries.length; i++) {
			IPath curr= usedEntries[i];
			if (!curr.equals(initialEntry)) {
				IResource resource= root.findMember(usedEntries[i]);
				if (resource instanceof IFile) {
					usedJars.add(resource);
				}
			}
		}
		
		IResource existing= root.findMember(initialEntry);
		
		ElementTreeSelectionDialog dialog= new ElementTreeSelectionDialog(shell, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		dialog.setValidator(validator);
		dialog.setTitle(NewWizardMessages.BuildPathDialogAccess_ZIPArchiveDialog_edit_title); 
		dialog.setMessage(NewWizardMessages.BuildPathDialogAccess_ZIPArchiveDialog_edit_description); 
		dialog.addFilter(new ArchiveFileFilter(usedJars, true));
		dialog.setInput(root);
		dialog.setSorter(new ResourceSorter(ResourceSorter.NAME));
		dialog.setInitialSelection(existing);

		if (dialog.open() == Window.OK) {
			IResource element= (IResource) dialog.getFirstResult();
			return element.getFullPath();
		}
		return null;
	}
	
	/**
	 * Shows the UI to select new JAR or ZIP archive entries located in the workspace.
	 * The dialog returns the selected entries or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @param initialSelection The path of the element (container or archive) to initially select or <code>null</code> to not select an entry. 
	 * @param usedEntries An array of paths that are already on the buildpath and therefore should not be
	 * selected again.
	 * @return Returns the new buildpath container entry paths or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IPath[] chooseArchiveEntries(Shell shell, IPath initialSelection, IPath[] usedEntries) {
		if (usedEntries == null) {
			throw new IllegalArgumentException();
		}
		
		Class[] acceptedClasses= new Class[] { IFile.class };
		TypedElementSelectionValidator validator= new TypedElementSelectionValidator(acceptedClasses, true);
		ArrayList usedJars= new ArrayList(usedEntries.length);
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		for (int i= 0; i < usedEntries.length; i++) {
			IResource resource= root.findMember(usedEntries[i]);
			if (resource instanceof IFile) {
				usedJars.add(resource);
			}
		}
		IResource focus= initialSelection != null ? root.findMember(initialSelection) : null;
		
		ElementTreeSelectionDialog dialog= new ElementTreeSelectionDialog(shell, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		dialog.setHelpAvailable(false);
		dialog.setValidator(validator);
		dialog.setTitle(NewWizardMessages.BuildPathDialogAccess_ZIPArchiveDialog_new_title); 
		dialog.setMessage(NewWizardMessages.BuildPathDialogAccess_ZIPArchiveDialog_new_description); 
		dialog.addFilter(new ArchiveFileFilter(usedJars, true));
		dialog.setInput(root);
		dialog.setSorter(new ResourceSorter(ResourceSorter.NAME));
		dialog.setInitialSelection(focus);

		if (dialog.open() == Window.OK) {
			Object[] elements= dialog.getResult();
			IPath[] res= new IPath[elements.length];
			for (int i= 0; i < res.length; i++) {
				IResource elem= (IResource)elements[i];
				res[i]= elem.getFullPath();
			}
			return res;
		}
		return null;
	}
	
	/**
	 * Shows the UI to configure an external JAR or ZIP archive.
	 * The dialog returns the configured or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @param initialEntry The path of the initial archive entry.
	 * @return Returns the configured buildpath container entry path or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IPath configureExternalArchiveEntry(Shell shell, IPath initialEntry) {
		if (initialEntry == null) {
			throw new IllegalArgumentException();
		}
		
		String lastUsedPath= initialEntry.removeLastSegments(1).toOSString();
		
		FileDialog dialog= new FileDialog(shell, SWT.SINGLE);
		dialog.setText(NewWizardMessages.BuildPathDialogAccess_ExtZIPArchiveDialog_edit_title); 
		dialog.setFilterExtensions(ArchiveFileFilter.FILTER_EXTENSIONS);
		dialog.setFilterPath(lastUsedPath);
		dialog.setFileName(initialEntry.lastSegment());
		
		String res= dialog.open();
		if (res == null) {
			return null;
		}
		DLTKUIPlugin.getDefault().getDialogSettings().put(IUIConstants.DIALOGSTORE_LASTEXTZIP, dialog.getFilterPath());

		return Path.fromOSString(res).makeAbsolute();	
	}
	
	/**
	 * Shows the UI to select new external JAR or ZIP archive entries.
	 * The dialog returns the selected entry paths or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @return Returns the new buildpath container entry paths or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IPath[] chooseExternalArchiveEntries(Shell shell) {
		String lastUsedPath= DLTKUIPlugin.getDefault().getDialogSettings().get(IUIConstants.DIALOGSTORE_LASTEXTZIP);
		if (lastUsedPath == null) {
			lastUsedPath= ""; //$NON-NLS-1$
		}
		FileDialog dialog= new FileDialog(shell, SWT.MULTI);
		dialog.setText(NewWizardMessages.BuildPathDialogAccess_ExtZIPArchiveDialog_new_title); 
		dialog.setFilterExtensions(ArchiveFileFilter.FILTER_EXTENSIONS);
		dialog.setFilterPath(lastUsedPath);
		
		String res= dialog.open();
		if (res == null) {
			return null;
		}
		String[] fileNames= dialog.getFileNames();
		int nChosen= fileNames.length;
			
		IPath filterPath= Path.fromOSString(dialog.getFilterPath());
		IPath[] elems= new IPath[nChosen];
		for (int i= 0; i < nChosen; i++) {
			elems[i]= filterPath.append(fileNames[i]).makeAbsolute();	
		}
		DLTKUIPlugin.getDefault().getDialogSettings().put(IUIConstants.DIALOGSTORE_LASTEXTZIP, dialog.getFilterPath());
		
		return elems;
	}
		
	/**
	 * Shows the UI to select new class folders.
	 * The dialog returns the selected buildpath entry paths or <code>null</code> if the dialog has
	 * been canceled. The dialog does not apply any changes.
	 * 
	 * @param shell The parent shell for the dialog.
	 * @param initialSelection The path of the element to initially select or <code>null</code>.
	 * @param usedEntries An array of paths that are already on the buildpath and therefore should not be
	 * selected again.
	 * @return Returns the configured buildpath container entry path or <code>null</code> if the dialog has
	 * been canceled by the user.
	 */
	public static IPath[] chooseSourceFolderEntries(Shell shell, IPath initialSelection, IPath[] usedEntries) {
		if (usedEntries == null) {
			throw new IllegalArgumentException();
		}
		String title= NewWizardMessages.BuildPathDialogAccess_ExistingClassFolderDialog_new_title; 
		String message= NewWizardMessages.BuildPathDialogAccess_ExistingClassFolderDialog_new_description; 
		return internalChooseFolderEntry(shell, initialSelection, usedEntries, title, message);
	}
	
	public static IPath[] chooseExtSourceFolderEntries(Shell shell, IPath initialSelection, IPath[] usedEntries) {
		if (usedEntries == null) {
			throw new IllegalArgumentException();
		}
		String title= NewWizardMessages.BuildPathDialogAccess_ExistingClassFolderDialog_new_title; 
		String message= NewWizardMessages.BuildPathDialogAccess_ExistingClassFolderDialog_new_description;
		return internalExtChooseFolderEntry(shell, initialSelection, usedEntries, title, message);
	}
	
		
	private static IPath[] internalChooseFolderEntry(Shell shell, IPath initialSelection, IPath[] usedEntries, String title, String message) {	
		Class[] acceptedClasses= new Class[] { IProject.class, IFolder.class };

		ArrayList usedContainers= new ArrayList(usedEntries.length);
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		for (int i= 0; i < usedEntries.length; i++) {
			IResource resource= root.findMember(usedEntries[i]);
			if (resource instanceof IContainer) {
				usedContainers.add(resource);
			}
		}
		
		IResource focus= initialSelection != null ? root.findMember(initialSelection) : null;
		Object[] used= usedContainers.toArray();
		
		MultipleFolderSelectionDialog dialog= new MultipleFolderSelectionDialog(shell, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		dialog.setExisting(used);
		dialog.setTitle(title); 
		dialog.setMessage(message); 
		dialog.setHelpAvailable(false);
		dialog.addFilter(new TypedViewerFilter(acceptedClasses, used));
		dialog.setInput(root);
		dialog.setInitialFocus(focus);
		
		if (dialog.open() == Window.OK) {
			Object[] elements= dialog.getResult();
			IPath[] res= new IPath[elements.length];
			for (int i= 0; i < res.length; i++) {
				IResource elem= (IResource) elements[i];
				res[i]= elem.getFullPath();
			}
			return res;
		}
		return null;		
	}	
	private static IPath[] internalExtChooseFolderEntry(Shell shell, IPath initialSelection, IPath[] usedEntries, String title, String message) {	
		String lastUsedPath= DLTKUIPlugin.getDefault().getDialogSettings().get(IUIConstants.DIALOGSTORE_LASTEXTSOURCE);
		if (lastUsedPath == null) {
			lastUsedPath= ""; //$NON-NLS-1$
		}
		DirectoryDialog dialog= new DirectoryDialog(shell, SWT.SINGLE);
		dialog.setText(NewWizardMessages.BuildPathDialogAccess_ExistingClassFolderDialog_new_title); 		
		dialog.setFilterPath(lastUsedPath);
		
		String res= dialog.open();
		if (res == null) {
			return null;
		}		
					
		IPath[] elems= new IPath[1];
		elems[0]= new Path(res).makeAbsolute();	
		
		DLTKUIPlugin.getDefault().getDialogSettings().put(IUIConstants.DIALOGSTORE_LASTEXTSOURCE, dialog.getFilterPath());
		
		return elems;
	}	
}
