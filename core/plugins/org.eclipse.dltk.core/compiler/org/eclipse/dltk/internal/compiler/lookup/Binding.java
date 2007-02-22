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

import org.eclipse.dltk.ast.ASTNode;




public abstract class Binding {

	// binding kinds
	public static final int FIELD = ASTNode.Bit1;
	public static final int LOCAL = ASTNode.Bit2;
	public static final int VARIABLE = FIELD | LOCAL;
	public static final int TYPE = ASTNode.Bit3;
	public static final int METHOD = ASTNode.Bit4;
	public static final int PACKAGE = ASTNode.Bit5;
	public static final int IMPORT = ASTNode.Bit6;
	public static final int ARRAY_TYPE = TYPE | ASTNode.Bit7;
	public static final int BASE_TYPE = TYPE | ASTNode.Bit8;
	public static final int PARAMETERIZED_TYPE = TYPE | ASTNode.Bit9;
	public static final int WILDCARD_TYPE = TYPE | ASTNode.Bit10;
	public static final int RAW_TYPE = TYPE | ASTNode.Bit11;
	public static final int GENERIC_TYPE = TYPE | ASTNode.Bit12;
	public static final int TYPE_PARAMETER = TYPE | ASTNode.Bit13;
	
	// Shared binding collections
	public static final TypeBinding[] NO_TYPES = new TypeBinding[0];
	public static final TypeBinding[] NO_PARAMETERS = new TypeBinding[0];	
	public static final FieldBinding[] NO_FIELDS = new FieldBinding[0];
	public static final MethodBinding[] NO_METHODS = new MethodBinding[0];
	public static final ReferenceBinding[] NO_SUPERINTERFACES = new ReferenceBinding[0];
	public static final ReferenceBinding[] NO_MEMBER_TYPES = new ReferenceBinding[0];
	
	
	public char[] declaringFileName;
	

	/*
	* Answer the receiver's binding type from Binding.BindingID.
	*/
	public abstract int kind();
	
	/**
	 * Computes a key that uniquely identifies this binding.
	 * Returns null if binding is not a TypeBinding, a MethodBinding, a FieldBinding or a PackageBinding.
	 */
	public char[] computeUniqueKey() {
		return computeUniqueKey(true/*leaf*/);
	}
	/**
	 * Computes a key that uniquely identifies this binding. Optinaly include access flags.
	 * Returns null if binding is not a TypeBinding, a MethodBinding, a FieldBinding or a PackageBinding.
	 */
	public char[] computeUniqueKey(boolean isLeaf) {
		return null;
	}
	
	/**
	 * Compute the tagbits for standard annotations. For source types, these could require
	 * lazily resolving corresponding annotation nodes, in case of forward references.
	 * @see org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding#getAnnotationTagBits()
	 */
	public long getAnnotationTagBits() {
		return 0;
	}
	
	/**
	 * Compute the tagbits for @Deprecated annotations; avoiding resolving
	 * entire annotation if not necessary.
	 * @see org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding#initializeDeprecatedAnnotationTagBits()
	 */
	public void initializeDeprecatedAnnotationTagBits() {
		// empty block
	}	
	
	/**
	 * Answer a printable representation of the receiver.
	 */
	public abstract char[] readableName();
	
	/**
	 * Shorter printable representation of the receiver (no qualified type)
	 * @return
	 */	
	public char[] shortReadableName(){
		return readableName();
	}
}
