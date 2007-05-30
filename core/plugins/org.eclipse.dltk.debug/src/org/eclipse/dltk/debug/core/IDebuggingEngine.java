package org.eclipse.dltk.debug.core;

public interface IDebuggingEngine {
	String getId();

	String getNatureId();
	
	String getName();

	String getDescription();

	int getPriority();

	IDebuggingEngineRunner getRunner();
}
