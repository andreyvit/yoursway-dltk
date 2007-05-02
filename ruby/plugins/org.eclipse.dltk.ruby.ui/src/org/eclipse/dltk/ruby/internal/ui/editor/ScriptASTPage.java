/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.editor;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchAdapter;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class ScriptASTPage extends Page implements IContentOutlinePage,
		IAdaptable {
	protected ScriptEditor fEditor;

	protected ScriptASTViewer fASTViewer;

	private Object fInput;

	protected static Object[] NO_CHILDREN = new Object[0];

	private ListenerList fSelectionChangedListeners = new ListenerList();

	private ListenerList fPostSelectionChangedListeners = new ListenerList();

	private org.eclipse.dltk.ruby.internal.ui.editor.ScriptASTPage.ElementChangedListener fFListener;

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

			updateInput();
			fASTViewer.setInput(fInput);
			fASTViewer.refresh();
		}

	}

	private class ScriptASTChildrenProvider implements ITreeContentProvider {

		private ElementChangedListener fListener;

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof ASTNode) {
				ASTNode node = (ASTNode) parentElement;
				List childs = node.getChilds();
				return childs.toArray(new ASTNode[childs.size()]);
			}
			return new Object[0];
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof ASTNode) {
				ASTNode node = (ASTNode) element;
				List childs = node.getChilds();
				return childs.size() > 0;
			}
			return false;
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public void dispose() {
			if (fListener != null) {
				DLTKCore.removeElementChangedListener(fListener);
				fListener = null;
			}

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			newInput = EditorUtility.getEditorInputModelElement(fEditor, false);
			;
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

	public class ScriptASTViewer extends TreeViewer {

		public ScriptASTViewer(Tree tree) {
			super(tree);
			setAutoExpandLevel(ALL_LEVELS);
			setUseHashlookup(true);
		}
	}

	public ScriptASTPage(ScriptEditor editor) {
		this.fEditor = editor;
		this.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				Object source = event.getSource();
				if (event.getSelection() instanceof StructuredSelection) {
					StructuredSelection selection = (StructuredSelection) event
							.getSelection();
					Object element = selection.getFirstElement();
					if (element instanceof ASTNode) {
						ASTNode node = (ASTNode) element;
						fEditor.selectAndReveal(node.sourceStart(), node
								.sourceEnd()
								- node.sourceStart());
					}
				}
			}

		});
	}

	protected ITreeContentProvider newScriptModelChildrenProvider() {
		return new ScriptASTChildrenProvider();
	}

	protected ILabelDecorator getLabelDecorator() {
		return null;
	}

	public void createControl(Composite parent) {

		Tree tree = new Tree(parent, SWT.MULTI);

		fASTViewer = new ScriptASTViewer(tree);
		fASTViewer.setContentProvider(newScriptModelChildrenProvider());
		fASTViewer.setLabelProvider(new LabelProvider() {
			private String getLabel(ASTNode node) {
				String className = node.getClass().getName();
				int index = className.lastIndexOf('.');
				if (index != -1) {
					className = className.substring(index + 1);
				}
				
				return className + " [" + node.sourceStart()
						+ ", " + node.sourceEnd() + "]";
			}

			public String getText(Object element) {
				if (element instanceof ASTNode) {
					return getLabel((ASTNode) element);
				} else
					return super.getText(element);
			}

			public Image getImage(Object element) {
				return DLTKPluginImages
						.get(DLTKPluginImages.IMG_OBJS_LOCAL_VARIABLE);
			}

		});

		Object[] listeners = fSelectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			fSelectionChangedListeners.remove(listeners[i]);
			fASTViewer
					.addSelectionChangedListener((ISelectionChangedListener) listeners[i]);
		}

		listeners = fPostSelectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			fPostSelectionChangedListeners.remove(listeners[i]);
			fASTViewer
					.addPostSelectionChangedListener((ISelectionChangedListener) listeners[i]);
		}

		IPageSite site = getSite();
		site.setSelectionProvider(fASTViewer);

		IActionBars bars = site.getActionBars();

		bars.setGlobalActionHandler(DLTKActionConstants.SHOW_DOCUMENTAION,
				fEditor.getAction("ShowDocumentation")); //$NON-NLS-1$

		registerToolbarActions(bars);

		if (fInput == null) {
			updateInput();
		}

		if (fFListener == null) {
			fFListener = new ElementChangedListener();
			DLTKCore.addElementChangedListener(fFListener);
		}
		fASTViewer.setInput(fInput);
	}

	private void updateInput() {
		IModelElement inputModelElement = EditorUtility
				.getEditorInputModelElement(fEditor, false);
		;
		if (inputModelElement instanceof ISourceModule) {
			ISourceModule module = (ISourceModule) inputModelElement;
			try {
				char[] sourceAsCharArray = module.getSourceAsCharArray();
				ISourceParser parser = DLTKLanguageManager
						.getSourceParser(fEditor.getLanguageToolkit()
								.getNatureID());
				ModuleDeclaration m = parser.parse(sourceAsCharArray, null);
				fInput = m;
			} catch (ModelException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Control getControl() {

		if (fASTViewer != null) {
			return fASTViewer.getControl();
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

		if (fASTViewer != null) {
			fASTViewer.addSelectionChangedListener(listener);
		} else {
			fSelectionChangedListeners.add(listener);
		}
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {

		if (fASTViewer != null) {
			fASTViewer.removeSelectionChangedListener(listener);
		} else {
			fSelectionChangedListeners.remove(listener);
		}
	}

	public ISelection getSelection() {
		if (fASTViewer == null)
			return StructuredSelection.EMPTY;
		return fASTViewer.getSelection();
	}

	public void addPostSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (fASTViewer != null)
			fASTViewer.addPostSelectionChangedListener(listener);
		else
			fPostSelectionChangedListeners.add(listener);
	}

	public void removePostSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (fASTViewer != null) {
			fASTViewer.removePostSelectionChangedListener(listener);
		} else {
			fPostSelectionChangedListeners.remove(listener);
		}
	}

	public void setSelection(ISelection selection) {
		if (fASTViewer != null) {
			fASTViewer.setSelection(selection);
		}

	}

	public void setInput(IModelElement module) {
		fInput = module;
		if (fASTViewer != null) {
			fASTViewer.setInput(fInput);
		}
	}

	private void registerToolbarActions(IActionBars actionBars) {
		IToolBarManager toolBarManager = actionBars.getToolBarManager();

		IMenuManager viewMenuManager = actionBars.getMenuManager();
		viewMenuManager.add(new Separator("EndFilterGroup")); //$NON-NLS-1$

	}

	public void select(ISourceReference reference) {
		if (fASTViewer != null) {

			ISelection s = fASTViewer.getSelection();
			if (s instanceof IStructuredSelection) {
				List elements = ((IStructuredSelection) s).toList();
				if (!elements.contains(reference)) {
					s = (reference == null ? StructuredSelection.EMPTY
							: new StructuredSelection(reference));
					fASTViewer.setSelection(s, true);
				}
			}
		}
	}

	public void dispose() {

		if (fEditor == null)
			return;

		fEditor = null;

		fSelectionChangedListeners.clear();
		fSelectionChangedListeners = null;

		fPostSelectionChangedListeners.clear();
		fPostSelectionChangedListeners = null;

		fASTViewer = null;

		if (fFListener != null) {
			DLTKCore.removeElementChangedListener(fFListener);
			fFListener = null;
		}

		super.dispose();
	}
}
