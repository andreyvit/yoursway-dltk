package org.eclipse.dltk.itcl.internal.core.parser.ast;

import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;


public class IncrTclMethodDeclaration extends ExtendedTclMethodDeclaration {
	public IncrTclMethodDeclaration(int start, int end) {
		super(start, end);
	}

	public IncrTclMethodDeclaration(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}
}
