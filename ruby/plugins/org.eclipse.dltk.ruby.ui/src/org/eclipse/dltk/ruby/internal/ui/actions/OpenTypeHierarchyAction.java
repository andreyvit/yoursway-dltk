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
package org.eclipse.dltk.ruby.internal.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.IDLTKStatusConstants;
import org.eclipse.dltk.internal.ui.actions.ActionMessages;
import org.eclipse.dltk.internal.ui.actions.ActionUtil;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.dltk.internal.ui.browsing.LogicalPackage;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.util.OpenTypeHierarchyUtil;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;

/**
 * This action opens a type hierarchy on the selected type.
 * <p>
 * The action is applicable to selections containing elements of type
 * <code>IType</code>.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @since 2.0
 */
public class OpenTypeHierarchyAction extends SelectionDispatchAction {
	
	private ScriptEditor fEditor;
	
	/**
	 * Creates a new <code>OpenTypeHierarchyAction</code>. The action requires
	 * that the selection provided by the site's selection provider is of type <code>
	 * org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param site the site providing context information for this action
	 */
	public OpenTypeHierarchyAction(IWorkbenchSite site) {
		super(site);
		setText(ActionMessages.OpenTypeHierarchyAction_label); 
		setToolTipText(ActionMessages.OpenTypeHierarchyAction_tooltip); 
		setDescription(ActionMessages.OpenTypeHierarchyAction_description); 
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.OPEN_TYPE_HIERARCHY_ACTION);
	}
	
	/**
	 * Creates a new <code>OpenTypeHierarchyAction</code>. The action requires
	 * that the selection provided by the given selection provider is of type <code>
	 * org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param site the site providing context information for this action
	 * @param provider a special selection provider which is used instead 
	 *  of the site's selection provider or <code>null</code> to use the site's
	 *  selection provider
	 * 
	 * @since 3.2
	 * @deprecated Use {@link #setSpecialSelectionProvider(ISelectionProvider)} instead. This API will be
	 * removed after 3.2 M5.
     */
    public OpenTypeHierarchyAction(IWorkbenchSite site, ISelectionProvider provider) {
        this(site);
        setSpecialSelectionProvider(provider);
    }

	
	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the Java editor
	 */
	public OpenTypeHierarchyAction(ScriptEditor editor) {
		this(editor.getEditorSite());
		fEditor= editor;
		setEnabled(SelectionConverter.canOperateOn(fEditor));
	}
	
	/* (non-Javadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void selectionChanged(ITextSelection selection) {
	}

	/* (non-Javadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void selectionChanged(IStructuredSelection selection) {
		setEnabled(isEnabled(selection));
	}
	
	private boolean isEnabled(IStructuredSelection selection) {
		if (selection.size() != 1)
			return false;
		Object input= selection.getFirstElement();
		
		
		if (input instanceof LogicalPackage)
			return true;
		
		if (!(input instanceof IModelElement))
			return false;
		switch (((IModelElement)input).getElementType()) {
			case IModelElement.METHOD:
			case IModelElement.FIELD:
			case IModelElement.TYPE:
				return true;
			case IModelElement.SCRIPT_PROJECT:
			case IModelElement.PROJECT_FRAGMENT:
			case IModelElement.PACKAGE_DECLARATION:
			case IModelElement.SOURCE_MODULE:
				return true;
			default:
				return false;
		}
	}
	
	/* (non-Javadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void run(ITextSelection selection) {
		IModelElement input= SelectionConverter.getInput(fEditor);
		if (!ActionUtil.isProcessable(getShell(), input))
			return;		
		
		try {
			IModelElement[] elements= SelectionConverter.codeResolveOrInputForked(fEditor);
			if (elements == null)
				return;
			List candidates= new ArrayList(elements.length);
			for (int i= 0; i < elements.length; i++) {
				IModelElement[] resolvedElements= OpenTypeHierarchyUtil.getCandidates(elements[i]);
				if (resolvedElements != null)	
					candidates.addAll(Arrays.asList(resolvedElements));
			}
			run((IModelElement[])candidates.toArray(new IModelElement[candidates.size()]));
		} catch (InvocationTargetException e) {
			ExceptionHandler.handle(e, getShell(), getDialogTitle(), ActionMessages.SelectionConverter_codeResolve_failed);
		} catch (InterruptedException e) {
			// cancelled
		}
	}
	
	/* (non-Javadoc)
	 * Method declared on SelectionDispatchAction.
	 */
	public void run(IStructuredSelection selection) {
		if (selection.size() != 1)
			return;
		Object input= selection.getFirstElement();
		
		if (input instanceof LogicalPackage) {
//			IProjectFragment[] fragments= ((LogicalPackage)input).getFragments();
//			if (fragments.length == 0)
				return;
			//input= fragments[0];
		}

		if (!(input instanceof IModelElement)) {
			IStatus status= createStatus(ActionMessages.OpenTypeHierarchyAction_messages_no_java_element); 
			ErrorDialog.openError(getShell(), getDialogTitle(), ActionMessages.OpenTypeHierarchyAction_messages_title, status); 
			return;
		}
		IModelElement element= (IModelElement) input;
		if (!ActionUtil.isProcessable(getShell(), element))
			return;

		List result= new ArrayList(1);
		IStatus status= compileCandidates(result, element);
		if (status.isOK()) {
			run((IModelElement[]) result.toArray(new IModelElement[result.size()]));
		} else {
			ErrorDialog.openError(getShell(), getDialogTitle(), ActionMessages.OpenTypeHierarchyAction_messages_title, status); 
		}
	}

	/*
	 * No Javadoc since the method isn't meant to be public but is
	 * since the beginning
	 */
	public void run(IModelElement[] elements) {
		if (elements.length == 0) {
			getShell().getDisplay().beep();
			return;
		}
		OpenTypeHierarchyUtil.open(elements, getSite().getWorkbenchWindow());
	}
	
	private static String getDialogTitle() {
		return ActionMessages.OpenTypeHierarchyAction_dialog_title; 
	}
	
	private static IStatus compileCandidates(List result, IModelElement elem) {
		IStatus ok= new Status(IStatus.OK, DLTKUIPlugin.getPluginId(), 0, "", null); //$NON-NLS-1$		
		try {
			switch (elem.getElementType()) {
				case IModelElement.METHOD:
				case IModelElement.FIELD:
				case IModelElement.TYPE:
				case IModelElement.SCRIPT_PROJECT:
					result.add(elem);
					return ok;
//				case IModelElement.PROJECT_FRAGMENT:
//					if (((IProjectFragment)elem).concontainsJavaResources()) {
//						result.add(elem);
//						return ok;
//					}
//					return createStatus(ActionMessages.OpenTypeHierarchyAction_messages_no_java_resources); 

				case IModelElement.SOURCE_MODULE:
					ISourceModule cu= (ISourceModule)elem;
					IType[] types= cu.getTypes();
					if (types.length > 0) {
						result.addAll(Arrays.asList(types));
						return ok;
					}
					return createStatus(ActionMessages.OpenTypeHierarchyAction_messages_no_types); 
			}
		} catch (ModelException e) {
			return e.getStatus();
		}
		return createStatus(ActionMessages.OpenTypeHierarchyAction_messages_no_valid_java_element); 
	}
	
	private static IStatus createStatus(String message) {
		return new Status(IStatus.INFO, DLTKUIPlugin.getPluginId(), IDLTKStatusConstants.INTERNAL_ERROR, message, null);
	}			
}
