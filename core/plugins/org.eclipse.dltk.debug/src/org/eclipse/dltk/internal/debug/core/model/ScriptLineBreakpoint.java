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
			return new URI("file", "///"
					+ resource.getLocation().toPortableString(), null);
		} catch (URISyntaxException e) {
			// TODO: log exception
			e.printStackTrace();
			return null;
		}
	}

	protected static void addAttributes(Map attributes, String debugModelId,
			boolean enabled, int lineNumber, int charStart, int charEnd) {
		attributes.put(IBreakpoint.ID, debugModelId);
		attributes.put(IBreakpoint.ENABLED, Boolean.valueOf(enabled));
		attributes.put(IMarker.LINE_NUMBER, new Integer(lineNumber));
		attributes.put(IMarker.CHAR_START, new Integer(charStart));
		attributes.put(IMarker.CHAR_END, new Integer(charEnd));
	}

	protected String getMarkerID() {
		return ScriptMarkerFactory.LINE_BREAKPOINT_MARKER_ID;
	}

	public ScriptLineBreakpoint() {

	}

	public ScriptLineBreakpoint(final String debugModelId,
			final IResource resource, final int lineNumber,
			final int charStart, final int charEnd, final int hitCount,
			final boolean add, final Map attributes) throws DebugException {

		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				addAttributes(attributes, debugModelId, true, lineNumber,
						charStart, charEnd);

				setMarker(ScriptMarkerFactory.makeMarker(resource, attributes,
						getMarkerID()));

				ensureMarker().setAttributes(attributes);

				register(add);
			}
		};

		run(getMarkerRule(resource), runnable);
	}

	// IBreakpoint
	public String getModelIdentifier() {
		try {
			return ensureMarker().getAttribute(IBreakpoint.ID, null);
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
