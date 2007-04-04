package org.eclipse.dltk.javascript.internal.launching;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;

public class GenericJavaScriptInstall extends AbstractInterpreterInstall {

	public GenericJavaScriptInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}

	public IInterpreterRunner getInterpreterRunner(String mode) {
		if (mode.equals(ILaunchManager.RUN_MODE)) {
			return new JavaScriptInterpreterRunner(this);
		} else if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			return new JavaScriptInterpreterDebugger(this);
		} 
		
		//TODO: possible throw exception
		return null;
	}
}