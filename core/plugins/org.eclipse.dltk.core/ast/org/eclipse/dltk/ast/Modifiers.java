/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */

package org.eclipse.dltk.ast;

/**
 * Flags for declaration modifiers
 */
public interface Modifiers {
	public static final int AccDefault = 0;
	public static final int AccAbstract = 0x1;
	public static final int AccConstant = 2 << 1;
	public static final int AccFinal = 2 << 2;
	public static final int AccInterface = 2 << 3;
	public static final int AccPrivate = 2 << 4;
	public static final int AccProtected = 2 << 5;
	public static final int AccPublic = 2 << 6;
	public static final int AccStatic = 2 << 7;
	public final static int AccReference = 2 << 8;
	public final static int AccConst = 2 << 9;
	public static final int AccModule = 2 << 10;
	public static final int AccNameSpace = 2 << 11;
	public static final int AccAnnotation = 2 << 12;
	public static final int AccGlobal = 2 << 13;
	public static final int AccUpVar = 2 << 14;
	
	public static final int AccTestCase = 2 << 15;
	public static final int AccTest = 2 << 16;

	public static final int USER_MODIFIER = 17;
}
