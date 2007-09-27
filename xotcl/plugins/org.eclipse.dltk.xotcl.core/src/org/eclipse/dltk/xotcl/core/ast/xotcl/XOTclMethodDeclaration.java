package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;


public class XOTclMethodDeclaration extends ExtendedTclMethodDeclaration {
	public XOTclMethodDeclaration(int start, int end) {
		super(start, end);
	}

	public XOTclMethodDeclaration(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}
}
