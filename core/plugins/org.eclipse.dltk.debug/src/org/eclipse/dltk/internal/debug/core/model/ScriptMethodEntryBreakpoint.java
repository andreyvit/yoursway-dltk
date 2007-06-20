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

	private static final String BREAK_ON_ENTRY = ScriptLineBreakpoint.ID
			+ ".methodEntry";

	private static final String BREAK_ON_EXIT = ScriptLineBreakpoint.ID
			+ ".methodExit";

	private static final String SECONDARY_ID = ScriptLineBreakpoint.ID
			+ ".secondaryId";

	protected String getMarkerID() {
		return ScriptMarkerFactory.METHOD_ENTRY_MARKER_ID;
	}

	public ScriptMethodEntryBreakpoint() {

	}

	public ScriptMethodEntryBreakpoint(String debugModelId, IResource resource,
			int lineNumber, int charStart, int charEnd, int hitCount,
			boolean register, Map attributes, String methodName)
			throws DebugException {

		super(debugModelId, resource, lineNumber, charStart, charEnd, hitCount,
				register, attributes);
		try {
			ensureMarker().setAttribute(METHOD_NAME, methodName);
		} catch (CoreException e) {
			throw new DebugException(e.getStatus());
		}
	}

	// Method name
	public String getMethodName() throws CoreException {
		return ensureMarker().getAttribute(METHOD_NAME, "");
	}

	// Break on entry
	public boolean breakOnEntry() throws CoreException {
		return ensureMarker().getAttribute(BREAK_ON_ENTRY, false);
	}

	public void setBreakOnEntry(boolean value) throws CoreException {
		ensureMarker().setAttribute(BREAK_ON_ENTRY, value);
	}

	// Break on exit
	public boolean breakOnExit() throws CoreException {
		return ensureMarker().getAttribute(BREAK_ON_EXIT, false);
	}

	public void setBreakOnExit(boolean value) throws CoreException {
		ensureMarker().setAttribute(BREAK_ON_EXIT, value);
	}

	// Secondary id
	public String getSecondaryId() throws CoreException {
		return ensureMarker().getAttribute(SECONDARY_ID, null);
	}

	public void setSecondaryId(String id) throws CoreException {
		ensureMarker().setAttribute(SECONDARY_ID, id);
	}
}
