package org.eclipse.dltk.internal.debug.core.eval;

import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptEvaluationResult implements IScriptEvaluationResult {
	private static final String[] NO_MESSAGES = new String[0];

	private IScriptThread thread;
	private String snippet;
	private IScriptVariable variable;

	public ScriptEvaluationResult(IScriptThread thread, String snippet,
			IScriptVariable variable) {
		this.thread = thread;
		this.variable = variable;
		this.snippet = snippet;
	}

	public String getSnippet() {
		return snippet;
	}

	public IScriptValue getValue() {
		try {
			return (IScriptValue) variable.getValue();
		} catch (DebugException e) {
			// TODO: log exception
		}

		return null;
	}

	public IScriptThread getThread() {
		return thread;
	}

	public String[] getErrorMessages() {
		return NO_MESSAGES;
	}

	public DebugException getException() {
		return null;
	}

	public boolean hasErrors() {
		return false;
	}
}
