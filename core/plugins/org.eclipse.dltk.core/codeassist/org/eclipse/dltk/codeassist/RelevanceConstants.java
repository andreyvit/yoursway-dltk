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
package org.eclipse.dltk.codeassist;

public final class RelevanceConstants {

	private RelevanceConstants() {
	}

	public static final int R_DEFAULT = 0;
	public static final int R_INTERESTING = 5;
	public static final int R_CASE = 10;
	public static final int R_CAMEL_CASE = 5;
	public static final int R_EXACT_NAME = 4;
	public static final int R_EXPECTED_TYPE = 20;
	public static final int R_EXACT_EXPECTED_TYPE = 30;
	public static final int R_INTERFACE = 20;
	public static final int R_TYPE = 20;
	public static final int R_ANNOTATION = 20;
	public static final int R_EXCEPTION = 20;
	public static final int R_UNQUALIFIED = 3;
	public static final int R_QUALIFIED = 2;
	public static final int R_NAME_FIRST_PREFIX = 6;
	public static final int R_NAME_PREFIX = 5;
	public static final int R_NAME_FIRST_SUFFIX = 4;
	public static final int R_NAME_SUFFIX = 3;
	public static final int R_NAME_LESS_NEW_CHARACTERS = 15;
	public static final int R_METHOD_OVERIDE = 3;
	public static final int R_NON_RESTRICTED = 3;
	public static final int R_TRUE_OR_FALSE = 1;
	public static final int R_INLINE_TAG = 31;
	public static final int R_VALUE_TAG = 31;
	public static final int R_NON_INHERITED = 2;
}
