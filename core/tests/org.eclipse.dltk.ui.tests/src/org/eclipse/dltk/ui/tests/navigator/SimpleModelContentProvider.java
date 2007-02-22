package org.eclipse.dltk.ui.tests.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class SimpleModelContentProvider implements ITreeContentProvider {
	Object[] NONE_ELEMENTS =new Object[0];
	public Object[] getChildren(Object element) {		
		if( element instanceof IWorkspaceRoot ){
			IWorkspaceRoot root = ((IWorkspaceRoot)element);
			return root.getProjects();			
			
		}
		else if(element instanceof IProject ) {
			if(DLTKLanguageManager.hasScriptNature((IProject)element)) {
				IDLTKProject project = DLTKCore.create((IProject)element);
				try {
					return project.getChildren();
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return NONE_ELEMENTS;
		}
		else if(element instanceof IParent) {
			try {
				return ((IParent)element).getChildren();
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}
		return NONE_ELEMENTS;
	}

	public Object getParent(Object element) {
		if(element instanceof IModelElement) {
			return ((IModelElement)element).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children.length > 0;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	// TODO Auto-generated method stub
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	// TODO Auto-generated method stub
	}
}
