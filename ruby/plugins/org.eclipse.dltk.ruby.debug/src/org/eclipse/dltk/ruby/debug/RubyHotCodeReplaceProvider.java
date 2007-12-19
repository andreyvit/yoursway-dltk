package org.eclipse.dltk.ruby.debug;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IHotCodeReplaceProvider;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public class RubyHotCodeReplaceProvider implements
		IHotCodeReplaceProvider {

	public RubyHotCodeReplaceProvider() {
	}

	public void performCodeReplace(IScriptDebugTarget target,
			IResource[] resources) throws DebugException {
		IScriptThread[] threads = (IScriptThread[]) target.getThreads();

		if (threads.length > 0) {
			IScriptThread thread = threads[0];
			IDbgpExtendedCommands extCmds = thread.getDbgpSession().getExtendedCommands();
			try {
				extCmds.evaluate(getReplacementCode(resources));
			} catch (DbgpException e) {
				fail(e);
			}
		}
	}

	private String getReplacementCode(IResource[] resources) throws DebugException {
		StringBuffer builder = new StringBuffer();
		getResourceReplacementCode(resources[0]);
		for (int i=0; i<resources.length; i++) {
			builder.append('\n');
			builder.append(getResourceReplacementCode(resources[i]));
		}
		return builder.toString();
	}

	private String getResourceReplacementCode(IResource resource) throws DebugException {
		IPath path = resource.getProjectRelativePath();
		if (path == null) {
			fail("Can not compute source file path");
		}		
		return "load '" + path.toOSString() + "'";
	}

	private void fail(String message) throws DebugException {
		fail(message, null);	
	}

	private void fail(Throwable e) throws DebugException {
		fail(e.getMessage(), e);		
	}
	
	private void fail(String message, Throwable e) throws DebugException {
		throw new DebugException(new Status(IStatus.ERROR,
				DLTKDebugPlugin.PLUGIN_ID, DebugPlugin.INTERNAL_ERROR, message,
				e));
	}
}
