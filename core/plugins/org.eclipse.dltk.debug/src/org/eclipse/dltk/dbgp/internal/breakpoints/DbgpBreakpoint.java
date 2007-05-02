/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;

public class DbgpBreakpoint implements IDbgpBreakpoint {
	private String id;

	private boolean enabled;

	private int hitValue;

	private int hitCount;

	private int hitCondition;

	protected int convertHitCondition(String s) {
		if (">=".equals(s)) {
			return HIT_CONDITION_GREATER;
		} else if ("==".equals(s)) {
			return HIT_CONDITION_EQUAL;
		} else if ("%".equals(s)) {
			return HIT_CONDITION_MULTIPLE;
		}

		throw new IllegalArgumentException("Invalud hitCondition value");
	}

	public DbgpBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition) {
		this.id = id;
		this.enabled = enabled;
		this.hitValue = hitValue;
		this.hitCount = hitCount;
		this.hitCondition = convertHitCondition(hitCondition);
	}

	public int getHitCondition() {
		return hitCondition;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getHitValue() {
		return hitValue;
	}

	public String getId() {
		return id;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
