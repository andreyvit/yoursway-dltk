package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

public class XOTclInstanceVariable extends FieldDeclaration {
	private TypeDeclaration type;
	private SimpleReference classInstanceName;
	public XOTclInstanceVariable(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}

	public void setType(TypeDeclaration type) {
		this.type = type;
	}
	public TypeDeclaration getType() {
		return this.type;
	}

	public SimpleReference getClassInstanceName() {
		return classInstanceName;
	}

	public void setClassInstanceName(SimpleReference classInstanceName) {
		this.classInstanceName = classInstanceName;
	}
}
