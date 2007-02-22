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
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.core.SourceModule;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ExternalStorageEditorInput;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;


public class DLTKSearchEditorOpener {

	private static class ReusedEditorWatcher implements IPartListener {
		
		private IEditorPart fReusedEditor;
		private IPartService fPartService;
		
		public ReusedEditorWatcher() {
			fReusedEditor= null;
			fPartService= null;
		}
		
		public IEditorPart getReusedEditor() {
			return fReusedEditor;
		}
		
		public void initialize(IEditorPart editor) {
			if (fReusedEditor != null) {
				fPartService.removePartListener(this);
			}
			fReusedEditor= editor;
			if (editor != null) {
				fPartService= editor.getSite().getWorkbenchWindow().getPartService();
				fPartService.addPartListener(this);
			} else {
				fPartService= null;
			}
		}
		
		public void partOpened(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
			if (part == fReusedEditor) {
				initialize(null);
			}
		}

		public void partActivated(IWorkbenchPart part) {
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}
	}
	
	private ReusedEditorWatcher fReusedEditorWatcher;


	public IEditorPart openElement(Object element) throws PartInitException, ModelException {
		IWorkbenchPage wbPage= DLTKUIPlugin.getActivePage();
		if (NewSearchUI.reuseEditor())
			return showWithReuse(element, wbPage);
		else
			return showWithoutReuse(element, wbPage);
	}
		
	public IEditorPart openMatch(Match match) throws PartInitException, ModelException {
		Object element= getElementToOpen(match);
		return openElement(element);
	}

	protected Object getElementToOpen(Match match) {
		return match.getElement();
	}

	private IEditorPart showWithoutReuse(Object element, IWorkbenchPage wbPage) throws PartInitException, ModelException {
		return EditorUtility.openInEditor(element, false);
	}

	private IEditorPart showWithReuse(Object element, IWorkbenchPage wbPage) throws ModelException, PartInitException {
		if( element instanceof IModelElement ) {
			IModelElement module = ((IModelElement)element).getAncestor(IModelElement.SOURCE_MODULE);
			if( module instanceof ExternalSourceModule ) {
				String editorID= getEditorID((ExternalSourceModule)module);
				return showInEditor(wbPage, new ExternalStorageEditorInput((ExternalSourceModule)module), editorID);
			}
			else if( module instanceof SourceModule ) {
				String editorID= getEditorID((SourceModule)module);
				IFile file= getFile(element);
				return showInEditor(wbPage, new FileEditorInput(file), editorID);
			}
		}
		
		IFile file= getFile(element);
		if (file != null) {
			String editorID= getEditorID(file);
			return showInEditor(wbPage, new FileEditorInput(file), editorID);
		} 
		return null;
	}

	private IFile getFile(Object element) throws ModelException {
		if (element instanceof IFile)
			return (IFile) element;
		if (element instanceof IModelElement) {
			IModelElement jElement= (IModelElement) element;
			ISourceModule cu= (ISourceModule) jElement.getAncestor(IModelElement.SOURCE_MODULE);
			if (cu != null) {
				return (IFile) cu.getCorrespondingResource();
			}
		}
		return null;
	}

	private String getEditorID(IFile file) throws PartInitException {
		IEditorDescriptor desc= null;
		if (desc == null)
			return DLTKUIPlugin.getDefault().getWorkbench().getEditorRegistry().findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID).getId();
		else
			return desc.getId();
	}
	private String getEditorID(IModelElement module) throws PartInitException {
		String editorID = null;
		IEditorDescriptor desc= null;
		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLangaugeToolkit(module);
			editorID = toolkit.getEditorID(module);
		} catch (CoreException e) {				
		}
		if (editorID != null) {
			desc = DLTKUIPlugin.getDefault().getWorkbench().getEditorRegistry().findEditor(editorID);
		}		
		
		if (desc == null)
			return DLTKUIPlugin.getDefault().getWorkbench().getEditorRegistry().findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID).getId();
		else
			return desc.getId();
	}

	private boolean isPinned(IEditorPart editor) {
		if (editor == null)
			return false;

		IEditorReference[] editorRefs= editor.getEditorSite().getPage().getEditorReferences();
		int i= 0;
		while (i < editorRefs.length) {
			if (editor.equals(editorRefs[i].getEditor(false)))
				return editorRefs[i].isPinned();
			i++;
		}
		return false;
	}

	private IEditorPart showInEditor(IWorkbenchPage page, IEditorInput input, String editorId) {
		IEditorPart editor= page.findEditor(input);
		if (editor != null)
			page.bringToTop(editor);
		else {
			IEditorPart reusedEditor= getReusedEditor();
			boolean isOpen= false;
			if (reusedEditor != null) {
				IEditorReference[] parts= page.getEditorReferences();
				int i= 0;
				while (!isOpen && i < parts.length)
					isOpen= reusedEditor == parts[i++].getEditor(false);
			}

			boolean canBeReused= isOpen && !reusedEditor.isDirty() && !isPinned(reusedEditor);
			boolean showsSameInputType= reusedEditor != null && reusedEditor.getSite().getId().equals(editorId);
			if (canBeReused && !showsSameInputType) {
				page.closeEditor(reusedEditor, false);
				setReusedEditor(null);
			}

			if (canBeReused && showsSameInputType) {
				((IReusableEditor) reusedEditor).setInput(input);
				page.bringToTop(reusedEditor);
				editor= reusedEditor;
			} else {
				try {
					editor= page.openEditor(input, editorId, false);
					if (editor instanceof IReusableEditor)
						setReusedEditor(editor);
					else
						setReusedEditor(null);
				} catch (PartInitException ex) {
					MessageDialog.openError(DLTKUIPlugin.getActiveWorkbenchShell(), SearchMessages.Search_Error_openEditor_title, SearchMessages.Search_Error_openEditor_message); 
					return null;
				}
			}
		}
		return editor;
	}

	private IEditorPart getReusedEditor() {
		if (fReusedEditorWatcher != null) 
			return fReusedEditorWatcher.getReusedEditor();
		return null;
	}
	
	private void setReusedEditor(IEditorPart editor) {
		if (fReusedEditorWatcher == null) {
			fReusedEditorWatcher= new ReusedEditorWatcher();
		}
		fReusedEditorWatcher.initialize(editor);
	}
}
