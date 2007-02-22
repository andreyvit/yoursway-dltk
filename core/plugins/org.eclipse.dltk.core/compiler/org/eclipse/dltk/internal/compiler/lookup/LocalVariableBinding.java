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
package org.eclipse.dltk.internal.compiler.lookup;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;

public class LocalVariableBinding extends VariableBinding {

	public FieldDeclaration declaration; // for source-positions

	public BlockScope declaringScope; // back-pointer to its declaring scope

	public LocalVariableBinding(char[] name, TypeBinding type,
			boolean isArgument) {
		super(name, type);
		if (isArgument)
			this.tagBits |= TagBits.IsArgument;
	}

	// regular local variable or argument
	public LocalVariableBinding(FieldDeclaration declaration, TypeBinding type,
			boolean isArgument) {

		this(declaration.getName().toCharArray(), type, isArgument);
		this.declaration = declaration;
	}

	/*
	 * API Answer the receiver's binding type from Binding.BindingID.
	 */
	public final int kind() {
		return LOCAL;
	}

	/*
	 * declaringUniqueKey # scopeIndex / varName p.X { void foo() { int local; } }
	 * --> Lp/X;.foo()V#1/local
	 */
	public char[] computeUniqueKey(boolean isLeaf) {
		StringBuffer buffer = new StringBuffer();

		// declaring method or type
		// BlockScope scope = this.declaringScope;
		// MethodScope methodScope = scope instanceof MethodScope ?
		// (MethodScope) scope : scope.enclosingMethodScope();
		// ReferenceContext referenceContext = methodScope.referenceContext;
		// if (referenceContext instanceof AbstractMethodDeclaration) {
		// MethodBinding methodBinding = ((AbstractMethodDeclaration)
		// referenceContext).binding;
		// if (methodBinding != null) {
		// buffer.append(methodBinding.computeUniqueKey(false/*not a leaf*/));
		// }
		// } else if (referenceContext instanceof TypeDeclaration) {
		// TypeBinding typeBinding = ((TypeDeclaration)
		// referenceContext).binding;
		// if (typeBinding != null) {
		// buffer.append(typeBinding.computeUniqueKey(false/*not a leaf*/));
		// }
		// }
		//
		// // scope index
		// getScopeKey(scope, buffer);

		// variable name
		buffer.append('#');
		buffer.append(this.name);

		int length = buffer.length();
		char[] uniqueKey = new char[length];
		buffer.getChars(0, length, uniqueKey, 0);
		return uniqueKey;
	}

	public String toString() {

		String s = super.toString();
		return s;
	}
}
