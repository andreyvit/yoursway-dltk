package org.eclipse.dltk.debug.core.eval;

import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;

public interface IScriptEvaluationCommand {
	IScriptDebugTarget getScriptDebugTarget();

	IScriptEvaluationResult syncEvaluate();

	void asyncEvaluate(IScriptEvaluationListener listener);

	void dispose();
}
