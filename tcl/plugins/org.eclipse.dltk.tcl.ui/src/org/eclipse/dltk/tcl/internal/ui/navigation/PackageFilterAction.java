/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.compiler.env.ISourceMethod;
import org.eclipse.dltk.compiler.env.ISourceType;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IPackageDeclaration;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.filters.IFilterElementNameProvider;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;


class PackageFilterAction extends Action {
	StructuredViewer fViewer;
	SimplePackagesContentProvider provider;
	ILabelProvider labelProvider;
	private PackageFilter fFilter;
	private IPreferenceStore fStore;
	String preferenceKey;
	private class SimplePackagesContentProvider implements ITreeContentProvider {
		private Object[] NO_ELEMENT = new Object[0];
		protected Map elements = new HashMap();

		public SimplePackagesContentProvider() {}

		public synchronized Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IWorkspaceRoot) {
				return elements.keySet().toArray();
			}
			return NO_ELEMENT;
		}

		public Object getParent(Object element) {
			if (element instanceof String) {
				return ResourcesPlugin.getWorkspace().getRoot();
			}
			return NO_ELEMENT;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof IWorkspaceRoot) {
				return true;
			}
			return false;
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public void dispose() {}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}
	}
	private class SimplePackageLabelProvider extends ScriptUILabelProvider implements IBaseLabelProvider {
		public Image getImage(Object element) {
			return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_PACKAGE);
		}

		public String getOriginalText(Object element) {
			return super.getText(element);
		}

		public String getText(Object element) {
			if (element instanceof String)
				return (String) element;
			if (element instanceof IPackageDeclaration) {
				return ((IPackageDeclaration) element).getElementName();
			}
			return super.getText(element);
		}
	}

	private String getElementName(Viewer viewer, Object element) {
		String matchName = null;
		if (element instanceof IModelElement) {
			if (viewer instanceof IFilterElementNameProvider) {
				matchName = ((IFilterElementNameProvider) viewer).getElementName((IModelElement) element);
			} else {
				matchName = ((IModelElement) element).getElementName();
			}
		} else if (element instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) element;
			IModelElement modelElement = (IModelElement) adaptable.getAdapter(IModelElement.class);
			if (modelElement != null)
				if (viewer instanceof IFilterElementNameProvider) {
					matchName = ((IFilterElementNameProvider) viewer).getElementName(modelElement);
				} else {
					matchName = modelElement.getElementName();
				}
			else {
				IResource resource = (IResource) adaptable.getAdapter(IResource.class);
				if (resource != null)
					matchName = resource.getName();
			}
		}
		return matchName;
	}

	private IPackageDeclaration getPackageDeclaration(IModelElement element) {
		IPackageDeclaration result = null;
		try {
			for (;;) {
				if (element == null || element instanceof IScriptProject)
					break;
				IModelElement parentElement = element.getParent();
				IParent parent = (IParent) parentElement;
				IModelElement childs[] = parent.getChildren();
				for (int i = 0; i < childs.length; i++) {
					if (childs[i] instanceof IPackageDeclaration) {
						// we've found it!
						result = (IPackageDeclaration) childs[i];
						break;
					}
				}
				element = parentElement;
			}
		} catch (ModelException e) {
		}
		return result;
	}

	private IPackageDeclaration[] getPackageDeclaration(Object element) {
		List modelElements = null;
		if (element instanceof IModelElement) {
			modelElements = new ArrayList();
			modelElements.add((IModelElement) element);
		} else if (element instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) element;
			modelElements = (List) adaptable.getAdapter(List.class);
		}
		List result = new ArrayList();
		synchronized (modelElements) {			
			for (Iterator iter = modelElements.iterator(); iter.hasNext();) {
				IModelElement e = (IModelElement) iter.next();
				IPackageDeclaration p = getPackageDeclaration(e);
				if (p != null)
					result.add(p);
			}
		}
		
		return (IPackageDeclaration[]) result.toArray(new IPackageDeclaration[result.size()]);
	}
	private class PackageFilter extends ViewerFilter {
		List elements = new ArrayList();

		public boolean select(Viewer viewer, Object parentElement, Object element) {
			IPackageDeclaration decl[] = getPackageDeclaration(element);
			if (decl == null)
				return false;
			boolean good = false;
			for (int i = 0; i < decl.length; i++) {
				String matchName = getElementName(viewer, decl[i]);
				if (matchName != null) {
					for (Iterator iter = elements.iterator(); iter.hasNext();) {
						String str = (String) iter.next();
						if (str.equals(matchName)) {
							good = true;
							break;
						}
					}
				}
				if (good)
					break;
			}
			return good;
		}

		void setElements(String[] strs) {
			elements.clear();
			for (int i = 0; i < strs.length; i++) {
				elements.add(strs[i]);
			}
		}
	}

	public PackageFilterAction(ElementsView view, StructuredViewer viewer) {
		fViewer = viewer;
		fStore = view.getStore();
		preferenceKey = view.getPreferencesId();
		setText("Filter by package...");
		provider = new SimplePackagesContentProvider();
		labelProvider = new SimplePackageLabelProvider();
		fFilter = new PackageFilter();
		fViewer.addFilter(fFilter);
		update();
	}

	private String getPreferenceKey() {
		return preferenceKey + "PackagesFilterList";
	}

	private void update() {
		String list = fStore.getString(getPreferenceKey());
		String[] data = list.split("#,#");
		fFilter.setElements(data);
		fViewer.refresh();
	}

	private void addElements(final IModelElement element) {
		if (element instanceof ISourceType || element instanceof ISourceMethod)
			return;
		if (element instanceof IPackageDeclaration) {
			String str = getElementName(fViewer, element);
			if (!provider.elements.containsKey(str))
					provider.elements.put(str, element);
			return;
		}
		if (element instanceof IParent) {
			IModelElement[] children = null;
			try {
				children = ((IParent) element).getChildren();
			} catch (ModelException e) {
				e.printStackTrace();
				return;
			}
			if (children != null) {
				for (int j = 0; j < children.length; ++j) {
					addElements(children[j]);
				}
			}
		}
	}

	public void run() {
		BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
			public void run() {
				provider.elements.clear();
				IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
				for (int i = 0; i < projects.length; ++i) {
					try {
						if (projects[i].hasNature(TclNature.NATURE_ID)) {
							IScriptProject project = DLTKCore.create(projects[i]);
							if (project != null) {
								addElements(project);
							}
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		});
		CheckedTreeSelectionDialog dlg = new CheckedTreeSelectionDialog(null, labelProvider, provider);
		dlg.setInput(ResourcesPlugin.getWorkspace().getRoot());
		dlg.setTitle("Select packages");
		dlg.setMessage("Select packages to be shown in view");
		if (dlg.open() == CheckedTreeSelectionDialog.OK) {
			// save preference
			Object res[] = dlg.getResult(); 
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < res.length; i++) {
				if (res[i] instanceof String) {
					String elemName = (String)res[i];
					if (elemName != null) {
						buf.append(elemName);
						buf.append("#,#"); 
					}
				}
			}
			String list = buf.toString();
			fStore.setValue(getPreferenceKey(), list);
			// update filter
			update();
		}
	}
}
