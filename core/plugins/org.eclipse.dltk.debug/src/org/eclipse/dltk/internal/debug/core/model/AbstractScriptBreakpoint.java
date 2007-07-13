/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.Breakpoint;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;

public abstract class AbstractScriptBreakpoint extends Breakpoint implements
		IScriptBreakpoint {
	
	private static final String ATTR_PREFIX = "org.eclipse.dltk.script_breakpoint";
	
	public static final String ENGINE_IDENTIFIER = ATTR_PREFIX + ".id";

	public static final String HIT_COUNT = ATTR_PREFIX + ".hit_count";

	public static final String EXPRESSION = ATTR_PREFIX + ".expression";

	public static final String EXPRESSION_STATE = EXPRESSION + ".state";

	public static final String HIT_VALUE = ATTR_PREFIX + ".hit_value";

	public static final String HIT_CONDITION = ATTR_PREFIX + ".hit_condition";
	
	public static URI makeUri(IResource resource) {
		try {
			return new URI("file", "///"
					+ resource.getLocation().toPortableString(), null);
		} catch (URISyntaxException e) {
			// TODO: log exception
			e.printStackTrace();
			return null;
		}
	}

	protected void addScriptBreakpointAttributes(Map attributes,
			String debugModelId, boolean enabled) {
		attributes.put(IBreakpoint.ID, debugModelId);
		attributes.put(IBreakpoint.ENABLED, new Boolean(enabled));
	}

	public AbstractScriptBreakpoint() {

	}

	public String getModelIdentifier() {
		try {
			return ensureMarker().getAttribute(IBreakpoint.ID, null);
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// Identifier
	public String getIdentifier() throws CoreException {
		return ensureMarker().getAttribute(ENGINE_IDENTIFIER, null);
	}

	public void setIdentifier(String id) throws CoreException {
		setAttribute(ENGINE_IDENTIFIER, id);
	}

	// Message
	public String getMessage() throws CoreException {
		return ensureMarker().getAttribute(IMarker.MESSAGE, null);
	}
	
	public void setMessage(String message) throws CoreException {
		setAttribute(IMarker.MESSAGE, message);
	}

	// Hit count
	public int getHitCount() throws CoreException {
		return ensureMarker().getAttribute(HIT_COUNT, -1);
	}

	public void setHitCount(int value) throws CoreException {
		setAttribute(HIT_COUNT, value);
	}

	// Hit value
	public int getHitValue() throws CoreException {
		return ensureMarker().getAttribute(HIT_VALUE, -1);
	}

	public void setHitValue(int hitValue) throws CoreException {
		if (getHitValue() != hitValue) {
			setAttribute(HIT_VALUE, hitValue);
		}
	}

	// Hit condition
	public int getHitCondition() throws CoreException {
		return ensureMarker().getAttribute(HIT_CONDITION, -1);
	}

	public void setHitCondition(int condition) throws CoreException {
		if (getHitCondition() != condition) {
			setAttribute(HIT_CONDITION, condition);
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
		setAttribute(EXPRESSION, expression);
	}

	public boolean getExpressionState() throws CoreException {
		return ensureMarker().getAttribute(EXPRESSION_STATE, false);
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
