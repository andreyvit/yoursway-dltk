package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;

public interface IScriptEvaluationEngine {
	void evaluate(String snippet, IScriptStackFrame frame,
			IScriptEvaluationListener listener) throws DebugException;

	IScriptDebugTarget getDebugTarget();

	void dispose();
}
