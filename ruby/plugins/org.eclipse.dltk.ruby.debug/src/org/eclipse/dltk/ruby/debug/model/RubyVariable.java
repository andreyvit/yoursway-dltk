package org.eclipse.dltk.ruby.debug.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public class RubyVariable extends PlatformObject implements IVariable, IValue {

	private RubyStackFrame frame;

	private IDbgpProperty property;

	private int suspendCount;

	private RubyVariable[] variables;

	protected RubyThread getThread() {
		return (RubyThread) frame.getThread();
	}

	public RubyVariable(RubyStackFrame frame, IDbgpProperty property) {
		this.frame = frame;
		this.property = property;
		this.suspendCount = -1;
		this.variables = new RubyVariable[0];
	}
	
	protected RubyVariable[] readVariables() throws DbgpException {
		IDbgpSession session = getThread().getSession();
		
		synchronized (session) {
			property = session.getCoreCommands().getPropertyByKey(
					property.getName(), property.getKey());

			IDbgpProperty[] props = property.getAvailableChildren();

			List newVariables = new ArrayList();
			for (int i = 0; i < props.length; ++i) {
				newVariables.add(new RubyVariable(frame, props[i]));
			}

			return (RubyVariable[]) newVariables
					.toArray(new RubyVariable[newVariables.size()]);
		}
	}

	private boolean dirty = true;

	protected void checkVariables() {
		try {
			if (dirty) {
				RubyVariable[] newVariables = readVariables();
				RubyVariableUpdater.update(variables, newVariables);
				variables = newVariables;
				// dirty = false;
			}
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbgpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setModified() {
		suspendCount = getThread().getSuspendCount();
	}

	public String getName() throws DebugException {
		return property.getName();
	}

	public String getReferenceTypeName() throws DebugException {
		return "Count: " + Integer.toString(suspendCount) + "; Hash: "
				+ super.hashCode(); // property.getType();
	}

	public IValue getValue() throws DebugException {
		return this;
	}

	public boolean hasValueChanged() throws DebugException {
		return getThread().getSuspendCount() == suspendCount;
	}

	// =========================================================================
	// =========================== IDebugElement ===============================
	// =========================================================================
	public IDebugTarget getDebugTarget() {
		return frame.getDebugTarget();
	}

	public ILaunch getLaunch() {
		return frame.getLaunch();
	}

	public String getModelIdentifier() {
		return frame.getModelIdentifier();
	}

	// =========================================================================
	// ========================= IValueModification ============================
	// =========================================================================
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

	// =========================================================================
	// ============================= IValue ====================================
	// =========================================================================
	public boolean isAllocated() throws DebugException {
		return false;
	}

	public String getValueString() throws DebugException {
		return property.getValue();
	}

	public IVariable[] getVariables() throws DebugException {
		checkVariables();
		
		System.out.println("############# RubyVariable.getVariables() ################");

		IVariable[] vars = (IVariable[]) variables.clone();
		
		for(int i = 0; i < vars.length; ++i) {
			System.out.println("Variable name: " + vars[i].getName());
		}
		
		return vars;
	}

	public boolean hasVariables() throws DebugException {
		return property.hasChildren();
	}

	// public void swap(RubyVariable variable) {
	// IDbgpProperty temp = property;
	// property = variable.property;
	// variable.property = temp;
	// }

	public RubyVariable clone(RubyVariable var) {
		RubyVariable v = new RubyVariable(frame, var.property);
		v.suspendCount = suspendCount;
		v.variables = variables;
		v.dirty = true;
		return v;
	}

	public void setDirty() {
		dirty = true;

	}
}
