package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptVariableWrapper extends AbstractScriptVariable {
	private final String name;
	private final IScriptVariable[] children;

	public ScriptVariableWrapper(IDebugTarget target, String name,
			IScriptVariable[] children) {
		super(target);
		this.name = name;
		this.children = children;
	}

	public IScriptVariable[] getChildren() throws DebugException {
		return (IScriptVariable[]) children.clone();
	}

	public String getEvalName() {
		return name;
	}

	public String getId() {
		return null;
	}

	public String getValueString() {
		return "";
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

	public boolean shouldHasChildren() {
		return false;
	}

	public IScriptType getType() {
		return new ScriptType("getType");
	}
}
