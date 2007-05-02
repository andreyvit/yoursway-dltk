/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.model.DLTKElementResourceMapping;
import org.eclipse.dltk.internal.ui.search.DLTKSearchPageScoreComputer;
import org.eclipse.dltk.internal.ui.search.SearchUtil;
import org.eclipse.search.ui.ISearchPageScoreComputer;
import org.eclipse.ui.IContainmentAdapter;
import org.eclipse.ui.IContributorResourceAdapter;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.ide.IContributorResourceAdapter2;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.FilePropertySource;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.ResourcePropertySource;
import org.eclipse.ui.views.tasklist.ITaskListResourceAdapter;


/**
 * Implements basic UI support for Script elements. Implements handle to
 * persistent support for Script elements.
 */
public class ModelElementAdapterFactory implements IAdapterFactory, IContributorResourceAdapter, IContributorResourceAdapter2 {
	private static Class[] PROPERTIES = new Class[] {
			IPropertySource.class, IResource.class, IWorkbenchAdapter.class, IResourceLocator.class, IPersistableElement.class,
			IContributorResourceAdapter.class, IContributorResourceAdapter2.class, ITaskListResourceAdapter.class,
			IContainmentAdapter.class
	};
	/*
	 * Do not use real type since this would cause the Search plug-in to be
	 * loaded.
	 */
	private Object fSearchPageScoreComputer;
	private static IResourceLocator fgResourceLocator;
	private static DLTKWorkbenchAdapter fgScriptWorkbenchAdapter;
	private static DLTKElementContainmentAdapter fgScriptElementContainmentAdapter;

	public Class[] getAdapterList() {
		updateLazyLoadedAdapters();
		return PROPERTIES;
	}

	public Object getAdapter(Object element, Class key) {
		updateLazyLoadedAdapters();
		IModelElement script= getModelElement(element);
		if (IPropertySource.class.equals(key)) {
			return getProperties(script);
		}
		if (IResource.class.equals(key)) {
			return getResource(script);
		}
		if (DLTKCore.DEBUG_SCOPES) {
			System.err.println("Add search scope computer support in ModelElementAdapterFactory");
		}
		if (fSearchPageScoreComputer != null && ISearchPageScoreComputer.class.equals(key)) {
			return fSearchPageScoreComputer;
		}
		if (IWorkbenchAdapter.class.equals(key)) {
			return getScriptWorkbenchAdapter();
		}
		if (IResourceLocator.class.equals(key)) {
			return getResourceLocator();
		}
		if (IPersistableElement.class.equals(key)) {
			return new PersistableModelElementFactory(script);
		}
		if (IContributorResourceAdapter.class.equals(key)) {
			return this;
		}
		if (IContributorResourceAdapter2.class.equals(key)) {
			return this;
		}
		if (ITaskListResourceAdapter.class.equals(key)) {
			return getTaskListAdapter();
		}
		if (IContainmentAdapter.class.equals(key)) {
			return getScriptElementContainmentAdapter();
		}
		return null;
	}

	private IResource getResource(IModelElement element) {
		// can't use IModelElement.getResource directly as we are interested in
		// the
		// corresponding resource
		switch (element.getElementType()) {
			case IModelElement.TYPE:
				// top level types behave like the CU
				IModelElement parent = element.getParent();
				if (parent instanceof ISourceModule) {
					return ((ISourceModule) parent).getPrimary().getResource();
				}
				return null;
			case IModelElement.SOURCE_MODULE:
				return ((ISourceModule) element).getPrimary().getResource();			
			case IModelElement.PROJECT_FRAGMENT:
				// test if in a archive
				IProjectFragment root = (IProjectFragment) element.getAncestor(IModelElement.PROJECT_FRAGMENT);
				if (DLTKCore.DEBUG_SCOPES) {
					System.err.println("Check for archives");
				}
				if (!root.isArchive()) {
					return element.getResource();
				}
				return null;
			case IModelElement.SCRIPT_FOLDER:
			case IModelElement.SCRIPT_PROJECT:
			case IModelElement.SCRIPT_MODEL:
				return element.getResource();
			default:
				return null;
		}
	}

	public IResource getAdaptedResource(IAdaptable adaptable) {
		IModelElement je = getModelElement(adaptable);
		if (je != null)
			return getResource(je);
		return null;
	}

	public ResourceMapping getAdaptedResourceMapping(IAdaptable adaptable) {
		IModelElement je = getModelElement(adaptable);
		if (je != null)
			return DLTKElementResourceMapping.create(je);
		return null;
	}

	private IModelElement getModelElement(Object element) {
		if (element instanceof IModelElement)
			return (IModelElement) element;		
		return null;
	}

	private IPropertySource getProperties(IModelElement element) {
		IResource resource = getResource(element);
		if (DLTKCore.DEBUG_SCOPES) {
			System.err.println("ModelElementAdapterFactory add to getPrperties model elemen properties...");
		}
//		if (resource == null)
//			return new ScriptElementProperties(element);
		if (resource.getType() == IResource.FILE)
			return new FilePropertySource((IFile) resource);
		return new ResourcePropertySource(resource);
	}

	private void updateLazyLoadedAdapters() {
		if (DLTKCore.DEBUG_SCOPES) {
			System.err.println("Add search page scope compiler");
		}
		if (fSearchPageScoreComputer == null && SearchUtil.isSearchPlugInActivated())
			createSearchPageScoreComputer();
	}

	private void createSearchPageScoreComputer() {
		if (DLTKCore.DEBUG_SCOPES) {
			System.err.println("Add search page scope compiler");
		}
		fSearchPageScoreComputer = new DLTKSearchPageScoreComputer();
		PROPERTIES = new Class[] {
				IPropertySource.class, IResource.class, ISearchPageScoreComputer.class, IWorkbenchAdapter.class, IResourceLocator.class,
				IPersistableElement.class, IProject.class, IContributorResourceAdapter.class, IContributorResourceAdapter2.class,
				ITaskListResourceAdapter.class, IContainmentAdapter.class
		};
	}

	private static IResourceLocator getResourceLocator() {
		if (fgResourceLocator == null)
			fgResourceLocator = new ResourceLocator();
		return fgResourceLocator;
	}

	private static DLTKWorkbenchAdapter getScriptWorkbenchAdapter() {
		if (fgScriptWorkbenchAdapter == null)
			fgScriptWorkbenchAdapter = new DLTKWorkbenchAdapter();
		return fgScriptWorkbenchAdapter;
	}

	private static ITaskListResourceAdapter getTaskListAdapter() {
//		if (fgTaskListAdapter == null)
//			fgTaskListAdapter = new ScriptTaskListAdapter();
//		return fgTaskListAdapter;
		if (DLTKCore.DEBUG_SCOPES) {
			System.err.println("Add Task list adaptor to ModelElementAdapterFactory");
		}
		return null;
	}

	private static DLTKElementContainmentAdapter getScriptElementContainmentAdapter() {
		if (fgScriptElementContainmentAdapter == null)
			fgScriptElementContainmentAdapter = new DLTKElementContainmentAdapter();
		return fgScriptElementContainmentAdapter;
	}
}
