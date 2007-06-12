/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.breakpoints;

public class DbgpBreakpointConfig {
	private static final String TEMPORARY_TRUE = "1";
	private static final String TEMPORARY_FALSE = "0";

	private static final String STATE_ENABLED = "enabled";
	private static final String STATE_DISABLED = "disabled";

	private static final String HIT_CONDITION_GREATER = ">=";
	private static final String HIT_CONDITION_EQUAL = "==";
	private static final String HIT_CONDITION_MULTIPLE = "%";

	private final boolean enabled;

	private final boolean temporary;

	private final int hitValue;

	private final int hitCondition;

	private final String conditionExpression;

	public String getConditionExpression() {
		return conditionExpression;
	}

	public DbgpBreakpointConfig(boolean enabled) {
		this(enabled, -1, -1, null);
	}

	public DbgpBreakpointConfig(boolean enabled, int hitValue,
			int hitCondition, String conString) {
		this(enabled, hitValue, hitCondition, false, conString);
	}

	public DbgpBreakpointConfig(boolean enabled, int hitValue,
			int hitCondition, boolean temporary, String conString) {
		this.enabled = enabled;
		this.hitValue = hitValue;
		this.hitCondition = hitCondition;
		this.temporary = temporary;
		this.conditionExpression = conString;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isTemporary() {
		return temporary;
	}

	public int getHitValue() {
		return hitValue;
	}

	public int getHitCondition() {
		return hitCondition;
	}

	public String getTemporaryString() {
		return temporary ? TEMPORARY_TRUE : TEMPORARY_FALSE;
	}

	public String getStateString() {
		return enabled ? STATE_ENABLED : STATE_DISABLED;
	}

	public String getHitConditionString() {
		if (hitCondition == IDbgpBreakpoint.HIT_CONDITION_EQUAL) {
			return HIT_CONDITION_EQUAL;
		} else if (hitCondition == IDbgpBreakpoint.HIT_CONDITION_GREATER) {
			return HIT_CONDITION_GREATER;
		} else if (hitCondition == IDbgpBreakpoint.HIT_CONDITION_MULTIPLE) {
			return HIT_CONDITION_MULTIPLE;
		}

		return null;
	}
}
