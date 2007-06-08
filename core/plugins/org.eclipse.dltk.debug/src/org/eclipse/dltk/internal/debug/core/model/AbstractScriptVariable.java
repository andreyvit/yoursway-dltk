package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public abstract class AbstractScriptVariable extends ScriptDebugElement
		implements IScriptVariable {
	private IDebugTarget target;

	private IValue value;

	protected AbstractScriptVariable(IDebugTarget target) {
		this.target = target;
	}

	public IValue getValue() throws DebugException {
		if (value == null) {
			value = new ScriptValue(this);
		}
		return value;
	}

	public IDebugTarget getDebugTarget() {
		return target;
	}
}
