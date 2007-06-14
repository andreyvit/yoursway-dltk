/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.Breakpoint;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;

public abstract class ScriptBreakpoint extends Breakpoint implements
		IScriptBreakpoint {
	public static final String BREAKPOINT = "org.eclipse.dltk.script_breakpoint";

	private static final String ID = BREAKPOINT + ".id";

	private static final String EXPRESSION = BREAKPOINT + ".expression";

	private static final String EXPRESSION_STATE = EXPRESSION + ".state";

	private static final String HIT_VALUE = BREAKPOINT + ".hit_value";

	private static final String HIT_CONDITION = BREAKPOINT + ".hit_condition";

	// Identifier
	public String getIdentifier() throws CoreException {
		return ensureMarker().getAttribute(ID, null);
	}

	public void setIdentifier(String id) throws CoreException {
		setAttribute(ID, id);
	}

	// Hit value
	public int getHitValue() throws CoreException {
		return Integer.parseInt(ensureMarker().getAttribute(HIT_VALUE, "-1"));
	}

	public void setHitValue(int hitValue) throws CoreException {
		if (getHitValue() != hitValue) {
			setAttribute(HIT_VALUE, Integer.toString(hitValue));
		}
	}

	// Hit condition
	public int getHitCondition() throws CoreException {
		return Integer.parseInt(ensureMarker()
				.getAttribute(HIT_CONDITION, "-1"));
	}

	public void setHitCondition(int condition) throws CoreException {
		if (getHitCondition() != condition) {
			setAttribute(HIT_CONDITION, Integer.toString(condition));
		}
	}

	// Resource name
	public String getResourceName() throws CoreException {
		return ensureMarker().getResource().getName();
	}

	// Expression
	public String getExpression() throws CoreException {
		return ensureMarker().getAttribute(EXPRESSION, null);
	}

	public void setExpression(String expression) throws CoreException {
		ensureMarker().setAttribute(EXPRESSION, expression);
	}

	public boolean getExpressionState() throws CoreException {
		return (new Boolean(ensureMarker().getAttribute(EXPRESSION_STATE,
				"false")).booleanValue());
	}

	public void setExpressionState(boolean state) throws CoreException {
		if (getExpressionState() != state) {
			setAttribute(EXPRESSION_STATE, state);
		}
	}

	/**
	 * Add this breakpoint to the breakpoint manager, or sets it as
	 * unregistered.
	 */
	protected void register(boolean register) throws CoreException {
		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null && register) {
			plugin.getBreakpointManager().addBreakpoint(this);
		} else {
			setRegistered(false);
		}
	}
}
