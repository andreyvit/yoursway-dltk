package org.eclipse.dltk.internal.debug.core.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;

public class ScriptMethodEntryBreakpoint extends ScriptLineBreakpoint implements
		IScriptMethodEntryBreakpoint {

	private static final String METHOD_NAME = DLTKDebugPlugin.PLUGIN_ID
			+ ".methodName"; //$NON-NLS-1$

	private static final String BREAK_ON_ENTRY = DLTKDebugPlugin.PLUGIN_ID
			+ ".methodEntry"; //$NON-NLS-1$

	private static final String BREAK_ON_EXIT = DLTKDebugPlugin.PLUGIN_ID
			+ ".methodExit"; //$NON-NLS-1$

	private static final String ENTRY_ID = DLTKDebugPlugin.PLUGIN_ID
			+ ".entryBrId"; //$NON-NLS-1$

	private static final String EXIT_ID = DLTKDebugPlugin.PLUGIN_ID
			+ ".exitBrId"; //$NON-NLS-1$

	protected String getMarkerId() {
		return ScriptMarkerFactory.METHOD_ENTRY_MARKER_ID;
	}

	public ScriptMethodEntryBreakpoint() {

	}

	public ScriptMethodEntryBreakpoint(String debugModelId, IResource resource, IPath path,
			int lineNumber, int charStart, int charEnd, boolean register,
			String methodName) throws DebugException {

		super(debugModelId, resource, path, lineNumber, charStart, charEnd, register);

		try {
			ensureMarker().setAttribute(METHOD_NAME, methodName);
		} catch (CoreException e) {
			throw new DebugException(e.getStatus());
		}
	}

	// Method name
	public String getMethodName() throws CoreException {
		return ensureMarker().getAttribute(METHOD_NAME, ""); //$NON-NLS-1$
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

	// Entry breakpoint id
	public String getEntryBreakpointId() throws CoreException {
		return ensureMarker().getAttribute(ENTRY_ID, null);
	}

	public void setEntryBreakpointId(String id) throws CoreException {
		ensureMarker().setAttribute(ENTRY_ID, id);
	}

	// Exit breakpoint id
	public String getExitBreakpointId() throws CoreException {
		return ensureMarker().getAttribute(EXIT_ID, null);
	}

	public void setExitBreakpointId(String id) throws CoreException {
		ensureMarker().setAttribute(EXIT_ID, id);
	}

	private static final String[] UPDATABLE_ATTRS = new String[] { METHOD_NAME,
			BREAK_ON_ENTRY, BREAK_ON_EXIT };

	public String[] getUpdatableAttributes() {
		List all = new ArrayList();
		all.addAll(Arrays.asList(super.getUpdatableAttributes()));
		all.addAll(Arrays.asList(UPDATABLE_ATTRS));
		return (String[]) all.toArray(new String[all.size()]);
	}
}
