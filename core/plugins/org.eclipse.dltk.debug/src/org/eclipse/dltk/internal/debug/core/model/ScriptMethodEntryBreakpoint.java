package org.eclipse.dltk.internal.debug.core.model;

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;

public class ScriptMethodEntryBreakpoint extends ScriptLineBreakpoint implements
		IScriptMethodEntryBreakpoint {

	private static final String METHOD_NAME = ScriptLineBreakpoint.ID
			+ ".methodName";
	private static final String METHOD_SIGNATURE = ScriptLineBreakpoint.ID
			+ ".methodSignature";

	private static final String SECONDARY_ID = ScriptLineBreakpoint.ID
			+ ".secondaryId";

	private static final String BREAK_ON_ENTRY = ScriptLineBreakpoint.ID
			+ ".methodEntry";

	private static final String BREAK_ON_EXIT = ScriptLineBreakpoint.ID
			+ ".methodExit";

	protected String getMarkerID() {
		return ScriptMarkerFactory.METHOD_ENTRY_MARKER_ID;
	}

	public ScriptMethodEntryBreakpoint() {

	}

	public ScriptMethodEntryBreakpoint(String debugModelId, IResource resource,
			int lineNumber, int charStart, int charEnd, int hitCount,
			boolean register, Map attributes, String methodName2,
			String methodSignature2) throws DebugException {
		super(debugModelId, resource, lineNumber, charStart, charEnd, hitCount,
				register, attributes);
		try {
			getMarker().setAttribute(METHOD_NAME, methodName2);
			getMarker().setAttribute(METHOD_SIGNATURE, methodSignature2);
		} catch (CoreException e) {
			throw new DebugException(e.getStatus());
		}
	}

	public String getMethodName() throws CoreException {
		return getMarker().getAttribute(METHOD_NAME, "");
	}

	public boolean shouldBreakOnEntry() {
		String attribute = getMarker().getAttribute(BREAK_ON_ENTRY, "true");
		return (new Boolean(attribute)).booleanValue();
	}

	public boolean shouldBreakOnExit() {
		String attribute = getMarker().getAttribute(BREAK_ON_EXIT, "true");
		return (new Boolean(attribute)).booleanValue();
	}

	public void setBreakOnEntry(boolean value) throws CoreException {
		getMarker().setAttribute(BREAK_ON_ENTRY, value + "");
	}

	public void setBreakOnExit(boolean value) throws CoreException {
		getMarker().setAttribute(BREAK_ON_EXIT, value + "");

	}

	public String getMethodSignature() throws CoreException {
		return getMarker().getAttribute(METHOD_SIGNATURE, "");
	}

	public String getSecondaryId() {
		return getMarker().getAttribute(SECONDARY_ID, null);
	}

	public void setSecondaryId(String id) throws CoreException {
		getMarker().setAttribute(SECONDARY_ID, id);
	}
}
