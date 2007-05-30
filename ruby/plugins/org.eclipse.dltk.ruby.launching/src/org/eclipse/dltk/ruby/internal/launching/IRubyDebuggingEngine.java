package org.eclipse.dltk.ruby.internal.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.launching.IInterpreterInstall;

public interface IRubyDebuggingEngine {
	void run(DbgpConnectParams params, IInterpreterInstall install) throws CoreException;
}
