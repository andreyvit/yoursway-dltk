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
package org.eclipse.dltk.ui.browsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IPackageDeclaration;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.StandardModelElementContentProvider;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

class ScriptBrowsingContentProvider extends StandardModelElementContentProvider
		implements IElementChangedListener {

	private StructuredViewer fViewer;
	private Object fInput;
	private ScriptBrowsingPart fBrowsingPart;
	private int fReadsInDisplayThread;
	private IDLTKLanguageToolkit fToolkit;

	public ScriptBrowsingContentProvider(boolean provideMembers,
			ScriptBrowsingPart browsingPart, IDLTKLanguageToolkit languageToolkit) {
		super(provideMembers);
		fBrowsingPart = browsingPart;
		fViewer = fBrowsingPart.getViewer();
		DLTKCore.addElementChangedListener(this);
		this.fToolkit = languageToolkit;
	}
	public IDLTKLanguageToolkit getToolkit() {
		return this.fToolkit;
	}

	public boolean hasChildren(Object element) {
		startReadInDisplayThread();
		try {
			return super.hasChildren(element);
		} finally {
			finishedReadInDisplayThread();
		}
	}

	public Object[] getChildren(Object element) {
		if (!exists(element))
			return NO_CHILDREN;

		startReadInDisplayThread();
		try {
			if (element instanceof Collection) {
				Collection elements = (Collection) element;
				if (elements.isEmpty())
					return NO_CHILDREN;
				Object[] result = new Object[0];
				Iterator iter = ((Collection) element).iterator();
				while (iter.hasNext()) {
					Object[] children = getChildren(iter.next());
					if (children != NO_CHILDREN)
						result = concatenate(result, children);
				}
				return filterExternal( result );
			}
			if (element instanceof IScriptFolder)
				return getScriptFolderContents((IScriptFolder) element);
			if (fProvideMembers && element instanceof IType)
				return getChildren((IType) element);
			if (fProvideMembers && element instanceof ISourceReference
					&& element instanceof IParent)
				return removeImportAndPackageDeclarations(super
						.getChildren(element));
			if (element instanceof IScriptProject)
				return getProjectFragments((IScriptProject) element);
			return super.getChildren(element);
		} catch (ModelException e) {
			return NO_CHILDREN;
		} finally {
			finishedReadInDisplayThread();
		}
	}

	private Object[] filterExternal(Object[] elements) {
		List result = new ArrayList();
		Map pr = new HashMap();
		for (int i = 0; i < elements.length; i++) {
			Object element = elements[i];
			if( element instanceof IModelElement ) {
				String name = ((IModelElement)element).getElementName();
				if( pr.containsKey(name) ) {
					IModelElement pre = (IModelElement) pr.get(name);
					IProjectFragment preModule = (IProjectFragment) pre.getAncestor(IModelElement.PROJECT_FRAGMENT);
					IProjectFragment elementModule = (IProjectFragment) ((IModelElement)element).getAncestor(IModelElement.PROJECT_FRAGMENT);
					if( preModule.isExternal() && !elementModule.isExternal()) { // we need to replace with this element.
						result.remove(pre);
						result.add(element);
						pr.put(name, element);
					}
				}
				else {
					result.add(element);
					pr.put(name, element);
				}
			}
			else {
				result.add(element);
			}
		}
		return result.toArray();
	}
	private Object[] getScriptFolderContents(IScriptFolder fragment)
			throws ModelException {
		ISourceReference[] sourceRefs;
		// if (fragment.getElementType() == IProjectFragment.K_SOURCE) {
		sourceRefs = fragment.getSourceModules();
		// }

		Object[] result = new Object[0];
		for (int i = 0; i < sourceRefs.length; i++)
			result = concatenate(
					result,
					removeImportAndPackageDeclarations(getChildren(sourceRefs[i])));
		return concatenate(result, fragment.getForeignResources());
	}

	private Object[] removeImportAndPackageDeclarations(Object[] members) {
		ArrayList tempResult = new ArrayList(members.length);
		for (int i = 0; i < members.length; i++)
			if (/* !(members[i] instanceof IImportContainer) && */!(members[i] instanceof IPackageDeclaration))
				tempResult.add(members[i]);
		return tempResult.toArray();
	}

	private Object[] getChildren(IType type) throws ModelException {
//		IParent parent;
		// if (type.isBinary())
		// parent= type.getClassFile();
		// else {
//		parent = type.getSourceModule();
		// }
//		if (type.getDeclaringType() != null)
//			return type.getChildren();

		// Add import declarations
		IModelElement[] members = type.getChildren();
		ArrayList tempResult = new ArrayList(members.length);
		// for (int i= 0; i < members.length; i++)
		// if ((members[i] instanceof IImportContainer))
		// tempResult.add(members[i]);
		tempResult.addAll(Arrays.asList(type.getChildren()));
		return tempResult.toArray();
	}

	protected Object[] getProjectFragments(IScriptProject project)
			throws ModelException {
		if (!project.getProject().isOpen())
			return NO_CHILDREN;

		IProjectFragment[] roots = project.getProjectFragments();
		List list = new ArrayList(roots.length);
		// filter out package fragments that correspond to projects and
		// replace them with the package fragments directly
		for (int i = 0; i < roots.length; i++) {
			IProjectFragment root = roots[i];
			if (!root.isExternal()) {
				Object[] children = root.getChildren();
				for (int k = 0; k < children.length; k++)
					list.add(children[k]);
			} else if (hasChildren(root)) {
				list.add(root);
			}
		}
		return concatenate(list.toArray(), project.getForeignResources());
	}

	// ---------------- Element change handling

	/*
	 * (non-Javadoc) Method declared on IContentProvider.
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		super.inputChanged(viewer, oldInput, newInput);

		if (newInput instanceof Collection) {
			// Get a template object from the collection
			Collection col = (Collection) newInput;
			if (!col.isEmpty())
				newInput = col.iterator().next();
			else
				newInput = null;
		}
		fInput = newInput;
	}

	/*
	 * (non-Javadoc) Method declared on IContentProvider.
	 */
	public void dispose() {
		super.dispose();
		DLTKCore.removeElementChangedListener(this);
	}

	/*
	 * (non-Javadoc) Method declared on IElementChangedListener.
	 */
	public void elementChanged(final ElementChangedEvent event) {
		try {
			processDelta(event.getDelta());
		} catch (ModelException e) {
			DLTKUIPlugin.log(e.getStatus());
		}
	}

	/**
	 * Processes a delta recursively. When more than two children are affected
	 * the tree is fully refreshed starting at this node. The delta is processed
	 * in the current thread but the viewer updates are posted to the UI thread.
	 */
	protected void processDelta(IModelElementDelta delta) throws ModelException {
		int kind = delta.getKind();
		int flags = delta.getFlags();
		final IModelElement element = delta.getElement();
		final boolean isElementValidForView = fBrowsingPart
				.isValidElement(element);

		// if (!getProvideWorkingCopy() && element instanceof ISourceModule &&
		// ((ISourceModule)element).isWorkingCopy())
		// return;

		if (element != null
				&& element.getElementType() == IModelElement.SOURCE_MODULE
				&& !isOnBuildPath((ISourceModule) element))
			return;

		// handle open and closing of a solution or project
		if (((flags & IModelElementDelta.F_CLOSED) != 0)
				|| ((flags & IModelElementDelta.F_OPENED) != 0)) {
			postRefresh(null);
			return;
		}

		if (kind == IModelElementDelta.REMOVED) {
			Object parent = internalGetParent(element);
			if (isElementValidForView) {
				if (element instanceof ISourceModule
						&& !((ISourceModule) element).isWorkingCopy()) {
					postRefresh(null);
				} else if (element instanceof ISourceModule
						&& ((ISourceModule) element).isWorkingCopy()) {
					// if (getProvideWorkingCopy())
					postRefresh(null);
				} else if (parent instanceof ISourceModule /*
															 * &&
															 * getProvideWorkingCopy()
															 */
						&& !((ISourceModule) parent).isWorkingCopy()) {
					if (element instanceof ISourceModule
							&& ((ISourceModule) element).isWorkingCopy()) {
						// working copy removed from system - refresh
						postRefresh(null);
					}
				} else if (element instanceof ISourceModule
						&& ((ISourceModule) element).isWorkingCopy()
						&& parent != null && parent.equals(fInput))
					// closed editor - removing working copy
					postRefresh(null);
				else
					postRemove(element);
			}

			if (fBrowsingPart.isAncestorOf(element, fInput)) {
				if (element instanceof ISourceModule
						&& ((ISourceModule) element).isWorkingCopy()) {
					postAdjustInputAndSetSelection(((IModelElement) fInput)
							.getPrimaryElement());
				} else
					postAdjustInputAndSetSelection(null);
			}

			if (fInput != null && fInput.equals(element))
				postRefresh(null);

			if (parent instanceof IScriptFolder
					&& fBrowsingPart.isValidElement(parent)) {
				// refresh if package gets empty (might be filtered)
				if (isScriptFolderEmpty((IScriptFolder) parent)
						&& fViewer.testFindItem(parent) != null)
					postRefresh(null);
			}

			return;
		}
		if (kind == IModelElementDelta.ADDED
				&& delta.getMovedFromElement() != null
				&& element instanceof ISourceModule)
			return;

		if (kind == IModelElementDelta.ADDED) {
			if (isElementValidForView) {
				Object parent = internalGetParent(element);
				if (element instanceof ISourceModule
						&& !((ISourceModule) element).isWorkingCopy()) {
					postAdd(parent, ((ISourceModule) element).getTypes());
				} else if (parent instanceof ISourceModule
						&& !((ISourceModule) parent).isWorkingCopy()) {
					// do nothing
				} else if (element instanceof ISourceModule
						&& ((ISourceModule) element).isWorkingCopy()) {
					// new working copy comes to live
					postRefresh(null);
				} else
					postAdd(parent, element);
			} else if (fInput == null) {
				IModelElement newInput = fBrowsingPart
						.findInputForJavaElement(element);
				if (newInput != null)
					postAdjustInputAndSetSelection(element);
			} else if (element instanceof IType
					&& fBrowsingPart.isValidInput(element)) {
				IModelElement cu1 = element
						.getAncestor(IModelElement.SOURCE_MODULE);
				IModelElement cu2 = ((IModelElement) fInput)
						.getAncestor(IModelElement.SOURCE_MODULE);
				if (cu1 != null && cu2 != null && cu1.equals(cu2))
					postAdjustInputAndSetSelection(element);
			}
			return;
		}

		if (kind == IModelElementDelta.CHANGED) {
			if (fInput != null && fInput.equals(element)
					&& (flags & IModelElementDelta.F_CHILDREN) != 0
					&& (flags & IModelElementDelta.F_FINE_GRAINED) != 0) {
				postRefresh(null, true);
				return;
			}
			if (isElementValidForView
					&& (flags & IModelElementDelta.F_MODIFIERS) != 0) {
				postUpdateIcon(element);
			}
		}

		if (isBuildPathChange(delta))
			// throw the towel and do a full refresh
			postRefresh(null);

		if ((flags & IModelElementDelta.F_ARCHIVE_CONTENT_CHANGED) != 0
				&& fInput instanceof IModelElement) {
			IProjectFragment pkgRoot = (IProjectFragment) element;
			IModelElement inputsParent = ((IModelElement) fInput)
					.getAncestor(IModelElement.PROJECT_FRAGMENT);
			if (pkgRoot.equals(inputsParent))
				postRefresh(null);
		}

		// the source attachment of a JAR has changed
		// if (element instanceof IProjectFragment && (((flags &
		// IModelElementDelta.F_SOURCEATTACHED) != 0 || ((flags &
		// IModelElementDelta.F_SOURCEDETACHED)) != 0)))
		// postUpdateIcon(element);

		IModelElementDelta[] affectedChildren = delta.getAffectedChildren();
		if (affectedChildren.length > 1) {
			// a package fragment might become non empty refresh from the parent
			if (element instanceof IScriptFolder) {
				IModelElement parent = (IModelElement) internalGetParent(element);
				// avoid posting a refresh to an invisible parent
				if (element.equals(fInput)) {
					postRefresh(element);
				} else {
					postRefresh(parent);
				}
			}
			// more than one child changed, refresh from here downwards
			if (element instanceof IProjectFragment && isElementValidForView) {
				postRefresh(skipProjectProjectFragment((IProjectFragment) element));
				return;
			}
		}
		for (int i = 0; i < affectedChildren.length; i++) {
			processDelta(affectedChildren[i]);
		}
	}

	private boolean isOnBuildPath(ISourceModule element) throws ModelException {
		IScriptProject project = element.getScriptProject();
		if (project == null || !project.exists())
			return false;
		return project.isOnBuildpath(element);
	}

	/**
	 * Updates the package icon
	 */
	private void postUpdateIcon(final IModelElement element) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed())
					fViewer.update(element,
							new String[] { IBasicPropertyConstants.P_IMAGE });
			}
		});
	}

	private void postRefresh(final Object root, final boolean updateLabels) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed())
					fViewer.refresh(root, updateLabels);
			}
		});
	}

	private void postRefresh(final Object root) {
		postRefresh(root, false);
	}

	private void postAdd(final Object parent, final Object element) {
		postAdd(parent, new Object[] { element });
	}

	private void postAdd(final Object parent, final Object[] elements) {
		if (elements == null || elements.length <= 0)
			return;

		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					Object[] newElements = getNewElements(elements);
					if (fViewer instanceof AbstractTreeViewer) {
						if (fViewer.testFindItem(parent) == null) {
							Object root = ((AbstractTreeViewer) fViewer)
									.getInput();
							if (root != null)
								((AbstractTreeViewer) fViewer).add(root,
										newElements);
						} else
							((AbstractTreeViewer) fViewer).add(parent,
									newElements);
					} else if (fViewer instanceof ListViewer)
						((ListViewer) fViewer).add(newElements);
					else if (fViewer instanceof TableViewer)
						((TableViewer) fViewer).add(newElements);
					if (fViewer.testFindItem(elements[0]) != null)
						fBrowsingPart.adjustInputAndSetSelection(elements[0]);
				}
			}
		});
	}

	private Object[] getNewElements(Object[] elements) {
		int elementsLength = elements.length;
		ArrayList result = new ArrayList(elementsLength);
		for (int i = 0; i < elementsLength; i++) {
			Object element = elements[i];
			if (fViewer.testFindItem(element) == null)
				result.add(element);
		}
		return result.toArray();
	}

	private void postRemove(final Object element) {
		postRemove(new Object[] { element });
	}

	private void postRemove(final Object[] elements) {
		if (elements.length <= 0)
			return;

		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					if (fViewer instanceof AbstractTreeViewer)
						((AbstractTreeViewer) fViewer).remove(elements);
					else if (fViewer instanceof ListViewer)
						((ListViewer) fViewer).remove(elements);
					else if (fViewer instanceof TableViewer)
						((TableViewer) fViewer).remove(elements);
				}
			}
		});
	}

	private void postAdjustInputAndSetSelection(final Object element) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					ctrl.setRedraw(false);
					fBrowsingPart.adjustInputAndSetSelection(element);
					ctrl.setRedraw(true);
				}
			}
		});
	}

	protected void startReadInDisplayThread() {
		if (isDisplayThread())
			fReadsInDisplayThread++;
	}

	protected void finishedReadInDisplayThread() {
		if (isDisplayThread())
			fReadsInDisplayThread--;
	}

	private boolean isDisplayThread() {
		Control ctrl = fViewer.getControl();
		if (ctrl == null)
			return false;

		Display currentDisplay = Display.getCurrent();
		return currentDisplay != null
				&& currentDisplay.equals(ctrl.getDisplay());
	}

	private void postRunnable(final Runnable r) {
		Control ctrl = fViewer.getControl();
		if (ctrl != null && !ctrl.isDisposed()) {
			fBrowsingPart.setProcessSelectionEvents(false);
			try {
				if (isDisplayThread() && fReadsInDisplayThread == 0)
					ctrl.getDisplay().syncExec(r);
				else
					ctrl.getDisplay().asyncExec(r);
			} finally {
				fBrowsingPart.setProcessSelectionEvents(true);
			}
		}
	}

	/**
	 * Returns the parent for the element.
	 * <p>
	 * Note: This method will return a working copy if the parent is a working
	 * copy. The super class implementation returns the original element
	 * instead.
	 * </p>
	 */
	protected Object internalGetParent(Object element) {
		if (element instanceof IScriptProject) {
			return ((IScriptProject) element).getModel();
		}
		// try to map resources to the containing package fragment
		if (element instanceof IResource) {
			IResource parent = ((IResource) element).getParent();
			Object jParent = DLTKCore.create(parent);
			if (jParent != null)
				return jParent;
			return parent;
		}

		// for package fragments that are contained in a project package
		// fragment
		// we have to skip the package fragment root as the parent.
		if (element instanceof IScriptFolder) {
			IProjectFragment parent = (IProjectFragment) ((IScriptFolder) element)
					.getParent();
			return skipProjectProjectFragment(parent);
		}
		if (element instanceof IModelElement)
			return ((IModelElement) element).getParent();

		return null;
	}
}
