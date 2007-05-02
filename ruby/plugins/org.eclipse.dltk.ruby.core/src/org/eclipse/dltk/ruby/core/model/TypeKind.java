/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.model;

public interface TypeKind {

	public class Unknown implements TypeKind {
	}

	public class Class implements TypeKind {
	}
	
	public class Builtin implements TypeKind {
	}

	public static final TypeKind UNKNOWN = new Unknown();

	public static final TypeKind CLASS = new Class();
	
	public static final TypeKind BUILTIN = new Builtin();

}
