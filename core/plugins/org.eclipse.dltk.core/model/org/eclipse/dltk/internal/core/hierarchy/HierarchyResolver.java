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

/**
 * This is the public entry point to resolve type hierarchies.
 * 
 * When requesting additional types from the name environment, the resolver
 * accepts all forms (binary, source & compilation unit) for additional types.
 * 
 * Side notes: Binary types already know their resolved supertypes so this only
 * makes sense for source types. Even though the compiler finds all binary types
 * to complete the hierarchy of a given source type, is there any reason why the
 * requestor should be informed that binary type X subclasses Y & implements I &
 * J?
 */

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.compiler.IProblemFactory;
import org.eclipse.dltk.compiler.env.IGenericType;
import org.eclipse.dltk.compiler.env.INameEnvironment;
import org.eclipse.dltk.compiler.env.ISourceType;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.internal.compiler.env.AccessRestriction;
import org.eclipse.dltk.internal.compiler.impl.ITypeRequestor;
import org.eclipse.dltk.internal.compiler.lookup.LookupEnvironment;

public class HierarchyResolver implements ITypeRequestor {

	private boolean superTypesOnly;
	private boolean hasMissingSuperClass;
	LookupEnvironment lookupEnvironment;
	// private CompilerOptions options;
	HierarchyBuilder builder;

	private int typeIndex;
	private IGenericType[] typeModels;

	public HierarchyResolver(INameEnvironment nameEnvironment, Map settings,
			HierarchyBuilder builder, IProblemFactory problemFactory) {
		// create a problem handler with the 'exit after all problems' handling
		// policy
		// this.options = new CompilerOptions(settings);
		// IErrorHandlingPolicy policy =
		// DefaultErrorHandlingPolicies.exitAfterAllProblems();
		// ProblemReporter problemReporter = new ProblemReporter(policy,
		// this.options, problemFactory);

		this.setEnvironment(new LookupEnvironment(this, /*
														 * this.options,
														 * problemReporter,
														 */
				nameEnvironment), builder);
	}

	public HierarchyResolver(LookupEnvironment lookupEnvironment,
			HierarchyBuilder builder) {
		this.setEnvironment(lookupEnvironment, builder);
	}

	/**
	 * Add an additional compilation unit.
	 * 
	 * @param sourceUnit
	 */
	public void accept(ISourceModule sourceUnit,
			AccessRestriction accessRestriction) {
		// System.out.println("Cannot accept compilation units inside the
		// HierarchyResolver.");
		// this.lookupEnvironment.problemReporter.abortDueToInternalError(
		// new StringBuffer(Messages.accept_cannot)
		// .append(sourceUnit.getFileName())
		// .toString());
	}

	/**
	 * Add additional source types
	 * 
	 * @param sourceTypes
	 * @param packageBinding
	 */
	public void accept(ISourceType[] sourceTypes, /*
													 * PackageBinding
													 * packageBinding,
													 */
			AccessRestriction accessRestriction) {
		IProgressMonitor progressMonitor = this.builder.hierarchy.progressMonitor;
		if (progressMonitor != null && progressMonitor.isCanceled())
			throw new OperationCanceledException();

		// find most enclosing type first (needed when explicit askForType(...)
		// is done
		// with a member type (e.g. p.A$B))
		ISourceType sourceType = sourceTypes[0];
		while (sourceType.getEnclosingType() != null)
			sourceType = sourceType.getEnclosingType();

		// build corresponding compilation unit
//		IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLangaugeToolkit(sourceType);
//		ModuleDeclaration unit = toolkit.createFullAST(module)

//		// build bindings
//		if (unit != null) {
//			try {
//				this.lookupEnvironment.buildTypeBindings(unit,
//						accessRestriction);
//
//				org.eclipse.dltk.core.ISourceModule cu = ((SourceTypeElementInfo) sourceType)
//						.getHandle().getSourceModule();
//				rememberAllTypes(unit, cu, false);
//
//				this.lookupEnvironment
//						.completeTypeBindings(unit, true/*
//														 * build constructor
//														 * only
//														 */);
//			} catch (AbortCompilation e) {
//				// missing 'java.lang' package: ignore
//			}
//		}
	}

	/*
	 * Creates the super class handle of the given type. Returns null if the
	 * type has no super class. Adds the simple name to the hierarchy missing
	 * types if the class is not found and returns null.
	 */
	private IType findSuperClass(IGenericType type) {
		return null;
	}
	
	private void reset() {
		this.lookupEnvironment.reset();
		this.superTypesOnly = false;
		this.typeIndex = -1;
		this.typeModels = new IGenericType[5];
	}


	private void setEnvironment(LookupEnvironment lookupEnvironment,
			HierarchyBuilder builder) {
		this.lookupEnvironment = lookupEnvironment;
		this.builder = builder;

		this.typeIndex = -1;
		this.typeModels = new IGenericType[5];
//		this.typeBindings = new ReferenceBinding[5];
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
}
