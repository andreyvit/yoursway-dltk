/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */

package org.eclipse.dltk.ast;

/**
 * Flags for declaration modifiers
 */
public interface Modifiers {
	public static final int AccAbstract = 0x1;

	public static final int AccConstant = 0x2;

	public static final int AccDefault = 0;

	public static final int AccFinal = 0x4;

	public static final int AccInterface = 0x8;

	public static final int AccPrivate = 0x10;

	public static final int AccProtected = 0x20;

	public static final int AccPublic = 0x40;

	public static final int AccStatic = 0x80;

	public final static int AccReference = 0x100;

	public final static int AccConst = 0x200;

	public static final int AccModule = 0x400;

	// this is used from tcl to declare namespaces.
	public static final int AccNameSpace = 0x800;
	
	public static final int AccAnnotation = 0x1000;

	public static final int USER_MODIFIER = 0x10000;
	
	

	
	
}
