package org.eclipse.dltk.tcl.testing;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.testing.ITestingProcessor;

public interface ITclTestingEngine {
	String getId();
	String getName();
	
	boolean isValidModule(ISourceModule module);
	ITestingProcessor getProcessor(ILaunch launch);
	void correctLaunchConfiguration(InterpreterConfig config);
}
