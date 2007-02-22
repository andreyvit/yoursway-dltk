package org.eclipse.dltk.ast.declarations;


public class FieldDeclaration extends Declaration {

	
	public FieldDeclaration(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(declStart, declEnd);
		this.setName(name);
		this.setNameStart(nameStart);
		this.setNameEnd(nameEnd);
	}
}
