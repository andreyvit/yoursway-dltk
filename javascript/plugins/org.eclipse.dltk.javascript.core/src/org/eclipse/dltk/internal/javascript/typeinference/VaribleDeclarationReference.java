/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;

import org.eclipse.dltk.ast.references.SimpleReference;

public class VaribleDeclarationReference extends SimpleReference{

	private IReference ref;
	public VaribleDeclarationReference(int start, int end, String name,IReference ref) {
		super(start, end, name);
		this.ref=ref;
	}

	public IReference getReference(){
		return ref;
	}
}
