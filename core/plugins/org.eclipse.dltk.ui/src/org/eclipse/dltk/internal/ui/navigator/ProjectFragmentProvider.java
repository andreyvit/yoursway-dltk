/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;


/**
 * Content provider which provides package fragments for hierarchical
 * Package Explorer layout.
 * 
	 *
 */
public class ProjectFragmentProvider implements IPropertyChangeListener {

	private TreeViewer fViewer;
	private boolean fFoldPackages;
	private IPreferenceStore fStore;
	
	public ProjectFragmentProvider(IPreferenceStore store) {
		fStore = store;
		fFoldPackages= arePackagesFoldedInHierarchicalLayout();		
		fStore.addPropertyChangeListener(this);
	}
	
	/*
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		try {
			if (parentElement instanceof IFolder) {
				IResource[] resources= ((IFolder) parentElement).members();
				return filter(getFolders(resources)).toArray();
			} else if (parentElement instanceof IModelElement) {
				IModelElement iModelElement= (IModelElement) parentElement;
				int type= iModelElement.getElementType();
	
				switch (type) {
					case IModelElement.SCRIPT_PROJECT: {
						IDLTKProject project= (IDLTKProject) iModelElement;
						
						IProjectFragment root= project.findProjectFragment(project.getPath());
						if (root != null) {
							List children= getTopLevelChildren(root);
							return filter(children).toArray();
						} 
						break;
					}
					case IModelElement.PROJECT_FRAGMENT: {
						IProjectFragment root= (IProjectFragment) parentElement;
						if (root.exists()) {
							return filter(getTopLevelChildren(root)).toArray();
						}
						break;
					}
					case IModelElement.SCRIPT_FOLDER: {
						IScriptFolder scriptFolder = (IScriptFolder) parentElement;
						if (!scriptFolder.isRootFolder()) {						
							IProjectFragment root= (IProjectFragment) scriptFolder.getParent();
							List children = getPackageChildren(root, scriptFolder);
							return filter(children).toArray();
						}
						break;
					}
					default :
						// do nothing
				}
			}
		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
		}
		return new Object[0];
	}
	
	private List filter(List children) throws ModelException {
		if (fFoldPackages) {
			int size= children.size();
			for (int i = 0; i < size; i++) {
				Object curr= children.get(i);
				if (curr instanceof IScriptFolder) {
					IScriptFolder fragment = (IScriptFolder) curr;
					if (!fragment.isRootFolder() && isEmpty(fragment)) {
						IScriptFolder collapsed= getCollapsed(fragment);
						if (collapsed != null) {
							children.set(i, collapsed); // replace with collapsed
						}
					}
				}
			}
		}
		return children;
	}
	
	private IScriptFolder getCollapsed(IScriptFolder pack) throws ModelException {
		IModelElement[] children= ((IProjectFragment) pack.getParent()).getChildren();
		IScriptFolder child= getSinglePackageChild(pack, children);
		while (child != null && isEmpty(child)) {
			IScriptFolder collapsed= getSinglePackageChild(child, children);
			if (collapsed == null) {
				return child;
			}
			child= collapsed;
		}
		return child;
	}
		
	private boolean isEmpty(IScriptFolder fragment) throws ModelException {
		return !fragment.containsScriptResources() && fragment.getForeignResources().length == 0;
	}
	
	private static IScriptFolder getSinglePackageChild(IScriptFolder fragment, IModelElement[] children) {
		String prefix= fragment.getElementName() + IScriptFolder.PACKAGE_DELIMITER;
		int prefixLen= prefix.length();
		IScriptFolder found= null;
		for (int i= 0; i < children.length; i++) {
			IModelElement element= children[i];
			String name= element.getElementName();
			if (name.startsWith(prefix) && name.length() > prefixLen && name.indexOf(IScriptFolder.PACKAGE_DELIMITER, prefixLen) == -1) {
				if (found == null) {
					found= (IScriptFolder) element;
				} else {
					return null;
				}
			}
		}
		return found;
	}
	
	
	private static List getPackageChildren(IProjectFragment parent, IScriptFolder fragment) throws ModelException {
		IModelElement[] children= parent.getChildren();
		ArrayList list= new ArrayList(children.length);
		String prefix= fragment.getElementName() + IScriptFolder.PACKAGE_DELIMITER;
		int prefixLen= prefix.length();
		for (int i= 0; i < children.length; i++) {
			IModelElement element= children[i];
			if (element instanceof IScriptFolder) { // see bug 134256
				String name= element.getElementName();
				if (name.startsWith(prefix) && name.length() > prefixLen && name.indexOf(IScriptFolder.PACKAGE_DELIMETER_STR, prefixLen) == -1) {
					list.add(element);
				}
			}
		}
		return list;
	}
	
	private static List getTopLevelChildren(IProjectFragment root) throws ModelException {
		IModelElement[] elements= root.getChildren();
		ArrayList topLevelElements= new ArrayList(elements.length);
		for (int i= 0; i < elements.length; i++) {
			IModelElement iModelElement= elements[i];
			//if the name of the ScriptFolder is the top level package it will contain no "." separators
			if (iModelElement instanceof IScriptFolder && iModelElement.getElementName().indexOf(IScriptFolder.PACKAGE_DELIMITER)==-1) {
				topLevelElements.add(iModelElement);
			}
		}	
		return topLevelElements;
	}

	private List getFolders(IResource[] resources) throws ModelException {
		List list= new ArrayList(resources.length);
		for (int i= 0; i < resources.length; i++) {
			IResource resource= resources[i];
			if (resource instanceof IFolder) {
				IFolder folder = (IFolder) resource;
//				IModelElement element= DLTKCore.create(folder);
//				if (element instanceof IScriptFolder) {
					IProject project = folder.getProject();
					IDLTKProject dltkProject= DLTKCore.create(project);
					if (dltkProject != null) {
						if (dltkProject.isOnBuildpath(folder))
							list.add(folder);	
					}
//				} 
			}	
		}
		return list;
	}


	/*
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
	 */
	public Object getParent(Object element) {

		if (element instanceof IScriptFolder) {
			IScriptFolder frag = (IScriptFolder) element;
			//@Changed: a fix, before: if(frag.exists() && isEmpty(frag))
		
			return filterParent(getActualParent(frag));
		}
		return null;
	}

	private Object getActualParent(IScriptFolder fragment) {
		try {

			if (fragment.exists()) {
				IModelElement parent = fragment.getParent();

				if ((parent instanceof IProjectFragment) && parent.exists()) {
					IProjectFragment root = (IProjectFragment) parent;
					if (root.isArchive()) {
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
					return parent;
				}
			}

		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
		}
		return null;
	}
	
	private Object filterParent(Object parent) {
		if (fFoldPackages && (parent!=null)) {
			try {
				if (parent instanceof IScriptFolder) {
					IScriptFolder fragment = (IScriptFolder) parent;
					if (isEmpty(fragment) && hasSingleChild(fragment)) {
						return filterParent(getActualParent(fragment));
					}
				}
			} catch (ModelException e) {
				DLTKUIPlugin.log(e);
			}
		}
		return parent;
	}

	private boolean hasSingleChild(IScriptFolder fragment) {
		return getChildren(fragment).length==1;
	}


	private Object findNextLevelParentByElementName(IScriptFolder child) {
		String name= child.getElementName();
		
		int index= name.lastIndexOf(IScriptFolder.PACKAGE_DELIMITER);
		if (index != -1) {
			String realParentName= name.substring(0, index);
			IScriptFolder element= ((IProjectFragment) child.getParent()).getScriptFolder(realParentName);
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
			IScriptFolder fragment= (IScriptFolder) element;
			if(fragment.isRootFolder())
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

	/*
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		fStore.removePropertyChangeListener(this);
	}

	/**
	 * Called when the view is closed and opened.
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(Viewer, Object, Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		fViewer= (TreeViewer)viewer;
	}
		
	public void elementChanged(ElementChangedEvent event) {
		processDelta(event.getDelta());
	}
	
	public void processDelta(IModelElementDelta delta) {

		int kind = delta.getKind();
		final IModelElement element = delta.getElement();

		if (element instanceof IScriptFolder) {

			if (kind == IModelElementDelta.REMOVED) {

				postRunnable(new Runnable() {
					public void run() {
						Control ctrl = fViewer.getControl();
						if (ctrl != null && !ctrl.isDisposed()) {
							if (!fFoldPackages)
								 fViewer.remove(element);
							else
								refreshGrandParent(element);
						}
					}
				});
				return;

			} else if (kind == IModelElementDelta.ADDED) {

				final Object parent = getParent(element);
				if (parent != null) {
					postRunnable(new Runnable() {
						public void run() {
							Control ctrl = fViewer.getControl();
							if (ctrl != null && !ctrl.isDisposed()) {
								if (!fFoldPackages)
									 fViewer.add(parent, element);
								else
									refreshGrandParent(element);
							}
						}
					});
				}
				return;
			} 
		}
	}

	// XXX: needs to be revisited - might be a performance issue
	private void refreshGrandParent(final IModelElement element) {
		if (element instanceof IScriptFolder) {
			Object gp= getGrandParent((IScriptFolder)element);
			if (gp instanceof IModelElement) {
				IModelElement el = (IModelElement) gp;
				if(el.exists())
					fViewer.refresh(gp);
			} else if (gp instanceof IFolder) {
				IFolder folder= (IFolder)gp;
				if (folder.exists())
					fViewer.refresh(folder);
			}
		}
	}

	private Object getGrandParent(IScriptFolder element) {

		Object parent= findNextLevelParentByElementName(element);
		if (parent instanceof IProjectFragment) {
			IProjectFragment root= (IProjectFragment) parent;
			if(isRootProject(root))
				return root.getScriptProject();
			else return root;
		}

		Object grandParent= getParent(parent);
		if(grandParent==null){
			return parent;
		}
		return grandParent;
	}

	private boolean isRootProject(IProjectFragment root) {		
		if (IProjectFragment.DEFAULT_PACKAGE_ROOT.equals(root.getElementName()))
			return true;
		return false;
	}
	
	private void postRunnable(final Runnable r) {
		Control ctrl= fViewer.getControl();
		if (ctrl != null && !ctrl.isDisposed()) {

			Display currentDisplay= Display.getCurrent();
			if (currentDisplay != null && currentDisplay.equals(ctrl.getDisplay()))
				ctrl.getDisplay().syncExec(r);
			else
				ctrl.getDisplay().asyncExec(r);
		}
	}

	/*
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (arePackagesFoldedInHierarchicalLayout() != fFoldPackages){
			fFoldPackages= arePackagesFoldedInHierarchicalLayout();
			if (fViewer != null && !fViewer.getControl().isDisposed()) {
				fViewer.getControl().setRedraw(false);
				Object[] expandedObjects= fViewer.getExpandedElements();
				fViewer.refresh();	
				fViewer.setExpandedElements(expandedObjects);
				fViewer.getControl().setRedraw(true);
			}
		}
	}

	private boolean arePackagesFoldedInHierarchicalLayout(){
		return fStore.getBoolean(PreferenceConstants.APPEARANCE_FOLD_PACKAGES_IN_PACKAGE_EXPLORER);		
	}
}
