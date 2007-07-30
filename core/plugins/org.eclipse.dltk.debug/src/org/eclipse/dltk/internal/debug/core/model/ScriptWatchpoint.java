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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptWatchpoint;

public class ScriptWatchpoint extends ScriptLineBreakpoint implements
		IScriptWatchpoint {

	private static final String FIELD_NAME = DLTKDebugPlugin.PLUGIN_ID
			+ ".fieldName";

	private static final String ACCESS = DLTKDebugPlugin.PLUGIN_ID + ".access";

	private static final String MODIFICATION = DLTKDebugPlugin.PLUGIN_ID
			+ ".modification";

	public ScriptWatchpoint(String debugModelId, IResource resource,
			int lineNumber, int start, int end, String fieldName)
			throws CoreException {
		super(debugModelId, resource, lineNumber, start, end, true);
		this.setFieldName(fieldName);
	}

	public ScriptWatchpoint() {
	}

	public String getFieldName() throws CoreException {
		return this.getMarker().getAttribute(FIELD_NAME, "");
	}

	public void setFieldName(String name) throws CoreException {
		this.getMarker().setAttribute(FIELD_NAME, name);
	}

	protected String getMarkerId() {
		return ScriptMarkerFactory.WATCHPOINT_MARKER_ID;
	}

	public boolean isAccess() throws CoreException {
		return (new Boolean(this.getMarker().getAttribute(ACCESS, "true")))
				.booleanValue();
	}

	public boolean isModification() throws CoreException {
		return (new Boolean(this.getMarker().getAttribute(MODIFICATION, "true")))
				.booleanValue();
	}

	public void setAccess(boolean access) throws CoreException {
		this.getMarker().setAttribute(ACCESS, Boolean.toString(access));
	}

	public void setModification(boolean modification) throws CoreException {
		this.getMarker().setAttribute(MODIFICATION,
				Boolean.toString(modification));
	}

	public boolean supportsAccess() {
		return true;
	}

	public boolean supportsModification() {
		return true;
	}

	private static final String[] UPDATABLE_ATTRS = new String[] { FIELD_NAME,
			ACCESS, MODIFICATION };

	public String[] getUpdatableAttributes() {
		List all = new ArrayList();
		Arrays.asList(super.getUpdatableAttributes());
		Arrays.asList(UPDATABLE_ATTRS);
		return (String[]) all.toArray(new String[all.size()]);
	}
}
