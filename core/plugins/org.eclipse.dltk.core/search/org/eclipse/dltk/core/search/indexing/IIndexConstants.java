/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.search.indexing;


public interface IIndexConstants {

	/* index encoding */
	char[] REF= "ref".toCharArray(); //$NON-NLS-1$
	char[] METHOD_REF= "methodRef".toCharArray(); //$NON-NLS-1$
	char[] CONSTRUCTOR_REF= "constructorRef".toCharArray(); //$NON-NLS-1$
	char[] SUPER_REF = "superRef".toCharArray(); //$NON-NLS-1$
	char[] TYPE_DECL = "typeDecl".toCharArray(); //$NON-NLS-1$
	char[] METHOD_DECL= "methodDecl".toCharArray(); //$NON-NLS-1$
	char[] CONSTRUCTOR_DECL= "constructorDecl".toCharArray(); //$NON-NLS-1$
	char[] FIELD_DECL= "fieldDecl".toCharArray(); //$NON-NLS-1$
	char[] OBJECT = "Object".toCharArray(); //$NON-NLS-1$
	char[] MIXIN = "mixin".toCharArray(); //$NON-NLS-1$
	char SEPARATOR= '{';
	char[][] COUNTS= 
		new char[][] { new char[] {SEPARATOR, '0'}, new char[] {SEPARATOR, '1'}, new char[] {SEPARATOR, '2'}, new char[] {SEPARATOR, '3'}, new char[] {SEPARATOR, '4'},
			new char[] {SEPARATOR, '5'}, new char[] {SEPARATOR, '6'}, new char[] {SEPARATOR, '7'}, new char[] {SEPARATOR, '8'}, new char[] {SEPARATOR, '9'}
	};
	char TYPE_SUFFIX = 'C';	
	char ANNOTATION_TYPE_SUFFIX = 'A';		
	char SECONDARY_SUFFIX = 'S';

	char[] ONE_STAR = new char[] {'*'};
	char[][] ONE_STAR_CHAR = new char[][] {ONE_STAR};

	// used as special marker for enclosing type name of local and anonymous classes
	char ZERO_CHAR = '0'; 
	char[] ONE_ZERO = new char[] { ZERO_CHAR }; 
	char[][] ONE_ZERO_CHAR = new char[][] {ONE_ZERO};

	int PKG_REF_PATTERN = 0x0001;
	int PKG_DECL_PATTERN = 0x0002;
	int TYPE_REF_PATTERN = 0x0004;
	int TYPE_DECL_PATTERN = 0x0008;
	int SUPER_REF_PATTERN = 0x0010;
	int CONSTRUCTOR_PATTERN = 0x0020;
	int FIELD_PATTERN = 0x0040;
	int METHOD_PATTERN = 0x0080;
	int OR_PATTERN = 0x0100;
	int LOCAL_VAR_PATTERN = 0x0200;
	int TYPE_PARAM_PATTERN = 0x0400;
}
