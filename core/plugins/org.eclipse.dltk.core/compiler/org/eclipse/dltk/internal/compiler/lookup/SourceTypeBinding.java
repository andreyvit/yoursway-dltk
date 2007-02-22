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
package org.eclipse.dltk.internal.compiler.lookup;


public class SourceTypeBinding extends ReferenceBinding {
	public ReferenceBinding[] superclasses;
	
	public FieldBinding[] fields;
	public MethodBinding[] methods;
	public ReferenceBinding[] memberTypes;

	public TypeScope scope;

	public char[] readableName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public char[] getQualifiedName() {
		// TODO Auto-generated method stub
		return null;
	}

}
