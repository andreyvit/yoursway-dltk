package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationCommand;
import org.eclipse.dltk.debug.core.model.AtomicScriptType;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptVariableWrapper extends ScriptDebugElement implements
		IScriptVariable {
	private final String name;
	private final IScriptVariable[] children;
	private IDebugTarget target;

	public ScriptVariableWrapper(IDebugTarget target, String name,
			IScriptVariable[] children) {

		this.name = name;
		this.children = children;
		this.target = target;
	}

	public IScriptVariable[] getChildren() throws DebugException {
		if(children == null ) {
			return new IScriptVariable[0];
		}
		return (IScriptVariable[]) children.clone();
	}

	public String getEvalName() {
		return name;
	}

	public String getId() {
		return null;
	}

	public String getValueString() {
		return ""; //$NON-NLS-1$
	}

	public boolean hasChildren() {
		if (children == null) {
			return false;
		}
		return children.length > 0;
	}

	public boolean isConstant() {
		return false;
	}

	public String getName() throws DebugException {
		return name;
	}

	public String getReferenceTypeName() throws DebugException {
		return "getReferenceTypeName"; //$NON-NLS-1$
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
		return new AtomicScriptType("getType"); //$NON-NLS-1$
	}

	public IScriptStackFrame getStackFrame() {
		return null;
	}

	public IValue getValue() throws DebugException {
		return new IScriptValue() {
			public String getReferenceTypeName() throws DebugException {
				return ""; //$NON-NLS-1$
			}

			public String getRawValue() {
			  return ""; //$NON-NLS-1$
			}

			public String getValueString() throws DebugException {
				return ""; //$NON-NLS-1$
			}

			public IVariable[] getVariables() throws DebugException {
				return ScriptVariableWrapper.this.getChildren();
			}

			public boolean hasVariables() throws DebugException {
				return ScriptVariableWrapper.this.hasChildren();
			}

			public boolean isAllocated() throws DebugException {
				// TODO Auto-generated method stub
				return false;
			}

			public IDebugTarget getDebugTarget() {
				return ScriptVariableWrapper.this.target;
			}

			public ILaunch getLaunch() {
				return ScriptVariableWrapper.this.target.getLaunch();
			}

			public String getModelIdentifier() {
				return ScriptVariableWrapper.this.target.getModelIdentifier();
			}

			public Object getAdapter(Class adapter) {
				return null;
			}

			public IScriptEvaluationCommand createEvaluationCommand(
					String messageTemplate, IScriptThread thread) {
				return null;
			}

			public String getEvalName() {
				return null;
			}

			public String getInstanceId() {
				return null;
			}

			public IScriptType getType() {
				return ScriptVariableWrapper.this.getType();
			}
			
		};
	}

	public IDebugTarget getDebugTarget() {
		return target;
	}
}
