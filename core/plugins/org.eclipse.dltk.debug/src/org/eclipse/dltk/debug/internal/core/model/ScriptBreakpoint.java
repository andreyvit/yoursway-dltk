package org.eclipse.dltk.debug.internal.core.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.Breakpoint;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;

public abstract class ScriptBreakpoint extends Breakpoint implements
		IScriptBreakpoint {
	private static final String BREAKPOINT = "org.eclipse.dltk.script_breakpoint";

	private static final String BREAKPOINT_ID = BREAKPOINT + ".id";

	private static final String EXPRESSION_ID = BREAKPOINT + ".expression";

	private static final String COND_EXPRESSION_ENABLED_ID = BREAKPOINT
			+ ".expression.enabled";

	private static final String BREAKPOINT_HIT_VALUE = BREAKPOINT
			+ ".hit_value";

	private static final String BREAKPOINT_HIT_CONDITION = BREAKPOINT
			+ ".hit_condition";

	// Identifier
	public String getIdentifier() {
		return getMarker().getAttribute(BREAKPOINT_ID, null);
	}

	public void setIdentifier(String id) throws CoreException {
		getMarker().setAttribute(BREAKPOINT_ID, id);
	}

	public String getConditionalExpression() {
		return getMarker().getAttribute(EXPRESSION_ID, null);
	}

	public boolean isConditionalExpressionEnabled() {
		return getMarker().getAttribute(COND_EXPRESSION_ENABLED_ID, "false")
				.equals("true");
	}

	public void setConditionalExpression(String id) throws CoreException {
		getMarker().setAttribute(EXPRESSION_ID, id);
	}

	public void setConditionalExpressionEnabled(boolean enabled)
			throws CoreException {
		getMarker().setAttribute(COND_EXPRESSION_ENABLED_ID, enabled + "");
	}

	// Hit count
	public int getHitCount() {
		return -1;
	}

	// Hit value
	public int getHitValue() {
		return Integer.parseInt(getMarker().getAttribute(BREAKPOINT_HIT_VALUE,
				"-1"));
	}

	public void setHitValue(int count) throws CoreException {
		setAttribute(BREAKPOINT_HIT_VALUE, Integer.toString(count));
	}

	// Hit condition
	public int getHitCondition() {
		return Integer.parseInt(getMarker().getAttribute(
				BREAKPOINT_HIT_CONDITION, "-1"));
	}

	public void setHitCondition(int condition) throws CoreException {
		setAttribute(BREAKPOINT_HIT_CONDITION, Integer.toString(condition));
	}

	// Resource name
	public String getResourceName() {
		return getMarker().getResource().getName();
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
