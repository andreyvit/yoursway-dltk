package org.eclipse.dltk.debug.internal.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptWatchExpressionResult implements IWatchExpressionResult {

	private String expression;
	private IScriptVariable variable;
	private String[] errorMessages;
	private DebugException exception;

	public ScriptWatchExpressionResult(String expression,
			DebugException exception) {
		this.expression = expression;
		this.exception = exception;
	}

	public ScriptWatchExpressionResult(String expression, String[] errorMessages) {
		this.expression = expression;
		this.errorMessages = errorMessages;
	}

	public ScriptWatchExpressionResult(String expression,
			IScriptVariable variable) {
		this.variable = variable;
		this.expression = expression;
	}

	public String[] getErrorMessages() {
		if (errorMessages == null) {
			return new String[0];
		}

		return errorMessages;
	}

	public DebugException getException() {
		return exception;
	}

	public String getExpressionText() {
		return "test"; //expression;
	}

	public IValue getValue() {
		try {
			if (variable != null) {
				return variable.getValue();
			}
		} catch (DebugException e) {
			// TODO: log exception
		}

		return null;
	}

	public boolean hasErrors() {
		return variable == null;
	}
}
