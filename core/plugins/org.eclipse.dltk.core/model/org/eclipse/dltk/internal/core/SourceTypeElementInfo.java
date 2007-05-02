/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;


import org.eclipse.dltk.compiler.env.ISourceField;
import org.eclipse.dltk.compiler.env.ISourceMethod;
import org.eclipse.dltk.compiler.env.ISourceType;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;

public class SourceTypeElementInfo extends MemberElementInfo implements ISourceType {

	protected static final SourceField[] NO_FIELDS = new SourceField[0];

	protected static final SourceMethod[] NO_METHODS = new SourceMethod[0];

	protected static final SourceType[] NO_TYPES = new SourceType[0];

	/**
	 * The name of the superclasses for this type.
	 */
	protected String[] superclassNames;

	/**
	 * Backpointer to my type handle - useful for translation from info to
	 * handle.
	 */
	protected IType handle = null;

	/**
	 * Sets the handle for this type info
	 */
	protected void setHandle(IType handle) {
		this.handle = handle;
	}

	protected void setSuperclassNames(String[] superclassNames) {
		this.superclassNames = superclassNames;
	}

	public String[] getSuperclassNames() {
		return superclassNames;
	}

	public ISourceType getEnclosingType() {
		IModelElement parent = this.handle.getParent();
		if (parent != null && parent.getElementType() == IModelElement.TYPE) {
			try {
				return (ISourceType) ((ModelElement) parent).getElementInfo();
			} catch (ModelException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public ISourceField[] getFields() {
		SourceField[] fieldHandles = getFieldHandles();
		int length = fieldHandles.length;
		ISourceField[] fields = new ISourceField[length];
		for (int i = 0; i < length; i++) {
			try {
				ISourceField field = (ISourceField) fieldHandles[i]
						.getElementInfo();
				fields[i] = field;
			} catch (ModelException e) {
				// ignore
			}
		}
		return fields;
	}

	public ISourceType[] getMemberTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISourceMethod[] getMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] getSuperclassName() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[][][] getTypeParameterBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[][] getTypeParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isBinaryType() {
		// TODO Auto-generated method stub
		return false;
	}

	public char[] getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public SourceField[] getFieldHandles() {
		int length = this.children.length;
		if (length == 0)
			return NO_FIELDS;
		SourceField[] fields = new SourceField[length];
		int fieldIndex = 0;
		for (int i = 0; i < length; i++) {
			IModelElement child = this.children[i];
			if (child instanceof SourceField)
				fields[fieldIndex++] = (SourceField) child;
		}
		if (fieldIndex == 0)
			return NO_FIELDS;
		if (fieldIndex < length)
			System.arraycopy(fields, 0, fields = new SourceField[fieldIndex],
					0, fieldIndex);
		return fields;
	}
	public IType getHandle() {
		return this.handle;
	}
}
