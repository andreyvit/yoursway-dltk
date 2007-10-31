/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
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
import java.util.List;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Table content provider for the hierarchical layout in the packages view.
 * <p>
 * XXX: The standard Java browsing part content provider needs and calls
 * the browsing part/view. This class currently doesn't need to do so
 * but might be required to later.
 * </p>
 */
class PackagesViewFlatContentProvider extends LogicalPackagesProvider implements IStructuredContentProvider {
	PackagesViewFlatContentProvider(StructuredViewer viewer) {
		super(viewer);
	}

	/*
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {

		if(parentElement instanceof IModelElement){
			IModelElement element= (IModelElement) parentElement;

			int type= element.getElementType();

			try {
				switch (type) {
					case IModelElement.SCRIPT_PROJECT :
						IScriptProject project= (IScriptProject) element;
						IScriptFolder[] children= getPackageFragments(project.getScriptFolders());
						if(isInCompoundState()) {
							fMapToLogicalPackage.clear();
							fMapToPackageFragments.clear();
							return combineSamePackagesIntoLogialPackages(children);
						} else
							return children;

					case IModelElement.PROJECT_FRAGMENT :
						fMapToLogicalPackage.clear();
						fMapToPackageFragments.clear();
						IProjectFragment root= (IProjectFragment) element;
						return root.getChildren();

					case IModelElement.SCRIPT_FOLDER :
						//no children in flat view
						break;

					default :
						//do nothing, empty array returned
				}
			} catch (ModelException e) {
				return NO_CHILDREN;
			}

		}
		return NO_CHILDREN;
	}

	/*
	 * Weeds out packageFragments from external jars
	 */
	private IScriptFolder[] getPackageFragments(IScriptFolder[] iPackageFragments) {
		List list= new ArrayList();
		for (int i= 0; i < iPackageFragments.length; i++) {
			IScriptFolder fragment= iPackageFragments[i];
			IModelElement el= fragment.getParent();
			if (el instanceof IProjectFragment) {
				IProjectFragment root= (IProjectFragment) el;
				if(root.isArchive() && root.isExternal())
					continue;
			}
			list.add(fragment);
		}
		return (IScriptFolder[]) list.toArray(new IScriptFolder[list.size()]);
	}

	/*
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	protected void processDelta(IModelElementDelta delta) throws ModelException {

		int kind= delta.getKind();
		final IModelElement element= delta.getElement();

		if (isClassPathChange(delta)) {
			Object input= fViewer.getInput();
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
			Object input= fViewer.getInput();
			if (input != null && input.equals(element)) {
					postRemove(input);
					return;
			}
		}

		if (element instanceof IScriptFolder) {
			final IScriptFolder frag= (IScriptFolder) element;

			if (kind == IModelElementDelta.REMOVED) {
				removeElement(frag);

			} else if (kind == IModelElementDelta.ADDED) {
				addElement(frag);

			} else if (kind == IModelElementDelta.CHANGED) {
				//just refresh
				Object toBeRefreshed= element;

				IScriptFolder pkgFragment= (IScriptFolder) element;
				LogicalPackage logicalPkg= findLogicalPackage(pkgFragment);
				//deal with packages that have been filtered and are now visible
				if (logicalPkg != null)
					toBeRefreshed= findElementToRefresh(logicalPkg);
				else
					toBeRefreshed= findElementToRefresh(pkgFragment);

				postRefresh(toBeRefreshed);
			}
			//in this view there will be no children of PackageFragment to refresh
			return;
		}
		processAffectedChildren(delta);
	}

	//test to see if element to be refreshed is being filtered out
	//and if so refresh the viewers input element (JavaProject or PackageFragmentRoot)
	private Object findElementToRefresh(IScriptFolder fragment) {
		if (fViewer.testFindItem(fragment) == null) {
			if(fInputIsProject)
				return fragment.getScriptProject();
			else return fragment.getParent();
		}
		return fragment;
	}

	//test to see if element to be refreshed is being filtered out
	//and if so refresh the viewers input element (JavaProject or PackageFragmentRoot)
	private Object findElementToRefresh(LogicalPackage logicalPackage) {
		if (fViewer.testFindItem(logicalPackage) == null) {
			IScriptFolder fragment= logicalPackage.getScriptFolders()[0];
			return fragment.getScriptProject();
		}
		return logicalPackage;
	}


	private void processAffectedChildren(IModelElementDelta delta) throws ModelException {
		IModelElementDelta[] children= delta.getAffectedChildren();
		for (int i= 0; i < children.length; i++) {
			IModelElementDelta elementDelta= children[i];
			processDelta(elementDelta);
		}
	}

	private void postAdd(final Object child) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					((TableViewer)fViewer).add(child);
				}
			}
		});
	}


	private void postRemove(final Object object) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl = fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					((TableViewer)fViewer).remove(object);
				}
			}
		});
	}

	private void postRunnable(final Runnable r) {
		Control ctrl= fViewer.getControl();
		if (ctrl != null && !ctrl.isDisposed()) {
		//	fBrowsingPart.setProcessSelectionEvents(false);
			try {
				Display currentDisplay= Display.getCurrent();
				if (currentDisplay != null && currentDisplay.equals(ctrl.getDisplay()))
					ctrl.getDisplay().syncExec(r);
				else
					ctrl.getDisplay().asyncExec(r);
			} finally {
		//		fBrowsingPart.setProcessSelectionEvents(true);
			}
		}
	}

	private void removeElement(IScriptFolder frag) {
		String key= getKey(frag);
		LogicalPackage lp= (LogicalPackage)fMapToLogicalPackage.get(key);

		if(lp != null){
			lp.remove(frag);
			//if you need to change the LogicalPackage to a PackageFragment
			if(lp.getScriptFolders().length == 1){
				IScriptFolder fragment= lp.getScriptFolders()[0];
				fMapToLogicalPackage.remove(key);
				fMapToPackageFragments.put(key,fragment);

				//@Improve: Should I replace this with a refresh of the parent?
				postRemove(lp);
				postAdd(fragment);
			} return;
		} else {
			fMapToPackageFragments.remove(key);
			postRemove(frag);
		}
	}


	private void postRefresh(final Object element) {
		postRunnable(new Runnable() {
			public void run() {
				Control ctrl= fViewer.getControl();
				if (ctrl != null && !ctrl.isDisposed()) {
					fViewer.refresh(element);
				}
			}
		});
	}

	private void addElement(IScriptFolder frag) {
		String key= getKey(frag);
		LogicalPackage lp= (LogicalPackage)fMapToLogicalPackage.get(key);

		if(lp != null && lp.belongs(frag)){
			lp.add(frag);
			return;
		}

		IScriptFolder fragment= (IScriptFolder)fMapToPackageFragments.get(key);
		if(fragment != null){
			//must create a new LogicalPackage
			if(!fragment.equals(frag)){
				lp= new LogicalPackage(fragment);
				lp.add(frag);
				fMapToLogicalPackage.put(key, lp);

				//@Improve: should I replace this with a refresh?
				postRemove(fragment);
				postAdd(lp);

				return;
			}
		}

		else {
			fMapToPackageFragments.put(key, frag);
			postAdd(frag);
		}
	}
}
