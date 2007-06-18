/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.core.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;

public interface IScriptBreakpoint extends IBreakpoint {
	int HIT_CONDITION_GREATER = IDbgpBreakpoint.HIT_CONDITION_GREATER;

	int HIT_CONDITION_EQUAL = IDbgpBreakpoint.HIT_CONDITION_EQUAL;

	int HIT_CONDITION_MULTIPLE = IDbgpBreakpoint.HIT_CONDITION_MULTIPLE;

	// Identifier
	String getIdentifier() throws CoreException;

	void setIdentifier(String id) throws CoreException;

	// Hit count (returns effective hit count during debugging or -1 if not
	// available)
	int getHitCount() throws CoreException;
	
	void setHitCount(int value) throws CoreException;

	// Hit value
	int getHitValue() throws CoreException;

	void setHitValue(int count) throws CoreException;

	// Hit condition
	int getHitCondition() throws CoreException;

	void setHitCondition(int condition) throws CoreException;

	// Resource name
	String getResourceName() throws CoreException;

	// Expressions
	String getExpression() throws CoreException;

	void setExpression(String expression) throws CoreException;

	boolean getExpressionState() throws CoreException;

	void setExpressionState(boolean state) throws CoreException;
}
