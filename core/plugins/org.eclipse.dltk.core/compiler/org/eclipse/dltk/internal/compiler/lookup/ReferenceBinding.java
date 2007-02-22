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

import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.compiler.env.IDependent;
import org.eclipse.dltk.compiler.env.lookup.Scope;

/*
Not all fields defined by this type (& its subclasses) are initialized when it is created.
Some are initialized only when needed.

Accessors have been provided for some public fields so all TypeBindings have the same API...
but access public fields directly whenever possible.
Non-public fields have accessors which should be used everywhere you expect the field to be initialized.

null is NOT a valid value for a non-public field... it just means the field is not initialized.
*/

abstract public class ReferenceBinding extends TypeBinding implements IDependent {
	
	public char[][] compoundName;
	public char[] sourceName;
	public int modifiers;
	char[] fileName;



//public FieldBinding[] availableFields() {
//	return fields();
//}
public MethodBinding[] availableMethods() {
	return methods();
}	

/* 
 * Answer true if the receiver is visible to the type provided by the scope.
 */
public final boolean canBeSeenBy(Scope scope) {
	return true; // TODO
}

/*
 * p.X<T extends Y & I, U extends Y> {} -> Lp/X<TT;TU;>;
 */
public char[] computeUniqueKey(boolean isLeaf) {
	return new char[0]; //TODO
}

public String debugName() {
	return (this.compoundName != null) ? new String(readableName()) : "UNNAMED TYPE"; //$NON-NLS-1$
}
public final int depth() {
	int depth = 0;
	ReferenceBinding current = this;
	while ((current = current.enclosingType()) != null)
		depth++;
	return depth;
}


public final ReferenceBinding enclosingTypeAt(int relativeDepth) {
	ReferenceBinding current = this;
	while (relativeDepth-- > 0 && current != null)
		current = current.enclosingType();
	return current;
}

public int fieldCount() {
	return fields().length;
}

//public FieldBinding[] fields() {
//	return Binding.NO_FIELDS;
//}

public MethodBinding getExactConstructor(TypeBinding[] argumentTypes) {
	return null;
}
public MethodBinding getExactMethod(char[] selector, TypeBinding[] argumentTypes, Scope refScope) {
	return null;
}
public FieldBinding getField(char[] fieldName, boolean needResolve) {
	return null;
}

public char[] getFileName() {
	return this.fileName;
}

public ReferenceBinding getMemberType(char[] typeName) {
	ReferenceBinding[] memberTypes = memberTypes();
	for (int i = memberTypes.length; --i >= 0;)
		if (CharOperation.equals(memberTypes[i].sourceName, typeName))
			return memberTypes[i];
	return null;
}

public MethodBinding[] getMethods(char[] selector) {
	return Binding.NO_METHODS;
}

public int hashCode() {
	// ensure ReferenceBindings hash to the same posiiton as UnresolvedReferenceBindings so they can be replaced without rehashing
	// ALL ReferenceBindings are unique when created so equals() is the same as ==
	return (this.compoundName == null || this.compoundName.length == 0)
		? super.hashCode()
		: CharOperation.hashCode(this.compoundName[this.compoundName.length - 1]);
}


public boolean hasMemberTypes() {
    return false;
}


/**
 * Answer true if the receiver is in the superclass hierarchy of aType
 * NOTE: Object.isSuperclassOf(Object) -> false
 */
public boolean isSuperclassOf(ReferenceBinding otherType) {
	while ((otherType = otherType.superclass()) != null) {
		if (otherType.isEquivalentTo(this)) return true;
	}
	return false;
}


public ReferenceBinding[] memberTypes() {
	return Binding.NO_MEMBER_TYPES;
}

public MethodBinding[] methods() {
	return Binding.NO_METHODS;
}

public final ReferenceBinding outermostEnclosingType() {
	ReferenceBinding current = this;
	while (true) {
		ReferenceBinding last = current;
		if ((current = current.enclosingType()) == null) 
			return last;
	}
}


public char[] sourceName() {
	return this.sourceName;
}

public ReferenceBinding superclass() {
	return null;
}

MethodBinding[] unResolvedMethods() { // for the MethodVerifier so it doesn't resolve types
	return methods();
}
}
