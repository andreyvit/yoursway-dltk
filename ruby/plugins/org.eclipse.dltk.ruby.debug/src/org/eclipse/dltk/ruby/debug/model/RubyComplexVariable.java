package org.eclipse.dltk.ruby.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.dbgp.IDbgpProperty;

public class RubyComplexVariable extends RubyVariable {
			
	public RubyComplexVariable(RubyStackFrame frame, IDbgpProperty property) {
		super(frame, property);
		// TODO Auto-generated constructor stub
	}

	public IVariable[] getVariables() throws DebugException {
		return new IVariable[0];
	}

	public boolean hasVariables() throws DebugException {		
		return true;
	}	
}
