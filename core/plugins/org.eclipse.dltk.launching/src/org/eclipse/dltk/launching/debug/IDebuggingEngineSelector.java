package org.eclipse.dltk.launching.debug;


public interface IDebuggingEngineSelector {
	// Returns selected engine from the list. If cannot select, returns null
	IDebuggingEngine select(IDebuggingEngine[] engines); 
}
