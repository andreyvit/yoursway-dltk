/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.model;


public class MethodArgument implements IMethodArgument {
	
	private String name;
	
	private MethodArgumentKind kind = MethodArgumentKind.SIMPLE;

	public MethodArgument(String name, MethodArgumentKind kind) {
		super();
		this.name = name;
		this.kind = kind;
	}

	public MethodArgumentKind getKind() {
		return kind;
	}

	public String getName() {
		return name;
	}			

}
