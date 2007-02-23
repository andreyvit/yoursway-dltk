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
package org.eclipse.dltk.ruby.internal.ui.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.DLTKUIMessages;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ruby.internal.ui.typehierarchy.TypeHierarchyViewPart;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;

public class OpenTypeHierarchyUtil {
	
	private OpenTypeHierarchyUtil() {
	}

	public static TypeHierarchyViewPart open(IModelElement element, IWorkbenchWindow window) {
		IModelElement[] candidates= getCandidates(element);
		if (candidates != null) {
			return open(candidates, window);
		}
		return null;
	}	
	
	public static TypeHierarchyViewPart open(IModelElement[] candidates, IWorkbenchWindow window) {
		Assert.isTrue(candidates != null && candidates.length != 0);
			
		IModelElement input= null;
		if (candidates.length > 1) {
			String title= DLTKUIMessages.OpenTypeHierarchyUtil_selectionDialog_title;  
			String message= DLTKUIMessages.OpenTypeHierarchyUtil_selectionDialog_message; 
			input= SelectionConverter.selectModelElement(candidates, window.getShell(), title, message);			
		} else {
			input= candidates[0];
		}
		if (input == null)
			return null;
			
//		try {
//			if (PreferenceConstants.OPEN_TYPE_HIERARCHY_IN_PERSPECTIVE.equals(PreferenceConstants.getPreferenceStore().getString(PreferenceConstants.OPEN_TYPE_HIERARCHY))) {
//				return openInPerspective(window, input);
//			} else {
				return openInViewPart(window, input);
//			}
				
//		} catch (WorkbenchException e) {
//			ExceptionHandler.handle(e, window.getShell(),
//				DLTKUIMessages.OpenTypeHierarchyUtil_error_open_perspective, 
//				e.getMessage());
//		} catch (ModelException e) {
//			ExceptionHandler.handle(e, window.getShell(),
//					DLTKUIMessages.OpenTypeHierarchyUtil_error_open_editor, 
//				e.getMessage());
//		}
//		return null;
	}

	private static TypeHierarchyViewPart openInViewPart(IWorkbenchWindow window, IModelElement input) {
		IWorkbenchPage page= window.getActivePage();
		try {
			TypeHierarchyViewPart result= (TypeHierarchyViewPart) page.findView(RubyUI.ID_TYPE_HIERARCHY);
			if (result != null) {
				result.clearNeededRefresh(); // avoid refresh of old hierarchy on 'becomes visible'
			}
			result= (TypeHierarchyViewPart) page.showView(RubyUI.ID_TYPE_HIERARCHY);
			result.setInputElement(input);
			return result;
		} catch (CoreException e) {
			ExceptionHandler.handle(e, window.getShell(), 
				DLTKUIMessages.OpenTypeHierarchyUtil_error_open_view, e.getMessage()); 
		}
		return null;		
	}
	
//	private static TypeHierarchyViewPart openInPerspective(IWorkbenchWindow window, IModelElement input) throws WorkbenchException, JavaModelException {
//		IWorkbench workbench= JavaPlugin.getDefault().getWorkbench();
//		// The problem is that the input element can be a working copy. So we first convert it to the original element if
//		// it exists.
//		IModelElement perspectiveInput= input;
//		
//		if (input instanceof IMember) {
//			if (input.getElementType() != IModelElement.TYPE) {
//				perspectiveInput= ((IMember)input).getDeclaringType();
//			} else {
//				perspectiveInput= input;
//			}
//		}
//		IWorkbenchPage page= workbench.showPerspective(JavaUI.ID_HIERARCHYPERSPECTIVE, window, perspectiveInput);
//		
//		TypeHierarchyViewPart part= (TypeHierarchyViewPart) page.findView(RubyUI.ID_TYPE_HIERARCHY);
//		if (part != null) {
//			part.clearNeededRefresh(); // avoid refresh of old hierarchy on 'becomes visible'
//		}		
//		part= (TypeHierarchyViewPart) page.showView(RubyUI.ID_TYPE_HIERARCHY);
//		part.setInputElement(input);
//		if (input instanceof IMember) {
//			if (page.getEditorReferences().length == 0) {
//				EditorUtility.openInEditor(input, false); // only open when the perspecive has been created
//			}
//		}
//		return part;
//	}


	/**
	 * Converts the input to a possible input candidates
	 */	
	public static IModelElement[] getCandidates(Object input) {
		if (!(input instanceof IModelElement)) {
			return null;
		}
		try {
			IModelElement elem= (IModelElement) input;
			switch (elem.getElementType()) {
				
				case IModelElement.METHOD:
				case IModelElement.FIELD:
				case IModelElement.TYPE:
				
				case IModelElement.SCRIPT_PROJECT:
					return new IModelElement[] { elem };
				
				case IModelElement.PACKAGE_DECLARATION:
					return new IModelElement[] { elem.getAncestor(IModelElement.PROJECT_FRAGMENT) };
						
				case IModelElement.SOURCE_MODULE: {
					ISourceModule cu= (ISourceModule) elem.getAncestor(IModelElement.SOURCE_MODULE);
					if (cu != null) {
						IType[] types= cu.getTypes();
						if (types.length > 0) {
							return types;
						}
					}
					break;
				}					
				default:
			}
		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
		}
		return null;	
	}
}
