/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IPackageDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * @see IPackageDeclaration
 */

/* package */class PackageDeclaration extends SourceRefElement implements IPackageDeclaration {

	String name;

	protected PackageDeclaration(ModelElement parent, String name) {
		super(parent);
		this.name = name;
	}

	public boolean equals(Object o) {
		if (!(o instanceof PackageDeclaration))
			return false;
		return super.equals(o);
	}

	public String getElementName() {
		return this.name;
	}

	/**
	 * @see IModelElement
	 */
	public int getElementType() {
		return PACKAGE_DECLARATION;
	}

	/**
	 * @see ModelElement#getHandleMemento()
	 */
	protected char getHandleMementoDelimiter() {
		return ModelElement.JEM_PACKAGEDECLARATION;
	}

	/*
	 * @see ModelElement#getPrimaryElement(boolean)
	 */
	public IModelElement getPrimaryElement(boolean checkOwner) {
		ISourceModule cu = (ISourceModule) getAncestor(SOURCE_MODULE);
		if (checkOwner && cu.isPrimary())
			return this;
		return cu.getPackageDeclaration(this.name);
	}

	/**
	 * @private Debugging purposes
	 */
	protected void toStringInfo(int tab, StringBuffer buffer, Object info, boolean showResolvedInfo) {
		buffer.append(this.tabString(tab));
		buffer.append("package "); //$NON-NLS-1$
		toStringName(buffer);
		if (info == null) {
			buffer.append(" (not open)"); //$NON-NLS-1$
		}
	}

	protected void closing(Object info) throws ModelException {
	}

	public void printNode(CorePrinter output) {
	}
}
