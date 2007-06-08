package org.eclipse.dltk.debug.core.eval;

import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;

public interface IScriptEvaluationEngine {
	IScriptDebugTarget getScriptDebugTarget();

	IScriptEvaluationResult syncEvaluate(String snippet, IScriptStackFrame frame);

	void asyncEvaluate(String snippet, IScriptStackFrame frame,
			IScriptEvaluationListener listener);

	void dispose();
}
