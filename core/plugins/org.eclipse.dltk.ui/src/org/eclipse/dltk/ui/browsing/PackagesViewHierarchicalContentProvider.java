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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Tree content provider for the hierarchical layout in the packages view.
 * <p>
 * XXX: The standard Java browsing part content provider needs and calls the
 * browsing part/view. This class currently doesn't need to do so but might be
 * required to later.
 * </p>
 */
class PackagesViewHierarchicalContentProvider extends LogicalPackagesProvider
		implements ITreeContentProvider {

	public PackagesViewHierarchicalContentProvider(StructuredViewer viewer) {
		super(viewer);
	}

	/*
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		try {
			if (parentElement instanceof IModelElement) {
				IModelElement iJavaElement = (IModelElement) parentElement;
				int type = iJavaElement.getElementType();

				switch (type) {
				case IModelElement.SCRIPT_PROJECT: {

					// create new element mapping
					fMapToLogicalPackage.clear();
					fMapToPackageFragments.clear();
					IScriptProject project = (IScriptProject) parentElement;

					IScriptFolder[] topLevelChildren = getTopLevelChildrenByElementName(project
							.getScriptFolders());
					List list = new ArrayList();
					for (int i = 0; i < topLevelChildren.length; i++) {
						IScriptFolder fragment = topLevelChildren[i];

						IModelElement el = fragment.getParent();
						if (el instanceof IProjectFragment) {
							IProjectFragment root = (IProjectFragment) el;
							if (!root.isArchive() || !root.isExternal())
								list.add(fragment);
						}
					}

					IProjectFragment[] packageFragmentRoots = project
							.getProjectFragments();
					List folders = new ArrayList();
					for (int i = 0; i < packageFragmentRoots.length; i++) {
						IProjectFragment root = packageFragmentRoots[i];
						IResource resource = root.getUnderlyingResource();
						if (resource != null && resource instanceof IFolder) {
							folders.addAll(getFolders(((IFolder) resource)
									.members()));
						}
					}

					Object[] logicalPackages = combineSamePackagesIntoLogialPackages((IScriptFolder[]) list
							.toArray(new IScriptFolder[list.size()]));
					if (folders.size() > 0) {
						if (logicalPackages.length > 0)
							folders.addAll(Arrays.asList(logicalPackages));
						return folders.toArray();
					} else {
						return logicalPackages;
					}
				}

				case IModelElement.PROJECT_FRAGMENT: {
					IProjectFragment root = (IProjectFragment) parentElement;

					// create new element mapping
					fMapToLogicalPackage.clear();
					fMapToPackageFragments.clear();
					IResource resource = root.getUnderlyingResource();
					if (root.isArchive()) {
						IScriptFolder[] fragments = new IScriptFolder[0];
						IModelElement[] els = root.getChildren();
						fragments = getTopLevelChildrenByElementName(els);
						addFragmentsToMap(fragments);
						return fragments;

					} else if (resource != null && resource instanceof IFolder) {
						List children = getFoldersAndElements(((IFolder) resource)
								.members());

						IScriptFolder defaultPackage = root.getScriptFolder(""); //$NON-NLS-1$
						if (defaultPackage.exists())
							children.add(defaultPackage);

						addFragmentsToMap(children);
						return children.toArray();
					} else {
						return NO_CHILDREN;
					}
				}

				case IModelElement.SCRIPT_FOLDER: {
					IScriptFolder packageFragment = (IScriptFolder) parentElement;
					if (packageFragment.isRootFolder())
						return NO_CHILDREN;

					IProjectFragment parent = (IProjectFragment) packageFragment
							.getParent();
					IScriptFolder[] fragments = findNextLevelChildrenByElementName(
							parent, packageFragment);

					addFragmentsToMap(fragments);

					Object[] nonJavaResources = packageFragment
							.getForeignResources();
					if (nonJavaResources.length == 0) {
						return fragments;
					}
					ArrayList combined = new ArrayList();
					combined.addAll(Arrays.asList(fragments));
					for (int i = 0; i < nonJavaResources.length; i++) {
						Object curr = nonJavaResources[i];
						if (curr instanceof IFolder) {
							combined.add(curr);
						}
					}
					return combined.toArray();
				}
				}

				// @Improve: rewrite using concatenate
			} else if (parentElement instanceof LogicalPackage) {

				List children = new ArrayList();
				LogicalPackage logicalPackage = (LogicalPackage) parentElement;
				IScriptFolder[] elements = logicalPackage.getScriptFolders();
				for (int i = 0; i < elements.length; i++) {
					IScriptFolder fragment = elements[i];
					IScriptFolder[] objects = findNextLevelChildrenByElementName(
							(IProjectFragment) fragment.getParent(), fragment);
					children.addAll(Arrays.asList(objects));
				}
				return combineSamePackagesIntoLogialPackages((IScriptFolder[]) children
						.toArray(new IScriptFolder[children.size()]));
			} else if (parentElement instanceof IFolder) {
				IFolder folder = (IFolder) parentElement;
				IResource[] resources = folder.members();
				List children = getFoldersAndElements(resources);
				addFragmentsToMap(children);
				return children.toArray();
			}

		} catch (Exception e) {
			return NO_CHILDREN;
		}
		return NO_CHILDREN;
	}

	private void addFragmentsToMap(List elements) {
		List packageFragments = new ArrayList();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object elem = iter.next();
			if (elem instanceof IScriptFolder)
				packageFragments.add(elem);
		}
		addFragmentsToMap((IScriptFolder[]) packageFragments
				.toArray(new IScriptFolder[packageFragments.size()]));
	}

	private List getFoldersAndElements(IResource[] resources)
			throws CoreException {
		List list = new ArrayList();
		for (int i = 0; i < resources.length; i++) {
			IResource resource = resources[i];

			if (resource instanceof IFolder) {
				IFolder folder = (IFolder) resource;
				IModelElement element = DLTKCore.create(folder);

				if (element instanceof IScriptFolder) {
					list.add(element);
				} else {
					list.add(folder);
				}
			}
		}
		return list;
	}

	private List getFolders(IResource[] resources) throws CoreException {
		List list = new ArrayList();
		for (int i = 0; i < resources.length; i++) {
			IResource resource = resources[i];

			if (resource instanceof IFolder) {
				IFolder folder = (IFolder) resource;
				IModelElement element = DLTKCore.create(folder);

				if (element == null) {
					list.add(folder);
				}
			}
		}
		return list;
	}

	private IScriptFolder[] findNextLevelChildrenByElementName(
			IProjectFragment parent, IScriptFolder fragment) {
		List list = new ArrayList();
		try {

			IModelElement[] children = parent.getChildren();
			String fragmentname = fragment.getElementName();
			for (int i = 0; i < children.length; i++) {
				IModelElement element = children[i];
				if (element instanceof IScriptFolder) {
					IScriptFolder frag = (IScriptFolder) element;

					String name = element.getElementName();
					if (name.length() > fragmentname.length()
							&& name.charAt(fragmentname.length()) == '.'
							&& frag.exists()
							&& !IScriptFolder.DEFAULT_FOLDER_NAME
									.equals(fragmentname)
							&& name.startsWith(fragmentname)
							&& !name.equals(fragmentname)) {
						String tail = name.substring(fragmentname.length() + 1);
						if (!IScriptFolder.DEFAULT_FOLDER_NAME.equals(tail)
								&& tail.indexOf('.') == -1) {
							list.add(frag);
						}
					}
				}
			}

		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
		}
		return (IScriptFolder[]) list.toArray(new IScriptFolder[list.size()]);
	}

	private IScriptFolder[] getTopLevelChildrenByElementName(
			IModelElement[] elements) {
		List topLevelElements = new ArrayList();
		for (int i = 0; i < elements.length; i++) {
			IModelElement iJavaElement = elements[i];
			// if the name of the PackageFragment is the top level package it
			// will contain no "." separators
			if (iJavaElement instanceof IScriptFolder
					&& iJavaElement.getElementName().indexOf('.') == -1) {
				topLevelElements.add(iJavaElement);
			}
		}
		return (IScriptFolder[]) topLevelElements
				.toArray(new IScriptFolder[topLevelElements.size()]);
	}

	/*
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
	 */
	public Object getParent(Object element) {

		try {
			if (element instanceof IScriptFolder) {
				IScriptFolder fragment = (IScriptFolder) element;
				if (!fragment.exists())
					return null;
				Object parent = getHierarchicalParent(fragment);
				if (parent instanceof IScriptFolder) {
					IScriptFolder pkgFragment = (IScriptFolder) parent;
					LogicalPackage logicalPkg = findLogicalPackage(pkgFragment);
					if (logicalPkg != null)
						return logicalPkg;
					else {
						LogicalPackage lp = createLogicalPackage(pkgFragment);
						if (lp == null)
							return pkgFragment;
						else
							return lp;
					}
				}
				return parent;

			} else if (element instanceof LogicalPackage) {
				LogicalPackage el = (LogicalPackage) element;
				IScriptFolder fragment = el.getScriptFolders()[0];
				Object parent = getHierarchicalParent(fragment);

				if (parent instanceof IScriptFolder) {
					IScriptFolder pkgFragment = (IScriptFolder) parent;
					LogicalPackage logicalPkg = findLogicalPackage(pkgFragment);
					if (logicalPkg != null)
						return logicalPkg;
					else {
						LogicalPackage lp = createLogicalPackage(pkgFragment);
						if (lp == null)
							return pkgFragment;
						else
							return lp;
					}
				} else
					return fragment.getScriptProject();
			} else if (element instanceof IFolder) {
				IFolder folder = (IFolder) element;
				IResource res = folder.getParent();

				IModelElement el = DLTKCore.create(res);
				if (el != null) {
					return el;
				} else {
					return res;
				}
			}

		} catch (Exception e) {
			DLTKUIPlugin.log(e);
		}
		return null;
	}

	/*
	 * Check if the given IPackageFragment should be the member of a
	 * LogicalPackage and if so creates the LogicalPackage and adds it to the
	 * map.
	 */
	private LogicalPackage createLogicalPackage(IScriptFolder pkgFragment) {
		if (!fInputIsProject)
			return null;

		List fragments = new ArrayList();
		try {
			IProjectFragment[] roots = pkgFragment.getScriptProject()
					.getProjectFragments();
			for (int i = 0; i < roots.length; i++) {
				IProjectFragment root = roots[i];
				IScriptFolder fragment = root.getScriptFolder(pkgFragment
						.getElementName());
				if (fragment.exists() && !fragment.equals(pkgFragment))
					fragments.add(fragment);
			}
			if (!fragments.isEmpty()) {
				LogicalPackage logicalPackage = new LogicalPackage(pkgFragment);
				fMapToLogicalPackage.put(getKey(pkgFragment), logicalPackage);
				Iterator iter = fragments.iterator();
				while (iter.hasNext()) {
					IScriptFolder f = (IScriptFolder) iter.next();
					if (logicalPackage.belongs(f)) {
						logicalPackage.add(f);
						fMapToLogicalPackage.put(getKey(f), logicalPackage);
					}
				}

				return logicalPackage;
			}

		} catch (Exception e) {
			DLTKUIPlugin.log(e);
		}

		return null;
	}

	private Object getHierarchicalParent(IScriptFolder fragment)
			throws Exception {
		IModelElement parent = fragment.getParent();

		if ((parent instanceof IProjectFragment) && parent.exists()) {
			IProjectFragment root = (IProjectFragment) parent;
			if (root.isArchive() || !fragment.exists()) {
				return findNextLevelParentByElementName(fragment);
			} else {
				IResource resource = fragment.getUnderlyingResource();
				if ((resource != null) && (resource instanceof IFolder)) {
					IFolder folder = (IFolder) resource;
					IResource res = folder.getParent();

					IModelElement el = DLTKCore.create(res);
					if (el != null) {
						return el;
					} else {
						return res;
					}
				}
			}
		}
		return parent;
	}

	private Object findNextLevelParentByElementName(IScriptFolder child) {
		String name = child.getElementName();

		int index = name.lastIndexOf('.');
		if (index != -1) {
			String realParentName = name.substring(0, index);
			IScriptFolder element = ((IProjectFragment) child.getParent())
					.getScriptFolder(realParentName);
			if (element.exists()) {
				return element;
			}
		}
		return child.getParent();
	}

	/*
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {

		if (element instanceof IScriptFolder) {
			IScriptFolder fragment = (IScriptFolder) element;
			if (fragment.isRootFolder() || !fragment.exists())
				return false;
		}
		return getChildren(element).length > 0;
	}

	/*
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	protected void processDelta(IModelElementDelta delta) {

		int kind = delta.getKind();
		final IModelElement element = delta.getElement();

		if (isClassPathChange(delta)) {
			Object input = fViewer.getInput();
			if (input != null) {
				if (fInputIsProject && input.equals(element.getScriptProject())) {
					postRefresh(input);
					return;
				} else if (!fInputIsProject && input.equals(element)) {
					if (element.exists())
						postRefresh(input);
					else
						postRemove(input);
					return;
				}
			}
		}

		if (kind == IModelElementDelta.REMOVED) {
			Object input = fViewer.getInput();
			if (input != null && input.equals(element)) {
				postRemove(input);
				return;
			}
		}

		if (element instanceof IScriptFolder) {
			final IScriptFolder frag = (IScriptFolder) element;

			// if fragment was in LogicalPackage refresh,
			// otherwise just remove
			if (kind == IModelElementDelta.REMOVED) {
				removeElement(frag);
				return;

			} else if (kind == IModelElementDelta.ADDED) {

				Object parent = getParent(frag);
				addElement(frag, parent);
				return;

			} else if (kind == IModelElementDelta.CHANGED) {
				// just refresh
				LogicalPackage logicalPkg = findLogicalPackage(frag);
				// in case changed object is filtered out
				if (logicalPkg != null)
					postRefresh(findElementToRefresh(logicalPkg));
				else
					postRefresh(findElementToRefresh(frag));
				return;
			}
		}

		try {
			processAffectedChildren(delta);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	private Object findElementToRefresh(Object object) {
		Object toBeRefreshed = object;
		if (fViewer.testFindItem(object) == null) {
			Object parent = getParent(object);
			if (parent instanceof IProjectFragment && fInputIsProject)
				parent = ((IProjectFragment) parent).getScriptProject();

			if (parent != null)
				toBeRefreshed = parent;
		}
		return toBeRefreshed;
	}

	private void processAffectedChildren(IModelElementDelta delta)
			throws Exception {
		IModelElementDelta[] affectedChildren = delta.getAffectedChildren();
		for (int i = 0; i < affectedChildren.length; i++) {
			if (!(affectedChildren[i] instanceof ISourceModule)) {
				processDelta(affectedChildren[i]);
			}
		}
	}

	private void postAdd(final Object child, final Object parent) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					((TreeViewer) fViewer).add(parent, child);
				}
			}
		});
	}

	private void postRemove(final Object object) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					((TreeViewer) fViewer).remove(object);
				}
			}
		});
	}

	private void postRefresh(final Object object) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					((TreeViewer) fViewer).refresh(object);
				}
			}
		});
	}

	private void postRunnable(final Runnable r) {
		Control ctrl = fViewer.getControl();
		if (ctrl != null && !ctrl.isDisposed()) {
			// fBrowsingPart.setProcessSelectionEvents(false);
			try {
				Display currentDisplay = Display.getCurrent();
				if (currentDisplay != null
						&& currentDisplay.equals(ctrl.getDisplay()))
					ctrl.getDisplay().syncExec(r);
				else
					ctrl.getDisplay().asyncExec(r);
			} finally {
				// fBrowsingPart.setProcessSelectionEvents(true);
			}
		}
	}

	private void addElement(IScriptFolder frag, Object parent) {

		String key = getKey(frag);
		LogicalPackage lp = (LogicalPackage) fMapToLogicalPackage.get(key);

		// if fragment must be added to an existing LogicalPackage
		if (lp != null && lp.belongs(frag)) {
			lp.add(frag);
			return;
		}

		// if a new LogicalPackage must be created
		IScriptFolder iPackageFragment = (IScriptFolder) fMapToPackageFragments
				.get(key);
		if (iPackageFragment != null && !iPackageFragment.equals(frag)) {
			lp = new LogicalPackage(iPackageFragment);
			lp.add(frag);
			// add new LogicalPackage to LogicalPackages map
			fMapToLogicalPackage.put(key, lp);

			// determine who to refresh
			if (parent instanceof IProjectFragment) {
				IProjectFragment root = (IProjectFragment) parent;
				if (fInputIsProject) {
					postRefresh(root.getScriptProject());
				} else {
					postRefresh(root);
				}
			} else {
				// @Improve: Should this be replaced by a refresh?
				postAdd(lp, parent);
				postRemove(iPackageFragment);
			}

		}
		// if this is a new Package Fragment
		else {
			fMapToPackageFragments.put(key, frag);

			// determine who to refresh
			if (parent instanceof IProjectFragment) {
				IProjectFragment root = (IProjectFragment) parent;
				if (fInputIsProject) {
					postAdd(frag, root.getScriptProject());
				} else
					postAdd(frag, root);
			} else {
				postAdd(frag, parent);
			}
		}
	}

	private void removeElement(IScriptFolder frag) {

		String key = getKey(frag);
		LogicalPackage lp = (LogicalPackage) fMapToLogicalPackage.get(key);

		if (lp != null) {
			lp.remove(frag);
			// if the LogicalPackage needs to revert back to a PackageFragment
			// remove it from the LogicalPackages map and add the
			// PackageFragment
			// to the PackageFragment map
			if (lp.getScriptFolders().length == 1) {
				IScriptFolder fragment = lp.getScriptFolders()[0];
				fMapToPackageFragments.put(key, fragment);
				fMapToLogicalPackage.remove(key);

				// remove the LogicalPackage from viewer
				postRemove(lp);

				Object parent = getParent(fragment);
				if (parent instanceof IProjectFragment) {
					parent = ((IProjectFragment) parent).getScriptProject();
				}
				postAdd(fragment, parent);
			}

		} else {
			// remove the fragment from the fragment map and viewer
			IScriptFolder fragment = (IScriptFolder) fMapToPackageFragments
					.get(key);
			if (fragment != null && fragment.equals(frag)) {
				fMapToPackageFragments.remove(key);
				postRemove(frag);
			}
		}
	}
}
