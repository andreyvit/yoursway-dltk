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
package org.eclipse.dltk.internal.core.hierarchy;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.compiler.DefaultProblemFactory;
import org.eclipse.dltk.compiler.env.IGenericType;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.compiler.lookup.ReferenceBinding;
import org.eclipse.dltk.internal.core.DLTKProject;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.NameLookup;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.SourceTypeElementInfo;
import org.eclipse.dltk.internal.core.util.ResourceSourceModule;
import org.eclipse.dltk.internal.core.util.Util;

public abstract class HierarchyBuilder {
	/**
	 * The hierarchy being built.
	 */
	protected TypeHierarchy hierarchy;
	/**
	 * @see NameLookup
	 */
	protected NameLookup nameLookup;
	/**
	 * The resolver used to resolve type hierarchies
	 * 
	 * @see HierarchyResolver
	 */
	protected HierarchyResolver hierarchyResolver;
	/**
	 * A temporary cache of infos to handles to speed info to handle translation -
	 * it only contains the entries for the types in the region (in other words,
	 * it contains no supertypes outside the region).
	 */
	protected Map infoToHandle;
	/*
	 * The dot-separated fully qualified name of the focus type, or null of
	 * none.
	 */
	protected String focusQualifiedName;

	public HierarchyBuilder(TypeHierarchy hierarchy) throws ModelException {

		this.hierarchy = hierarchy;
		DLTKProject project = (DLTKProject) hierarchy.javaProject();

		IType focusType = hierarchy.getType();
		org.eclipse.dltk.core.ISourceModule unitToLookInside = focusType == null ? null
				: focusType.getSourceModule();
		org.eclipse.dltk.core.ISourceModule[] workingCopies = this.hierarchy.workingCopies;
		org.eclipse.dltk.core.ISourceModule[] unitsToLookInside;
		if (unitToLookInside != null) {
			int wcLength = workingCopies == null ? 0 : workingCopies.length;
			if (wcLength == 0) {
				unitsToLookInside = new org.eclipse.dltk.core.ISourceModule[] { unitToLookInside };
			} else {
				unitsToLookInside = new org.eclipse.dltk.core.ISourceModule[wcLength + 1];
				unitsToLookInside[0] = unitToLookInside;
				System.arraycopy(workingCopies, 0, unitsToLookInside, 1,
						wcLength);
			}
		} else {
			unitsToLookInside = workingCopies;
		}
		if (project != null) {
			ISearchableEnvironment searchableEnvironment = project
					.newSearchableNameEnvironment(unitsToLookInside);
			this.nameLookup = searchableEnvironment.getNameLookup();
			this.hierarchyResolver = new HierarchyResolver(
					searchableEnvironment, project.getOptions(true), this,
					new DefaultProblemFactory());
		}
		this.infoToHandle = new HashMap(5);
		this.focusQualifiedName = focusType == null ? null : focusType
				.getFullyQualifiedName();
	}

	public abstract void build(boolean computeSubtypes) throws ModelException,
			CoreException;

	/**
	 * Configure this type hierarchy by computing the supertypes only.
	 */
	protected void buildSupertypes() {
		IType focusType = this.getType();
		if (focusType == null)
			return;
		// get generic type from focus type
		IGenericType type;
		try {
			type = (IGenericType) ((ModelElement) focusType).getElementInfo();
		} catch (ModelException e) {
			// if the focus type is not present, or if cannot get workbench path
			// we cannot create the hierarchy
			return;
		}
		// NB: no need to set focus type on hierarchy resolver since no other
		// type is injected
		// in the hierarchy resolver, thus there is no need to check that a type
		// is
		// a sub or super type of the focus type.
		this.hierarchyResolver.resolve(type);

		// Add focus if not already in (case of a type with no explicit super
		// type)
		if (!this.hierarchy.contains(focusType)) {
			this.hierarchy.addRootClass(focusType);
		}
	}

	/**
	 * Connect the supplied type to its superclass & superinterfaces. The
	 * superclass & superinterfaces are the identical binary or source types as
	 * supplied by the name environment.
	 */
	public void connect(IGenericType type, IType typeHandle,
			IType[] superclassHandles) {

		/*
		 * Temporary workaround for 1G2O5WK: ITPJCORE:WINNT -
		 * NullPointerException when selecting "Show in Type Hierarchy" for a
		 * inner class
		 */
		if (typeHandle == null)
			return;
		if (TypeHierarchy.DEBUG) {
			System.out
					.println("Connecting: " + ((ModelElement) typeHandle).toStringWithAncestors()); //$NON-NLS-1$
			System.out.print("  superclassess:"); //$NON-NLS-1$
			if (superclassHandles == null || superclassHandles.length == 0) {
				System.out.println(" <None>"); //$NON-NLS-1$
			} else {
				System.out.println();
				for (int i = 0, length = superclassHandles.length; i < length; i++) {
					if (superclassHandles[i] == null)
						continue;
					System.out
							.println("    " + ((ModelElement) superclassHandles[i]).toStringWithAncestors()); //$NON-NLS-1$
				}
			}
		}
		// now do the caching
		if (superclassHandles == null) {
			this.hierarchy.addRootClass(typeHandle);
		} else {
			for (int q = 0; q < superclassHandles.length; ++q) {
				this.hierarchy
						.cacheSuperclass(typeHandle, superclassHandles[q]);
			}
		}
		if (superclassHandles == null) {
			superclassHandles = TypeHierarchy.NO_TYPE;
		}

		// record flags
		this.hierarchy.cacheFlags(typeHandle, type.getModifiers());
	}

	/**
	 * Returns a handle for the given generic type or null if not found.
	 */
	protected IType getHandle(IGenericType genericType, ReferenceBinding binding) {
		if (genericType == null)
			return null;
		if (genericType instanceof HierarchyType) {
			IType handle = (IType) this.infoToHandle.get(genericType);
			if (handle == null) {
				handle = ((HierarchyType) genericType).typeHandle;
//				handle = (IType) ((ModelElement) handle).resolved(binding);
				this.infoToHandle.put(genericType, handle);
			}
			return handle;
		} else if (genericType instanceof SourceTypeElementInfo) {
			IType handle = ((SourceTypeElementInfo) genericType).getHandle();
//			return (IType) ((ModelElement) handle).resolved(binding);
			return handle;
		} else
			return null;
	}

	protected IType getType() {
		return this.hierarchy.getType();
	}

	protected void worked(IProgressMonitor monitor, int work) {
		if (monitor != null) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			} else {
				monitor.worked(work);
			}
		}
	}

	/**
	 * Create an ICompilationUnit info from the given compilation unit on disk.
	 */
	protected ISourceModule createCompilationUnitFromPath(Openable handle,
			IFile file) {
		final char[] elementName = handle.getElementName().toCharArray();
		return new ResourceSourceModule(file, file.getLocationURI()) {
			public char[] getFileName() {
				return elementName;
			}
		};
	}
}
