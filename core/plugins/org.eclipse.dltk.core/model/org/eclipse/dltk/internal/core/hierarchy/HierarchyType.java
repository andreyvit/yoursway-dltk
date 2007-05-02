/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.hierarchy;

import org.eclipse.dltk.compiler.env.IGenericType;
import org.eclipse.dltk.core.IType;

/**
 * 
 * Partial implementation of an IGenericType used to answer hierarchies.
 */
public class HierarchyType implements IGenericType {

	public IType typeHandle;
	public char[] name;
	public int modifiers;
	public char[] superclassName;
	public char[][] superInterfaceNames;

	public HierarchyType(IType typeHandle, char[] name, int modifiers,
			char[] superclassName, char[][] superInterfaceNames) {

		this.typeHandle = typeHandle;
		this.name = name;
		this.modifiers = modifiers;
		this.superclassName = superclassName;
		this.superInterfaceNames = superInterfaceNames;
	}

	/**
	 * @see org.eclipse.jdt.internal.compiler.env.IDependent#getFileName()
	 */
	public char[] getFileName() {
		return this.typeHandle.getSourceModule().getElementName()
				.toCharArray();
	}

	/**
	 * Answer an int whose bits are set according the access constants defined
	 * by the VM spec.
	 */
	public int getModifiers() {
		return this.modifiers;
	}

	/**
	 * Answer whether the receiver contains the resolved binary form or the
	 * unresolved source form of the type.
	 */
	public boolean isBinaryType() {
		return false;
	}
}
