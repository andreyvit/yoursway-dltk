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

public final class BaseTypeBinding extends TypeBinding {

	public int id;
	public char[] simpleName;

	BaseTypeBinding(int id, char[] name) {
		this.tagBits |= TagBits.IsBaseType;
		this.id = id;
		this.simpleName = name;
	}

	/**
	 * int -> I
	 */
	public char[] computeUniqueKey(boolean isLeaf) {
		return simpleName;
	}
	
	
	/**
	 * @see org.eclipse.jdt.internal.compiler.lookup.Binding#kind()
	 */
	public int kind() {
		return Binding.BASE_TYPE;
	}
	public char[] qualifiedSourceName() {
		return simpleName;
	}

	public char[] readableName() {
		return simpleName;
	}

	public char[] shortReadableName() {
		return simpleName;
	}

	public char[] sourceName() {
		return simpleName;
	}

	public String toString() {
		return new String(simpleName) + " (id=" + id + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public char[] getQualifiedName() {
		return simpleName;
	}
}
