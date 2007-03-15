/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.views.contentoutline;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.wst.javascript.ui.internal.common.ContentElement;
import org.eclipse.wst.javascript.ui.internal.common.ContentElementComparerImpl;
import org.eclipse.wst.javascript.ui.internal.common.JSContentElementImpl;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImageHelper;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImages;
import org.eclipse.wst.javascript.ui.internal.editor.JavaScriptUIMessages;
import org.eclipse.wst.javascript.ui.internal.editor.Logger;
import org.eclipse.wst.sse.ui.internal.contentoutline.PropertyChangeUpdateAction;
import org.eclipse.wst.sse.ui.internal.contentoutline.PropertyChangeUpdateActionContributionItem;
import org.eclipse.wst.sse.ui.internal.edit.util.SharedEditorPluginImageHelper;

public class JSContentOutlinePage extends ContentOutlinePage implements IDocumentListener {
	/**
	 * Structured source files tend to have large/long tree structures. Add a
	 * collapse action to help with navigation.
	 */
	protected class CollapseTreeAction extends Action {
		private TreeViewer fTreeViewer = null;

		public CollapseTreeAction(TreeViewer viewer) {
			super(JavaScriptUIMessages.JSContentOutlinePage_0, AS_PUSH_BUTTON); //$NON-NLS-1$
			setImageDescriptor(COLLAPSE_E);
			setDisabledImageDescriptor(COLLAPSE_D);
			setToolTipText(getText());
			fTreeViewer = viewer;
		}

		public void run() {
			super.run();
			fTreeViewer.collapseAll();
		}
	}

	protected class DeleteAction extends Action {
		public DeleteAction() {
			super(JavaScriptUIMessages.JSContentOutlinePage_5); //$NON-NLS-1$
			setImageDescriptor(DELETE_E);
			setDisabledImageDescriptor(DELETE_D);
			setToolTipText(getText());
		}

		public void run() {
			ISelection selection = getTreeViewer().getSelection();
			if (selection != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection) selection).getFirstElement();
				if (firstElement instanceof ContentElement) {
					ContentElement contentElement = (ContentElement) firstElement;
					try {
						fDocument.replace(contentElement.getOffset(), contentElement.getLength(), ""); //$NON-NLS-1$
						getTreeViewer().refresh();
					}
					catch (BadLocationException exception) {
						Logger.logException(exception);
					}
				}
			}
		}
	}

	private class DoubleClickProvider implements IDoubleClickListener {
		private IDoubleClickListener[] listeners = null;

		void addDoubleClickListener(IDoubleClickListener newListener) {
			if (listeners == null) {
				listeners = new IDoubleClickListener[]{newListener};
			}
			else {
				IDoubleClickListener[] newListeners = new IDoubleClickListener[listeners.length + 1];
				System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
				newListeners[listeners.length] = newListener;
				listeners = newListeners;
			}
		}

		public void doubleClick(DoubleClickEvent event) {
			fireDoubleClickEvent(event);
		}

		private void fireDoubleClickEvent(final DoubleClickEvent event) {
			IDoubleClickListener[] firingListeners = listeners;
			for (int i = 0; i < firingListeners.length; ++i) {
				final IDoubleClickListener l = firingListeners[i];
				Platform.run(new SafeRunnable() {
					public void run() {
						l.doubleClick(event);
					}
				});
			}
		}

		void removeDoubleClickListener(IDoubleClickListener oldListener) {
			if (listeners != null) {
				if (listeners.length == 1 && listeners[0].equals(oldListener)) {
					listeners = null;
				}
				else {
					List newListeners = new ArrayList(Arrays.asList(listeners));
					newListeners.remove(oldListener);
					listeners = (IDoubleClickListener[]) newListeners.toArray(new IDoubleClickListener[listeners.length - 1]);
				}
			}
		}
	}

	protected class ShowHierarchyAction extends PropertyChangeUpdateAction {
		public ShowHierarchyAction(String text, IPreferenceStore store, String preferenceKey, boolean defaultValue) {
			super(text, store, preferenceKey, defaultValue);
			setToolTipText(getText());
			setImageDescriptor(JSEditorPluginImageHelper.getInstance().getImageDescriptor(JSEditorPluginImages.IMG_OBJ_HIERARCHY));
		}

		public void update() {
			super.update();
			JSContentElementImpl.setSupportChildren(isChecked());
			Object[] expanded = getTreeViewer().getExpandedElements();
			getTreeViewer().refresh();
			getTreeViewer().setExpandedElements(expanded);
		}
	}

	private class ShowInTarget implements IShowInTarget {
		/*
		 * @see org.eclipse.ui.part.IShowInTarget#show(org.eclipse.ui.part.ShowInContext)
		 */
		public boolean show(ShowInContext context) {
			setSelection(getStructuredSelectionFor(context.getSelection()));
			return getTreeViewer().getSelection().equals(context.getSelection());
		}
	}

	protected class ShowVariablesAction extends PropertyChangeUpdateAction {
		public ShowVariablesAction(String text, IPreferenceStore store, String preferenceKey, boolean defaultValue) {
			super(text, store, preferenceKey, defaultValue);
			setToolTipText(getText());
			setImageDescriptor(JSEditorPluginImageHelper.getInstance().getImageDescriptor(JSEditorPluginImages.IMG_OBJ_DEFAULT));
		}

		public void update() {
			super.update();
			JSContentElementImpl.setSupportVariables(isChecked());
			Object[] expanded = getTreeViewer().getExpandedElements();
			getTreeViewer().refresh();
			getTreeViewer().setExpandedElements(expanded);
		}
	}

	protected class SortAction extends PropertyChangeUpdateAction {
		/**
		 * @deprecated use CategoryComparator instead
		 */
		protected class CategorySorter extends ViewerSorter {
			public CategorySorter() {
				super();
			}

			public CategorySorter(Collator collator) {
				super(collator);
			}

			public int category(Object element) {
				if (element instanceof ContentElement) {
					return ((ContentElement) element).getType();
				}
				return 0;
			}
		}

		protected class CategoryComparator extends ViewerComparator {
			public CategoryComparator() {
				super();
			}

			public int category(Object element) {
				if (element instanceof ContentElement) {
					return ((ContentElement) element).getType();
				}
				return 0;
			}
		}

		private TreeViewer treeViewer;

		public SortAction(TreeViewer viewer, IPreferenceStore store, String preferenceKey) {
			super(JavaScriptUIMessages.JSContentOutlinePage_4, store, preferenceKey, false); //$NON-NLS-1$
			setImageDescriptor(JSEditorPluginImageHelper.getInstance().getImageDescriptor(JSEditorPluginImages.IMG_OBJ_SORT));
			setToolTipText(getText());
			treeViewer = viewer;
			if (isChecked()) {
				treeViewer.setComparator(new CategoryComparator());
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.texteditor.IUpdate#update()
		 */
		public void update() {
			super.update();
			treeViewer.getControl().setRedraw(false);
			Object[] expandedElements = treeViewer.getExpandedElements();
			ISelection selection = treeViewer.getSelection();
			if (isChecked()) {
				treeViewer.setComparator(new CategoryComparator());
			}
			else {
				treeViewer.setComparator(null);
			}
			treeViewer.setInput(treeViewer.getInput());
			treeViewer.setExpandedElements(expandedElements);
			treeViewer.setSelection(selection);
			treeViewer.getControl().setRedraw(true);
		}
	}

	protected class ToggleLinkAction extends PropertyChangeUpdateAction {
		public ToggleLinkAction(IPreferenceStore store, String preference) {
			super(JavaScriptUIMessages.JSContentOutlinePage_1, store, preference, true); //$NON-NLS-1$
			setToolTipText(getText());
			setDisabledImageDescriptor(SYNCED_D);
			setImageDescriptor(SYNCED_E);
			update();
		}

		public void update() {
			setLinkWithEditor(isChecked());
		}
	}

	private static final String OUTLINE_LINK_PREF = "outline-link-editor"; //$NON-NLS-1$
	private static final String OUTLINE_SHOW_HIERARCHY_PREF = "outline-show-hierarchy"; //$NON-NLS-1$
	private static final String OUTLINE_SHOW_VARIABLES_PREF = "outline-show-variables"; //$NON-NLS-1$
	private static final String OUTLINE_SORT_PREF = "outline-sort"; //$NON-NLS-1$

	protected ImageDescriptor COLLAPSE_D = SharedEditorPluginImageHelper.getImageDescriptor(SharedEditorPluginImageHelper.IMG_DLCL_COLLAPSEALL);
	protected ImageDescriptor COLLAPSE_E = SharedEditorPluginImageHelper.getImageDescriptor(SharedEditorPluginImageHelper.IMG_ELCL_COLLAPSEALL);
	protected ImageDescriptor DELETE_D = SharedEditorPluginImageHelper.getImageDescriptor(SharedEditorPluginImageHelper.IMG_DLCL_DELETE);
	protected ImageDescriptor DELETE_E = SharedEditorPluginImageHelper.getImageDescriptor(SharedEditorPluginImageHelper.IMG_ELCL_DELETE);
	private MenuManager fContextMenu = null;
	private boolean fContextMenuRegistered = false;

	protected DeleteAction fDeleteAction = new DeleteAction();
	protected IDocument fDocument = null;
	private DoubleClickProvider fDoubleClickProvider;
	private boolean fIsLinked = true;
	private ISelectionListener fSelectionListener;

	private PropertyChangeUpdateActionContributionItem fShowHierarchyItem;
	private PropertyChangeUpdateActionContributionItem fShowVariablesItem;
	private PropertyChangeUpdateActionContributionItem fSortItem;
	protected ISourceViewer fSourceViewer = null;
	private PropertyChangeUpdateActionContributionItem fToggleLinkItem;
	protected ImageDescriptor SYNCED_D = SharedEditorPluginImageHelper.getImageDescriptor(SharedEditorPluginImageHelper.IMG_DLCL_SYNCED);
	protected ImageDescriptor SYNCED_E = SharedEditorPluginImageHelper.getImageDescriptor(SharedEditorPluginImageHelper.IMG_ELCL_SYNCED);

	public JSContentOutlinePage(IDocument document, ISourceViewer sourceViewer) {
		setDocument(document);
		setSourceViewer(sourceViewer);
	}

	public void addDoubleClickListener(IDoubleClickListener listener) {
		if (fDoubleClickProvider == null) {
			fDoubleClickProvider = new DoubleClickProvider();
		}
		fDoubleClickProvider.addDoubleClickListener(listener);
	}

	protected void contextMenuAboutToShow(IMenuManager menuManager) {
		menuManager.add(fDeleteAction);
		IContributionItem[] items = menuManager.getItems();
		if (items.length > 0 && items[items.length - 1].getId() != null) {
			menuManager.insertAfter(items[items.length - 1].getId(), new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		}
		else {
			menuManager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	public void createControl(Composite parent) {
		super.createControl(parent);

		getTreeViewer().setContentProvider(new JSTreeContentProvider());
		getTreeViewer().setLabelProvider(new JSLabelProvider());
		getTreeViewer().setInput(fDocument);
		getTreeViewer().setComparer(new ContentElementComparerImpl());
		if (fDoubleClickProvider == null) {
			fDoubleClickProvider = new DoubleClickProvider();
		}
		getTreeViewer().addDoubleClickListener(fDoubleClickProvider);

		if (fDocument != null) {
			fDocument.addDocumentListener(this);
		}

		fContextMenu = new MenuManager("#popup"); //$NON-NLS-1$
		fContextMenu.setRemoveAllWhenShown(true);
		fContextMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager menuManager) {
				contextMenuAboutToShow(menuManager);
			}
		});
		Menu menu = fContextMenu.createContextMenu(getTreeViewer().getTree());
		getTreeViewer().getTree().setMenu(menu);
		registerContextMenu();
	}

	public void dispose() {
		super.dispose();

		// remove this from DocumentListener
		if (fDocument != null)
			fDocument.removeDocumentListener(this);

		// disconnect preference change listening contribution items
		if (fShowHierarchyItem != null)
			fShowHierarchyItem.disconnect();
		if (fSortItem != null)
			fSortItem.disconnect();
		if (fShowVariablesItem != null)
			fShowVariablesItem.disconnect();
		if (fToggleLinkItem != null)
			fToggleLinkItem.disconnect();

		getSite().getWorkbenchWindow().getSelectionService().removePostSelectionListener(getSelectionServiceListener());
	}

	/**
	 * The manipulation described by the document event will be performed.
	 * 
	 * @param event
	 *            the document event describing the document change
	 */
	public void documentAboutToBeChanged(DocumentEvent event) {
		//
	}

	/**
	 * The manipulation described by the document event has been performed.
	 * 
	 * @param event
	 *            the document event describing the document change
	 */
	public void documentChanged(DocumentEvent event) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		Object adapter = null;
		if (key.equals(IShowInTarget.class)) {
			adapter = new ShowInTarget();
		}

		final IEditorPart editor = getActiveEditorPart();

		if (key.equals(IShowInSource.class) && editor != null) {
			adapter = new IShowInSource() {
				public ShowInContext getShowInContext() {
					return new ShowInContext(editor.getEditorInput(), editor.getEditorSite().getSelectionProvider().getSelection());
				}
			};
		}
		else if (key.equals(IShowInTargetList.class) && editor != null) {
			adapter = editor.getAdapter(key);
		}
		return adapter;
	}

	IEditorPart getActiveEditorPart() {
		return getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
	}

	ContentElement getContentElementAt(ContentElement contentElement, int caretPosition) {
		ContentElement result = null;

		List children = contentElement.getChildren();
		if (children != null) {
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				ContentElement eachContentElement = (ContentElement) iter.next();
				result = getContentElementAt(eachContentElement, caretPosition);

				if (result != null)
					return result;
			}
		}

		int offset = contentElement.getOffset();
		int length = contentElement.getLength();
		if ((caretPosition >= offset) && (caretPosition <= offset + length))
			result = contentElement;

		return result;
	}

	private ISelectionListener getSelectionServiceListener() {
		if (fSelectionListener == null) {
			fSelectionListener = new ISelectionListener() {

				public void selectionChanged(IWorkbenchPart part, ISelection selection) {
					if (!getTreeViewer().getControl().isDisposed() && !getTreeViewer().getControl().isFocusControl() && getTreeViewer().getControl().isVisible()) {
						if (fIsLinked) {
							IStructuredSelection structuredSelection = getStructuredSelectionFor(selection);
							if (structuredSelection != null) {
								getTreeViewer().setSelection(structuredSelection);
							}
						}
						getTreeViewer().refresh();
					}
				}
			};
		}
		return fSelectionListener;
	}

	protected ISourceViewer getSourceViewer() {
		return fSourceViewer;
	}

	IStructuredSelection getStructuredSelectionFor(ISelection selection) {
		IStructuredSelection structuredSelection = null;
		if (selection instanceof IStructuredSelection) {
			structuredSelection = (IStructuredSelection) selection;
		}
		else if (selection instanceof ITextSelection) {
			Object o = null;
			if (selection instanceof ITextSelection) {
				Object[] treeItems = getTreeViewer().getTree().getItems();
				for (int i = 0; i < treeItems.length; i++) {
					ContentElement eachContentElement = (ContentElement) ((TreeItem) treeItems[i]).getData();

					o = getContentElementAt(eachContentElement, ((ITextSelection) selection).getOffset());
					if (o != null)
						break;
				}
				if (o != null) {
					structuredSelection = new StructuredSelection(o);
				}
			}
		}
		return structuredSelection;

	}

	public void init(IPageSite pageSite) {
		super.init(pageSite);
		getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(getSelectionServiceListener());
	}

	void registerContextMenu() {
		if (!fContextMenuRegistered && getTreeViewer() != null && getTreeViewer().getControl() != null) {
			IWorkbenchPage page = getSite().getWorkbenchWindow().getActivePage();
			if (page != null) {
				IEditorPart ownerEditor = page.getActiveEditor();
				if (ownerEditor != null) {
					fContextMenuRegistered = true;
					getSite().registerContextMenu(ownerEditor.getEditorSite().getId() + ".OutlineContext", fContextMenu, this); //$NON-NLS-1$
				}
			}
		}
	}

	public void removeDoubleClickListener(IDoubleClickListener listener) {
		if (fDoubleClickProvider != null) {
			fDoubleClickProvider.removeDoubleClickListener(listener);
		}
	}

	public void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);

		SortAction sortAction = new SortAction(getTreeViewer(), JSEditorPlugin.getDefault().getPreferenceStore(), OUTLINE_SORT_PREF);
		fSortItem = new PropertyChangeUpdateActionContributionItem(sortAction);

		PropertyChangeUpdateAction action = new ShowHierarchyAction(JavaScriptUIMessages.JSContentOutlinePage_2, JSEditorPlugin.getInstance().getPreferenceStore(), OUTLINE_SHOW_HIERARCHY_PREF, true); //$NON-NLS-1$
		fShowHierarchyItem = new PropertyChangeUpdateActionContributionItem(action);

		action = new ShowVariablesAction(JavaScriptUIMessages.JSContentOutlinePage_3, JSEditorPlugin.getInstance().getPreferenceStore(), OUTLINE_SHOW_VARIABLES_PREF, true); //$NON-NLS-1$
		fShowVariablesItem = new PropertyChangeUpdateActionContributionItem(action);

		IAction collapseAction = new CollapseTreeAction(getTreeViewer());
		fToggleLinkItem = new PropertyChangeUpdateActionContributionItem(new ToggleLinkAction(JSEditorPlugin.getInstance().getPreferenceStore(), OUTLINE_LINK_PREF));

		actionBars.getToolBarManager().add(fSortItem);
		actionBars.getToolBarManager().add(collapseAction);

		actionBars.getMenuManager().add(fToggleLinkItem);
		actionBars.getMenuManager().add(fShowHierarchyItem);
		actionBars.getMenuManager().add(fShowVariablesItem);

	}

	public void setDocument(IDocument document) {
		if (document != fDocument) {
			fDocument = document;

			if (getTreeViewer() != null) {
				getTreeViewer().setInput(fDocument);
				getTreeViewer().refresh();
			}
		}
	}

	public void setLinkWithEditor(boolean b) {
		fIsLinked = b;
	}

	public void setSelection(ISelection selection) {
		if (getTreeViewer() != null && fIsLinked)
			getTreeViewer().setSelection(selection, true);
	}

	protected void setSourceViewer(ISourceViewer sourceViewer) {
		fSourceViewer = sourceViewer;
	}
}
