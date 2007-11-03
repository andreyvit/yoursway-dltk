package org.eclipse.dltk.launching.debug;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;

public interface IDebuggingEngine {
	String getId();

	String getModelId();

	String getNatureId();

	String getPreferencePageId();

	String getName();

	String getDescription();

	int getPriority();

	boolean supportsSuspendOnEntry();

	boolean supportsSuspendOnExit();
	
	IInterpreterRunner getRunner(IInterpreterInstall install);
}
