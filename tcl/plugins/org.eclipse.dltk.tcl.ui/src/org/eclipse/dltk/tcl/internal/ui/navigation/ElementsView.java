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
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ExternalScriptFolder;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.filters.IFilterElementNameProvider;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.ModelElementSorter;
import org.eclipse.dltk.ui.actions.CustomFiltersActionGroup;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public abstract class ElementsView extends ViewPart {
	private ElementsTreeViewer treeViewer;
	protected ElementsContentProvider provider;

	protected abstract String getPreferencesId();

	private CustomFiltersActionGroup fCustomFiltersActionGroup;
	private boolean lookIntoExternal;

	protected IPreferenceStore getStore() {
		return TclUI.getDefault().getPreferenceStore();
	}

	private class ElementsTreeViewer extends TreeViewer implements
			IFilterElementNameProvider {
		public ElementsTreeViewer(Composite container, int border) {
			super(container, border);
		}

		public String getElementName(Object element) {
			return labelProvider.getText(element);
		}
	}

	class ToggleExternalAction extends Action {
		public ToggleExternalAction() {
			super();
			setText("Show external elements");
			// DLTKPluginImages.setLocalImageDescriptors(this,
			// "package_obj.gif"); //$NON-NLS-1$
			setImageDescriptor(DLTKPluginImages
					.getDescriptor(DLTKPluginImages.IMG_OBJS_PACKAGE));
			boolean checked = getStore().getBoolean(
					getPreferencesId() + "ToggleExternalAction.isChecked"); //$NON-NLS-1$		
			valueChanged(checked, false);
		}

		public void run() {
			valueChanged(isChecked(), true);
		}

		private void valueChanged(final boolean on, boolean store) {
			setChecked(on);
			if (store)
				getStore()
						.setValue(
								getPreferencesId()
										+ "ToggleExternalAction.isChecked", on); //$NON-NLS-1$
			BusyIndicator.showWhile(treeViewer.getControl().getDisplay(),
					new Runnable() {
						public void run() {
							lookIntoExternal = on;
							provider.clear();
							runAsync();
						}
					});
		}
	}

	class LexicalSortingAction extends Action {
		private ModelElementSorter fSorter = new ModelElementSorter() {
			protected String getElementName(Object element) {
				return labelProvider.getText(element);
			}

			public int compare(Viewer viewer, Object e1, Object e2) {
				Object o1 = e1;
				Object o2 = e2;
				if (e1 != null && e1 instanceof ElementList) {
					o1 = ((ElementList) e1).getFirstElement();
				}
				if (e2 != null && e2 instanceof ElementList) {
					o2 = ((ElementList) e2).getFirstElement();
				}
				return super.compare(viewer, o1, o2);
			}
		};

		public LexicalSortingAction() {
			super();
			if (DLTKCore.DEBUG) {
				System.err
						.println("LexicalSortingAction: Need to set correct info here.");
			}
			// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
			// IDLTKHelpContextIds.LEXICAL_SORTING_OUTLINE_ACTION);
			setText("Alphabetical Sort");
			DLTKPluginImages.setLocalImageDescriptors(this,
					"alphab_sort_co.gif"); //$NON-NLS-1$
			boolean checked = getStore().getBoolean(
					getPreferencesId() + "LexicalSortingAction.isChecked"); //$NON-NLS-1$		
			valueChanged(checked, false);
		}

		public void run() {
			valueChanged(isChecked(), true);
		}

		private void valueChanged(final boolean on, boolean store) {
			setChecked(on);
			BusyIndicator.showWhile(treeViewer.getControl().getDisplay(),
					new Runnable() {
						public void run() {
							treeViewer.setSorter(on ? fSorter : null);
						}
					});
			if (store)
				getStore()
						.setValue(
								getPreferencesId()
										+ "LexicalSortingAction.isChecked", on); //$NON-NLS-1$			
		}
	}

	protected ElementsViewLabelProvider labelProvider;

	class ElementList implements IAdaptable {
		private String name;
		private List childs;

		ElementList(String name, List childs) {
			this.name = name;
			this.childs = childs;
		}

		Object getFirstElement() {
			if (childs != null && childs.size() > 0) {
				return childs.get(0);
			}
			return null;
		}

		public String toString() {
			return this.name;
		}

		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final ElementList other = (ElementList) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		public List getElements() {
			return this.childs;
		}

		public Object getAdapter(Class adapter) {
			if (adapter == IModelElement.class) {
				return getFirstElement();
			}
			if (adapter == List.class) {
				return childs;
			}
			return null;
		}
	}

	private class ElementsContentProvider implements ITreeContentProvider {
		private Object[] NO_ELEMENT = new Object[0];
		private Map elements = new HashMap();

		public ElementsContentProvider() {
			DLTKCore.addElementChangedListener(new IElementChangedListener() {
				public void elementChanged(ElementChangedEvent event) {
					IModelElementDelta delta = event.getDelta();
					processChildren(delta);
					asyncRefresh();
				}

				private void processChildren(IModelElementDelta delta) {
					IModelElement element = delta.getElement();

					if (delta.getKind() == IModelElementDelta.ADDED) {
						addElementsJob(element);
					}
					if (delta.getKind() == IModelElementDelta.REMOVED) {
						removeTypesJob(element);
					}
					if ((delta.getFlags() & IModelElementDelta.F_ADDED_TO_BUILDPATH) != 0) {
						addElementsJob(element);
					}
					if ((delta.getFlags() & IModelElementDelta.F_REMOVED_FROM_BUILDPATH) != 0) {
						synchronized (elements) {
							elements.clear();
						}
						runAsync();
					}
					if ((delta.getFlags() & IModelElementDelta.F_CHILDREN) != 0) {
						IModelElementDelta[] affectedChildren = delta
								.getAffectedChildren();
						for (int i = 0; i < affectedChildren.length; i++) {
							IModelElementDelta child = affectedChildren[i];
							processChildren(child);
						}
					}
				}
			});
		}

		public synchronized Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IWorkspaceRoot) {
				List result = new ArrayList();
				Set keys = elements.keySet();
				Iterator i = keys.iterator();
				while (i.hasNext()) {
					Object e = i.next();
					Object o = elements.get(e);
					if (o instanceof IModelElement) {
						if (!result.contains(o)) {
							result.add(o);
						}
					} else if (o instanceof List) {
						ElementList el = new ElementList((String) e, (List) o);
						if (!result.contains(o)) {
							result.add(el);
						}
					}
				}
				return result.toArray();
			}
			return NO_ELEMENT;
		}

		public Object getParent(Object element) {
			if (element instanceof IModelElement) {
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

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		synchronized public void addElement(IModelElement element) {
			try {
				IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager
						.getLanguageToolkit(element);
				if (!languageToolkit.getNatureId().equals(
						TclLanguageToolkit.getDefault().getNatureId())) {
					return;
				}
			} catch (CoreException e) {
			}

			String name = labelProvider.getText(element);
			if (elements.containsKey(name)) {
				Object o = elements.get(name);
				if (o instanceof IModelElement) {
					List els = new ArrayList();
					els.add(o);
					if (!els.contains(element)) {
						els.add(element);
					}
					elements.put(name, els);
				} else if (o instanceof List) {
					List els = (List) o;
					if (!els.contains(o)) {
						els.add(element);
					}
				}
			} else {
				elements.put(name, element);
			}
		}

		synchronized public void clear() {
			elements.clear();
		}

		synchronized public void removeElement(IModelElement element) {
			String name = labelProvider.getText(element);
			if (elements.containsKey(name)) {
				Object o = elements.get(name);
				if (o instanceof IModelElement && o.equals(element)) {
					elements.remove(name);
				} else if (o instanceof List) {
					List els = (List) o;
					els.remove(element);
					if (els.size() == 0) {
						elements.remove(name);
					}
				}
			}
		}
	}

	public abstract String getElementName(Object o);

	public class ElementsViewLabelProvider extends ScriptUILabelProvider
			implements IBaseLabelProvider {
		public Image getImage(Object element) {
			if (element instanceof ElementList) {
				return getImage(((ElementList) element).getFirstElement());
			}
			/*
			 * if( element instanceof IType ) { IType type = (IType)element; int
			 * flags = 0; try { flags = type.getFlags(); } catch (ModelException
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); } if
			 * ((flags & Modifiers.AccNameSpace) == 0) return
			 * DLTKPluginImages.DESC_OBJS_CLASS.createImage(); return
			 * DLTKPluginImages.DESC_OBJS_NAMESPACE.createImage(); }
			 */
			return super.getImage(element);
		}

		public String getOriginalText(Object element) {
			return super.getText(element);
		}

		public String getText(Object element) {
			String text = getElementName(element);
			if (text != null) {
				return text;
			}
			if (element instanceof ElementList) {
				return ((ElementList) element).toString();
			}
			return super.getText(element);
		}
	}

	public abstract boolean isElement(IModelElement e);

	public abstract boolean needProcessChildren(IModelElement e);

	private void addElements(final IModelElement element,
			IProgressMonitor monitor) {
		if (monitor.isCanceled())
			return;
		monitor.worked(1);
		if (isElement(element)) {
			provider.addElement(element);

		}
		if (element instanceof ISourceModule)
			asyncRefresh();
		boolean bad = !lookIntoExternal
				&& (element instanceof ExternalScriptFolder || element instanceof IExternalSourceModule);
		if (element instanceof IParent && !bad) {
			IModelElement[] children = null;
			try {
				children = ((IParent) element).getChildren();
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				return;
			}
			if (children != null) {
				for (int j = 0; j < children.length; ++j) {
					addElements(children[j], monitor);
				}
			}
		}
		// asyncRefresh();
		monitor.done();
	}

	public abstract String getJobTitle();

	private void addElementsJob(final IModelElement element) {
		Job job = new Job(getTitle()) {
			protected IStatus run(IProgressMonitor monitor) {
				addElements(element, monitor);
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	private void removeElements(IModelElement element) {
		try {
			IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager
					.getLanguageToolkit(element);
			if (languageToolkit != null
					&& !languageToolkit.getNatureId().equals(
							TclLanguageToolkit.getDefault().getNatureId())) {
				return;
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return;
		}
		if (isElement(element)) {
			provider.removeElement(element);
			asyncRefresh();
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
					removeElements(children[j]);
				}
			}
		}
	}

	private void removeTypesJob(final IModelElement element) {
		Job job = new Job(getTitle()) {
			protected IStatus run(IProgressMonitor monitor) {
				removeElements(element);
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	private void asyncRefresh() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (treeViewer != null && !treeViewer.getTree().isDisposed()) {
					treeViewer.refresh(true);
				}
			}
		});
	}

	private Job currentJob;

	protected void runAsync() {
		if (currentJob != null && currentJob.getResult() == null) {
			currentJob.cancel();
			provider.clear();
			currentJob = null;
		}
		Job job = new Job(getJobTitle()) {
			protected IStatus run(IProgressMonitor monitor) {
				IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
						.getProjects();
				// monitor.beginTask(getTitle(), projects.length);
				for (int i = 0; i < projects.length; ++i) {
					if (monitor.isCanceled())
						return Status.OK_STATUS;
					try {
						if (projects[i].isAccessible()
								&& projects[i].hasNature(TclNature.NATURE_ID)) {
							IScriptProject project = DLTKCore
									.create(projects[i]);
							if (project != null) {
								addElements(project, monitor);
							}
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
					monitor.worked(1);
				}
				asyncRefresh();
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		currentJob = job;
	}

	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		treeViewer = new ElementsTreeViewer(container, SWT.BORDER);
		provider = new ElementsContentProvider();
		labelProvider = new ElementsViewLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setContentProvider(provider);
		treeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				ITreeSelection selection = (ITreeSelection) (event
						.getSelection());
				Object source = selection.getFirstElement();
				if (source == null)
					return;
				try {
					if (source instanceof IModelElement) {
						IEditorPart editor = EditorUtility.openInEditor(source);
						EditorUtility.revealInEditor(editor,
								(IModelElement) source);
					} else if (source instanceof ElementList) {
						ElementList el = (ElementList) source;
						List elements = el.getElements();
						if (elements != null) {
							for (int i = 0; i < elements.size(); ++i) {
								Object element = elements.get(i);
								if (element instanceof IModelElement) {
									IEditorPart editor = EditorUtility
											.openInEditor(element);
									EditorUtility.revealInEditor(editor,
											(IModelElement) element);
								} else
									EditorUtility.openInEditor(element);
							}
						}
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
		});
		IViewSite site = (IViewSite) this.getSite();
		IActionBars bars = site.getActionBars();
		registerToolbarActions(bars);
		runAsync();
	}

	private void registerToolbarActions(IActionBars actionBars) {
		IToolBarManager toolBarManager = actionBars.getToolBarManager();
		if (toolBarManager != null) {
			toolBarManager.add(new LexicalSortingAction());
			toolBarManager.add(new ToggleExternalAction());
		}
		// registerSpecialToolbarActions(actionBars);
		// Custom filter group
		fCustomFiltersActionGroup = new CustomFiltersActionGroup(this,
				treeViewer) {
		};
		fCustomFiltersActionGroup.fillActionBars(actionBars);
		/*
		 * actionBars.getMenuManager().add( new PackageFilterAction(this,
		 * treeViewer));
		 */
		IMenuManager viewMenuManager = actionBars.getMenuManager();
		viewMenuManager.add(new Separator("EndFilterGroup")); //$NON-NLS-1$
		// fToggleLinkingAction= new ToggleLinkingAction(this);
		// viewMenuManager.add(fToggleLinkingAction);
	}

	public void dispose() {
		if (fCustomFiltersActionGroup != null) {
			fCustomFiltersActionGroup.dispose();
			fCustomFiltersActionGroup = null;
		}
		super.dispose();
	}

	public void setFocus() {
	}

	public String getOriginalElementText(Object o) {
		return labelProvider.getOriginalText(o);
	}
}
