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
package org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;


public class AddSelectedLibraryToBuildpathAction extends Action implements ISelectionChangedListener {

	private final IWorkbenchSite fSite;
	private IFile[] fSelectedElements;

	public AddSelectedLibraryToBuildpathAction(IWorkbenchSite site) {
		super(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_AddSelLibToCP_label, DLTKPluginImages.DESC_OBJS_EXTJAR);
		setToolTipText(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_AddSelLibToCP_tooltip);
		fSite= site;
		fSelectedElements= null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void run() {
		try {
			final IFile[] files= fSelectedElements;
			if (files == null) {
				return;
			}
			
			final IRunnableWithProgress runnable= new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
				        IDLTKProject project= DLTKCore.create(files[0].getProject());
				        List result= addLibraryEntries(files, project, monitor);
						selectAndReveal(new StructuredSelection(result));
					} catch (CoreException e) {
						throw new InvocationTargetException(e);
					}
				}
			};
			PlatformUI.getWorkbench().getProgressService().run(true, false, runnable);
		} catch (final InvocationTargetException e) {
			if (e.getCause() instanceof CoreException) {
				showExceptionDialog((CoreException)e.getCause());
			} else {
				DLTKUIPlugin.log(e);
			}
		} catch (final InterruptedException e) {
		}
	}
	
	private List addLibraryEntries(IFile[] resources, IDLTKProject project, IProgressMonitor monitor) throws CoreException {
		List addedEntries= new ArrayList();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 4); 
			for (int i= 0; i < resources.length; i++) {
				IResource res= resources[i];
				addedEntries.add(new BPListElement(project, IBuildpathEntry.BPE_LIBRARY, res.getFullPath(), res, false));
			}
			monitor.worked(1);
			
			List existingEntries= BuildpathModifier.getExistingEntries(project);
			BuildpathModifier.setNewEntry(existingEntries, addedEntries, project, new SubProgressMonitor(monitor, 1));
			BuildpathModifier.commitBuildPath(existingEntries, project, new SubProgressMonitor(monitor, 1));

			List result= new ArrayList(addedEntries.size());
			for (int i= 0; i < resources.length; i++) {
				IResource res= resources[i];
				IModelElement elem= project.getProjectFragment(res);
				if (elem != null) {
					result.add(elem);
				}
			}
					
			monitor.worked(1);
			return result;
		} finally {
			monitor.done();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(final SelectionChangedEvent event) {
		final ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			setEnabled(canHandle((IStructuredSelection) selection));
		} else {
			setEnabled(canHandle(StructuredSelection.EMPTY));
		}
	}

	private boolean canHandle(IStructuredSelection elements) {
		fSelectedElements= getSelectedResources(elements);
		return fSelectedElements != null;
	}
	
	private IFile[] getSelectedResources(IStructuredSelection elements) {
		if (elements.size() == 0)
			return null;
		
		ArrayList res= new ArrayList();
		try {
			for (Iterator iter= elements.iterator(); iter.hasNext();) {
				Object element= iter.next();
				if (element instanceof IFile) {
					IFile file= (IFile)element;
					IDLTKProject project= DLTKCore.create(file.getProject());
					if (project == null)
						return null;
					
					if (!BuildpathModifier.isArchive(file, project))
						return null;
				} else {
					return null;
				}
				res.add(element);
			}
			return (IFile[]) res.toArray(new IFile[res.size()]);
		} catch (CoreException e) {
		}
		return null;
	}
	
	
	private void showExceptionDialog(CoreException exception) {
		showError(exception, fSite.getShell(), NewWizardMessages.AddSelectedLibraryToBuildpathAction_ErrorTitle, exception.getMessage());
	}
	
	private void showError(CoreException e, Shell shell, String title, String message) {
		IStatus status= e.getStatus();
		if (status != null) {
			ErrorDialog.openError(shell, message, title, status);
		} else {
			MessageDialog.openError(shell, title, message);
		}
	}

	private void selectAndReveal(final ISelection selection) {
		// validate the input
		IWorkbenchPage page= fSite.getPage();
		if (page == null)
			return;

		// get all the view and editor parts
		List parts= new ArrayList();
		IWorkbenchPartReference refs[]= page.getViewReferences();
		for (int i= 0; i < refs.length; i++) {
			IWorkbenchPart part= refs[i].getPart(false);
			if (part != null)
				parts.add(part);
		}
		refs= page.getEditorReferences();
		for (int i= 0; i < refs.length; i++) {
			if (refs[i].getPart(false) != null)
				parts.add(refs[i].getPart(false));
		}

		Iterator itr= parts.iterator();
		while (itr.hasNext()) {
			IWorkbenchPart part= (IWorkbenchPart) itr.next();

			// get the part's ISetSelectionTarget implementation
			ISetSelectionTarget target= null;
			if (part instanceof ISetSelectionTarget)
				target= (ISetSelectionTarget) part;
			else
				target= (ISetSelectionTarget) part.getAdapter(ISetSelectionTarget.class);

			if (target != null) {
				// select and reveal resource
				final ISetSelectionTarget finalTarget= target;
				page.getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						finalTarget.selectReveal(selection);
					}
				});
			}
		}
	}

}
