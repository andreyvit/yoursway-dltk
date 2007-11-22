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
import org.eclipse.dltk.debug.core.model.IScriptWatchpoint;

public class ScriptDebugModel {
	public static String getDebugModelId(IResource resource) {
		final String natureId = DLTKLanguageManager.findToolkit(resource)
				.getNatureId();
		return ScriptDebugManager.getInstance().getDebugModelByNature(natureId);
	}

	public static IScriptLineBreakpoint createLineBreakpoint(
			IResource resource, int lineNumber, int charStart, int charEnd,
			boolean register, Map attributes) throws CoreException {

		return new ScriptLineBreakpoint(getDebugModelId(resource), resource,
				lineNumber, charStart, charEnd, register);
	}

	public static IScriptLineBreakpoint createMethodEntryBreakpoint(
			IResource resource, int lineNumber, int charStart, int charEnd,
			boolean register, Map attributes, String methodName)
			throws CoreException {

		return new ScriptMethodEntryBreakpoint(getDebugModelId(resource),
				resource, lineNumber, charStart, charEnd, register, methodName);
	}

	public static IScriptWatchpoint createWatchPoint(IResource resource,
			int lineNumber, int start, int end, String fieldName)
			throws CoreException {
		return new ScriptWatchpoint(getDebugModelId(resource), resource,
				lineNumber, start, end, fieldName);
	}

	public static ScriptExceptionBreakpoint createExceptionBreakpoint(
			String debugModelId, IResource resource, String typename, boolean caught,
			boolean uncaught, boolean register, Map attributes) throws CoreException {
		if (attributes == null)
			attributes = new HashMap();
		
		return new ScriptExceptionBreakpoint(debugModelId,
				resource, typename, caught, uncaught, register, attributes);
	}
}
