/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.breakpoints;

public interface IDbgpBreakpoint {
	final int HIT_CONDITION_GREATER = 0;

	final int HIT_CONDITION_EQUAL = 1;

	final int HIT_CONDITION_MULTIPLE = 2;

	String getId();

	boolean isEnabled();

	// -1 if not available
	int getHitCount();

	// -1 of not set
	int getHitValue();

	int getHitCondition();
}
