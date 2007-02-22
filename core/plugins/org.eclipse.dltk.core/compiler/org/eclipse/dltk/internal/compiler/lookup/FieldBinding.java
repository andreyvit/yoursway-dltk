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
import org.eclipse.dltk.compiler.CharOperation;

public class FieldBinding extends VariableBinding {
	public ReferenceBinding declarer;

	public FieldBinding(char[] name, TypeBinding type,
			ReferenceBinding declaringClass) {
		super(name, type);
		this.declarer = declaringClass;
	}

	/*
	 * public FieldBinding(FieldDeclaration field, TypeBinding type, int
	 * modifiers, ReferenceBinding declaringClass) { this(field.name, type,
	 * modifiers, declaringClass, null); field.binding = this; // record binding
	 * in declaration }
	 */

	public final int kind() {
		return FIELD;
	}

	/*
	 * declaringUniqueKey dot fieldName ) returnTypeUniqueKey p.X { X<T> x} -->
	 * Lp/X;.x)p/X<TT;>;
	 */
	public char[] computeUniqueKey(boolean isLeaf) {
		// declaring key
		char[] declaringKey = this.declarer == null /*
													 * case of length field for
													 * an array
													 */
		? CharOperation.NO_CHAR : this.declarer
				.computeUniqueKey(false/* not a leaf */);
		int declaringLength = declaringKey.length;

		// name
		int nameLength = this.name.length;

		// return type
		char[] returnTypeKey = this.type == null ? new char[] { 'V' }
				: this.type.computeUniqueKey(false/* not a leaf */);
		int returnTypeLength = returnTypeKey.length;

		char[] uniqueKey = new char[declaringLength + 1 + nameLength + 1
				+ returnTypeLength];
		int index = 0;
		System.arraycopy(declaringKey, 0, uniqueKey, index, declaringLength);
		index += declaringLength;
		uniqueKey[index++] = '.';
		System.arraycopy(this.name, 0, uniqueKey, index, nameLength);
		index += nameLength;
		uniqueKey[index++] = ')';
		System.arraycopy(returnTypeKey, 0, uniqueKey, index, returnTypeLength);
		return uniqueKey;
	}

	/**
	 * Returns the original field (as opposed to parameterized instances)
	 */
	public FieldBinding original() {
		return this;
	}

	public FieldDeclaration sourceField() {
//		SourceTypeBinding sourceType;
//		try {
//			sourceType = (SourceTypeBinding) declarer;
//		} catch (ClassCastException e) {
//			return null;
//		}

		// FieldDeclaration[] fields = sourceType.scope.referenceContext.fields;
		// if (fields != null) {
		// for (int i = fields.length; --i >= 0;)
		// if (this == fields[i].binding)
		// return fields[i];
		// }
		return null;
	}
}
