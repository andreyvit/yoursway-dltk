/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.AbstractToggleLinkingAction;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.ModelElementSorter;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.actions.CustomFiltersActionGroup;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchAdapter;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class ScriptOutlinePage extends Page implements IContentOutlinePage,
		IAdaptable {
	protected ScriptEditor fEditor;

	protected ScriptOutlineViewer fOutlineViewer;

	protected IPreferenceStore fStore;

	private IModelElement fInput;

	protected static Object[] NO_CHILDREN = new Object[0];

	private ListenerList fSelectionChangedListeners = new ListenerList();

	private ListenerList fPostSelectionChangedListeners = new ListenerList();

	private CustomFiltersActionGroup fCustomFiltersActionGroup;

	private org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage.ToggleLinkingAction fToggleLinkingAction;

	protected static class NoClassElement extends WorkbenchAdapter implements
			IAdaptable {
		public NoClassElement() {
		}

		public String toString() {
			return "no class element";
		}

		public Object getAdapter(Class clas) {
			if (clas == IWorkbenchAdapter.class) {
				return this;
			}
			return null;
		}
	}

	/**
	 * The element change listener of thescriptoutline viewer.
	 * 
	 * @see IElementChangedListener
	 */
	protected class ElementChangedListener implements IElementChangedListener {

		public ElementChangedListener() {
		}

		public void elementChanged(final ElementChangedEvent e) {

			if (getControl() == null)
				return;

			Display d = getControl().getDisplay();
			if (d != null) {
				d.asyncExec(new Runnable() {
					public void run() {

						ISourceModule cu = (ISourceModule) fInput;
						IModelElement base = cu;

						IModelElementDelta delta = findElement(base, e
								.getDelta());
						if (delta != null && fOutlineViewer != null) {
							fOutlineViewer.reconcile(delta);
						}
					}
				});
			}
		}

		private boolean isPossibleStructuralChange(IModelElementDelta cuDelta) {

			if (cuDelta.getKind() != IModelElementDelta.CHANGED) {
				return true; // add or remove
			}
			int flags = cuDelta.getFlags();
			if ((flags & IModelElementDelta.F_CHILDREN) != 0) {
				return true;
			}
			return (flags & (IModelElementDelta.F_CONTENT | IModelElementDelta.F_FINE_GRAINED)) == IModelElementDelta.F_CONTENT;
		}

		protected IModelElementDelta findElement(IModelElement unit,
				IModelElementDelta delta) {

			if (delta == null || unit == null)
				return null;

			IModelElement element = delta.getElement();

			if (unit.equals(element)) {
				if (isPossibleStructuralChange(delta)) {
					return delta;
				}
				return null;
			}

			if (element.getElementType() > IModelElement.SOURCE_MODULE)
				return null;

			IModelElementDelta[] children = delta.getAffectedChildren();
			if (children == null || children.length == 0)
				return null;

			for (int i = 0; i < children.length; i++) {
				IModelElementDelta d = findElement(unit, children[i]);
				if (d != null)
					return d;
			}

			return null;
		}
	}

	private class ScriptModelChildrenProvider implements ITreeContentProvider {
		private Object[] NO_CLASS = new Object[] { new NoClassElement() };

		private ElementChangedListener fListener;

		public Object[] getChildren(Object parentElement) {

			if (parentElement instanceof IParent) {
				IParent parent = (IParent) parentElement;
				try {
					Object[] children = parent.getChildren();
					// TODO: Add name filtration here. Show only first elements
					// with same name.
					return children;
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
			return NO_CHILDREN;
		}     

		public Object getParent(Object child) {

			if (child instanceof IModelElement) {
				IModelElement e = (IModelElement) child;
				return e.getParent();
			}
			return null;
		}

		public boolean hasChildren(Object parent) {

			if (parent instanceof IParent) {
				IParent c = (IParent) parent;
				try {
					IModelElement[] children = c.getChildren();
					return ((children != null && children.length > 0));
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		public Object[] getElements(Object parent) {

			return getChildren(parent);
		}

		public void dispose() {

			if (fListener != null) {
				DLTKCore.removeElementChangedListener(fListener);
				fListener = null;
			}
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			boolean isSM = (newInput instanceof ISourceModule);

			if (isSM && fListener == null) {
				fListener = new ElementChangedListener();
				DLTKCore.addElementChangedListener(fListener);
			} else if (!isSM && fListener != null) {
				DLTKCore.removeElementChangedListener(fListener);
				fListener = null;
			}
		}
	}

	public class ScriptOutlineViewer extends TreeViewer {
		private Item fReusedExpandedItem;

		private boolean fReorderedMembers;

		private boolean fForceFireSelectionChanged;

		public ScriptOutlineViewer(Tree tree) {
			super(tree);
			setAutoExpandLevel(ALL_LEVELS);
			setUseHashlookup(true);
		}

		public void reconcile(IModelElementDelta delta) {
			fReorderedMembers = false;
			fForceFireSelectionChanged = false;
			// refresh();

			if (getSorter() == null) {
				Widget w = findItem(fInput);
				if (w != null && !w.isDisposed())
					update(w, delta);
				if (fForceFireSelectionChanged)
					fireSelectionChanged(new SelectionChangedEvent(getSite()
							.getSelectionProvider(), this.getSelection()));
				if (fReorderedMembers) {
					refresh(false);
					fReorderedMembers = false;
				}
			} else { // just for now
				refresh(true);
			}

		}

		protected void update(Widget w, IModelElementDelta delta) {

			Item item;

			IModelElement parent = delta.getElement();
			IModelElementDelta[] affected = delta.getAffectedChildren();
			Item[] children = getChildren(w);

			boolean doUpdateParent = false;
			boolean doUpdateParentsPlus = false;

			Vector deletions = new Vector();
			Vector additions = new Vector();

			for (int i = 0; i < affected.length; i++) {
				IModelElementDelta affectedDelta = affected[i];
				IModelElement affectedElement = affectedDelta.getElement();
				int status = affected[i].getKind();

				// find tree item with affected element
				int j;
				for (j = 0; j < children.length; j++)
					if (affectedElement.equals(children[j].getData()))
						break;

				if (j == children.length) {
					// remove from collapsed parent
					if ((status & IModelElementDelta.REMOVED) != 0) {
						doUpdateParentsPlus = true;
						continue;
					}
					// addition
					if ((status & IModelElementDelta.CHANGED) != 0
							&& (affectedDelta.getFlags() & IModelElementDelta.F_MODIFIERS) != 0
							&& !filtered(parent, affectedElement)) {
						additions.addElement(affectedDelta);
					}
					continue;
				}

				item = children[j];

				// removed
				if ((status & IModelElementDelta.REMOVED) != 0) {
					deletions.addElement(item);

					// changed
				} else if ((status & IModelElementDelta.CHANGED) != 0) {
					int change = affectedDelta.getFlags();

					if ((change & IModelElementDelta.F_MODIFIERS) != 0) {
						if (filtered(parent, affectedElement))
							deletions.addElement(item);
						else
							updateItem(item, affectedElement);
					}

					if ((change & IModelElementDelta.F_CONTENT) != 0)
						updateItem(item, affectedElement);

					if ((change & IModelElementDelta.F_CHILDREN) != 0)
						update(item, affectedDelta);

					if ((change & IModelElementDelta.F_REORDER) != 0)
						fReorderedMembers = true;
				}
			}

			// find all elements to add
			IModelElementDelta[] add = delta.getAddedChildren();
			if (additions.size() > 0) {
				IModelElementDelta[] tmp = new IModelElementDelta[add.length
						+ additions.size()];
				System.arraycopy(add, 0, tmp, 0, add.length);
				for (int i = 0; i < additions.size(); i++)
					tmp[i + add.length] = (IModelElementDelta) additions
							.elementAt(i);
				add = tmp;
			}

			// add at the right position
			go2: for (int i = 0; i < add.length; i++) {

				try {

					IModelElement e = add[i].getElement();
					if (filtered(parent, e))
						continue go2;

					ISourceRange rng = getSourceRange(e);
					int start = rng.getOffset();
					int end = start + rng.getLength() - 1;
					int nameOffset = Integer.MAX_VALUE;
					if (e instanceof IField) {
						ISourceRange nameRange = ((IField) e).getNameRange();
						if (nameRange != null)
							nameOffset = nameRange.getOffset();
					}

					Item last = null;
					item = null;
					children = getChildren(w);

					for (int j = 0; j < children.length; j++) {
						item = children[j];
						IModelElement r = (IModelElement) item.getData();

						if (r == null) {
							// parent node collapsed and not be opened before ->
							// do nothing
							continue go2;
						}

						try {
							rng = getSourceRange(r);

							// multi-field declarations always start at
							// the same offset. They also have the same
							// end offset if the field sequence is terminated
							// with a semicolon. If not, the source range
							// ends behind the identifier / initializer
							// see
							// https://bugs.eclipse.org/bugs/show_bug.cgi?id=51851
							boolean multiFieldDeclaration = r.getElementType() == IModelElement.FIELD
									&& e.getElementType() == IModelElement.FIELD
									&& rng.getOffset() == start;

							// elements are inserted by occurrence
							// however, multi-field declarations have
							// equal source ranges offsets, therefore we
							// compare name-range offsets.
							boolean multiFieldOrderBefore = false;
							if (multiFieldDeclaration) {
								if (r instanceof IField) {
									ISourceRange nameRange = ((IField) r)
											.getNameRange();
									if (nameRange != null) {
										if (nameRange.getOffset() > nameOffset)
											multiFieldOrderBefore = true;
									}
								}
							}

							if (!multiFieldDeclaration
									&& overlaps(rng, start, end)) {

								// be tolerant if the delta is not correct, or
								// if
								// the tree has been updated other than by a
								// delta
								// reuseTreeItem(item, e);
								createTreeItem(w, e, -1);
								// updateItem(item, e);
								// updatePlus(item, e);
								// internalExpandToLevel(item, ALL_LEVELS);
								//
								// fReusedExpandedItem = null;
								// fForceFireSelectionChanged = true;
								continue go2;

							} else if (multiFieldOrderBefore
									|| rng.getOffset() > start) {

								if (last != null && deletions.contains(last)) {
									// reuse item
									deletions.removeElement(last);
									reuseTreeItem(last, e);
								} else {
									// nothing to reuse
									createTreeItem(w, e, j);
								}
								continue go2;
							}

						} catch (ModelException x) {
							// stumbled over deleted element
						}

						last = item;
					}

					// add at the end of the list
					if (last != null && deletions.contains(last)) {
						// reuse item
						deletions.removeElement(last);
						reuseTreeItem(last, e);
					} else {
						// nothing to reuse
						createTreeItem(w, e, -1);
					}

				} catch (ModelException x) {
					// the element to be added is not present -> don't add it
				}
			}

			// remove items which haven't been reused
			Enumeration e = deletions.elements();
			while (e.hasMoreElements()) {
				item = (Item) e.nextElement();
				disassociate(item);
				item.dispose();
			}

			if (doUpdateParent) {
				updateItem(w, delta.getElement());
			}
			if (!doUpdateParent && doUpdateParentsPlus && w instanceof Item) {
				updatePlus((Item) w, delta.getElement());
			}
		}

		protected boolean filtered(IModelElement parent, IModelElement child) {

			Object[] result = new Object[] { child };
			ViewerFilter[] filters = getFilters();
			for (int i = 0; i < filters.length; i++) {
				result = filters[i].filter(this, parent, result);
				if (result.length == 0)
					return true;
			}

			return false;
		}

		protected void reuseTreeItem(Item item, Object element) {

			// remove children
			Item[] c = getChildren(item);
			if (c != null && c.length > 0) {

				if (getExpanded(item)) {
					fReusedExpandedItem = item;
				}

				for (int k = 0; k < c.length; k++) {
					if (c[k].getData() != null) {
						disassociate(c[k]);
					}
					c[k].dispose();
				}
			}

			updateItem(item, element);
			updatePlus(item, element);
			internalExpandToLevel(item, ALL_LEVELS);

			fReusedExpandedItem = null;
			fForceFireSelectionChanged = true;
		}

		protected ISourceRange getSourceRange(IModelElement element)
				throws ModelException {

			if (element instanceof ISourceReference)
				return ((ISourceReference) element).getSourceRange();
			if (element instanceof IMember)
				return ((IMember) element).getNameRange();
			// if( element instanceof IField ) return ( ( IField )element
			// ).getNameRange( );
			return null;
		}

		protected boolean overlaps(ISourceRange range, int start, int end) {

			return start <= (range.getOffset() + range.getLength() - 1)
					&& range.getOffset() <= end;
		}
	}

	public ScriptOutlinePage(ScriptEditor editor, IPreferenceStore store) {
		fStore = store;
		this.fEditor = editor;
	}

	protected ITreeContentProvider newScriptModelChildrenProvider() {
		return new ScriptModelChildrenProvider();
	}

	protected ILabelDecorator getLabelDecorator() {
		return null;
	}

	public void createControl(Composite parent) {

		Tree tree = new Tree(parent, SWT.MULTI);

		// TODO: Add preference store handline here.

		AppearanceAwareLabelProvider lprovider = new AppearanceAwareLabelProvider(
				AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS,
				AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS, this.fStore) {
			protected ImageDescriptor getSourceModuleIcon(
					IModelElement element, int renderFlags) {
				if (DLTKCore.DEBUG) {
					System.err
							.println("ScriptOutlinePage create label provider returns ghost for source modules...");
				}
								
				//return DLTKPluginImages.DESC_OBJS_GHOST;
				
				return null;
			}
		};
		
		ILabelDecorator ldecorator = getLabelDecorator();
		if (ldecorator != null)
			lprovider.addLabelDecorator(ldecorator);

		fOutlineViewer = new ScriptOutlineViewer(tree);
		fOutlineViewer.setContentProvider(newScriptModelChildrenProvider());		
		fOutlineViewer.setLabelProvider(lprovider);

		Object[] listeners = fSelectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			fSelectionChangedListeners.remove(listeners[i]);
			fOutlineViewer
					.addSelectionChangedListener((ISelectionChangedListener) listeners[i]);
		}

		listeners = fPostSelectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			fPostSelectionChangedListeners.remove(listeners[i]);
			fOutlineViewer
					.addPostSelectionChangedListener((ISelectionChangedListener) listeners[i]);
		}

		IPageSite site = getSite();
		site.setSelectionProvider(fOutlineViewer);

		IActionBars bars = site.getActionBars();

		bars.setGlobalActionHandler(DLTKActionConstants.SHOW_DOCUMENTAION,
				fEditor.getAction("ShowDocumentation")); //$NON-NLS-1$

		registerToolbarActions(bars);

		fOutlineViewer.setInput(fInput);
	}

	public Control getControl() {

		if (fOutlineViewer != null) {
			return fOutlineViewer.getControl();
		}
		return null;
	}

	public void setFocus() {

		// TODO Auto-generated method stub

	}

	public Object getAdapter(Class adapter) {

		// TODO Auto-generated method stub
		return null;
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {

		if (fOutlineViewer != null) {
			fOutlineViewer.addSelectionChangedListener(listener);
		} else {
			fSelectionChangedListeners.add(listener);
		}
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {

		if (fOutlineViewer != null) {
			fOutlineViewer.removeSelectionChangedListener(listener);
		} else {
			fSelectionChangedListeners.remove(listener);
		}
	}

	public ISelection getSelection() {
		if (fOutlineViewer == null)
			return StructuredSelection.EMPTY;
		return fOutlineViewer.getSelection();
	}

	public void addPostSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (fOutlineViewer != null)
			fOutlineViewer.addPostSelectionChangedListener(listener);
		else
			fPostSelectionChangedListeners.add(listener);
	}

	public void removePostSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (fOutlineViewer != null) {
			fOutlineViewer.removePostSelectionChangedListener(listener);
		} else {
			fPostSelectionChangedListeners.remove(listener);
		}
	}

	public void setSelection(ISelection selection) {
		if (fOutlineViewer != null) {
			fOutlineViewer.setSelection(selection);
		}

	}

	public void setInput(IModelElement module) {
		fInput = module;
		if (fOutlineViewer != null) {
			fOutlineViewer.setInput(fInput);
		}
	}

	private void registerToolbarActions(IActionBars actionBars) {
		IToolBarManager toolBarManager = actionBars.getToolBarManager();
		if (toolBarManager != null) {
			toolBarManager.add(new LexicalSortingAction());
		}

		registerSpecialToolbarActions(actionBars);

		// Custom filter group
		fCustomFiltersActionGroup = new CustomFiltersActionGroup(fEditor
				.getEditorId(), fOutlineViewer);
		fCustomFiltersActionGroup.fillActionBars(actionBars);

		IMenuManager viewMenuManager = actionBars.getMenuManager();
		viewMenuManager.add(new Separator("EndFilterGroup")); //$NON-NLS-1$

		fToggleLinkingAction = new ToggleLinkingAction(this);
		viewMenuManager.add(fToggleLinkingAction);

	}

	protected void registerSpecialToolbarActions(IActionBars actionBars) {
		// derived classes could implement it
	}

	/**
	 * This action toggles whether this Script Outline page links its selection
	 * to the active editor.
	 * 
	 * 
	 */
	public class ToggleLinkingAction extends AbstractToggleLinkingAction {

		ScriptOutlinePage fScriptOutlinePage;

		/**
		 * Constructs a new action.
		 * 
		 * @param outlinePage
		 *            the script outline page
		 */
		public ToggleLinkingAction(ScriptOutlinePage outlinePage) {
			boolean isLinkingEnabled = fStore
					.getBoolean(PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE);
			setChecked(isLinkingEnabled);
			fScriptOutlinePage = outlinePage;
		}

		/**
		 * Runs the action.
		 */
		public void run() {
			fStore.setValue(
					PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE,
					isChecked());
			if (isChecked() && fEditor != null)
				fEditor.synchronizeOutlinePage(fEditor
						.computeHighlightRangeSourceReference(), false);
		}

	}

	class LexicalSortingAction extends Action {

		private ModelElementSorter fSorter = new ModelElementSorter();

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

			boolean checked = fStore
					.getBoolean("LexicalSortingAction.isChecked"); //$NON-NLS-1$		
			valueChanged(checked, false);
		}

		public void run() {
			valueChanged(isChecked(), true);
		}

		private void valueChanged(final boolean on, boolean store) {
			setChecked(on);
			BusyIndicator.showWhile(fOutlineViewer.getControl().getDisplay(),
					new Runnable() {
						public void run() {
							fOutlineViewer.setSorter(on ? fSorter : null);
						}
					});

			if (store)
				fStore.setValue("LexicalSortingAction.isChecked", on); //$NON-NLS-1$			
		}
	}

	public void select(ISourceReference reference) {
		if (fOutlineViewer != null) {

			ISelection s = fOutlineViewer.getSelection();
			if (s instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) s;
				List elements = ss.toList();
				if (!elements.contains(reference)) {
					s = (reference == null ? StructuredSelection.EMPTY
							: new StructuredSelection(reference));
					fOutlineViewer.setSelection(s, true);
				}
			}
		}
	}

	public void dispose() {

		if (fEditor == null)
			return;

		if (fCustomFiltersActionGroup != null) {
			fCustomFiltersActionGroup.dispose();
			fCustomFiltersActionGroup = null;
		}

		fEditor.outlinePageClosed();
		fEditor = null;

		fSelectionChangedListeners.clear();
		fSelectionChangedListeners = null;

		fPostSelectionChangedListeners.clear();
		fPostSelectionChangedListeners = null;

		fOutlineViewer = null;

		super.dispose();
	}
}
