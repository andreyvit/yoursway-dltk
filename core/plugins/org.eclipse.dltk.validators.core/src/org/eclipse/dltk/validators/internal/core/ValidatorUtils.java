/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.internal.core.BuiltinProjectFragment;
import org.eclipse.dltk.internal.core.BuiltinSourceModule;
import org.eclipse.dltk.internal.core.ExternalProjectFragment;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.core.util.HandleFactory;

public class ValidatorUtils {
	private static class ResourceVisitor implements IResourceVisitor {
		private List resources;

		public ResourceVisitor(List resources) {
			this.resources = resources;
		}

		public boolean visit(IResource resource) {
			if (!this.resources.contains(resource)
					&& resource.getType() == IResource.FILE) {
				resources.add(resource);
				return false;
			}
			return true;
		}
	}

	private static class SourceModuleVisitor implements IModelElementVisitor {
		private List elements;

		public SourceModuleVisitor(List elements) {
			this.elements = elements;
		}

		/**
		 * Visit only external source modules, witch we aren't builded yet.
		 */
		public boolean visit(IModelElement element) {
			if (element.getElementType() == IModelElement.PROJECT_FRAGMENT) {
				if ((element instanceof ExternalProjectFragment)
						|| (element instanceof BuiltinProjectFragment)) {
					return false;
				}
			}
			if (element.getElementType() == IModelElement.SOURCE_MODULE
					&& !(element instanceof ExternalSourceModule || element instanceof BuiltinSourceModule)) {
				if (!elements.contains(element)) {
					elements.add(element);
				}
				return false; // do not enter into source module content.
			}
			return true;
		}
	}
	public static void processResourcesToElements(Object o, final List elements, final List resources ) {
		if( o instanceof IResource ) {
			List els = new ArrayList();
			ResourceVisitor visitor = new ResourceVisitor(els);
			try {
				((IResource)o).accept(visitor);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < els.size(); i++) {
				Object eo = convertResourceToModelElement(els.get(i));
				if( eo != null ) {
					if( eo instanceof IModelElement && !elements.contains(eo) ) {
						elements.add(eo);
					}
					else if( eo instanceof IResource && !resources.contains(eo) ) {
						resources.add(eo);
					}
				}
			}
		}
		else if( o instanceof IModelElement ) {
			if( o instanceof IParent ) {
				SourceModuleVisitor visitor = new SourceModuleVisitor(elements);
				try {
					((IModelElement)o).accept(visitor);
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
			else if ( !(o instanceof ISourceModule )) {
				ISourceModule module = (ISourceModule)((IModelElement)o).getAncestor(IModelElement.SOURCE_MODULE);
				if( elements.contains(module)) {
					elements.add(module);
				}
			}
			else if( o instanceof ISourceModule) {
				if( !elements.contains(o)) {
					elements.add(o);
				}
			}
		}
	}
	private static HandleFactory factory = new HandleFactory();
	private static Object convertResourceToModelElement( Object o ) {
		if( o instanceof IModelElement ) {
			return o;
		}
		if( !(o instanceof IResource)) {
			return null;
		}
		IResource res = (IResource)o;
		IProject project = res.getProject();
		if( !DLTKLanguageManager.hasScriptNature(project)) {
			return null; // Lets pass not script projects. 
		}
		IScriptProject scriptProject = DLTKCore.create(project);
		IDLTKSearchScope scope = SearchEngine
		.createSearchScope(new IModelElement[] { scriptProject });
		
		IModelElement element = factory.createOpenable(res.getFullPath()
				.toString(), scope);
		if (element != null
				&& element.getElementType() == IModelElement.SOURCE_MODULE
				&& element.exists()) {
//			elements.add(element);
			return element;
		} else {
			return res;
		}
	}
}
