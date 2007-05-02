package org.eclipse.dltk.debug.internal.core.model;

import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ScriptMarkerFactory {
	public static final String MARKER_ID = "org.eclipse.dltk.debug.scriptLineBreakpointMarker";
	public static final String METHOD_ENTRY_MARKER_ID = "org.eclipse.dltk.debug.scriptMethodEntryBreakpointMarker";

	public static IMarker makeMarker(IResource resource, Map attributes,String id)
			throws CoreException {
		IMarker marker = resource.createMarker(id);
		marker.setAttributes(attributes);
		return marker;
	}
}
