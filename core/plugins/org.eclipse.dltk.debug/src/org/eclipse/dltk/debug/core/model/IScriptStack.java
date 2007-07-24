package org.eclipse.dltk.debug.core.model;

import org.eclipse.dltk.internal.debug.core.model.ScriptThread;

public interface IScriptStack {
	ScriptThread getThread();

	int size();

	boolean hasFrames();

	IScriptStackFrame[] getFrames();

	IScriptStackFrame getTopFrame();
}
