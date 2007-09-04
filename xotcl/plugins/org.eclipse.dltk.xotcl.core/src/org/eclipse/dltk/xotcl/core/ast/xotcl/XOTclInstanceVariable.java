package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.references.SimpleReference;

public class XOTclInstanceVariable extends XOTclFieldDeclaration {
	private SimpleReference classInstanceName;
	public XOTclInstanceVariable(String name, int nameStart, int nameEnd,
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
