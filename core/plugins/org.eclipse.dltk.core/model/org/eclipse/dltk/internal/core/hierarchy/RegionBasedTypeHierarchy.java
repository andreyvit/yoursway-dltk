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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IOpenable;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IRegion;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.Region;
import org.eclipse.dltk.internal.core.SourceModule;
import org.eclipse.dltk.internal.core.TypeVector;

public class RegionBasedTypeHierarchy extends TypeHierarchy {
	/**
	 * The region of types for which to build the hierarchy
	 */
	protected IRegion region;
	
/**
 * Creates a TypeHierarchy on the types in the specified region,
 * considering first the given working copies,
 * using the projects in the given region for a name lookup context. If a specific
 * type is also specified, the type hierarchy is pruned to only
 * contain the branch including the specified type.
 */
public RegionBasedTypeHierarchy(IRegion region, ISourceModule[] workingCopies, IType type, boolean computeSubtypes) {
	super(type, workingCopies, (IDLTKSearchScope)null, computeSubtypes);
	
	Region newRegion = new Region() {
		public void add(IModelElement element) {
			if (!contains(element)) {
				//"new" element added to region
				removeAllChildren(element);
				fRootElements.add(element);
				if (element.getElementType() == IModelElement.SCRIPT_PROJECT) {
					// add jar roots as well so that jars don't rely on their parent to know 
					// if they are contained in the region
					// (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=146615)
					try {
						IProjectFragment[] roots = ((IDLTKProject) element).getProjectFragments();
						for (int i = 0, length = roots.length; i < length; i++) {
							if (roots[i].isArchive() && !fRootElements.contains(roots[i]))
								fRootElements.add(roots[i]);
						}
					} catch (ModelException e) {
						// project doesn't exist
					}
				}
				fRootElements.trimToSize();
			}
		}
	};
	IModelElement[] elements = region.getElements();
	for (int i = 0, length = elements.length; i < length; i++) {
		newRegion.add(elements[i]);
		
	}
	this.region = newRegion;
	if (elements.length > 0)
		this.project = elements[0].getScriptProject();
}
/*
 * @see TypeHierarchy#initializeRegions
 */
protected void initializeRegions() {
	super.initializeRegions();
	IModelElement[] roots = this.region.getElements();
	for (int i = 0; i < roots.length; i++) {
		IModelElement root = roots[i];
		if (root instanceof IOpenable) {
			this.files.put(root, new ArrayList());
		} else {
			Openable o = (Openable) ((ModelElement) root).getOpenableParent();
			if (o != null) {
				this.files.put(o, new ArrayList());
			}
		}
		checkCanceled();
	}
}
/**
 * Compute this type hierarchy.
 */
protected void compute() throws ModelException, CoreException {
	HierarchyBuilder builder = new RegionBasedHierarchyBuilder(this);
	builder.build(this.computeSubtypes);
}
protected boolean isAffectedByOpenable(IModelElementDelta delta, IModelElement element) {
	// change to working copy
	if (element instanceof SourceModule && ((SourceModule)element).isWorkingCopy()) {
		return super.isAffectedByOpenable(delta, element);
	}

	// if no focus, hierarchy is affected if the element is part of the region
	if (this.focusType == null) {
		return this.region.contains(element);
	} else {
		return super.isAffectedByOpenable(delta, element);
	}
}
/**
 * Returns the java project this hierarchy was created in.
 */
public IDLTKProject javaProject() {
	return this.project;
}
public void pruneDeadBranches() {
	pruneDeadBranches(getRootClasses());
}
/*
 * Returns whether all subtypes of the given type have been pruned.
 */
private boolean pruneDeadBranches(IType type) {
	TypeVector subtypes = (TypeVector)this.typeToSubtypes.get(type);
	if (subtypes == null) return true;
	pruneDeadBranches(subtypes.copy().elements());
	subtypes = (TypeVector)this.typeToSubtypes.get(type);
	return (subtypes == null || subtypes.size == 0);
}
private void pruneDeadBranches(IType[] types) {
	for (int i = 0, length = types.length; i < length; i++) {
		IType type = types[i];
		if (pruneDeadBranches(type) && !this.region.contains(type)) {
			removeType(type);
		}
	}
}
/**
 * Removes all the subtypes of the given type from the type hierarchy,
 * removes its superclass entry and removes the references from its super types.
 */
protected void removeType(IType type) {
	IType[] subtypes = this.getSubtypes(type);
	this.typeToSubtypes.remove(type);
	if (subtypes != null) {
		for (int i= 0; i < subtypes.length; i++) {
			this.removeType(subtypes[i]);
		}
	}
	IType[] superclasses = (IType[])this.classToSuperclass.remove(type);
	if (superclasses != null) {
		for (int i = 0, length = superclasses.length; i < length; i++) {
			IType superinterface = superclasses[i];
			TypeVector types = (TypeVector)this.typeToSubtypes.get(superinterface);
			if (types != null) types.remove(type);
		}
	}
}

}
