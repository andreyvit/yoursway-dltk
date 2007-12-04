package org.eclipse.dltk.launching.debug;

import org.eclipse.dltk.core.IDLTKContributedExtension;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;

public interface IDebuggingEngine extends IDLTKContributedExtension {

	String getModelId();
	
	IInterpreterRunner getRunner(IInterpreterInstall install);
}
