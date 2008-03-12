/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.utils.CorePrinter;

public class SourceField extends NamedMember implements IField {

	public SourceField(ModelElement parent, String name) {
		super(parent, name);
	}

	public int getElementType() {
		return FIELD;
	}

	public boolean equals(Object o) {
		if (!(o instanceof SourceField)) {
			return false;
		}
		return super.equals(o);
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("DLTK Source field:" + getElementName()); //$NON-NLS-1$
	}

	protected char getHandleMementoDelimiter() {
		return JEM_FIELD;
	}

	public String getFullyQualifiedName(String enclosingTypeSeparator) {
		try {
			return getFullyQualifiedName(enclosingTypeSeparator, false/*
																		 * don't
																		 * show
																		 * parameters
																		 */);
		} catch (ModelException e) {
			// exception thrown only when showing parameters
			return null;
		}
	}

	public String getFullyQualifiedName() {
		return getFullyQualifiedName("$"); //$NON-NLS-1$
	}
}
