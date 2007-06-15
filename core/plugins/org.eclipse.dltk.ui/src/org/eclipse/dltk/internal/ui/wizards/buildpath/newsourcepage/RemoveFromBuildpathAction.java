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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IRemoveLinkedFolderQuery;
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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;


public class RemoveFromBuildpathAction extends Action implements ISelectionChangedListener {
	private final IWorkbenchSite fSite;
	private List fSelectedElements; // IPackageFramgentRoot || IDLTKProject ||
									// BuildpathContainer iff isEnabled()

	public RemoveFromBuildpathAction(IWorkbenchSite site) {
		super(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_RemoveFromCP_label, DLTKPluginImages.DESC_ELCL_REMOVE_FROM_BP);
		setToolTipText(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_RemoveFromCP_tooltip);
		fSite = site;
		fSelectedElements = new ArrayList();
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		try {
			final IScriptProject project;
			Object object = fSelectedElements.get(0);
			if (object instanceof IScriptProject) {
				project = (IScriptProject) object;
			} else if (object instanceof IProjectFragment) {
				IProjectFragment root = (IProjectFragment) object;
				project = root.getScriptProject();
			} else {
				BuildPathContainer container = (BuildPathContainer) object;
				project = container.getScriptProject();
			}
			final List elementsToRemove = new ArrayList();
			final List foldersToDelete = new ArrayList();
			queryToRemoveLinkedFolders(elementsToRemove, foldersToDelete);
			final IRunnableWithProgress runnable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveFromBuildpath, elementsToRemove.size()
								+ foldersToDelete.size());
						List result = removeFromBuildpath(elementsToRemove, project, new SubProgressMonitor(monitor,
								elementsToRemove.size()));
						result.removeAll(foldersToDelete);
						deleteFolders(foldersToDelete, new SubProgressMonitor(monitor, foldersToDelete.size()));
						if (result.size() == 0)
							result.add(project);
						selectAndReveal(new StructuredSelection(result));
					} catch (CoreException e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}
			};
			PlatformUI.getWorkbench().getProgressService().run(true, false, runnable);
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof CoreException) {
				showExceptionDialog((CoreException) e.getCause());
			} else {
				DLTKUIPlugin.log(e);
			}
		} catch (InterruptedException e) {
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteFolders(List folders, IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveFromBuildpath, folders.size());
			for (Iterator iter = folders.iterator(); iter.hasNext();) {
				IFolder folder = (IFolder) iter.next();
				folder.delete(true, true, new SubProgressMonitor(monitor, 1));
			}
		} finally {
			monitor.done();
		}
	}

	private List removeFromBuildpath(List elements, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveFromBuildpath, elements.size() + 1);
			List existingEntries = BuildpathModifier.getExistingEntries(project);
			List result = new ArrayList();
			for (int i = 0; i < elements.size(); i++) {
				Object element = elements.get(i);
				if (element instanceof IScriptProject) {
					Object res = BuildpathModifier.removeFromBuildpath((IScriptProject) element, existingEntries, new SubProgressMonitor(
							monitor, 1));
					result.add(res);
				} else if (element instanceof IProjectFragment) {
					Object res = BuildpathModifier.removeFromBuildpath((IProjectFragment) element, existingEntries, project,
							new SubProgressMonitor(monitor, 1));
					if (res != null)
						result.add(res);
				} else {
					existingEntries.remove(BPListElement.createFromExisting(((BuildPathContainer) element).getBuildpathEntry(), project));
				}
			}
			BuildpathModifier.commitBuildPath(existingEntries, project, new SubProgressMonitor(monitor, 1));
			return result;
		} finally {
			monitor.done();
		}
	}

	private void queryToRemoveLinkedFolders(final List elementsToRemove, final List foldersToDelete) throws ModelException {
		final Shell shell = fSite.getShell() != null ? fSite.getShell() : DLTKUIPlugin.getActiveWorkbenchShell();
		for (Iterator iter = fSelectedElements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof IProjectFragment) {
				IFolder folder = getLinkedSourceFolder((IProjectFragment) element);
				if (folder != null) {
					RemoveLinkedFolderDialog dialog = new RemoveLinkedFolderDialog(shell, folder);
					final int result = dialog.open() == Window.OK ? dialog.getRemoveStatus() : IRemoveLinkedFolderQuery.REMOVE_CANCEL;
					if (result != IRemoveLinkedFolderQuery.REMOVE_CANCEL) {
						if (result == IRemoveLinkedFolderQuery.REMOVE_BUILD_PATH) {
							elementsToRemove.add(element);
						} else if (result == IRemoveLinkedFolderQuery.REMOVE_BUILD_PATH_AND_FOLDER) {
							elementsToRemove.add(element);
							foldersToDelete.add(folder);
						}
					}
				} else {
					elementsToRemove.add(element);
				}
			} else {
				elementsToRemove.add(element);
			}
		}
	}

	private IFolder getLinkedSourceFolder(IProjectFragment root) throws ModelException {
		if (root.getKind() != IProjectFragment.K_SOURCE)
			return null;
		final IResource resource = root.getCorrespondingResource();
		if (!(resource instanceof IFolder))
			return null;
		final IFolder folder = (IFolder) resource;
		if (!folder.isLinked())
			return null;
		return folder;
	}

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
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				Object element = iter.next();
				fSelectedElements.add(element);
				if (!(element instanceof IProjectFragment || element instanceof IScriptProject || element instanceof BuildPathContainer))
					return false;
				if (element instanceof IScriptProject) {
					IScriptProject project = (IScriptProject) element;
					if (!BuildpathModifier.isSourceFolder(project))
						return false;
				} else if (element instanceof IProjectFragment) {
					IBuildpathEntry entry = ((IProjectFragment) element).getRawBuildpathEntry();
					if (entry != null && entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
						return false;
					}
				}
			}
			return true;
		} catch (ModelException e) {
		}
		return false;
	}

	private void showExceptionDialog(CoreException exception) {
		showError(exception, fSite.getShell(), NewWizardMessages.RemoveFromBuildpathAction_ErrorTitle, exception.getMessage());
	}

	private void showError(CoreException e, Shell shell, String title, String message) {
		IStatus status = e.getStatus();
		if (status != null) {
			ErrorDialog.openError(shell, message, title, status);
		} else {
			MessageDialog.openError(shell, title, message);
		}
	}

	private void selectAndReveal(final ISelection selection) {
		// validate the input
		IWorkbenchPage page = fSite.getPage();
		if (page == null)
			return;
		// get all the view and editor parts
		List parts = new ArrayList();
		IWorkbenchPartReference refs[] = page.getViewReferences();
		for (int i = 0; i < refs.length; i++) {
			IWorkbenchPart part = refs[i].getPart(false);
			if (part != null)
				parts.add(part);
		}
		refs = page.getEditorReferences();
		for (int i = 0; i < refs.length; i++) {
			if (refs[i].getPart(false) != null)
				parts.add(refs[i].getPart(false));
		}
		Iterator itr = parts.iterator();
		while (itr.hasNext()) {
			IWorkbenchPart part = (IWorkbenchPart) itr.next();
			// get the part's ISetSelectionTarget implementation
			ISetSelectionTarget target = null;
			if (part instanceof ISetSelectionTarget)
				target = (ISetSelectionTarget) part;
			else
				target = (ISetSelectionTarget) part.getAdapter(ISetSelectionTarget.class);
			if (target != null) {
				// select and reveal resource
				final ISetSelectionTarget finalTarget = target;
				page.getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						finalTarget.selectReveal(selection);
					}
				});
			}
		}
	}
}
