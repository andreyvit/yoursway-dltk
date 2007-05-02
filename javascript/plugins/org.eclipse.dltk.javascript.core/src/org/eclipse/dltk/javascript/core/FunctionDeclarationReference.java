/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.core;

import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;

public class FunctionDeclarationReference extends SimpleReference{

	private HostCollection collection;
	
	public FunctionDeclarationReference(int start, int end, String name,HostCollection collection) {
		super(start, end, name);
		this.collection=collection;
	}
	
	public HostCollection getCollection(){
		return this.collection;
	}
}
