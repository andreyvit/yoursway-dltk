package org.eclipse.dltk.ast.references;

import org.eclipse.dltk.ast.DLTKToken;

public class VariableReference extends SimpleReference {
	
	private VariableKind variableKind;

	public VariableReference(int start, int end, String name) {
		this(start, end, name, VariableKind.UNKNOWN);
	}
	
	public VariableReference(int start, int end, String name, VariableKind kind) {
		super(start, end, name);
		this.variableKind = kind;
	}

	public VariableReference(DLTKToken token) {
		super(token);
		this.variableKind = VariableKind.UNKNOWN;
	}

	public VariableKind getVariableKind() {
		return variableKind;
	}

	public void setVariableKind(VariableKind kind) {
		this.variableKind = kind;
	}
	
	
	
}
