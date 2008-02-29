/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.buildpath;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.ArchiveFileFilter;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElementAttribute;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BuildPathBasePage;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddArchivesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddLibrariesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ICreateFolderQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IInclusionExclusionQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ILinkToQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IRemoveLinkedFolderQuery;


public class BuildpathModifier {

	/**
	 * Interface for listeners that want to receive a notification about 
	 * changes on <code>IBuildpathEntry</code>. For example, if a source 
	 * folder changes one of it's inclusion/exclusion filters, then 
	 * this event will be fired.
	 */
	public static interface IBuildpathModifierListener {
		/**
		 * The new build path entry that was generated upon calling a method of 
		 * <code>BuildpathModifier</code>. The type indicates which kind of 
		 * interaction was executed on this entry.
		 * 
		 * Note that the list does not contain elements of type 
		 * <code>IBuildpathEntry</code>, but <code>BPListElement</code>
		 * 
		 * @param newEntries list of <code>BPListElement</code>
		 */
		public void buildpathEntryChanged(List newEntries);
	}

	private IBuildpathModifierListener fListener;

	public BuildpathModifier() {
		this(null);
	}

	protected BuildpathModifier(IBuildpathModifierListener listener) {
		fListener= listener;
	}

	/**
	 * Create a linked source folder.
	 * 
	 * @param query a query to create a linked source folder
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return a list containing a <code>IProjectFragment</code> representing 
	 * the linked source folder
	 * @throws CoreException
	 */
	protected List createLinkedSourceFolder(ILinkToQuery query, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		if (query.doQuery()) {
			IFolder folder= query.getCreatedFolder();
			if (folder != null) {
				List folderList= new ArrayList();
				folderList.add(folder);
				List root= addToBuildpath(folderList, project, monitor);
				if (root.size() == 0)
					folder.delete(false, null);
				return root;
			}
		}
		return new ArrayList();
	}

	/**
	 * Create a folder given a <code>FolderCreationQuery</code>.
	 * The query does only have to handle the creation of the folder,
	 * filter manipulations are handlet by the <code>
	 * Buildpathmodifier</code> itself using the return value
	 * of <code>FolderCreationQuery.getCreatedFolder()</code>.
	 * 
	 * @param folderQuery query to create the new folder
	 * @param outputQuery query to get information about whether the project should be 
	 * removed as source folder and update build folder to <code>outputLocation</code>
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return a list containing the created object (either of type <code>IResource</code>
	 * of <code>IModelElement</code>, or an empty list if no folder was created 
	 * (e.g. the operation was cancelled).
	 * @throws CoreException 
	 * @throws OperationCanceledException 
	 * @see BuildpathModifierQueries.ICreateFolderQuery
	 * @see BuildpathModifierQueries.OutputFolderQuery
	 */
	protected List createFolder(ICreateFolderQuery folderQuery, IScriptProject project, IProgressMonitor monitor) throws OperationCanceledException, CoreException {
		if (folderQuery.doQuery()) {
			IFolder folder= folderQuery.getCreatedFolder();
			if (folder != null) {
				List folderList= new ArrayList();
				folderList.add(folder);
				if (folderQuery.isSourceFolder()) {
					List root= addToBuildpath(folderList, project, monitor);
					if (root.size() == 0)
						folder.delete(false, null);
					return root;
				} else {
					List entries= getExistingEntries(project);
					exclude(folder.getFullPath(), entries, new ArrayList(), project, monitor);
					updateBuildpath(entries, project, null);
				}
				return folderList;
			}
		}
		return new ArrayList();
	}

	/**
	 * Add a list of elements to the build path.
	 * 
	 * @param elements a list of elements to be added to the build path. An element 
	 * must either be of type <code>IFolder</code>, <code>IModelElement</code> or 
	 * <code>IFile</code> (only allowed if the file is a .zip file!).
	 * @param project the script project
	 * @param query for information about whether the project should be removed as
	 * source folder and update build folder
	 * @param monitor progress monitor, can be <code>null</code> 
	 * @return returns a list of elements of type <code>IProjectFragment</code> or 
	 * <code>IScriptProject</code> that have been added to the build path or an 
	 * empty list if the operation was aborted
	 * @throws CoreException 
	 * @throws OperationCanceledException 
	 * @see BuildpathModifierQueries.OutputFolderQuery
	 */
	protected List addToBuildpath(List elements, IScriptProject project, IProgressMonitor monitor) throws OperationCanceledException, CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 2 * elements.size() + 3); 
			//IWorkspaceRoot workspaceRoot= DLTKUIPlugin.getWorkspace().getRoot();

			if (DLTKLanguageManager.hasScriptNature(project.getProject())) {
				//IPath outputLocation= project.getOutputLocation();
				//IPath projPath= project.getProject().getFullPath();
				List existingEntries= getExistingEntries(project);				

				List newEntries= new ArrayList();
				for (int i= 0; i < elements.size(); i++) {
					Object element= elements.get(i);
					BPListElement entry;
					if (element instanceof IResource)
						entry= addToBuildpath((IResource) element, existingEntries, newEntries, project, monitor);
					else
						entry= addToBuildpath((IModelElement) element, existingEntries, newEntries, project, monitor);
					newEntries.add(entry);
				}
				
				Set modifiedSourceEntries= new HashSet();
				BuildPathBasePage.fixNestingConflicts((BPListElement[])newEntries.toArray(new BPListElement[newEntries.size()]), (BPListElement[])existingEntries.toArray(new BPListElement[existingEntries.size()]), modifiedSourceEntries);

				setNewEntry(existingEntries, newEntries, project, new SubProgressMonitor(monitor, 1));

				updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 1));

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
			} else {
				StatusInfo rootStatus= new StatusInfo();
				rootStatus.setError(NewWizardMessages.BuildpathModifier_Error_NoNatures); 
				throw new CoreException(rootStatus);
			}
		} finally {
			monitor.done();
		}
	}

	/**
	 * Add external archives (.zip files) to the buildpath. The 
	 * method uses the query to find out which entries need to be added. 
	 * 
	 * @param query the query to get the information which entries need to be added
	 * @param project the script project 
	 * @param monitor progress monitor, can be <code>null</code> 
	 * @return a list of <code>IProjectFragment</code>s representing the added 
	 * archives or an empty list if no element was added.
	 * @throws CoreException
	 * 
	 * @see IAddArchivesQuery
	 */
	protected List addExternalArchives(IAddArchivesQuery query, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		IPath[] selected= query.doQuery();
		List addedEntries= new ArrayList();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 4); 
			if (selected != null) {
				for (int i= 0; i < selected.length; i++) {
					addedEntries.add(new BPListElement(project, IBuildpathEntry.BPE_LIBRARY, selected[i], null, false));
				}
				monitor.worked(1);

				List existingEntries= getExistingEntries(project);
				setNewEntry(existingEntries, addedEntries, project, new SubProgressMonitor(monitor, 1));
				updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 1));

				List result= new ArrayList(addedEntries.size());
				for (int i= 0; i < addedEntries.size(); i++) {
					IBuildpathEntry entry= ((BPListElement) addedEntries.get(i)).getBuildpathEntry();
					IModelElement elem= project.findProjectFragment(entry.getPath());
					if (elem != null) {
						result.add(elem);
					}
				}
				monitor.worked(1);
				return result;
			}
		} finally {
			monitor.done();
		}
		return new ArrayList();
	}

	/**
	 * Add libraries to the buildpath. The 
	 * method uses the query to find out which entries need to be added. 
	 * 
	 * @param query the query to get the information which entries need to be added
	 * @param project the script project 
	 * @param monitor progress monitor, can be <code>null</code> 
	 * @return a list of <code>BuildpathContainer</code>s representing the added 
	 * archives or an empty list if no element was added.
	 * @throws CoreException
	 * 
	 * @see IAddArchivesQuery
	 */
	protected List addLibraries(IAddLibrariesQuery query, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		IBuildpathEntry[] selected= query.doQuery(project, project.getRawBuildpath());
		List addedEntries= new ArrayList();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 4); 
			if (selected != null) {
				for (int i= 0; i < selected.length; i++) {
					addedEntries.add(new BPListElement(project, IBuildpathEntry.BPE_CONTAINER, selected[i].getPath(), null, false));
				}
				monitor.worked(1);

				List existingEntries= getExistingEntries(project);
				setNewEntry(existingEntries, addedEntries, project, new SubProgressMonitor(monitor, 1));
				updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 1));

				List result= new ArrayList(addedEntries.size());
				for (int i= 0; i < addedEntries.size(); i++) {
					result.add(new BuildPathContainer(project, selected[i]));
				}
				monitor.worked(1);
				return result;
			}
		} finally {
			monitor.done();
		}
		return new ArrayList();
	}
	
	protected List addLibraryEntries(List resources, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		List addedEntries= new ArrayList();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 4); 
			for (int i= 0; i < resources.size(); i++) {
				IResource res= (IResource) resources.get(i);
				addedEntries.add(new BPListElement(project, IBuildpathEntry.BPE_LIBRARY, res.getFullPath(), res, false));
			}
			monitor.worked(1);
			
			List existingEntries= getExistingEntries(project);
			setNewEntry(existingEntries, addedEntries, project, new SubProgressMonitor(monitor, 1));
			updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 1));

			List result= new ArrayList(addedEntries.size());
			for (int i= 0; i < resources.size(); i++) {
				IResource res= (IResource) resources.get(i);
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
	 * Remove a list of elements to the build path.
	 * 
	 * @param query query to remove unused linked folders from the project
	 * @param elements a list of elements to be removed from the build path. An element 
	 * must either be of type <code>IScriptProject</code>, <code>IProjectFragment</code> or 
	 * <code>BuildPathContainer</code>
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code> 
	 * @return returns a list of elements of type <code>IFile</code> (in case of removed archives) or 
	 * <code>IFolder</code> that have been removed from the build path
	 * @throws CoreException 
	 * @throws OperationCanceledException 
	 */
	protected List removeFromBuildpath(IRemoveLinkedFolderQuery query, List elements, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveFromBuildpath, elements.size() + 1); 
			List existingEntries= getExistingEntries(project);
			List resultElements= new ArrayList();

			boolean archiveRemoved= false;
			for (int i= 0; i < elements.size(); i++) {
				Object element= elements.get(i);
				Object res= null;
				if (element instanceof IScriptProject) {
					res= removeFromBuildpath(project, existingEntries, new SubProgressMonitor(monitor, 1));
				} else {
					if (element instanceof IProjectFragment) {
						IProjectFragment root= (IProjectFragment) element;
						if (root.getKind() == IProjectFragment.K_BINARY) {
							archiveRemoved= true;
							res= removeFromBuildpath(root, existingEntries, project, new SubProgressMonitor(monitor, 1));
						} else {
							final IResource resource= root.getCorrespondingResource();
							if (resource instanceof IFolder) {
								final IFolder folder= (IFolder) resource;
								if (folder.isLinked()) {
									final int result= query.doQuery(folder);
									if (result != IRemoveLinkedFolderQuery.REMOVE_CANCEL) {
										if (result == IRemoveLinkedFolderQuery.REMOVE_BUILD_PATH) {
											res= removeFromBuildpath(root, existingEntries, project, new SubProgressMonitor(monitor, 1));
										} else if (result == IRemoveLinkedFolderQuery.REMOVE_BUILD_PATH_AND_FOLDER) {
											res= removeFromBuildpath(root, existingEntries, project, new SubProgressMonitor(monitor, 1));
											folder.delete(true, true, new SubProgressMonitor(monitor, 1));
										}
									}
								} else {
									res= removeFromBuildpath(root, existingEntries, project, new SubProgressMonitor(monitor, 1));
								}
							} else {
								res= removeFromBuildpath(root, existingEntries, project, new SubProgressMonitor(monitor, 1));
							}
						}
					} else {
						archiveRemoved= true;
						BuildPathContainer container= (BuildPathContainer) element;
						existingEntries.remove(BPListElement.createFromExisting(container.getBuildpathEntry(), project));
					}
				}
				if (res != null) {
					resultElements.add(res);
				}
				
			}

			updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 1));
			fireEvent(existingEntries);
			if (archiveRemoved && resultElements.size() == 0)
				resultElements.add(project);
			return resultElements;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Include a list of elements to the build path. This means that the inclusion filter for the
	 * corresponding <code>IProjectFragment</code>s need to be modified.
	 * All elements must be either be of type <code>IResource</code> 
	 * or <code>IModelElement</code>.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param elements a list of elements to be included. The elements must be either of type
	 * <code>IResource</code> or <code>IModelElement</code>.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return a list of <code>IModelElement</code>s corresponding to the included ones.
	 * @throws ModelException
	 * 
	 * @see #exclude(List, IScriptProject, IProgressMonitor)
	 */
	protected List include(List elements, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Including, 2 * elements.size()); 

			List existingEntries= getExistingEntries(project);
			List resources= new ArrayList();
			for (int i= 0; i < elements.size(); i++) {
				IResource resource;
				if (elements.get(i) instanceof IResource)
					resource= (IResource) elements.get(i);
				else {
					IModelElement elem= (IModelElement) elements.get(i);
					resource= elem.getResource();
				}
				resources.add(resource);
				IProjectFragment root= getFragmentRoot(resource, project, new SubProgressMonitor(monitor, 1));
				if (root != null) {
					BPListElement entry= getBuildpathEntry(existingEntries, root);
					include(resource, entry, project, new SubProgressMonitor(monitor, 1));
				}
			}

			updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 4));
			List scriptElements= getCorrespondingElements(resources, project);
			return scriptElements;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Exclude a list of <code>IModelElement</code>s. This means that the exclusion filter for the
	 * corresponding <code>IProjectFragment</code>s needs to be modified.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param scriptElements list of script elements to be excluded
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return list of objects representing the excluded elements
	 * @throws ModelException
	 */
	protected List exclude(List scriptElements, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Excluding, scriptElements.size() + 4); 

			List existingEntries= getExistingEntries(project);
			List resources= new ArrayList();
			for (int i= 0; i < scriptElements.size(); i++) {
				IModelElement scriptElement= (IModelElement) scriptElements.get(i);
				IProjectFragment root= (IProjectFragment) scriptElement.getAncestor(IModelElement.PROJECT_FRAGMENT);
				BPListElement entry= getBuildpathEntry(existingEntries, root);

				IResource resource= exclude(scriptElement, entry, project, new SubProgressMonitor(monitor, 1));
				if (resource != null) {
					resources.add(resource);
				}
			}

			updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 4));
			return resources;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Inverse operation to include.
	 * The <code>IModelElement</code>s in the list will be removed from
	 * their fragment roots inclusion filter.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param scriptElements a list of <code>IModelElements</code> to be unincluded
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return a list of elements representing unexcluded elements 
	 * @throws ModelException
	 * 
	 * @see #include(List, IScriptProject, IProgressMonitor)
	 */
	protected List unInclude(List scriptElements, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveInclusion, 10); 

			List existingEntries= getExistingEntries(project);
			for (int i= 0; i < scriptElements.size(); i++) {
				IModelElement scriptElement= (IModelElement) scriptElements.get(i);
				IProjectFragment root= (IProjectFragment) scriptElement.getAncestor(IModelElement.PROJECT_FRAGMENT);
				BPListElement entry= getBuildpathEntry(existingEntries, root);

				unInclude(scriptElement, entry, project, new SubProgressMonitor(monitor, 1));
			}

			updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 4));
			List result= getCorrespondingElements(scriptElements, project);
			return result;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Inverse operation to <code>exclude</code>.
	 * The list of elements of type <code>IResource</code> will be 
	 * removed from the exclusion filters of their parent roots.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param elements list of <code>IResource</code>s to be unexcluded
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return an object representing the unexcluded element 
	 * @throws ModelException
	 * 
	 * @see #exclude(List, IScriptProject, IProgressMonitor)
	 * @see #unExclude(List, IScriptProject, IProgressMonitor)
	 */
	protected List unExclude(List elements, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Including, 2 * elements.size()); 

			List entries= getExistingEntries(project);
			for (int i= 0; i < elements.size(); i++) {
				IResource resource= (IResource) elements.get(i);
				IProjectFragment root= getFragmentRoot(resource, project, new SubProgressMonitor(monitor, 1));
				if (root != null) {
					BPListElement entry= getBuildpathEntry(entries, root);
					unExclude(resource, entry, project, new SubProgressMonitor(monitor, 1));
				}
			}

			updateBuildpath(entries, project, new SubProgressMonitor(monitor, 4));
			List resultElements= getCorrespondingElements(elements, project);
			return resultElements;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Edit the filters of a given <code>IModelElement</code> by using the 
	 * passed <code>IInclusionExclusionQuery</code>.
	 * 
	 * @param element the script element to edit the filters on. Must be either of
	 * type <code>IScriptProject</code> or <code>IProjectFragment</code>.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return returns the edited script element or <code>null</code> if the operation was
	 * cancelled
	 * @throws ModelException
	 */
	protected IModelElement editFilters(IModelElement element, IScriptProject project, IInclusionExclusionQuery query, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_EditInclusionExclusionFilters, 4); 
			List existingEntries= getExistingEntries(project);
			BPListElement entry= getListElement(element.getPath(), existingEntries);
			if (entry != null) {
				if (query.doQuery(entry, false)) {
					entry.setAttribute(BPListElement.INCLUSION, query.getInclusionPattern());
					entry.setAttribute(BPListElement.EXCLUSION, query.getExclusionPattern());
					updateBuildpath(existingEntries, project, new SubProgressMonitor(monitor, 4));
					return element;
				}
			}
		} finally {
			monitor.done();
		}
		return null;
	}	

	/**
	 * Reset a list of elements. The elements can be either of type 
	 * <li><code>IScriptProject</code></li>
	 * <li><code>IProjectFragment</code></li>
	 * <li><code>BPListElementAttribute</code></li><br>
	 * 
	 * Depending on the element, resetting performs two different operations:
	 * <li>On <code>IScriptProject</code> or <code>IProjectFragment</code>, the 
	 * inclusion and exclusion filters are reset. Only entries in the filters that 
	 * correspond to either source folders or output folders will not be 
	 * removed (to prevent damage on the project layout)</li>
	 * <li>On <code>BPListElementAttribute</code>, the output location of the 
	 * given attribute is reset to the default output location.</li>
	 * 
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return a list of elements representing the elements on which 'reset' was called. 
	 * They can either be of type <code>BPListElement</code>, <code>IScriptProject</code> or 
	 * <code>IProjectFragment</code>
	 */
	protected List reset(List elements, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Resetting, elements.size()); 
			List entries= getExistingEntries(project);
			List result= new ArrayList();
			for (int i= 0; i < elements.size(); i++) {
				Object element= elements.get(i);
				if (element instanceof IModelElement) {
					IModelElement scriptElement= (IModelElement) element;
					IProjectFragment root;
					if (element instanceof IScriptProject)
						root= project.getProjectFragment(project.getResource());
					else
						root= (IProjectFragment) element;
					BPListElement entry= getBuildpathEntry(entries, root);
					resetFilters(scriptElement, entry, project, new SubProgressMonitor(monitor, 1));
					result.add(scriptElement);
				} else {
					//BPListElement selElement= ((BPListElementAttribute) element).getParent();
					//BPListElement entry= getBuildpathEntry(entries, selElement);										
				}
			}

			updateBuildpath(entries, project, null);
			fireEvent(entries);
			return result;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Get the <code>IBuildpathEntry</code> from the project and 
	 * convert it into a list of <code>BPListElement</code>s.
	 * 
	 * @param project the script project to get it's build path entries from
	 * @return a list of <code>BPListElement</code>s corresponding to the 
	 * build path entries of the project
	 * @throws ModelException
	 */
	public static List getExistingEntries(IScriptProject project) throws ModelException {
		IBuildpathEntry[] buildpathEntries= project.getRawBuildpath();
		ArrayList newBuildPath= new ArrayList();
		for (int i= 0; i < buildpathEntries.length; i++) {
			IBuildpathEntry curr= buildpathEntries[i];
			newBuildPath.add(BPListElement.createFromExisting(curr, project));
		}
		return newBuildPath;
	}

	/**
	 * Try to find the corresponding and modified <code>BPListElement</code> for the root 
	 * in the list of elements and return it.
	 * If no one can be found, the roots <code>BuildpathEntry</code> is converted to a 
	 * <code>BPListElement</code> and returned.
	 * 
	 * @param elements a list of <code>BPListElements</code>
	 * @param root the root to find the <code>BuildpathEntry</code> for represented by 
	 * a <code>BPListElement</code>
	 * @return the <code>BPListElement</code> found in the list (matching by using the path) or 
	 * the roots own <code>IBuildpathEntry</code> converted to a <code>BPListElement</code>.
	 * @throws ModelException
	 */
	public static BPListElement getBuildpathEntry(List elements, IProjectFragment root) throws ModelException {
		IBuildpathEntry entry= root.getRawBuildpathEntry();
		for (int i= 0; i < elements.size(); i++) {
			BPListElement element= (BPListElement) elements.get(i);
			if (element.getPath().equals(root.getPath()) && element.getEntryKind() == entry.getEntryKind())
				return (BPListElement) elements.get(i);
		}
		BPListElement newElement= BPListElement.createFromExisting(entry, root.getScriptProject());
		elements.add(newElement);
		return newElement;
	}

	/**
	 * For a given <code>IResource</code>, try to
	 * convert it into a <code>IProjectFragment</code>
	 * if possible or return <code>null</code> if no
	 * fragment root could be created.
	 * 
	 * @param resource the resource to be converted
	 * @return the <code>resource<code> as
	 * <code>IScriptFolder</code>,or <code>null</code>
	 * if failed to convert
	 */
	public static IScriptFolder getFragment(IResource resource) {
		IModelElement elem= DLTKCore.create(resource);
		if (elem instanceof IScriptFolder)
			return (IScriptFolder) elem;
		return null;
	}

	/**
	 * Get the source folder of a given <code>IResource</code> element,
	 * starting with the resource's parent.
	 * 
	 * @param resource the resource to get the fragment root from
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return resolved fragment root
	 * @throws ModelException
	 */
	public static IProjectFragment getFragmentRoot(IResource resource, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		IModelElement scriptElem= null;
		if (resource.getFullPath().equals(project.getPath()))
			return project.getProjectFragment(resource);
		IContainer container= resource.getParent();
		do {
			if (container instanceof IFolder)
				scriptElem= DLTKCore.create(container);
			if (container.getFullPath().equals(project.getPath())) {
				scriptElem= project;
				break;
			}
			container= container.getParent();
			if (container == null)
				return null;
		} while (scriptElem == null || !(scriptElem instanceof IProjectFragment));
		if (scriptElem instanceof IScriptProject) {
			if (!isSourceFolder((IScriptProject)scriptElem))
				return null;
			scriptElem= project.getProjectFragment(project.getResource());
		}
		return (IProjectFragment) scriptElem;
	}

	/**
	 * Get the <code>IBuildpathEntry</code> for the
	 * given path by looking up all
	 * build path entries on the project
	 * 
	 * @param path the path to find a build path entry for
	 * @param project the script project
	 * @return the <code>IBuildpathEntry</code> corresponding
	 * to the <code>path</code> or <code>null</code> if there
	 * is no such entry
	 * @throws ModelException
	 */
	public static IBuildpathEntry getBuildpathEntryFor(IPath path, IScriptProject project, int entryKind) throws ModelException {
		IBuildpathEntry[] entries= project.getRawBuildpath();
		for (int i= 0; i < entries.length; i++) {
			IBuildpathEntry entry= entries[i];
			if (entry.getPath().equals(path) && equalEntryKind(entry, entryKind))
				return entry;
		}
		return null;
	}

	/**
	 * Check whether the current selection is the project's 
	 * default output folder or not
	 * 
	 * @param attrib the attribute to be checked
	 * @return <code>true</code> if is the default output folder,
	 * <code>false</code> otherwise.
	 */
	public static boolean isDefaultOutputFolder(BPListElementAttribute attrib) {
		return attrib.getValue() == null;
	}

	/**
	 * Determines whether the current selection (of type
	 * <code>ISourceModule</code> or <code>IScriptFolder</code>)
	 * is on the inclusion filter of it's parent source folder.
	 * 
	 * @param selection the current script element
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return <code>true</code> if the current selection is included,
	 * <code>false</code> otherwise.
	 * @throws ModelException 
	 */
	public static boolean isIncluded(IModelElement selection, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_ContainsPath, 4); 
			IProjectFragment root= (IProjectFragment) selection.getAncestor(IModelElement.PROJECT_FRAGMENT);
			IBuildpathEntry entry= root.getRawBuildpathEntry();
			if (entry == null)
				return false;
			return contains(selection.getPath().removeFirstSegments(root.getPath().segmentCount()), entry.getInclusionPatterns(), new SubProgressMonitor(monitor, 2));
		} finally {
			monitor.done();
		}
	}

	/**
	 * Find out whether the <code>IResource</code> excluded or not.
	 * 
	 * @param resource the resource to be checked
	 * @param project the script project
	 * @return <code>true</code> if the resource is excluded, <code>
	 * false</code> otherwise
	 * @throws ModelException
	 */
	public static boolean isExcluded(IResource resource, IScriptProject project) throws ModelException {
		IProjectFragment root= getFragmentRoot(resource, project, null);
		if (root == null)
			return false;
		String fragmentName= getName(resource.getFullPath(), root.getPath());
		fragmentName= completeName(fragmentName);
		IBuildpathEntry entry= root.getRawBuildpathEntry();
		return entry != null && contains(new Path(fragmentName), entry.getExclusionPatterns(), null);
	}

	/**
	 * Find out whether one of the <code>IResource</code>'s parents
	 * is excluded.
	 * 
	 * @param resource check the resources parents whether they are
	 * excluded or not
	 * @param project the script project
	 * @return <code>true</code> if there is an excluded parent, 
	 * <code>false</code> otherwise
	 * @throws ModelException
	 */
	public static boolean parentExcluded(IResource resource, IScriptProject project) throws ModelException {
		if (resource.getFullPath().equals(project.getPath()))
			return false;
		IProjectFragment root= getFragmentRoot(resource, project, null);
		if (root == null) {
			return true;
		}
		IPath path= resource.getFullPath().removeFirstSegments(root.getPath().segmentCount());
		IBuildpathEntry entry= root.getRawBuildpathEntry();
		if (entry == null)
			return true; // there is no build path entry, this is equal to the fact that the parent is excluded
		while (path.segmentCount() > 0) {
			if (contains(path, entry.getExclusionPatterns(), null))
				return true;
			path= path.removeLastSegments(1);
		}
		return false;
	}
	
	protected static String escapeSpecialChars(String value) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);

			switch (c) {
			case '&':
				buf.append("&amp;"); //$NON-NLS-1$
				break;
			case '<':
				buf.append("&lt;"); //$NON-NLS-1$
				break;
			case '>':
				buf.append("&gt;"); //$NON-NLS-1$
				break;
			case '\'':
				buf.append("&apos;"); //$NON-NLS-1$
				break;
			case '\"':
				buf.append("&quot;"); //$NON-NLS-1$
				break;
			case 160:
				buf.append(" "); //$NON-NLS-1$
				break;
			default:
				buf.append(c);
				break;
			}
		}
		return buf.toString();
	}
	

	/**
	 * Check whether the <code>IScriptProject</code>
	 * is a source folder 
	 * 
	 * @param project the project to test
	 * @return <code>true</code> if <code>project</code> is a source folder
	 * <code>false</code> otherwise.
	 */
	public static boolean isSourceFolder(IScriptProject project) throws ModelException {
		return BuildpathModifier.getBuildpathEntryFor(project.getPath(), project, IBuildpathEntry.BPE_SOURCE) != null;
	}
	
	/**
	 * Check whether the <code>IScriptFolder</code>
	 * corresponds to the project's default fragment.
	 * 
	 * @param fragment the package fragment to be checked
	 * @return <code>true</code> if is the default package fragment,
	 * <code>false</code> otherwise.
	 */
	public static boolean isDefaultFragment(IScriptFolder fragment) {
		return fragment.getElementName().length() == 0;
	}

	/**
	 * Determines whether the inclusion filter of the element's source folder is empty
	 * or not
	 * @return <code>true</code> if the inclusion filter is empty,
	 * <code>false</code> otherwise.
	 * @throws ModelException 
	 */
	public static boolean includeFiltersEmpty(IResource resource, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_ExamineInputFilters, 4); 
			IProjectFragment root= getFragmentRoot(resource, project, new SubProgressMonitor(monitor, 4));
			if (root != null) {
				IBuildpathEntry entry= root.getRawBuildpathEntry();
				return entry.getInclusionPatterns().length == 0;
			}
			return true;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Check whether the input paramenter of type <code>
	 * IProjectFragment</code> has either it's inclusion or
	 * exclusion filter or both set (that means they are
	 * not empty).
	 * 
	 * @param root the fragment root to be inspected
	 * @return <code>true</code> inclusion or exclusion filter set,
	 * <code>false</code> otherwise.
	 */
	public static boolean filtersSet(IProjectFragment root) throws ModelException {
		if (root == null)
			return false;
		IBuildpathEntry entry= root.getRawBuildpathEntry();
		IPath[] inclusions= entry.getInclusionPatterns();
		IPath[] exclusions= entry.getExclusionPatterns();
		if (inclusions != null && inclusions.length > 0)
			return true;
		if (exclusions != null && exclusions.length > 0)
			return true;
		return false;
	}

	/**
	 * Add a resource to the build path.
	 * 
	 * @param resource the resource to be added to the build path
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code> 
	 * @return returns the new element of type <code>IProjectFragment</code> that has been added to the build path
	 * @throws CoreException 
	 * @throws OperationCanceledException 
	 */
	public static BPListElement addToBuildpath(IResource resource, List existingEntries, List newEntries, IScriptProject project, IProgressMonitor monitor) throws OperationCanceledException, CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 2); 
			exclude(resource.getFullPath(), existingEntries, newEntries, project, new SubProgressMonitor(monitor, 1));
			BPListElement entry= new BPListElement(project, IBuildpathEntry.BPE_SOURCE, resource.getFullPath(), resource, false);
			return entry;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Check whether the provided file is an archive (.zip).
	 * 
	 * @param file the file to be checked
	 * @param project the script project
	 * @return <code>true</code> if the file is an archive, <code>false</code> 
	 * otherwise
	 * @throws ModelException
	 */
	public static boolean isArchive(IFile file, IScriptProject project) throws ModelException {
		if (!ArchiveFileFilter.isArchivePath(file.getFullPath()))
			return false;
		if (project != null && project.exists() && (project.findProjectFragment(file.getFullPath()) == null))
			return true;
		return false;
	}

	/**
	 * Add a script element to the build path.
	 * 
	 * @param scriptElement element to be added to the build path
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code> 
	 * @return returns the new element of type <code>IProjectFragment</code> that has been added to the build path
	 * @throws CoreException 
	 * @throws OperationCanceledException 
	 */
	public static BPListElement addToBuildpath(IModelElement scriptElement, List existingEntries, List newEntries, IScriptProject project, IProgressMonitor monitor) throws OperationCanceledException, CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_AddToBuildpath, 10); 
			BPListElement entry= new BPListElement(project, IBuildpathEntry.BPE_SOURCE, scriptElement.getPath(), scriptElement.getResource(), false);
			return entry;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Remove the script project from the build path
	 * 
	 * @param project the project to be removed
	 * @param existingEntries a list of existing <code>BPListElement</code>. This list 
	 * will be traversed and the entry for the project will be removed.
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return returns the script project
	 * @throws CoreException
	 */
	public static IScriptProject removeFromBuildpath(IScriptProject project, List existingEntries, IProgressMonitor monitor) throws CoreException {
		BPListElement elem= getListElement(project.getPath(), existingEntries);
		if (elem != null) {
			existingEntries.remove(elem);
		}
		return project;
	}

	/**
	 * Remove a given <code>IProjectFragment</code> from the build path.
	 * 
	 * @param root the <code>IProjectFragment</code> to be removed from the build path
	 * @param existingEntries a list of <code>BPListElements</code> representing the build path 
	 * entries of the project. The entry for the root will be looked up and removed from the list.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return returns the <code>IResource</code> that has been removed from the build path; 
	 * is of type <code>IFile</code> if the root was an archive, otherwise <code>IFolder</code> or <code>null<code> for external archives.
	 */
	public static IResource removeFromBuildpath(IProjectFragment root, List existingEntries, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveFromBuildpath, 1); 
			IBuildpathEntry entry= root.getRawBuildpathEntry();
			BPListElement elem= BPListElement.createFromExisting(entry, project);
			existingEntries.remove(elem);
			removeFilters(elem.getPath(), project, existingEntries);
			return elem.getResource();
		} finally {
			monitor.done();
		}
	}
	
	/**
	 * Remove <code>path</code> from inclusion/exlusion filters in all <code>existingEntries</code>
	 * 
	 * @param path the path to remove
	 * @param project the script project
	 * @param existingEntries a list of <code>BPListElement</code> representing the build path
	 * entries of the project.
	 * @return returns a <code>List</code> of <code>BPListElement</code> of modified elements, not null.
	 */
	public static List removeFilters(IPath path, IScriptProject project, List existingEntries) {
		if (path == null)
			return Collections.EMPTY_LIST;
		
		IPath projPath= project.getPath();
		if (projPath.isPrefixOf(path)) {
			path= path.removeFirstSegments(projPath.segmentCount()).addTrailingSeparator();
		}
		
		List result= new ArrayList();
		for (Iterator iter= existingEntries.iterator(); iter.hasNext();) {
			BPListElement element= (BPListElement)iter.next();
			boolean hasChange= false;
			IPath[] exlusions= (IPath[])element.getAttribute(BPListElement.EXCLUSION);
			if (exlusions != null) {
				List exlusionList= new ArrayList(exlusions.length);
				for (int i= 0; i < exlusions.length; i++) {
					if (!exlusions[i].equals(path)) {
						exlusionList.add(exlusions[i]);
					} else {
						hasChange= true;
					}
				}
				element.setAttribute(BPListElement.EXCLUSION, exlusionList.toArray(new IPath[exlusionList.size()]));
			}
			
			IPath[] inclusion= (IPath[])element.getAttribute(BPListElement.INCLUSION);
			if (inclusion != null) {
				List inclusionList= new ArrayList(inclusion.length);
				for (int i= 0; i < inclusion.length; i++) {
					if (!inclusion[i].equals(path)) {
						inclusionList.add(inclusion[i]);
					} else {
						hasChange= true;
					}
				}
				element.setAttribute(BPListElement.INCLUSION, inclusionList.toArray(new IPath[inclusionList.size()]));
			}
			if (hasChange) {
				result.add(element);
			}
		}
		return result;
	}

	/**
	 * Include the given <code>IResource</code>. This means that the inclusion filter for the
	 * corresponding <code>IProjectFragment</code> needs to be modified.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param resource the element to be included
	 * @param entry the <code>BPListElement</code> representing the 
	 * <code>IBuildpathEntry</code> of the resource's root
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 *
	 * @throws ModelException
	 * 
	 * @see #exclude(List, IScriptProject, IProgressMonitor)
	 */
	private void include(IResource resource, BPListElement entry, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Including, 10); 

			String name= getName(resource.getFullPath(), entry.getPath());

			IPath[] includedPath= (IPath[]) entry.getAttribute(BPListElement.INCLUSION);
			IPath[] newIncludedPath= new IPath[includedPath.length + 1];
			String completedName= completeName(name);
			IPath relPath= new Path(completedName);
			if (!contains(relPath, includedPath, new SubProgressMonitor(monitor, 2))) {
				System.arraycopy(includedPath, 0, newIncludedPath, 0, includedPath.length);
				newIncludedPath[includedPath.length]= relPath;
				entry.setAttribute(BPListElement.INCLUSION, newIncludedPath);
				entry.setAttribute(BPListElement.EXCLUSION, remove(relPath, (IPath[]) entry.getAttribute(BPListElement.EXCLUSION), new SubProgressMonitor(monitor, 2)));
			}
		} finally {
			monitor.done();
		}
	}

	/**
	 * Exclude an element with a given name and absolute path
	 * from the build path.
	 * 
	 * @param name the name of the element to be excluded
	 * @param fullPath the absolute path of the element
	 * @param entry the build path entry to be modified
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return a <code>IResource</code> corresponding to the excluded element
	 * @throws ModelException 
	 */
	private static IResource exclude(String name, IPath fullPath, BPListElement entry, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		IResource result;
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Excluding, 6); 
			IPath[] excludedPath= (IPath[]) entry.getAttribute(BPListElement.EXCLUSION);
			IPath[] newExcludedPath= new IPath[excludedPath.length + 1];
			name= completeName(name);
			IPath path= new Path(name);
			if (!contains(path, excludedPath, new SubProgressMonitor(monitor, 2))) {
				System.arraycopy(excludedPath, 0, newExcludedPath, 0, excludedPath.length);
				newExcludedPath[excludedPath.length]= path;
				entry.setAttribute(BPListElement.EXCLUSION, newExcludedPath);
				entry.setAttribute(BPListElement.INCLUSION, remove(path, (IPath[]) entry.getAttribute(BPListElement.INCLUSION), new SubProgressMonitor(monitor, 4)));
			}
			result= fullPath == null ? null : getResource(fullPath, project);
		} finally {
			monitor.done();
		}
		return result;
	}

	/**
	 * Exclude an object at a given path.
	 * This means that the exclusion filter for the
	 * corresponding <code>IProjectFragment</code> needs to be modified.
	 * 
	 * First, the fragment root needs to be found. To do so, the new entries 
	 * are and the existing entries are traversed for a match and the entry 
	 * with the path is removed from one of those lists.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param path absolute path of an object to be excluded
	 * @param existingEntries a list of existing build path entries
	 * @param newEntries a list of new build path entries
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 */
	public static void exclude(IPath path, List existingEntries, List newEntries, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_Excluding, 1); 
			BPListElement elem= null;
			BPListElement existingElem= null;
			int i= 0;
			do {
				i++;
				IPath rootPath= path.removeLastSegments(i);

				if (rootPath.segmentCount() == 0)
					return;

				elem= getListElement(rootPath, newEntries);
				existingElem= getListElement(rootPath, existingEntries);
			} while (existingElem == null && elem == null);
			if (elem == null) {
				elem= existingElem;
			}
			exclude(path.removeFirstSegments(path.segmentCount() - i).toString(), null, elem, project, new SubProgressMonitor(monitor, 1)); 
		} finally {
			monitor.done();
		}
	}

	/**
	 * Exclude a <code>IModelElement</code>. This means that the exclusion filter for the
	 * corresponding <code>IProjectFragment</code>s need to be modified.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param scriptElement the script element to be excluded
	 * @param entry the <code>BPListElement</code> representing the 
	 * <code>IBuildpathEntry</code> of the script element's root.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * 
	 * @return the resulting <code>IResource<code>
	 * @throws ModelException
	 */
	public static IResource exclude(IModelElement scriptElement, BPListElement entry, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			String name= getName(scriptElement.getPath(), entry.getPath());
			return exclude(name, scriptElement.getPath(), entry, project, new SubProgressMonitor(monitor, 1));
		} finally {
			monitor.done();
		}
	}

	/**
	 * Inverse operation to <code>include</code>. The provided 
	 * <code>IModelElement</code> will be removed from the inclusion 
	 * filters of it's root.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param scriptElement the script element to be unincluded
	 * @param entry the <code>BPListElement</code> representing the 
	 * <code>IBuildpathEntry</code> of the root.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @throws ModelException
	 * 
	 * @see #include(List, IScriptProject, IProgressMonitor)
	 */
	private void unInclude(IModelElement scriptElement, BPListElement entry, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveInclusion, 10); 
			String name= getName(scriptElement.getPath(), entry.getPath());

			IPath[] includedPath= (IPath[]) entry.getAttribute(BPListElement.INCLUSION);
			IPath relPath= new Path(completeName(name));
			IPath[] newIncludedPath= remove(relPath, includedPath, new SubProgressMonitor(monitor, 3));
			entry.setAttribute(BPListElement.INCLUSION, newIncludedPath);
		} finally {
			monitor.done();
		}
	}

	/**
	 * Inverse operation to <code>exclude</code>.
	 * The resource removed from it's fragment roots exlusion filter.
	 * 
	 * Note: the <code>IModelElement</code>'s fragment (if there is one)
	 * is not allowed to be excluded! However, inclusion (or simply no
	 * filter) on the parent fragment is allowed.
	 * 
	 * @param resource the resource to be unexcluded
	 * @param entry the <code>BPListElement</code> representing the 
	 * <code>IBuildpathEntry</code> of the resource's root.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @throws ModelException
	 * 
	 * @see #exclude(List, IScriptProject, IProgressMonitor)
	 */
	public static void unExclude(IResource resource, BPListElement entry, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemoveExclusion, 10); 
			String name= getName(resource.getFullPath(), entry.getPath());
			IPath[] excludedPath= (IPath[]) entry.getAttribute(BPListElement.EXCLUSION);
			IPath[] newExcludedPath= remove(new Path(completeName(name)), excludedPath, new SubProgressMonitor(monitor, 3));
			entry.setAttribute(BPListElement.EXCLUSION, newExcludedPath);
		} finally {
			monitor.done();
		}
	}

	/**
	 * Resets inclusion and exclusion filters for the given
	 * <code>IModelElement</code>
	 * 
	 * @param element element to reset it's filters
	 * @param entry the <code>BPListElement</code> to reset its filters for
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @throws ModelException
	 */
	private void resetFilters(IModelElement element, BPListElement entry, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_ResetFilters, 3); 

			List exclusionList= getFoldersOnBP(element.getPath(), project, new SubProgressMonitor(monitor, 2));						
			IPath[] exclusions= (IPath[]) exclusionList.toArray(new IPath[exclusionList.size()]);

			entry.setAttribute(BPListElement.INCLUSION, new IPath[0]);
			entry.setAttribute(BPListElement.EXCLUSION, exclusions);
		} finally {
			monitor.done();
		}
	}	

	/**
	 * Try to find the corresponding and modified <code>BPListElement</code> for the provided 
	 * <code>BPListElement</code> in the list of elements and return it.
	 * If no one can be found, the provided <code>BPListElement</code> is returned.
	 * 
	 * @param elements a list of <code>BPListElements</code>
	 * @param cpElement the <code>BPListElement</code> to find the corresponding entry in 
	 * the list
	 * @return the <code>BPListElement</code> found in the list (matching by using the path) or 
	 * the second <code>BPListElement</code> parameter itself if there is no match.
	 * @throws ModelException
	 */
	public static BPListElement getBuildpathEntry(List elements, BPListElement cpElement) throws ModelException {
		for (int i= 0; i < elements.size(); i++) {
			if (((BPListElement) elements.get(i)).getPath().equals(cpElement.getPath()))
				return (BPListElement) elements.get(i);
		}
		elements.add(cpElement);
		return cpElement;
	}

	/**
	 * For a given path, find the corresponding element in the list.
	 * 
	 * @param path the path to found an entry for
	 * @param elements a list of <code>BPListElement</code>s
	 * @return the mathed <code>BPListElement</code> or <code>null</code> if 
	 * no match could be found
	 */
	private static BPListElement getListElement(IPath path, List elements) {
		for (int i= 0; i < elements.size(); i++) {
			BPListElement element= (BPListElement) elements.get(i);
			if (element.getEntryKind() == IBuildpathEntry.BPE_SOURCE && element.getPath().equals(path)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Updates the build path if changes have been applied to a
	 * build path entry. For example, this can be necessary after
	 * having edited some filters on a build path entry, which can happen
	 * when including or excluding an object.
	 * 
	 * @param newEntries a list of <code>BPListElements</code> that should be used 
	 * as build path entries for the project.
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @throws ModelException in case that validation for the new entries fails
	 */
	private void updateBuildpath(List newEntries, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			IBuildpathEntry[] entries= convert(newEntries);			

			IModelStatus status= BuildpathEntry.validateBuildpath(project, entries);
			if (!status.isOK())
				throw new ModelException(status);

			project.setRawBuildpath(entries, new SubProgressMonitor(monitor, 2));
			fireEvent(newEntries);
		} finally {
			monitor.done();
		}
	}
	
	public static void commitBuildPath(List newEntries, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			IBuildpathEntry[] entries= convert(newEntries);			

			IModelStatus status= BuildpathEntry.validateBuildpath(project, entries);
			if (!status.isOK())
				throw new ModelException(status);

			project.setRawBuildpath(entries, new SubProgressMonitor(monitor, 2));
		} finally {
			monitor.done();
		}
	}

	/**
	 * For a given list of entries, find out what representation they 
	 * will have in the project and return a list with corresponding 
	 * elements.
	 * 
	 * @param entries a list of entries to find an appropriate representation 
	 * for. The list can contain elements of two types: 
	 * <li><code>IResource</code></li>
	 * <li><code>IModelElement</code></li>
	 * @param project the script project
	 * @return a list of elements corresponding to the passed entries.
	 */
	public static List getCorrespondingElements(List entries, IScriptProject project) {
		List result= new ArrayList();
		for (int i= 0; i < entries.size(); i++) {
			Object element= entries.get(i);
			IPath path;
			if (element instanceof IResource)
				path= ((IResource) element).getFullPath();
			else
				path= ((IModelElement) element).getPath();
			IResource resource= getResource(path, project);
			if (resource != null) {
				IModelElement elem= DLTKCore.create(resource);
				if (elem != null && project.isOnBuildpath(elem))
					result.add(elem);
				else
					result.add(resource);
			}

		}
		return result;
	}

	/**
	 * Returns for the given absolute path the corresponding
	 * resource, this is either element of type <code>IFile</code>
	 * or <code>IFolder</code>.
	 *  
	 * @param path an absolute path to a resource
	 * @param project the script project
	 * @return the resource matching to the path. Can be
	 * either an <code>IFile</code> or an <code>IFolder</code>.
	 */
	private static IResource getResource(IPath path, IScriptProject project) {
		return project.getProject().getWorkspace().getRoot().findMember(path);
	}

	/**
	 * Find out whether the provided path equals to one
	 * in the array.
	 * 
	 * @param path path to find an equivalent for
	 * @param paths set of paths to compare with
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return <code>true</code> if there is an occurrence, <code>
	 * false</code> otherwise
	 */
	private static boolean contains(IPath path, IPath[] paths, IProgressMonitor monitor) {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		if (path == null)
			return false;
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_ComparePaths, paths.length); 
			if (path.getFileExtension() == null)
				path= new Path(completeName(path.toString())); 
			for (int i= 0; i < paths.length; i++) {
				if (paths[i].equals(path))
					return true;
				monitor.worked(1);
			}
		} finally {
			monitor.done();
		}
		return false;
	}

	/**
	 * Add a '/' at the end of the name if
	 * it does not end with extension.
	 * 
	 * @param name append '/' at the end if
	 * necessary
	 * @return modified string
	 */
	private static String completeName(String name) {
		if (DLTKCore.DEBUG) {
			System.err.println("Add Buildpath name completion here");
		}
//		if (!DLTKCore.isScriptLikeFileName(name)) {
//			name= name + "/"; //$NON-NLS-1$
//			name= name.replace('.', '/');
//			return name;
//		}
		return name;
	}

	/**
	 * Removes <code>path</code> out of the set of given <code>
	 * paths</code>. If the path is not contained, then the 
	 * initially provided array of paths is returned.
	 * 
	 * Only the first occurrence will be removed.
	 * 
	 * @param path path to be removed
	 * @param paths array of path to apply the removal on
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return array which does not contain <code>path</code>
	 */
	private static IPath[] remove(IPath path, IPath[] paths, IProgressMonitor monitor) {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_RemovePath, paths.length + 5); 
			if (!contains(path, paths, new SubProgressMonitor(monitor, 5)))
				return paths;

			ArrayList newPaths= new ArrayList();
			for (int i= 0; i < paths.length; i++) {
				monitor.worked(1);
				if (!paths[i].equals(path))
					newPaths.add(paths[i]);
			}
			
			return (IPath[]) newPaths.toArray(new IPath[newPaths.size()]);
		} finally {
			monitor.done();
		}

	}

	/**
	 * Find all folders that are on the build path and
	 * <code>path</code> is a prefix of those folders
	 * path entry, that is, all folders which are a
	 * subfolder of <code>path</code>.
	 * 
	 * For example, if <code>path</code>=/MyProject/src 
	 * then all folders having a path like /MyProject/src/*,
	 * where * can be any valid string are returned if
	 * they are also on the project's build path.
	 * 
	 * @param path absolute path
	 * @param project the script project
	 * @param monitor progress monitor, can be <code>null</code>
	 * @return an array of paths which belong to subfolders
	 * of <code>path</code> and which are on the build path
	 * @throws ModelException
	 */
	private List getFoldersOnBP(IPath path, IScriptProject project, IProgressMonitor monitor) throws ModelException {
		if (monitor == null)
			monitor= new NullProgressMonitor();
		List srcFolders= new ArrayList();
		IBuildpathEntry[] cpEntries= project.getRawBuildpath();
		for (int i= 0; i < cpEntries.length; i++) {
			IPath cpPath= cpEntries[i].getPath();
			if (path.isPrefixOf(cpPath) && path.segmentCount() + 1 == cpPath.segmentCount())
				srcFolders.add(new Path(completeName(cpPath.lastSegment())));
		}
		return srcFolders;
	}

	/**
	 * Returns a string corresponding to the <code>path</code>
	 * with the <code>rootPath<code>'s number of segments
	 * removed
	 * 
	 * @param path path to remove segments
	 * @param rootPath provides the number of segments to
	 * be removed
	 * @return a string corresponding to the mentioned
	 * action
	 */
	private static String getName(IPath path, IPath rootPath) {
		return path.removeFirstSegments(rootPath.segmentCount()).toString();
	}

	/**
	 * Sets and validates the new entries. Note that the elments of 
	 * the list containing the new entries will be added to the list of 
	 * existing entries (therefore, there is no return list for this method).
	 * 
	 * @param existingEntries a list of existing buildpath entries
	 * @param newEntries a list of entries to be added to the existing ones
	 * @param project the script project
	 * @param monitor a progress monitor, can be <code>null</code>
	 * @throws CoreException in case that validation on one of the new entries fails
	 */
	public static void setNewEntry(List existingEntries, List newEntries, IScriptProject project, IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_SetNewEntry, existingEntries.size()); 
			for (int i= 0; i < newEntries.size(); i++) {
				BPListElement entry= (BPListElement) newEntries.get(i);
				validateAndAddEntry(entry, existingEntries, project);
				monitor.worked(1);
			}
		} finally {
			monitor.done();
		}
	}

	/**
	 * Convert a list of <code>BPListElement</code>s to 
	 * an array of <code>IBuildpathEntry</code>.
	 * 
	 * @param list the list to be converted
	 * @return an array containing build path entries 
	 * corresponding to the list
	 */
	private static IBuildpathEntry[] convert(List list) {
		IBuildpathEntry[] entries= new IBuildpathEntry[list.size()];
		for (int i= 0; i < list.size(); i++) {
			BPListElement element= (BPListElement) list.get(i);
			entries[i]= element.getBuildpathEntry();
		}
		return entries;
	}

	/**
	 * Validate the new entry in the context of the existing entries. Furthermore, 
	 * check if exclusion filters need to be applied and do so if necessary.
	 * 
	 * If validation was successfull, add the new entry to the list of existing entries.
	 * 
	 * @param entry the entry to be validated and added to the list of existing entries.
	 * @param existingEntries a list of existing entries representing the build path
	 * @param project the script project
	 * @throws CoreException in case that validation fails
	 */
	private static void validateAndAddEntry(BPListElement entry, List existingEntries, IScriptProject project) throws CoreException {
		IPath path= entry.getPath();
		//IPath projPath= project.getProject().getFullPath();
		IWorkspaceRoot workspaceRoot= ResourcesPlugin.getWorkspace().getRoot();
		IStatus validate= workspaceRoot.getWorkspace().validatePath(path.toString(), IResource.FOLDER);
		StatusInfo rootStatus= new StatusInfo();
		rootStatus.setOK();
		boolean isExternal= isExternalArchiveOrLibrary(entry, project);
		if (!isExternal && validate.matches(IStatus.ERROR) && !project.getPath().equals(path)) {
			rootStatus.setError(Messages.format(NewWizardMessages.NewSourceFolderWizardPage_error_InvalidRootName, validate.getMessage())); 
			throw new CoreException(rootStatus);
		} else {
			if (!isExternal && !project.getPath().equals(path)) {
				IResource res= workspaceRoot.findMember(path);
				if (res != null) {
					if (res.getType() != IResource.FOLDER && res.getType() != IResource.FILE) {
						rootStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_NotAFolder); 
						throw new CoreException(rootStatus);
					}
				} else {
					URI projLocation= project.getProject().getLocationURI();
					if (projLocation != null) {
						IFileStore store= EFS.getStore(projLocation).getChild(path);
						if (store.fetchInfo().exists()) {
							rootStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_AlreadyExistingDifferentCase); 
							throw new CoreException(rootStatus);
						}
					}
				}
			}

			for (int i= 0; i < existingEntries.size(); i++) {
				BPListElement curr= (BPListElement) existingEntries.get(i);
				if (curr.getEntryKind() == IBuildpathEntry.BPE_SOURCE) {
					if (path.equals(curr.getPath()) && !project.getPath().equals(path)) {
						rootStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_AlreadyExisting); 
						throw new CoreException(rootStatus);
					}
				}
			}

			if (!isExternal && !entry.getPath().equals(project.getPath()))
				exclude(entry.getPath(), existingEntries, new ArrayList(), project, null);
			
			insertAtEndOfCategory(entry, existingEntries);

			IBuildpathEntry[] entries= convert(existingEntries);

			IModelStatus status= BuildpathEntry.validateBuildpath(project, entries);
			if (!status.isOK()) {				
				rootStatus.setError(status.getMessage());
				throw new CoreException(rootStatus);
			}

			if (isSourceFolder(project) || project.getPath().equals(path)) {
				rootStatus.setWarning(NewWizardMessages.NewSourceFolderWizardPage_warning_ReplaceSF); 
				return;
			}

			rootStatus.setOK();
			return;
		}
	}

	private static void insertAtEndOfCategory(BPListElement entry, List existingEntries) {
		int length= existingEntries.size();
		BPListElement[] elements= (BPListElement[])existingEntries.toArray(new BPListElement[length]);
		int i= 0;
		while (i < length && elements[i].getBuildpathEntry().getEntryKind() != entry.getBuildpathEntry().getEntryKind()) {
			i++;
		}
		if (i < length) {
			i++;
			while (i < length && elements[i].getBuildpathEntry().getEntryKind() == entry.getBuildpathEntry().getEntryKind()) {
				i++;
			}
			existingEntries.add(i, entry);
			return;
		}
		
		switch (entry.getBuildpathEntry().getEntryKind()) {
		case IBuildpathEntry.BPE_SOURCE:
			existingEntries.add(0, entry);
			break;
		case IBuildpathEntry.BPE_CONTAINER:
		case IBuildpathEntry.BPE_LIBRARY:
		case IBuildpathEntry.BPE_PROJECT:		
		default:
			existingEntries.add(entry);
			break;
		}
	}

	private static boolean isExternalArchiveOrLibrary(BPListElement entry, IScriptProject project) {
		if (entry.getEntryKind() == IBuildpathEntry.BPE_LIBRARY || entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
			if (entry.getResource() instanceof IFolder) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Test if the provided kind is of type
	 * <code>IBuildpathEntry.BPE_SOURCE</code>
	 * 
	 * @param entry the buildpath entry to be compared with the provided type
	 * @param kind the kind to be checked
	 * @return <code>true</code> if kind equals
	 * <code>IBuildpathEntry.BPE_SOURCE</code>, 
	 * <code>false</code> otherwise
	 */
	private static boolean equalEntryKind(IBuildpathEntry entry, int kind) {
		return entry.getEntryKind() == kind;
	}

	/**
	 * Event fired whenever build pathentries changed.
	 * The event parameter corresponds to the 
	 * a <code>List</code> of <code>BPListElement</code>s
	 * 
	 * @param newEntries
	 * 
	 * @see #addToBuildpath(List, IScriptProject, OutputFolderQuery, IProgressMonitor)
	 * @see #removeFromBuildpath(IRemoveLinkedFolderQuery, List, IScriptProject, IProgressMonitor)
	 */
	private void fireEvent(List newEntries) {
		if (fListener != null)
			fListener.buildpathEntryChanged(newEntries);
	}
}
