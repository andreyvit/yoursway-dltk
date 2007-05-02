/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgDestinationValidator;
import org.eclipse.dltk.internal.ui.StandardModelElementContentProvider;
import org.eclipse.dltk.ui.DLTKUIPlugin;



public final class DestinationContentProvider extends StandardModelElementContentProvider {
	
	private IReorgDestinationValidator fValidator;
	
	public DestinationContentProvider(IReorgDestinationValidator validator) {
		super(true);
		fValidator= validator;
	}
	
	public boolean hasChildren(Object element) {
		if (element instanceof IModelElement){
			IModelElement modelElement= (IModelElement) element;
			if (! fValidator.canChildrenBeDestinations(modelElement))
				return false;
			if (modelElement.getElementType() == IModelElement.PROJECT_FRAGMENT){
				if (((IProjectFragment)modelElement).isArchive())
					return false;
			}
		} else if (element instanceof IResource) {
			IResource resource= (IResource) element;
			if (! fValidator.canChildrenBeDestinations(resource))
				return false;
		}
		return super.hasChildren(element);
	}
	
	public Object[] getChildren(Object parentElement) {
		try {
			if (parentElement instanceof IScriptModel) {
				return concatenate(getScriptProjects((IScriptModel)parentElement), getOpenNonScriptProjects((IScriptModel)parentElement));
			} else {
				Object[] children= super.getChildren(parentElement);
				ArrayList result= new ArrayList(children.length);
				for (int i= 0; i < children.length; i++) {
					if (children[i] instanceof IModelElement) {
						IModelElement modelElement= (IModelElement) children[i];
						if (fValidator.canElementBeDestination(modelElement) || fValidator.canChildrenBeDestinations(modelElement))
							result.add(modelElement);
					} else if (children[i] instanceof IResource) {
						IResource resource= (IResource) children[i];
						if (fValidator.canElementBeDestination(resource) || fValidator.canChildrenBeDestinations(resource))
							result.add(resource);
					}
				}
				return result.toArray();
			}
		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
			return new Object[0];
		}
	}

	private static Object[] getOpenNonScriptProjects(IScriptModel model) throws ModelException {
		Object[] nonScriptProjects= model.getForeignResources();
		ArrayList result= new ArrayList(nonScriptProjects.length);
		for (int i= 0; i < nonScriptProjects.length; i++) {
			IProject project = (IProject) nonScriptProjects[i];
			if (project.isOpen())
				result.add(project);
		}
		return result.toArray();
	}

}
