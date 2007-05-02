/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.compiler.lookup;

import org.eclipse.dltk.compiler.env.lookup.Scope;

/**
 * Particular block scope used for methods, constructors or clinits,
 * representing its outermost blockscope. Note also that such a scope will be
 * provided to enclose field initializers subscopes as well.
 */
public class MethodScope extends BlockScope {
	public MethodScope(Scope parent, boolean isStatic) {
		super(METHOD_SCOPE, parent);
		this.startIndex = 0;
	}
}
