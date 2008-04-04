/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.internal.core.hierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.ScriptProject;

public class RegionBasedHierarchyBuilder extends HierarchyBuilder {

	public RegionBasedHierarchyBuilder(TypeHierarchy hierarchy)
		throws ModelException {
		super();
		setRequestor(hierarchy);
	}

public void build(boolean computeSubtypes) {

	ModelManager manager = ModelManager.getModelManager();
	try {
		// optimize access to zip files while building hierarchy
		manager.cacheZipFiles();

		if (this.hierarchy.focusType == null || computeSubtypes) {
			IProgressMonitor typeInRegionMonitor =
				this.hierarchy.progressMonitor == null ?
					null :
					new SubProgressMonitor(this.hierarchy.progressMonitor, 30);
			HashMap allOpenablesInRegion = determineOpenablesInRegion(typeInRegionMonitor);
			this.hierarchy.initialize(allOpenablesInRegion.size());
			IProgressMonitor buildMonitor =
				this.hierarchy.progressMonitor == null ?
					null :
					new SubProgressMonitor(this.hierarchy.progressMonitor, 70);
			createTypeHierarchyBasedOnRegion(allOpenablesInRegion, buildMonitor);
			((RegionBasedTypeHierarchy)this.hierarchy).pruneDeadBranches();
		} else {
			this.hierarchy.initialize(1);
			this.buildSupertypes();
		}
	} finally {
		manager.flushZipFiles();
	}
}
/**
 * Configure this type hierarchy that is based on a region.
 */
private void createTypeHierarchyBasedOnRegion(HashMap allOpenablesInRegion, IProgressMonitor monitor) {

	int size = allOpenablesInRegion.size();
	if (size == 0) {
		if (monitor != null) {
			monitor.done();
		}
		return;
	}

	this.infoToHandle = new HashMap(size);
	Iterator javaProjects = allOpenablesInRegion.entrySet().iterator();
	while (javaProjects.hasNext()) {
		Map.Entry entry = (Map.Entry) javaProjects.next();
		ScriptProject project = (ScriptProject) entry.getKey();
		ArrayList allOpenables = (ArrayList) entry.getValue();
		Openable[] openables = new Openable[allOpenables.size()];
		allOpenables.toArray(openables);

		try {
			// resolve
			if (monitor != null) {
				monitor.beginTask("", size * 2/* 1 for build binding, 1 for connect hierarchy*/); //$NON-NLS-1$
			}
			ISearchableEnvironment searchableEnvironment = project.newSearchableNameEnvironment(this.hierarchy.workingCopies);
			this.nameLookup = searchableEnvironment.getNameLookup();
//			this.hierarchyResolver.resolve(openables, null, monitor);
		} catch (ModelException e) {
			// project doesn't exit: ignore
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
}

	/**
	 * Returns all of the openables defined in the region of this type hierarchy.
	 * Returns a map from IJavaProject to ArrayList of Openable
	 */
	private HashMap determineOpenablesInRegion(IProgressMonitor monitor) {

		try {
			HashMap allOpenables = new HashMap();
			IModelElement[] roots =
				((RegionBasedTypeHierarchy) this.hierarchy).region.getElements();
			int length = roots.length;
			if (monitor != null) {
				monitor.beginTask("", length); //$NON-NLS-1$
			}
			for (int i = 0; i <length; i++) {
				IModelElement root = roots[i];
				IScriptProject javaProject = root.getScriptProject();
				ArrayList openables = (ArrayList) allOpenables.get(javaProject);
				if (openables == null) {
					openables = new ArrayList();
					allOpenables.put(javaProject, openables);
				}
				switch (root.getElementType()) {
					case IModelElement.SCRIPT_PROJECT :
						injectAllOpenablesForJavaProject((IScriptProject) root, openables);
						break;
					case IModelElement.PROJECT_FRAGMENT :
						injectAllOpenablesForPackageFragmentRoot((IProjectFragment) root, openables);
						break;
					case IModelElement.SCRIPT_FOLDER :
						injectAllOpenablesForPackageFragment((IScriptFolder) root, openables);
						break;
					case IModelElement.SOURCE_MODULE :
						openables.add(root);
						break;
					case IModelElement.TYPE :
						IType type = (IType)root;
						openables.add(type.getSourceModule());
						break;
					default :
						break;
				}
				worked(monitor, 1);
			}
			return allOpenables;
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	/**
	 * Adds all of the openables defined within this java project to the
	 * list.
	 */
	private void injectAllOpenablesForJavaProject(
		IScriptProject project,
		ArrayList openables) {
		try {
			IProjectFragment[] devPathRoots =
				((ScriptProject) project).getProjectFragments();
			if (devPathRoots == null) {
				return;
			}
			for (int i = 0; i < devPathRoots.length; i++) {
				IProjectFragment root = devPathRoots[i];
				injectAllOpenablesForPackageFragmentRoot(root, openables);
			}
		} catch (ModelException e) {
			// ignore
		}
	}

	/**
	 * Adds all of the openables defined within this package fragment to the
	 * list.
	 */
	private void injectAllOpenablesForPackageFragment(
		IScriptFolder packFrag,
		ArrayList openables) {

		try {
			IProjectFragment root = (IProjectFragment) packFrag.getParent();
			int kind = root.getKind();
			if (kind != 0) {
				boolean isSourcePackageFragment = (kind == IProjectFragment.K_SOURCE);
				if (isSourcePackageFragment) {
					ISourceModule[] cus = packFrag.getSourceModules();
					for (int i = 0; i < cus.length; i++) {
						openables.add(cus[i]);
					}
				}
			}
		} catch (ModelException e) {
			// ignore
		}
	}

	/**
	 * Adds all of the openables defined within this package fragment root to the
	 * list.
	 */
	private void injectAllOpenablesForPackageFragmentRoot(
		IProjectFragment root,
		ArrayList openables) {
		try {
			IModelElement[] packFrags = root.getChildren();
			for (int i = 0; i < packFrags.length; i++) {
				IScriptFolder packFrag = (IScriptFolder) packFrags[i];
				injectAllOpenablesForPackageFragment(packFrag, openables);
			}
		} catch (ModelException e) {
			return;
		}
	}

}
