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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;

public class ScriptLineBreakpoint extends AbstractScriptBreakpoint implements
		IScriptLineBreakpoint {

	protected String getMarkerId() {
		return ScriptMarkerFactory.LINE_BREAKPOINT_MARKER_ID;
	}

	protected void addLineBreakpointAttributes(Map attributes, int lineNumber,
			int charStart, int charEnd) {
		attributes.put(IMarker.LINE_NUMBER, new Integer(lineNumber));
		attributes.put(IMarker.CHAR_START, new Integer(charStart));
		attributes.put(IMarker.CHAR_END, new Integer(charEnd));
	}

	public ScriptLineBreakpoint() {

	}

	public ScriptLineBreakpoint(final String debugModelId,
			final IResource resource, final int lineNumber,
			final int charStart, final int charEnd, final int hitCount,
			final boolean add) throws DebugException {

		final Map attributes = new HashMap();

		IWorkspaceRunnable wr = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				// create the marker
				setMarker(resource.createMarker(getMarkerId()));

				// add attributes
				addScriptBreakpointAttributes(attributes, debugModelId, true);
				addLineBreakpointAttributes(attributes, lineNumber, charStart,
						charEnd);

				// set attributes
				ensureMarker().setAttributes(attributes);

				// add to breakpoint manager if requested
				register(add);
			}
		};
		run(getMarkerRule(resource), wr);
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
			// TODO: log exception
			e.printStackTrace();
			return null;
		}
	}
}
