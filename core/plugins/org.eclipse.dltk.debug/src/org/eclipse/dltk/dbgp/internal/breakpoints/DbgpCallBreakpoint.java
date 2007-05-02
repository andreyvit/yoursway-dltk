/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpCallBreakpoint;

public class DbgpCallBreakpoint extends DbgpBreakpoint implements
		IDbgpCallBreakpoint {
	private String function;

	public DbgpCallBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String function) {
		super(id, enabled, hitValue, hitCount, hitCondition);
		this.function = function;
	}

	public String getFunction() {
		return function;
	}
}
