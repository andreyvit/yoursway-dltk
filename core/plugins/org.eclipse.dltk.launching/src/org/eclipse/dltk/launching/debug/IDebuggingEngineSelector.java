package org.eclipse.dltk.launching.debug;


public interface IDebuggingEngineSelector {
	IDebuggingEngine select(IDebuggingEngine[] engines); 
}
