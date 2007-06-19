/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;

public class ScriptDebugModel {
	protected static String getDenbugModelId(IResource resource) {
		final String natureId = DLTKLanguageManager.findToolkit(resource)
				.getNatureId();
		return ScriptDebugManager.getInstance().getDebugModelByNature(natureId);
	}

	public static IScriptLineBreakpoint createLineBreakpoint(
			IResource resource, int lineNumber, int charStart, int charEnd,
			int hitCount, boolean register, Map attributes)
			throws CoreException {

		if (attributes == null) {
			attributes = new HashMap(10);
		}

		return new ScriptLineBreakpoint(getDenbugModelId(resource), resource,
				lineNumber, charStart, charEnd, hitCount, register, attributes);
	}

	public static IScriptLineBreakpoint createMethodEntryBreakpoint(
			IResource resource, int lineNumber, int charStart, int charEnd,
			int hitCount, boolean register, Map attributes, String methodName,
			String methodSignature) throws CoreException {

		if (attributes == null) {
			attributes = new HashMap(10);
		}

		// String debugModelId = null; // TODO

		return new ScriptMethodEntryBreakpoint(getDenbugModelId(resource),
				resource, lineNumber, charStart, charEnd, hitCount, register,
				attributes, methodName, methodSignature);
	}

	public static IScriptWatchPoint createWatchPoint(IResource resource,
			int lineNumber, int start, int end, Map attributes, String fieldName)
			throws CoreException {
		return new ScriptWatchpoint(getDenbugModelId(resource), resource,
				lineNumber, start, end, attributes, fieldName);
	}
}
