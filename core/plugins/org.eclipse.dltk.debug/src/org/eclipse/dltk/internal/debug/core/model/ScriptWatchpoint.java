/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;

public class ScriptWatchpoint extends ScriptLineBreakpoint implements
		IScriptWatchPoint {

	private static final String FIELD_NAME = ScriptLineBreakpoint.ID
			+ ".fieldName";

	private static final String ACCESS = ScriptLineBreakpoint.ID + ".access";

	private static final String MODIFICATION = ScriptLineBreakpoint.ID
			+ ".modification";

	public ScriptWatchpoint(IResource resource, int lineNumber, int start,
			int end, Map attributes, String fieldName) throws CoreException {
		super(resource, lineNumber, start, end, end, true, attributes);
		this.setFieldName(fieldName);
	}

	public ScriptWatchpoint() {
	}

	public String getFieldName() throws CoreException {
		return getMarker().getAttribute(FIELD_NAME, "");
	}

	public void setFieldName(String str) throws CoreException {
		getMarker().setAttribute(FIELD_NAME, str);
	}

	public boolean isAccessSuspend(IDebugTarget target) {
		return false;
	}

	protected String getMarkerID() {
		return ScriptMarkerFactory.WATCHPOINT_MARKER_ID;
	}

	public boolean isAccess() throws CoreException {
		return (new Boolean(getMarker().getAttribute(ACCESS, "true")))
				.booleanValue();
	}

	public boolean isModification() throws CoreException {
		return (new Boolean(getMarker().getAttribute(MODIFICATION, "true")))
				.booleanValue();
	}

	public void setAccess(boolean access) throws CoreException {
		getMarker().setAttribute(ACCESS, Boolean.toString(access));
	}

	public void setModification(boolean modification) throws CoreException {
		getMarker().setAttribute(MODIFICATION, Boolean.toString(modification));
	}

	public boolean supportsAccess() {
		return true;
	}

	public boolean supportsModification() {
		return true;
	}

}
