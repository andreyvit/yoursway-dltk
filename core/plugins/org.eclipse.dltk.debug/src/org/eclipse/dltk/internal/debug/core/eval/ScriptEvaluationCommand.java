package org.eclipse.dltk.internal.debug.core.eval;

import org.eclipse.dltk.debug.core.eval.IScriptEvaluationCommand;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;

public class ScriptEvaluationCommand implements IScriptEvaluationCommand {
	private final IScriptEvaluationEngine engine;
	private final String snippet;
	private final IScriptStackFrame frame;

	public ScriptEvaluationCommand(IScriptEvaluationEngine engine,
			String snippet, IScriptStackFrame frame) {
		this.snippet = snippet;
		this.engine = engine;
		this.frame = frame;
	}

	public IScriptDebugTarget getScriptDebugTarget() {
		return engine.getScriptDebugTarget();
	}

	public IScriptEvaluationResult syncEvaluate() {
		return engine.syncEvaluate(snippet, frame);
	}

	public void asyncEvaluate(IScriptEvaluationListener listener) {
		engine.asyncEvaluate(snippet, frame, listener);
	}

	public void dispose() {
		engine.dispose();
	}
}
