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

	private boolean enabled;

	private boolean temporary;

	private int hitValue;

	private int hitCondition;

	private String expression;

	// Constructors
	public DbgpBreakpointConfig() {
		this(true);
	}

	public DbgpBreakpointConfig(boolean enabled) {
		this(enabled, -1, -1, null);
	}

	public DbgpBreakpointConfig(boolean enabled, int hitValue,
			int hitCondition, String expression) {
		this(enabled, hitValue, hitCondition, false, expression);
	}

	public DbgpBreakpointConfig(boolean enabled, int hitValue,
			int hitCondition, boolean temporary, String expression) {
		this.enabled = enabled;
		this.hitValue = hitValue;
		this.hitCondition = hitCondition;
		this.temporary = temporary;
		this.expression = expression;
	}

	// Enabled
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean value) {
		this.enabled = value;
	}

	// Temporary
	public boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(boolean value) {
		this.temporary = value;
	}

	// Hit value
	public int getHitValue() {
		return hitValue;
	}

	public void setHitValue(int hitValue) {
		this.hitValue = hitValue;
	}

	// Hit condition
	public int getHitCondition() {
		return hitCondition;
	}

	public void setHitCondition(int hitCondition) {
		this.hitCondition = hitCondition;
	}

	// Expression
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	// Strings
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
