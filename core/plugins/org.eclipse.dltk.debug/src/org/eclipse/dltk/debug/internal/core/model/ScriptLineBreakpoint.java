package org.eclipse.dltk.debug.internal.core.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;

public class ScriptLineBreakpoint extends ScriptBreakpoint implements
		IScriptLineBreakpoint {

	public static URI makeUri(IResource resource) {
		try {
			return new URI("file", "///" + resource.getLocation().toOSString(),
					null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void addScriptLineBreakpointAttributes(Map attributes,
			String modelIdentifier, boolean enabled, int lineNumber,
			int charStart, int charEnd) {
		attributes.put(IBreakpoint.ID, modelIdentifier);
		attributes.put(IBreakpoint.ENABLED, Boolean.valueOf(enabled));
		attributes.put(IMarker.LINE_NUMBER, new Integer(lineNumber));
		attributes.put(IMarker.CHAR_START, new Integer(charStart));
		attributes.put(IMarker.CHAR_END, new Integer(charEnd));
	}

	public ScriptLineBreakpoint() {

	}

	public String getModelIdentifier() {
		return ScriptModelConstants.MODEL_ID;
	}

	public ScriptLineBreakpoint(final IResource resource, final int lineNumber,
			final int charStart, final int charEnd, final int hitCount,
			final boolean add, final Map attributes) throws DebugException {

		IWorkspaceRunnable wr = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				addScriptLineBreakpointAttributes(attributes,
						getModelIdentifier(), true, lineNumber, charStart,
						charEnd);

				setMarker(ScriptMarkerFactory.makeMarker(resource, attributes,getMarkerID()));
				ensureMarker().setAttributes(attributes);

				register(add);
			}
		};

		run(getMarkerRule(resource), wr);
	}
	protected String getMarkerID(){
		return ScriptMarkerFactory.MARKER_ID;
	}

	// ILineBreakpoint
	public int getLineNumber() throws CoreException {
		return ensureMarker().getAttribute(IMarker.LINE_NUMBER, -1);
	}

	public int getCharStart() throws CoreException {
		return ensureMarker().getAttribute(IMarker.CHAR_START, -1);
	}

	public int getCharEnd() throws CoreException {
		return ensureMarker().getAttribute(IMarker.CHAR_END, -1);

	}

	// IScriptLineBreakpoint
	public URI getResourceURI() {
		try {
			return makeUri(ensureMarker().getResource());
		} catch (DebugException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
