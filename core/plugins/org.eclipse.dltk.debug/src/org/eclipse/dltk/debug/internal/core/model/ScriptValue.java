package org.eclipse.dltk.debug.internal.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptValue extends ScriptDebugElement implements IScriptValue {

	private IScriptVariable variable;

	protected ScriptValue(IScriptVariable variable) {
		super(variable.getDebugTarget());

		this.variable = variable;
	}

	public String getReferenceTypeName() throws DebugException {
		return variable.getTypeString();
	}

	public String getValueString() throws DebugException {
		return variable.getValueString();
	}

	public IVariable[] getVariables() throws DebugException {
		return variable.getChildren();
	}

	public boolean hasVariables() throws DebugException {
		return variable.hasChildren();
	}

	public boolean isAllocated() throws DebugException {
		// TODO: By default return true...
		return true;
	}
}
