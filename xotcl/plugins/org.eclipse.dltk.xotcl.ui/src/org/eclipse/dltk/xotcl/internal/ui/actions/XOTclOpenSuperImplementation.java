package org.eclipse.dltk.xotcl.internal.ui.actions;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.dltk.tcl.internal.ui.editor.TclEditor;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.xotcl.internal.core.XOTclResolver;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class XOTclOpenSuperImplementation implements
		IWorkbenchWindowActionDelegate {
	private IMethod method;

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}

	public void run(IAction action) {
		if (this.method == null) {
			this.method = findTclEditorElement();
		}
		if( method != null ) {
			IMethod superMethod = XOTclResolver.resolveSuperMethod(this.method);
			if (superMethod != null) {
				try {
					DLTKUIPlugin.openInEditor(superMethod);
				} catch (PartInitException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				} catch (ModelException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.method = getMethod(selection);
	}

	private IMethod getMethod(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection s = (IStructuredSelection) selection;
			if (s.size() != 1) {
				return null;
			}
			Object element = s.getFirstElement();
			if (element instanceof IMethod) {
				return (IMethod) element;
			}
		} else if (selection instanceof TextSelection) {
			findTclEditorElement();
		}
		return null;
	}

	private IMethod findTclEditorElement() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = window.getActivePage();
		IEditorPart activeEditor = activePage.getActiveEditor();
		if (activeEditor instanceof TclEditor) {
			TclEditor editor = (TclEditor) activeEditor;
			IModelElement e;
			try {
				e = SelectionConverter.getElementAtOffset(editor);
			} catch (ModelException e1) {
				if (DLTKCore.DEBUG) {
					e1.printStackTrace();
				}
				return null;
			}
			if (e instanceof IMethod) {
				return (IMethod) e;
			}
		}
		return null;
	}
}
