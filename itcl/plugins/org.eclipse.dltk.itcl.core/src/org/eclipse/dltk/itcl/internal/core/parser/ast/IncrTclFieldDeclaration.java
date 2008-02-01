package org.eclipse.dltk.itcl.internal.core.parser.ast;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;

public class IncrTclFieldDeclaration extends FieldDeclaration {

	private TypeDeclaration type;
	private String name;

	public IncrTclFieldDeclaration(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}

	public void setDeclaringType(TypeDeclaration type) {
		this.type = type;
	}

	public TypeDeclaration getDeclaringType() {
		return this.type;
	}

	public void setDeclaringTypeName(String name) {
		this.name = name;
	}

	public String getDeclaringTypeName() {
		return this.name;
	}

}