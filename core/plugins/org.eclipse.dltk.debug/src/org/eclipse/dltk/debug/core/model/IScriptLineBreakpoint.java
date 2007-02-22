package org.eclipse.dltk.debug.core.model;

import java.net.URI;

import org.eclipse.debug.core.model.ILineBreakpoint;

public interface IScriptLineBreakpoint extends IScriptBreakpoint,
		ILineBreakpoint {
	URI getResourceURI();
}
