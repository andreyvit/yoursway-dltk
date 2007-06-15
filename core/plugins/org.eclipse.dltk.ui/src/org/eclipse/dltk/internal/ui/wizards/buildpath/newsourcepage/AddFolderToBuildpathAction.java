/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BuildPathBasePage;
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


public class AddFolderToBuildpathAction extends Action implements ISelectionChangedListener {

	private final IWorkbenchSite fSite;
	private final List fSelectedElements; //IScriptProject || IPackageFrament || IFolder

	public AddFolderToBuildpathAction(IWorkbenchSite site) {
		super(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_AddSelSFToCP_label, DLTKPluginImages.DESC_OBJS_PACKFRAG_ROOT);
		setToolTipText(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_AddSelSFToCP_tooltip);
		fSite= site;
		fSelectedElements= new ArrayList();
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {

		final IScriptProject project;
		Object object= fSelectedElements.get(0);
		if (object instanceof IScriptProject) {
			project= (IScriptProject)object;
		} else if (object instanceof IScriptFolder) {
			project= ((IScriptFolder)object).getScriptProject();
		} else {
			IFolder folder= (IFolder)object;
			project= DLTKCore.create(folder.getProject());
			if (project == null)
				return;
		}

		final boolean removeProjectFromBuildpath;					
		if (fSelectedElements.size() == 1 && fSelectedElements.get(0) instanceof IScriptProject //if only the project should be added, then the query does not need to be executed 
				) {
			removeProjectFromBuildpath = true;
		} else {
			removeProjectFromBuildpath= false;				
		}

		try {
			final IRunnableWithProgress runnable= new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						List result= addToBuildpath(fSelectedElements, project, removeProjectFromBuildpath, monitor);
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

	private List addToBuildpath(List elements, IScriptProject project, boolean removeProjectFromBuildpath, IProgressMonitor monitor) throws OperationCanceledException, CoreException {
		if (!DLTKLanguageManager.hasScriptNature(project.getProject())) {
			StatusInfo rootStatus= new StatusInfo();
			rootStatus.setError(NewWizardMessages.BuildpathModifier_Error_NoNatures); 
			throw new CoreException(rootStatus);
		}
		
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, elements.size() + 4); 			
			
			monitor.worked(1);

			List existingEntries= BuildpathModifier.getExistingEntries(project);
			if (removeProjectFromBuildpath) {
				BuildpathModifier.removeFromBuildpath(project, existingEntries, new SubProgressMonitor(monitor, 1));
			} else {
				monitor.worked(1);
			}

			List newEntries= new ArrayList();
			for (int i= 0; i < elements.size(); i++) {
				Object element= elements.get(i);
				BPListElement entry;
				if (element instanceof IResource)
					entry= BuildpathModifier.addToBuildpath((IResource) element, existingEntries, newEntries, project, new SubProgressMonitor(monitor, 1));
				else
					entry= BuildpathModifier.addToBuildpath((IModelElement) element, existingEntries, newEntries, project, new SubProgressMonitor(monitor, 1));
				newEntries.add(entry);
			}

			Set modifiedSourceEntries= new HashSet();
			BuildPathBasePage.fixNestingConflicts((BPListElement[])newEntries.toArray(new BPListElement[newEntries.size()]), (BPListElement[])existingEntries.toArray(new BPListElement[existingEntries.size()]), modifiedSourceEntries);

			BuildpathModifier.setNewEntry(existingEntries, newEntries, project, new SubProgressMonitor(monitor, 1));

			BuildpathModifier.commitBuildPath(existingEntries, project, new SubProgressMonitor(monitor, 1));

			List result= new ArrayList();
			for (int i= 0; i < newEntries.size(); i++) {
				IBuildpathEntry entry= ((BPListElement) newEntries.get(i)).getBuildpathEntry();
				IModelElement root;
				if (entry.getPath().equals(project.getPath()))
					root= project;
				else
					root= project.findProjectFragment(entry.getPath());
				if (root != null) {
					result.add(root);
				}
			}

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
		if (elements.size() == 0)
			return false;
		try {
			fSelectedElements.clear();
			for (Iterator iter= elements.iterator(); iter.hasNext();) {
				Object element= iter.next();
				fSelectedElements.add(element);
				if (element instanceof IScriptProject) {
					if (BuildpathModifier.isSourceFolder((IScriptProject)element))
						return false;
				} else if (element instanceof IScriptFolder) {
					int type= DialogPackageExplorerActionGroup.getType(element, ((IScriptFolder)element).getScriptProject());
					if (type != DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT && type != DialogPackageExplorerActionGroup.INCLUDED_FOLDER)
						return false;
				} else if (element instanceof IFolder) {
					IProject project= ((IFolder)element).getProject();
					IScriptProject scriptProject= DLTKCore.create(project);
					if (scriptProject == null || !scriptProject.exists())
						return false;
				} else {
					return false;
				}
			}
			return true;
		} catch (CoreException e) {
		}
		return false;
	}

	private void showExceptionDialog(CoreException exception) {
		showError(exception, fSite.getShell(), NewWizardMessages.AddSourceFolderToBuildpathAction_ErrorTitle, exception.getMessage());
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
