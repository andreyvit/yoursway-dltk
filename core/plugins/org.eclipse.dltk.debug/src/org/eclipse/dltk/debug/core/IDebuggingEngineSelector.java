package org.eclipse.dltk.debug.core;

public interface IDebuggingEngineSelector {
	IDebuggingEngine select(IDebuggingEngine[] engines); 
}
