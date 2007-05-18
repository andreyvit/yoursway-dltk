package org.eclipse.dltk.debug.internal.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptVariableWrapper extends AbstractScriptVariable {
	private String name;
	private IScriptVariable[] children;

	public ScriptVariableWrapper(IDebugTarget target, String name,
			IScriptVariable[] children) {
		super(target);
		this.name = name;
		this.children = children;
	}

	public IScriptVariable[] getChildren() throws DebugException {
		return (IScriptVariable[]) children.clone();
	}

	public String getFullName() {
		return name;
	}

	public String getKey() {
		return null;
	}

	public String getTypeString() {
		return "getTypeString";
	}

	public String getValueString() {
		return null;
	}

	public boolean hasChildren() {
		return children.length > 0;
	}

	public boolean isConstant() {
		return false;
	}

	public String getName() throws DebugException {
		return name;
	}

	public String getReferenceTypeName() throws DebugException {
		return "getReferenceTypeName";
	}

	public boolean hasValueChanged() throws DebugException {
		return false;
	}

	public void setValue(String expression) throws DebugException {

	}

	public void setValue(IValue value) throws DebugException {

	}

	public boolean supportsValueModification() {
		return false;
	}

	public boolean verifyValue(String expression) throws DebugException {
		return false;
	}

	public boolean verifyValue(IValue value) throws DebugException {
		return false;
	}
}
