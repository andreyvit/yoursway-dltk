package org.eclipse.dltk.ui.browsing.ext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.internal.ui.search.SearchUtil;
import org.eclipse.dltk.ui.browsing.ScriptElementTypeComparator;
import org.eclipse.dltk.ui.infoviews.AbstractInfoView;
import org.eclipse.dltk.ui.viewsupport.IViewPartInputProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;

public class ExtendedClassesView extends ViewPart implements
		IViewPartInputProvider, ISelectionListener, ISelectionProvider,
		IExecutableExtension {

	private MultiSelectionListViewer browsingPane;

	//
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		browsingPane = new MultiSelectionListViewer(parent, SWT.NONE) {
			public void elementSelectionChanged(ISelection selection) {
				Object[] listeners = listenerList.getListeners();
				SelectionChangedEvent event = new SelectionChangedEvent(
						ExtendedClassesView.this, convertSelection(selection));
				for (int i = 0; i < listeners.length; i++) {
					((ISelectionChangedListener) (listeners[i]))
							.selectionChanged(event);
				}
			}
		};
		browsingPane.setContentProvider(new ExtendedClasesContentProvider(
				this, SearchEngine.createWorkspaceScope(this.fToolkit)));
		browsingPane.setLabelProvider(new ExtendedClasesLabelProvider(this));
		
		getSite().setSelectionProvider(this);
		getViewSite().getPage().addPostSelectionListener(this);
		getViewSite().getPage().addPartListener(fPartListener);
	}

	/**
	 * We need to prefer local elements with same name
	 * 
	 * @param selection
	 * @return
	 */
	protected ISelection convertSelection(ISelection selection) {
		List result = new ArrayList();
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			List list = sel.toList();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object o = (Object) iterator.next();
				if (o instanceof MixedClass) {
					List mixedElements = ((MixedClass) o).getElements();
					result.addAll(mixedElements);
				} else {
					result.add(o);
				}
			}
		}
		if (result.size() > 0) {
			return new StructuredSelection(result);
		}
		return new StructuredSelection();
	}

	public void setFocus() {
	}

	public Object getViewPartInput() {
		return browsingPane.getSelection();
	}

	private boolean isSearchResultView(IWorkbenchPart part) {
		return SearchUtil.isSearchPlugInActivated()
				&& part instanceof ISearchResultViewPart;
	}

	protected IWorkbenchPart fPreviousSelectionProvider;
	protected Object fPreviousSelectedElement;

	protected boolean needsToProcessSelectionChanged(IWorkbenchPart part,
			ISelection selection) {
		if (!fProcessSelectionEvents || part == this
				|| isSearchResultView(part) || part instanceof AbstractInfoView) {
			if (part == this)
				fPreviousSelectionProvider = part;
			return false;
		}
		return true;
	}

	protected final Object getSingleElementFromSelection(ISelection selection) {
		if (!(selection instanceof IStructuredSelection) || selection.isEmpty())
			return null;

		Iterator iter = ((IStructuredSelection) selection).iterator();
		Object firstElement = iter.next();
		if (!(firstElement instanceof IModelElement)) {
			if (firstElement instanceof IMarker)
				firstElement = ((IMarker) firstElement).getResource();
			if (firstElement instanceof IAdaptable) {
				IModelElement je = (IModelElement) ((IAdaptable) firstElement)
						.getAdapter(IModelElement.class);
				if (je == null && firstElement instanceof IFile) {
					IContainer parent = ((IFile) firstElement).getParent();
					if (parent != null)
						return parent.getAdapter(IModelElement.class);
					else
						return null;
				} else
					return je;

			} else
				return firstElement;
		}
		Object currentInput = browsingPane.getInput();
		List elements = new ArrayList();
		if (currentInput == null
				|| !currentInput.equals((IModelElement) firstElement))
			if (iter.hasNext() && selection instanceof StructuredSelection) {
				// multi-selection and view is empty
				return ((StructuredSelection) selection).toList();
			} else
				// OK: single selection and view is empty
				return firstElement;

		// be nice to multi-selection
		while (iter.hasNext()) {
			Object element = iter.next();
			if (!(element instanceof IModelElement))
				return null;
			if (!currentInput.equals((IModelElement) element))
				return null;
		}
		return firstElement;
	}

	private ScriptElementTypeComparator fTypeComparator;

	private Comparator getTypeComparator() {
		if (fTypeComparator == null) {
			fTypeComparator = new ScriptElementTypeComparator();
		}
		return fTypeComparator;
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (!needsToProcessSelectionChanged(part, selection))
			return;
		if (!(selection instanceof IStructuredSelection))
			return;
		// Set selection
		Object selectedElement = getSingleElementFromSelection(selection);

		if (selectedElement != null
				&& (part == null || part.equals(fPreviousSelectionProvider))
				&& selectedElement.equals(fPreviousSelectedElement))
			return;
		fPreviousSelectedElement = selectedElement;

		if (selectedElement != null
				&& (selectedElement instanceof IScriptProject || selectedElement instanceof IProjectFragment)) {
			browsingPane.setInput(selectedElement);
		}
	}

	private boolean fProcessSelectionEvents = true;
	private IPartListener2 fPartListener = new IPartListener2() {
		public void partActivated(IWorkbenchPartReference ref) {
		}

		public void partBroughtToTop(IWorkbenchPartReference ref) {
		}

		public void partInputChanged(IWorkbenchPartReference ref) {
		}

		public void partClosed(IWorkbenchPartReference ref) {
		}

		public void partDeactivated(IWorkbenchPartReference ref) {
		}

		public void partOpened(IWorkbenchPartReference ref) {
		}

		public void partVisible(IWorkbenchPartReference ref) {
			if (ref != null && ref.getId() == getSite().getId()) {
				fProcessSelectionEvents = true;
				IWorkbenchPage page = getSite().getWorkbenchWindow()
						.getActivePage();
				if (page != null)
					selectionChanged(page.getActivePart(), page.getSelection());
			}
		}

		public void partHidden(IWorkbenchPartReference ref) {
			if (ref != null && ref.getId() == getSite().getId())
				fProcessSelectionEvents = false;
		}
	};

	ListenerList listenerList = new ListenerList();
	private IDLTKLanguageToolkit fToolkit;

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		listenerList.add(listener);
	}

	public ISelection getSelection() {
		return convertSelection(browsingPane.getSelection());
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		listenerList.remove(listener);
	}

	public void setSelection(ISelection selection) {
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) {
		super.setInitializationData(config, propertyName, data);
		String nature = null;
		if (data instanceof String) {
			nature = (String) data;

		} else if (data instanceof Map) {
			nature = (String) ((Map) data).get("nature");
		}
		if (nature != null) {
			try {
				this.fToolkit = DLTKLanguageManager.getLanguageToolkit(nature);
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				throw new RuntimeException(
						"Nature attribute should be specified and correct", e);
			}
		} else {
			throw new RuntimeException(
					"Nature attribute should be specified and correct");
		}
	}
}
