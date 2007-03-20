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
package org.eclipse.dltk.compiler.env;

public interface IGenericType extends IDependent {
	int getModifiers();

	/**
	 * Answer whether the receiver contains the resolved binary form or the
	 * unresolved source form of the type.
	 */
	boolean isBinaryType();
}
