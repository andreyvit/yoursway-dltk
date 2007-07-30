package org.eclipse.dltk.debug.tests.breakpoints;

import junit.framework.Test;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.debug.tests.ScriptDebugTest;
import org.eclipse.dltk.internal.debug.core.model.ScriptLineBreakpoint;

public class BreakpointTests extends ScriptDebugTest {
	public BreakpointTests(String name) {
		super("Breakpoint tests", name);
	}

	private IScriptLineBreakpoint breakpoint;

	public void setUpSuite() throws Exception {
		super.setUpSuite();

		IResource resource = scriptProject.getProject().findMember(
				"src/test.rb");

		breakpoint = new ScriptLineBreakpoint("test_debug_model", resource, 1,
				-1, -1, true);
	}

	public static Test suite() {
		return new Suite(BreakpointTests.class);
	}

	protected String getProjectName() {
		return "debug";
	}

	public void testSetGet() throws Exception {
		// Id
		final String id = "32145";
		breakpoint.setIdentifier(id);
		assertEquals(id, breakpoint.getIdentifier());

		// HitCount
		final int hitCount = 234;
		assertEquals(-1, breakpoint.getHitCount());
		breakpoint.setHitCount(hitCount);
		assertEquals(hitCount, breakpoint.getHitCount());

		// Expression state
		assertEquals(false, breakpoint.getExpressionState());
		breakpoint.setExpressionState(true);
		assertEquals(true, breakpoint.getExpressionState());

		// Expression
		final String expression = "x + y > 3245";
		assertNull(breakpoint.getExpression());
		breakpoint.setExpression(expression);
		assertEquals(expression, breakpoint.getExpression());

		// Hit condition
		final int hitCondition = IScriptBreakpoint.HIT_CONDITION_EQUAL;
		assertEquals(IScriptBreakpoint.HIT_CONDITION_GREATER_OR_EQUAL,
				breakpoint.getHitCondition());
		breakpoint.setHitCondition(hitCondition);
		assertEquals(hitCondition, breakpoint.getHitCondition());

		// Hit value
		final int hitValue = 22;
		assertEquals(1, breakpoint.getHitValue());
		breakpoint.setHitValue(hitValue);
		assertEquals(hitValue, breakpoint.getHitValue());
	}
}
