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


/*
 * This class is one big TODO
 */
abstract public class TypeBinding extends Binding {
	
	public int id = TypeIds.NoId;
	public long tagBits = 0; // See values in the interface TagBits below
	
	public abstract char[] getQualifiedName ();
	
	/* Answer the receiver's enclosing type... null if the receiver is a top level type.
	 */
	public ReferenceBinding enclosingType() {
		return null;
	}
	
	/* API
	 * Answer the receiver's binding type from Binding.BindingID.
	 */
	public int kind() {
		return Binding.TYPE;
	}
	
	public MethodBinding[] methods() {
		return Binding.NO_METHODS;
	}
	
	public VariableBinding[] fields() {
		return Binding.NO_FIELDS;
	}
	
	public TypeBinding[] types() {
		return Binding.NO_TYPES;
	}
	
	/**
	 * Returns true if a type is identical to another one,
	 * or for generic types, true if compared to its raw type.
	 */
	public boolean isEquivalentTo(TypeBinding otherType) {
		if (this == otherType)
			return true;
		if (otherType == null)
			return false;		
		return false;
	}

}
