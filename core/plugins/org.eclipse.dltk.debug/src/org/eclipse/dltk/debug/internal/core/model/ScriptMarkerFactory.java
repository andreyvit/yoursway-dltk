package org.eclipse.dltk.debug.internal.core.model;

import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ScriptMarkerFactory {
	public static final String MARKER_ID = "org.eclipse.dltk.debug.scriptLineBreakpointMarker";

	public static IMarker makeMarker(IResource resource, Map attributes)
			throws CoreException {
		IMarker marker = resource.createMarker(MARKER_ID);
		marker.setAttributes(attributes);
		return marker;
	}
}
