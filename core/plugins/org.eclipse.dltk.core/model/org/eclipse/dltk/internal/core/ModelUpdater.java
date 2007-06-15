/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.core;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;


/**
 * This class is used by <code>ModelManager</code> to update the Model
 * based on some <code>IElementDelta</code>s.
 */
public class ModelUpdater {

	HashSet projectsToUpdate = new HashSet();

	/**
	 * Adds the given child handle to its parent's cache of children. 
	 */
	protected void addToParentInfo(Openable child) {

		Openable parent = (Openable) child.getParent();
		if (parent != null && parent.isOpen()) {
			try {
				ModelElementInfo info = (ModelElementInfo)parent.getElementInfo();
				info.addChild(child);
			} catch (ModelException e) {
				// do nothing - we already checked if open
			}
		}
	}

	/**
	 * Closes the given element, which removes it from the cache of open elements.
	 */
	protected static void close(Openable element) {

		try {
			element.close();
		} catch (ModelException e) {
			// do nothing
		}
	}

	/**
	 * Processing for an element that has been added:<ul>
	 * <li>If the element is a project, do nothing, and do not process
	 * children, as when a project is created it does not yet have any
	 * natures - specifically a script nature.
	 * <li>If the elemet is not a project, process it as added (see
	 * <code>basicElementAdded</code>.
	 * </ul>
	 */
	protected void elementAdded(Openable element) {

		int elementType = element.getElementType();
		if (elementType == IModelElement.SCRIPT_PROJECT) {
			// project add is handled by ScriptProject.configure() because
			// when a project is created, it does not yet have a script nature
			addToParentInfo(element);
			this.projectsToUpdate.add(element);
		} else {
			addToParentInfo(element);

			// Force the element to be closed as it might have been opened 
			// before the resource modification came in and it might have a new child
			// For example, in an IWorkspaceRunnable:
			// 1. create a package fragment p using a script model operation
			// 2. open package p
			// 3. add file X.java in folder p
			// When the resource delta comes in, only the addition of p is notified, 
			// but the package p is already opened, thus its children are not recomputed
			// and it appears empty.
			close(element);
		}

		switch (elementType) {
			case IModelElement.PROJECT_FRAGMENT :
				// when a root is added, and is on the buildpath, the project must be updated
				this.projectsToUpdate.add(element.getScriptProject());
				break;
			case IModelElement.SCRIPT_FOLDER :
				// get rid of package fragment cache
				ScriptProject project = (ScriptProject) element.getScriptProject();
				project.resetCaches();
				break;
		}
	}

	/**
	 * Generic processing for elements with changed contents:<ul>
	 * <li>The element is closed such that any subsequent accesses will re-open
	 * the element reflecting its new structure.
	 * </ul>
	 */
	protected void elementChanged(Openable element) {

		close(element);
	}

	/**
	 * Generic processing for a removed element:<ul>
	 * <li>Close the element, removing its structure from the cache
	 * <li>Remove the element from its parent's cache of children
	 * <li>Add a REMOVED entry in the delta
	 * </ul>
	 */
	protected void elementRemoved(Openable element) {

		if (element.isOpen()) {
			close(element);
		}
		removeFromParentInfo(element);
		int elementType = element.getElementType();

		switch (elementType) {
			case IModelElement.SCRIPT_MODEL :
				ModelManager.getModelManager().getIndexManager().reset();
				break;
			case IModelElement.SCRIPT_PROJECT :
				ModelManager manager = ModelManager.getModelManager();
				ScriptProject scriptProject = (ScriptProject) element;
				manager.removePerProjectInfo(scriptProject);
				manager.containerRemove(scriptProject);
				break;
			case IModelElement.PROJECT_FRAGMENT :
				this.projectsToUpdate.add(element.getScriptProject());
				break;
			case IModelElement.SCRIPT_FOLDER :
				// get rid of package fragment cache
				ScriptProject project = (ScriptProject) element.getScriptProject();
				project.resetCaches();
				break;
		}
	}

	/**
	 * Converts a <code>IResourceDelta</code> rooted in a <code>Workspace</code> into
	 * the corresponding set of <code>IModelElementDelta</code>, rooted in the
	 * relevant <code>Model</code>s.
	 */
	public void processDelta(IModelElementDelta delta) {

//		if (DeltaProcessor.VERBOSE){
//			System.out.println("UPDATING Model with Delta: ["+Thread.currentThread()+":" + delta + "]:");
//		}

		try {
			this.traverseDelta(delta, null, null); // traverse delta

			// update package fragment roots of projects that were affected
			Iterator iterator = this.projectsToUpdate.iterator();
			while (iterator.hasNext()) {
				ScriptProject project = (ScriptProject) iterator.next();
				project.updateProjectFragments();
			}
		} finally {
			this.projectsToUpdate = new HashSet();
		}
	}

	/**
	 * Removes the given element from its parents cache of children. If the
	 * element does not have a parent, or the parent is not currently open,
	 * this has no effect. 
	 */
	protected void removeFromParentInfo(Openable child) {

		Openable parent = (Openable) child.getParent();
		if (parent != null && parent.isOpen()) {
			try {
				ModelElementInfo info = (ModelElementInfo)parent.getElementInfo();
				info.removeChild(child);
			} catch (ModelException e) {
				// do nothing - we already checked if open
			}
		}
	}

	/**
	 * Converts an <code>IResourceDelta</code> and its children into
	 * the corresponding <code>IModelElementDelta</code>s.
	 * Return whether the delta corresponds to a resource on the buildpath.
	 * If it is not a resource on the buildpath, it will be added as a non-java
	 * resource by the sender of this method.
	 */
	protected void traverseDelta(
		IModelElementDelta delta,
		IProjectFragment root,
		IScriptProject project) {

		boolean processChildren = true;

		Openable element = (Openable) delta.getElement();
		switch (element.getElementType()) {
			case IModelElement.SCRIPT_PROJECT :
				project = (IScriptProject) element;
				break;
			case IModelElement.PROJECT_FRAGMENT :
				root = (IProjectFragment) element;
				break;
			case IModelElement.SOURCE_MODULE :
				// filter out working copies that are not primary (we don't want to add/remove them to/from the package fragment
				SourceModule cu = (SourceModule)element;
				if (cu.isWorkingCopy() && !cu.isPrimary()) {
					return;
				}
			case IModelElement.BINARY_MODULE :
				processChildren = false;
				break;
		}

		switch (delta.getKind()) {
			case IModelElementDelta.ADDED :
				elementAdded(element);
				break;
			case IModelElementDelta.REMOVED :
				elementRemoved(element);
				break;
			case IModelElementDelta.CHANGED :
				if ((delta.getFlags() & IModelElementDelta.F_CONTENT) != 0){
					elementChanged(element);
				}
				break;
		}
		if (processChildren) {
			IModelElementDelta[] children = delta.getAffectedChildren();
			for (int i = 0; i < children.length; i++) {
				IModelElementDelta childDelta = children[i];
				this.traverseDelta(childDelta, root, project);
			}
		}
	}
}
