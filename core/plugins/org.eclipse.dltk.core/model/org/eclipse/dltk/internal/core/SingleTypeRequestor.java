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
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IType;

/**
 * The SingleTypeRequestor is an IModelElementRequestor that only accepts one
 * result element and then cancels.
 */
/* package */class SingleTypeRequestor implements IModelElementRequestor {
	/**
	 * The single accepted element
	 */
	protected IType fElement = null;

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptField(IField field) {
		// implements interface method
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptMemberType(IType type) {
		fElement = type;
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptMethod(IMethod method) {
		// implements interface method
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptScriptFolder(IScriptFolder ScriptFolder) {
		// implements interface method
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptType(IType type) {
		fElement = type;
	}

	/**
	 * Returns the type accepted by this requestor, or <code>null</code> if no
	 * type has been accepted.
	 */
	public IType getType() {
		return fElement;
	}

	/**
	 * @see IModelElementRequestor
	 */
	public boolean isCanceled() {
		return fElement != null;
	}

	/**
	 * Reset the state of this requestor
	 */
	public void reset() {
		fElement = null;
	}
}
