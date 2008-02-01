package org.eclipse.dltk.itcl.internal.core.parser.ast;

import org.eclipse.dltk.ast.references.SimpleReference;

public class IncrTclInstanceVariable extends IncrTclFieldDeclaration {
	private SimpleReference classInstanceName;
	public IncrTclInstanceVariable(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}
	
	public SimpleReference getClassInstanceName() {
		return classInstanceName;
	}

	public void setClassInstanceName(SimpleReference classInstanceName) {
		this.classInstanceName = classInstanceName;
	}
}
