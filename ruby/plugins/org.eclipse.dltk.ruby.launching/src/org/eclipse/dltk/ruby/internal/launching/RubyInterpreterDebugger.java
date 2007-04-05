package org.eclipse.dltk.ruby.internal.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.ruby.launching.IRubyLaunchConfigurationConstants;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;

public class RubyInterpreterDebugger extends AbstractInterpreterDebugger {
	public RubyInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	protected String getPluginIdentifier() {
		return RubyLaunchingPlugin.getUniqueIdentifier();
	}

	protected String getProcessType() {
		return IRubyLaunchConfigurationConstants.ID_RUBY_PROCESS_TYPE;
	}

	protected String[] getCommandLine(String id, String host, int port,
			String script, String[] args, String shell) throws CoreException {
		return new String[] { "notepad.exe" };
	}

	protected File getWorkingDir() throws CoreException {
		return new File("C:/");
	}
}
