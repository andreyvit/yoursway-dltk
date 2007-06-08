package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;

public class ScriptWatchExpressionResult implements IWatchExpressionResult {

	private IScriptEvaluationResult result;

	public ScriptWatchExpressionResult(IScriptEvaluationResult result) {
		this.result = result;
	}

	public String[] getErrorMessages() {
		return result.getErrorMessages();
	}

	public DebugException getException() {
		return result.getException();
	}

	public String getExpressionText() {
		return result.getSnippet();
	}

	public IValue getValue() {
		return result.getValue();
	}

	public boolean hasErrors() {
		return result.hasErrors();
	}
}
