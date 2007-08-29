package org.eclipse.dltk.launching.sourcelookup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;

public class RemoteScriptSourceLookupParticipant extends AbstractSourceLookupParticipant {

	public String getSourceName(Object object) throws CoreException {
		ScriptStackFrame frame = (ScriptStackFrame) object;
		
		// TODO: check if the working directory is set, and if so, strip
		// off that portion of the path - the lookup will then act like
		// the 'ScriptSourceLookupParticipant' was called
		
		String path = frame.getFileName().getPath();
		
		return path;
	}
}
