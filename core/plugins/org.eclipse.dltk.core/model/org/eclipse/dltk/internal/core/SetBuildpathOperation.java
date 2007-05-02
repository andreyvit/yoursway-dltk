/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.dltk.compiler.util.ObjectVector;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.indexing.IndexManager;
import org.eclipse.dltk.internal.compiler.env.AccessRuleSet;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.internal.core.util.Util;


/**
 * This operation sets an <code>IScriptProject</code>'s buildpath.
 * 
 * @see IScriptProject
 */
public class SetBuildpathOperation extends ModelOperation {

	IBuildpathEntry[] oldResolvedPath, newResolvedPath;

	IBuildpathEntry[] newRawPath;

	boolean canChangeResources;

	boolean buildpathWasSaved;

	boolean needCycleCheck;

	boolean needValidation;

	boolean needSave;

	DLTKProject project;

	boolean identicalRoots;

	/*
	 * Used to indicate that the buildpath entries remain the same.
	 */
	public static final IBuildpathEntry[] DO_NOT_SET_ENTRIES = new IBuildpathEntry[0];

	public static final IBuildpathEntry[] DO_NOT_UPDATE_PROJECT_REFS = new IBuildpathEntry[0];

	/**
	 * When executed, this operation sets the buildpath of the given project.
	 */
	public SetBuildpathOperation(DLTKProject project, IBuildpathEntry[] oldResolvedPath, IBuildpathEntry[] newRawPath, boolean canChangeResource, boolean needValidation, boolean needSave) {

		super(new IModelElement[] { project });
		this.oldResolvedPath = oldResolvedPath;
		this.newRawPath = newRawPath;
		this.canChangeResources = canChangeResource;
		this.needValidation = needValidation;
		this.needSave = needSave;
		this.project = project;
	}

	/**
	 * Adds deltas for the given roots, with the specified change flag, and
	 * closes the root. Helper method for #setBuildpath
	 */
	protected void addBuildpathDeltas(IProjectFragment[] roots, int flag, ModelElementDelta delta) {

		for (int i = 0; i < roots.length; i++) {
			IProjectFragment root = roots[i];
			delta.changed(root, flag);
			if ((flag & IModelElementDelta.F_REMOVED_FROM_BUILDPATH) != 0) {
				try {
					root.close();
				} catch (ModelException e) {
					// ignore
				}
			}
		}
	}

	protected boolean canModifyRoots() {
		// setting buildpath can modify roots
		return true;
	}

	/**
	 * Returns the index of the item in the list if the given list contains the
	 * specified entry. If the list does not contain the entry, -1 is returned.
	 * A helper method for #setBuildpath
	 */
	protected int buildpathContains(IBuildpathEntry[] list, IBuildpathEntry entry) {

		IPath[] exclusionPatterns = entry.getExclusionPatterns();
		IPath[] inclusionPatterns = entry.getInclusionPatterns();
		nextEntry: for (int i = 0; i < list.length; i++) {
			IBuildpathEntry other = list[i];
			if (other.getContentKind() == entry.getContentKind() && other.getEntryKind() == entry.getEntryKind()
					&& other.isExported() == entry.isExported() && other.getPath().equals(entry.getPath())) {

				// check inclusion patterns
				IPath[] otherIncludes = other.getInclusionPatterns();
				if (inclusionPatterns != otherIncludes) {
					if (inclusionPatterns == null)
						continue;
					int includeLength = inclusionPatterns.length;
					if (otherIncludes == null || otherIncludes.length != includeLength)
						continue;
					for (int j = 0; j < includeLength; j++) {
						// compare toStrings instead of IPaths
						// since IPath.equals is specified to ignore trailing
						// separators
						if (!inclusionPatterns[j].toString().equals(otherIncludes[j].toString()))
							continue nextEntry;
					}
				}
				// check exclusion patterns
				IPath[] otherExcludes = other.getExclusionPatterns();
				if (exclusionPatterns != otherExcludes) {
					if (exclusionPatterns == null)
						continue;
					int excludeLength = exclusionPatterns.length;
					if (otherExcludes == null || otherExcludes.length != excludeLength)
						continue;
					for (int j = 0; j < excludeLength; j++) {
						// compare toStrings instead of IPaths
						// since IPath.equals is specified to ignore trailing
						// separators
						if (!exclusionPatterns[j].toString().equals(otherExcludes[j].toString()))
							continue nextEntry;
					}
				}
				return i;
			}
		}
		return -1;
	}

	/**
	 * Recursively adds all subfolders of <code>folder</code> to the given
	 * collection.
	 */
	protected void collectAllSubfolders(IFolder folder, ArrayList collection) throws ModelException {
		try {
			IResource[] members = folder.members();
			for (int i = 0, max = members.length; i < max; i++) {
				IResource r = members[i];
				if (r.getType() == IResource.FOLDER) {
					collection.add(r);
					collectAllSubfolders((IFolder) r, collection);
				}
			}
		} catch (CoreException e) {
			throw new ModelException(e);
		}
	}

	/**
	 * Returns a collection of package fragments that have been added/removed as
	 * the result of changing the output location to/from the given location.
	 * The collection is empty if no package fragments are affected.
	 */
	protected ArrayList determineAffectedScriptFolders(IPath location) throws ModelException {
		ArrayList fragments = new ArrayList();

		// see if this will cause any package fragments to be affected
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResource resource = null;
		if (location != null) {
			resource = workspace.getRoot().findMember(location);
		}
		if (resource != null && resource.getType() == IResource.FOLDER) {
			IFolder folder = (IFolder) resource;
			// only changes if it actually existed
			IBuildpathEntry[] buildpath = project.getExpandedBuildpath(true);
			for (int i = 0; i < buildpath.length; i++) {
				IBuildpathEntry entry = buildpath[i];
				IPath path = buildpath[i].getPath();
				if (entry.getEntryKind() != IBuildpathEntry.BPE_PROJECT && path.isPrefixOf(location) && !path.equals(location)) {
					IProjectFragment[] roots = project.computeProjectFragments(buildpath[i]);
					ProjectFragment root = (ProjectFragment) roots[0];
					// now the output location becomes a package fragment -
					// along with any subfolders
					ArrayList folders = new ArrayList();
					folders.add(folder);
					collectAllSubfolders(folder, folders);
					Iterator elements = folders.iterator();
					int segments = path.segmentCount();
					while (elements.hasNext()) {
						IFolder f = (IFolder) elements.next();
						IPath relativePath = f.getFullPath().removeFirstSegments(segments);
						IScriptFolder pkg = root.getScriptFolder(relativePath);
						fragments.add(pkg);
					}
				}
			}
		}
		return fragments;
	}

	/**
	 * Sets the buildpath of the pre-specified project.
	 */
	protected void executeOperation() throws ModelException {
		// project reference updated - may throw an exception if unable to write
		// .project file
		updateProjectReferencesIfNecessary();

		// buildpath file updated - may throw an exception if unable to write
		// .buildpath file
		saveBuildpathIfNecessary();

		// perform buildpath and output location updates, if exception occurs in
		// buildpath update,
		// make sure the output location is updated before surfacing the
		// exception (in case the output
		// location update also throws an exception, give priority to the
		// buildpath update one).
		
		try {
			if (this.newRawPath == DO_NOT_UPDATE_PROJECT_REFS)
				this.newRawPath = project.getRawBuildpath();
			if (this.newRawPath != DO_NOT_SET_ENTRIES) {
				updateBuildpath();
				project.updateProjectFragments();
				ModelManager.getModelManager().getDeltaProcessor().addForRefresh(project);
			}

		} catch (ModelException e) {
			throw e;
		} finally { 
			// if traversed by an exception we still need to update the
			// output location when necessary
			// ensures the project is getting rebuilt if only variable is
			// modified
			if (!this.identicalRoots && this.canChangeResources) {
				try {
					this.project.getProject().touch(this.progressMonitor);
				} catch (CoreException e) {
					if (ModelManager.BP_RESOLVE_VERBOSE) {
						Util.verbose("CPContainer INIT - FAILED to touch project: " + this.project.getElementName(), System.err); //$NON-NLS-1$
						e.printStackTrace();
					}
				}
			}
		}
		done();
	}

	/**
	 * Generates the delta of removed/added/reordered roots. Use three deltas in
	 * case the same root is removed/added/reordered (for instance, if it is
	 * changed from K_SOURCE to K_BINARY or vice versa)
	 */
	protected void generateBuildpathChangeDeltas() {

		ModelManager manager = ModelManager.getModelManager();
		if (manager.deltaState.findProject(this.project.getElementName()) == null)
			// project doesn't exist yet (we're in an IWorkspaceRunnable)
			// no need to create a delta here and no need to index (see
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=133334)
			// the delta processor will create an ADDED project delta, and index
			// the project
			return;
		boolean needToUpdateDependents = false;
		ModelElementDelta delta = new ModelElementDelta(getModel());
		boolean hasDelta = false;
		if (this.buildpathWasSaved) {
			delta.changed(this.project, IModelElementDelta.F_BUILDPATH_CHANGED);
			hasDelta = true;
		}
		int oldLength = oldResolvedPath.length;
		int newLength = newResolvedPath.length;

		final IndexManager indexManager = manager.getIndexManager();
		Map oldRoots = null;
		IProjectFragment[] roots = null;
		if (project.isOpen()) {
			try {
				roots = project.getProjectFragments();
			} catch (ModelException e) {
				// ignore
			}
		} else {
			Map allRemovedRoots;
			if ((allRemovedRoots = manager.getDeltaProcessor().removedRoots) != null) {
				roots = (IProjectFragment[]) allRemovedRoots.get(project);
			}
		}
		if (roots != null) {
			oldRoots = new HashMap();
			for (int i = 0; i < roots.length; i++) {
				IProjectFragment root = roots[i];
				oldRoots.put(root.getPath(), root);
			}
		}
		for (int i = 0; i < oldLength; i++) {

			int index = buildpathContains(newResolvedPath, oldResolvedPath[i]);
			if (index == -1) {
				// do not notify remote project changes
				if (oldResolvedPath[i].getEntryKind() == IBuildpathEntry.BPE_PROJECT) {
					needToUpdateDependents = true;
					this.needCycleCheck = true;
					continue;
				}

				IProjectFragment[] pkgFragmentRoots = null;
				if (oldRoots != null) {
					IProjectFragment oldRoot = (IProjectFragment) oldRoots.get(oldResolvedPath[i].getPath());
					if (oldRoot != null) { // use old root if any (could be
											// none if entry wasn't bound)
						pkgFragmentRoots = new IProjectFragment[] { oldRoot };
					}
				}
				if (pkgFragmentRoots == null) {
					try {
						ObjectVector accumulatedRoots = new ObjectVector();
						HashSet rootIDs = new HashSet(5);
						rootIDs.add(project.rootID());
						project.computeProjectFragments(oldResolvedPath[i], accumulatedRoots, rootIDs, null, // inside
																												// original
																												// project
								false, // don't check existency
								false, // don't retrieve exported roots
								null); /* no reverse map */
						pkgFragmentRoots = new IProjectFragment[accumulatedRoots.size()];
						accumulatedRoots.copyInto(pkgFragmentRoots);
					} catch (ModelException e) {
						pkgFragmentRoots = new IProjectFragment[] {};
					}
				}
				addBuildpathDeltas(pkgFragmentRoots, IModelElementDelta.F_REMOVED_FROM_BUILDPATH, delta);

				int changeKind = oldResolvedPath[i].getEntryKind();
				needToUpdateDependents |= (changeKind == IBuildpathEntry.BPE_SOURCE) || oldResolvedPath[i].isExported();

				// Remove the .java files from the index for a source folder
				// For a lib folder or a archive file, remove the corresponding
				// index if not shared.
				if (indexManager != null) {
					IBuildpathEntry oldEntry = oldResolvedPath[i];
					final IPath path = oldEntry.getPath();
					switch (changeKind) {
					case IBuildpathEntry.BPE_SOURCE:
						final char[][] inclusionPatterns = ((BuildpathEntry) oldEntry).fullInclusionPatternChars();
						final char[][] exclusionPatterns = ((BuildpathEntry) oldEntry).fullExclusionPatternChars();
						postAction(new IPostAction() {
							public String getID() {
								return path.toString();
							}

							public void run() /* throws ModelException */{
								indexManager.removeSourceFolderFromIndex(project, path, inclusionPatterns, exclusionPatterns);
							}
						}, REMOVEALL_APPEND);
						break;
					case IBuildpathEntry.BPE_LIBRARY:
						final DeltaProcessingState deltaState = manager.deltaState;
						postAction(new IPostAction() {
							public String getID() {
								return path.toString();
							}

							public void run() /* throws ModelException */{
								if (deltaState.otherRoots.get(path) == null) { // if
																				// root
																				// was
																				// not
																				// shared
									indexManager.discardJobs(path.toString());
									indexManager.removeIndex(path);
									// TODO (kent) we could just remove the
									// in-memory index and have the indexing
									// check for timestamps
								}
							}
						}, REMOVEALL_APPEND);
						break;
					}
				}
				hasDelta = true;

			} else {
				// do not notify remote project changes
				if (oldResolvedPath[i].getEntryKind() == IBuildpathEntry.BPE_PROJECT) {
					// Need to updated dependents in case old and/or new entries
					// are exported and have an access restriction
					BuildpathEntry oldEntry = (BuildpathEntry) oldResolvedPath[i];
					BuildpathEntry newEntry = (BuildpathEntry) newResolvedPath[index];
					if (oldEntry.isExported || newEntry.isExported) { // then
																		// we
																		// need
																		// to
																		// verify
																		// if
																		// there's
																		// access
																		// restriction
						AccessRuleSet oldRuleSet = oldEntry.getAccessRuleSet();
						AccessRuleSet newRuleSet = newEntry.getAccessRuleSet();
						if (index != i) { // entry has been moved
							needToUpdateDependents |= (oldRuleSet != null || newRuleSet != null); // there's
																									// an
																									// access
																									// restriction,
																									// this
																									// may
																									// change
																									// combination
						} else if (oldRuleSet == null) {
							needToUpdateDependents |= newRuleSet != null; // access
																			// restriction
																			// was
																			// added
						} else {
							needToUpdateDependents |= !oldRuleSet.equals(newRuleSet); // access
																						// restriction
																						// has
																						// changed
																						// or
																						// has
																						// been
																						// removed
						}
					}
					this.needCycleCheck |= (oldEntry.isExported() != newEntry.isExported());
					continue;
				}
				needToUpdateDependents |= (oldResolvedPath[i].isExported() != newResolvedPath[index].isExported());
				if (index != i) { // reordering of the buildpath
					addBuildpathDeltas(project.computeProjectFragments(oldResolvedPath[i]), IModelElementDelta.F_REORDER, delta);
					int changeKind = oldResolvedPath[i].getEntryKind();
					needToUpdateDependents |= (changeKind == IBuildpathEntry.BPE_SOURCE);

					hasDelta = true;
				}
			}
		}

		for (int i = 0; i < newLength; i++) {

			int index = buildpathContains(oldResolvedPath, newResolvedPath[i]);
			if (index == -1) {
				// do not notify remote project changes
				if (newResolvedPath[i].getEntryKind() == IBuildpathEntry.BPE_PROJECT) {
					needToUpdateDependents = true;
					this.needCycleCheck = true;
					continue;
				}
				addBuildpathDeltas(project.computeProjectFragments(newResolvedPath[i]), IModelElementDelta.F_ADDED_TO_BUILDPATH, delta);
				int changeKind = newResolvedPath[i].getEntryKind();

				// Request indexing
				if (indexManager != null) {
					switch (changeKind) {
					case IBuildpathEntry.BPE_LIBRARY:
						boolean pathHasChanged = true;
						final IPath newPath = newResolvedPath[i].getPath();
						for (int j = 0; j < oldLength; j++) {
							IBuildpathEntry oldEntry = oldResolvedPath[j];
							if (oldEntry.getPath().equals(newPath)) {
								pathHasChanged = false;
								break;
							}
						}
						if (pathHasChanged) {
							postAction(new IPostAction() {
								public String getID() {
									return newPath.toString();
								}

								public void run() /* throws ModelException */{
									indexManager.indexLibrary(newPath, project.getProject());
								}
							}, REMOVEALL_APPEND);
						}
						break;
					case IBuildpathEntry.BPE_SOURCE:
						IBuildpathEntry entry = newResolvedPath[i];
						final IPath path = entry.getPath();
						final char[][] inclusionPatterns = ((BuildpathEntry) entry).fullInclusionPatternChars();
						final char[][] exclusionPatterns = ((BuildpathEntry) entry).fullExclusionPatternChars();
						postAction(new IPostAction() {
							public String getID() {
								return path.toString();
							}

							public void run() /* throws ModelException */{
								indexManager.indexSourceFolder(project, path, inclusionPatterns, exclusionPatterns);
							}
						}, APPEND); // append so that a removeSourceFolder
									// action is not removed
						break;
					}
				}

				needToUpdateDependents |= (changeKind == IBuildpathEntry.BPE_SOURCE) || newResolvedPath[i].isExported();
				hasDelta = true;

			} // buildpath reordering has already been generated in previous
				// loop
		}

		if (hasDelta) {
			this.addDelta(delta);
		} else {
			this.identicalRoots = true;
		}
		if (needToUpdateDependents) {
			updateAffectedProjects(project.getProject().getFullPath());
		}
	}

	protected ISchedulingRule getSchedulingRule() {
		return null; // no lock taken while setting the buildpath
	}

	/**
	 * Returns <code>true</code> if this operation performs no resource
	 * modifications, otherwise <code>false</code>. Subclasses must override.
	 */
	public boolean isReadOnly() {
		return !this.canChangeResources;
	}

	protected void saveBuildpathIfNecessary() throws ModelException {

		if (!this.canChangeResources || !this.needSave)
			return;

		IBuildpathEntry[] buildpathForSave;
		if (this.newRawPath == DO_NOT_SET_ENTRIES || this.newRawPath == DO_NOT_UPDATE_PROJECT_REFS) {
			buildpathForSave = project.getRawBuildpath();
		} else {
			buildpathForSave = this.newRawPath;
		}
		// if read-only .buildpath, then the buildpath setting will never been
		// performed completely
		if (project.saveBuildpath(buildpathForSave)) {
			this.buildpathWasSaved = true;
			this.setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(20);
		buffer.append("SetBuildpathOperation\n"); //$NON-NLS-1$
		buffer.append(" - buildpath : "); //$NON-NLS-1$
		if (this.newRawPath == DO_NOT_SET_ENTRIES) {
			buffer.append("<Reuse Existing Buildpath Entries>"); //$NON-NLS-1$
		} else {
			buffer.append("{"); //$NON-NLS-1$
			for (int i = 0; i < this.newRawPath.length; i++) {
				if (i > 0)
					buffer.append(","); //$NON-NLS-1$
				IBuildpathEntry element = this.newRawPath[i];
				buffer.append(" ").append(element.toString()); //$NON-NLS-1$
			}
		}		
		return buffer.toString();
	}

	private void updateBuildpath() throws ModelException {

		beginTask(Messages.bind(Messages.buildpath_settingProgress, project.getElementName()), 2);

		// SIDE-EFFECT: from thereon, the buildpath got modified
		project.getPerProjectInfo().updateBuildpathInformation(this.newRawPath);

		// resolve new path (asking for marker creation if problems)
		if (this.newResolvedPath == null) {
			this.newResolvedPath = project.getResolvedBuildpath(true, this.canChangeResources, false/*
																									 * don't
																									 * returnResolutionInProgress
																									 */);
		}

		if (this.oldResolvedPath != null) {
			generateBuildpathChangeDeltas();
		} else {
			this.needCycleCheck = true;
			updateAffectedProjects(project.getProject().getFullPath());
		}

		updateCycleMarkersIfNecessary();
	}

	/**
	 * Update projects which are affected by this buildpath change: those which
	 * refers to the current project as source (indirectly)
	 */
	protected void updateAffectedProjects(IPath prerequisiteProjectPath) {

		// remove all update buildpath post actions for this project
		final String updateBuildpath = "UpdateClassPath:"; //$NON-NLS-1$
		removeAllPostAction(updateBuildpath + prerequisiteProjectPath.toString());

		try {
			IScriptModel model = ModelManager.getModelManager().getModel();
			IDLTKProject initialProject = this.project;
			IDLTKProject[] projects = model.getScriptProjects();
			for (int i = 0, projectCount = projects.length; i < projectCount; i++) {
				try {
					final DLTKProject affectedProject = (DLTKProject) projects[i];
					if (affectedProject.equals(initialProject))
						continue; // skip itself
					if (!affectedProject.isOpen())
						continue; // skip project as its namelookup caches do
									// not exist

					// consider ALL dependents (even indirect ones), since they
					// may need to
					// flush their respective namelookup caches (all pkg
					// fragment roots).

					IBuildpathEntry[] buildpath = affectedProject.getExpandedBuildpath(true);
					for (int j = 0, entryCount = buildpath.length; j < entryCount; j++) {
						IBuildpathEntry entry = buildpath[j];
						if (entry.getEntryKind() == IBuildpathEntry.BPE_PROJECT && entry.getPath().equals(prerequisiteProjectPath)) {

							postAction(new IPostAction() {
								public String getID() {
									return updateBuildpath + affectedProject.getPath().toString();
								}

								public void run() throws ModelException {
									affectedProject.setRawBuildpath(DO_NOT_UPDATE_PROJECT_REFS, SetBuildpathOperation.this.progressMonitor,
											SetBuildpathOperation.this.canChangeResources, affectedProject
													.getResolvedBuildpath(true/* ignoreUnresolvedEntry */, false/*
																												 * don't
																												 * generateMarkerOnError
																												 */,
															false/*
																	 * don't
																	 * returnResolutionInProgress
																	 */), false, // updating
																												// only
																												// - no
																												// validation
											false); // updating only - no need
													// to save
								}
							}, REMOVEALL_APPEND);
							break;
						}
					}
				} catch (ModelException e) {
					// ignore
				}
			}
		} catch (ModelException e) {
			// ignore
		}

	}

	/**
	 * Update cycle markers
	 */
	protected void updateCycleMarkersIfNecessary() {

		if (!this.needCycleCheck)
			return;
		if (!this.canChangeResources)
			return;

		if (!project.hasCycleMarker() && !project.hasBuildpathCycle(newResolvedPath)) {
			return;
		}

		postAction(new IPostAction() {
			public String getID() {
				return "updateCycleMarkers"; //$NON-NLS-1$
			}

			public void run() throws ModelException {
				DLTKProject.updateAllCycleMarkers(null);
			}
		}, REMOVEALL_APPEND);
	}

	/**
	 * Update projects references so that the build order is consistent with the
	 * buildpath
	 */
	protected void updateProjectReferencesIfNecessary() throws ModelException {

		if (this.newRawPath == DO_NOT_SET_ENTRIES || this.newRawPath == DO_NOT_UPDATE_PROJECT_REFS)
			return;
		// will run now, or be deferred until next pre-auto-build notification
		// if resource tree is locked
		ModelManager.getModelManager().deltaState.updateProjectReferences(project, oldResolvedPath, newResolvedPath, newRawPath, canChangeResources);
	}

	public IModelStatus verify() {

		IModelStatus status = super.verify();
		if (!status.isOK()) {
			return status;
		}

		if (needValidation) {
			// retrieve buildpath
			IBuildpathEntry[] entries = this.newRawPath;
			if (entries == DO_NOT_SET_ENTRIES) {
				try {
					entries = project.getRawBuildpath();
				} catch (ModelException e) {
					return e.getModelStatus();
				}
			}

			// perform validation
			return BuildpathEntry.validateBuildpath(project, entries);
		}

		return ModelStatus.VERIFIED_OK;
	}
}
