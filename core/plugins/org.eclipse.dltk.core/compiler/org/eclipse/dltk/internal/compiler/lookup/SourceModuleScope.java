/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Erling Ellingsen -  patch for bug 125570
 *******************************************************************************/
package org.eclipse.dltk.internal.compiler.lookup;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.lookup.Scope;
import org.eclipse.dltk.compiler.util.HashtableOfObject;

public class SourceModuleScope extends Scope {
	public LookupEnvironment environment;
	public ModuleDeclaration referenceContext;
	public char[][] currentPackageName;
	public HashtableOfObject typeOrPackageCache; // used in

	// Scope.getTypeOrPackage()
	// private CompoundNameVector qualifiedReferences;
	// private SimpleNameVector simpleNameReferences;
	// private ObjectVector referencedTypes;
	// private ObjectVector referencedSuperTypes;
	// HashtableOfType constantPoolNameUsage;
	// private int captureID = 1;

	public SourceModuleScope(ModuleDeclaration unit,
			LookupEnvironment environment) {
		super(COMPILATION_UNIT_SCOPE, null);
		this.environment = environment;
		this.referenceContext = unit;
		unit.scope = this;
		// this.currentPackageName = unit.currentPackage == null ?
		// CharOperation.NO_CHAR_CHAR : unit.currentPackage.tokens;
		// if (compilerOptions().produceReferenceInfo) {
		// ===
		// this.qualifiedReferences = new CompoundNameVector();
		// this.simpleNameReferences = new SimpleNameVector();
		// this.referencedTypes = new ObjectVector();
		// this.referencedSuperTypes = new ObjectVector();
		// ===
		// } else {
		// this.qualifiedReferences = null; // used to test if dependencies
		// should be recorded
		// this.simpleNameReferences = null;
		// this.referencedTypes = null;
		// this.referencedSuperTypes = null;
		// }
	}
}
