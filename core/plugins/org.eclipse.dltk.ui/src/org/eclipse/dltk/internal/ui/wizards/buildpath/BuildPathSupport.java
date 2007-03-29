/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;


/**
 *
 */
public class BuildPathSupport {
	
	public static final String InterpreterEnvironment_PREF_PAGE_ID= "org.eclipse.dltk.debug.ui.preferences.InterpreterPreferencePage"; //$NON-NLS-1$

	
	private BuildPathSupport() {
		super();
	}		
	
	private static class UpdatedBuildpathContainer implements IBuildpathContainer {

		private IBuildpathEntry[] fNewEntries;
		private IBuildpathContainer fOriginal;

		public UpdatedBuildpathContainer(IBuildpathContainer original, IBuildpathEntry[] newEntries) {
			fNewEntries= newEntries;
			fOriginal= original;
		}

		public IBuildpathEntry[] getBuildpathEntries() {
			return fNewEntries;
		}

		public String getDescription() {
			return fOriginal.getDescription();
		}

		public int getKind() {
			return fOriginal.getKind();
		}

		public IPath getPath() {
			return fOriginal.getPath();
		}

		public IBuiltinModuleProvider getBuiltinProvider() {
			return fOriginal.getBuiltinProvider();
		}
	}

	/**
	 * Apply a modified buildpath entry to the buildpath. The buildpath entry can also be from a buildpath container.
	 * @param shell If not null and the entry could not be found on the projects buildpath, a dialog will ask to put the entry on the buildpath
	 * @param newEntry The modified entry. The entry's kind or path must be unchanged.
	 * @param changedAttributes The attibutes that have changed. See {@link BPListElement} for constants values.
	 * @param jproject Project where the entry belongs to
	 * @param containerPath The path of the entry's parent container or <code>null</code> if the entry is not in a container
	 * @param monitor The progress monitor to use
	 * @throws CoreException
	 */
	public static void modifyBuildpathEntry(Shell shell, IBuildpathEntry newEntry, String[] changedAttributes, IDLTKProject jproject, IPath containerPath, IProgressMonitor monitor) throws CoreException {
		if (containerPath != null) {
			updateContainerBuildpath(jproject, containerPath, newEntry, changedAttributes, monitor);
		} else {
			updateProjectBuildpath(shell, jproject, newEntry, changedAttributes, monitor);
		}
	}
	
	
	/**
	 * Apply a modified buildpath entry to the buildpath. The buildpath entry can also be from a buildpath container.
	 * @param shell If not null and the entry could not be found on the projects buildpath, a dialog will ask to put the entry on the buildpath
	 * @param newEntry The modified entry. The entry's kind or path must be unchanged.
	 * @param jproject Project where the entry belongs to
	 * @param containerPath The path of the entry's parent container or <code>null</code> if the entry is not in a container
	 * @param monitor The progress monitor to use
	 * @throws CoreException
	 */
	public static void modifyBuildpathEntry(Shell shell, IBuildpathEntry newEntry, IDLTKProject jproject, IPath containerPath, IProgressMonitor monitor) throws CoreException {
		modifyBuildpathEntry(shell, newEntry, null, jproject, containerPath, monitor);
	}

	private static void updateContainerBuildpath(IDLTKProject jproject, IPath containerPath, IBuildpathEntry newEntry, String[] changedAttributes, IProgressMonitor monitor) throws CoreException {
		IBuildpathContainer container= DLTKCore.getBuildpathContainer(containerPath, jproject);
		if (container == null) {
			throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, IStatus.ERROR, "Container " + containerPath + " cannot be resolved", null));  //$NON-NLS-1$//$NON-NLS-2$
		}
		IBuildpathEntry[] entries= container.getBuildpathEntries();
		IBuildpathEntry[] newEntries= new IBuildpathEntry[entries.length];
		for (int i= 0; i < entries.length; i++) {
			IBuildpathEntry curr= entries[i];
			if (curr.getEntryKind() == newEntry.getEntryKind() && curr.getPath().equals(newEntry.getPath())) {
				newEntries[i]= getUpdatedEntry(curr, newEntry, changedAttributes, jproject);
			} else {
				newEntries[i]= curr;
			}
		}
		requestContainerUpdate(jproject, container, newEntries);
		monitor.worked(1);
	}

	private static IBuildpathEntry getUpdatedEntry(IBuildpathEntry currEntry, IBuildpathEntry updatedEntry, String[] updatedAttributes, IDLTKProject jproject) {
		if (updatedAttributes == null) {
			return updatedEntry; // used updated entry 'as is'
		}
		BPListElement currElem= BPListElement.createFromExisting(currEntry, jproject);
		BPListElement newElem= BPListElement.createFromExisting(updatedEntry, jproject);
		for (int i= 0; i < updatedAttributes.length; i++) {
			String attrib= updatedAttributes[i];
			currElem.setAttribute(attrib, newElem.getAttribute(attrib));
		}
		return currElem.getBuildpathEntry();
	}

	/**
	 * Request a container update.
	 * @param jproject The project of the container
	 * @param container The container to requesta  change to
	 * @param newEntries The updated entries
	 * @throws CoreException
	 */
	public static void requestContainerUpdate(IDLTKProject jproject, IBuildpathContainer container, IBuildpathEntry[] newEntries) throws CoreException {
		IPath containerPath= container.getPath();
		IBuildpathContainer updatedContainer= new UpdatedBuildpathContainer(container, newEntries);
		BuildpathContainerInitializer initializer= DLTKCore.getBuildpathContainerInitializer(containerPath.segment(0));
		if (initializer != null) {
			initializer.requestBuildpathContainerUpdate(containerPath, jproject, updatedContainer);
			if(DLTKCore.DEBUG) {
				System.err.println("BuildPathSupport: Add user library preference page container");
			//initializer.requestBuildpathContainerUpdate(containerPath, UserLibraryPreferencePage.getPlaceholderProject(), updatedContainer);
			//DLTKCore.setBuildpathContainer(containerPath, new IDLTKProject[] {jproject, UserLibraryPreferencePage.getPlaceholderProject()}, new IBuildpathContainer[] {updatedContainer, updatedContainer}, null); // force updating of containers, bug 62250
			}
		}
	}

	private static void updateProjectBuildpath(Shell shell, IDLTKProject jproject, IBuildpathEntry newEntry, String[] changedAttributes, IProgressMonitor monitor) throws ModelException {
		IBuildpathEntry[] oldBuildpath= jproject.getRawBuildpath();
		int nEntries= oldBuildpath.length;
		ArrayList newEntries= new ArrayList(nEntries + 1);
		int entryKind= newEntry.getEntryKind();
		IPath archivePath= newEntry.getPath();
		boolean found= false;
		for (int i= 0; i < nEntries; i++) {
			IBuildpathEntry curr= oldBuildpath[i];
			if (curr.getEntryKind() == entryKind && curr.getPath().equals(archivePath)) {
				// add modified entry
				newEntries.add(getUpdatedEntry(curr, newEntry, changedAttributes, jproject));
				found= true;
			} else {
				newEntries.add(curr);
			}
		}
		if (!found) {
			if (!putArchiveOnBuildpathDialog(shell)) {
				return;
			}
			// add new
			newEntries.add(newEntry);			
		}
		IBuildpathEntry[] newBuildpath= (IBuildpathEntry[]) newEntries.toArray(new IBuildpathEntry[newEntries.size()]);
		jproject.setRawBuildpath(newBuildpath, monitor);
	}
	
	private static boolean putArchiveOnBuildpathDialog(final Shell shell) {
		if (shell == null) {
			return false;
		}
		
		final boolean[] result= new boolean[1];
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
				String title= NewWizardMessages.BuildPathSupport_putoncpdialog_title; 
				String message= NewWizardMessages.BuildPathSupport_putoncpdialog_message; 
				result[0]= MessageDialog.openQuestion(shell, title, message);
			}
		});
		return result[0];
	}
}
