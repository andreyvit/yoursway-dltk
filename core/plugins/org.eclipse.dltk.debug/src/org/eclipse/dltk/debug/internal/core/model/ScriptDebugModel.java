package org.eclipse.dltk.debug.internal.core.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;

public class ScriptDebugModel {
	public static IScriptLineBreakpoint createLineBreakpoint(
			IResource resource, int lineNumber, int charStart, int charEnd,
			int hitCount, boolean register, Map attributes)
			throws CoreException {
		
		if (attributes == null) {
			attributes = new HashMap(10);
		}
		
		return new ScriptLineBreakpoint(resource, lineNumber, charStart,
				charEnd, hitCount, register, attributes);
	}
	
	public static IScriptLineBreakpoint createMethodEntryBreakpoint(
			IResource resource, int lineNumber, int charStart, int charEnd,
			int hitCount, boolean register, Map attributes,String methodName,String methodSignature)
			throws CoreException {
		
		if (attributes == null) {
			attributes = new HashMap(10);
		}
		
		return new ScriptMethodEntryBreakpoint(resource, lineNumber, charStart,
				charEnd, hitCount, register, attributes,methodName,methodSignature);
	}
}
