package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.declarations.TypeDeclaration;

public class XOTclObjectDeclaration extends TypeDeclaration {
	public XOTclObjectDeclaration(String name, int nameStart, int nameEnd,
			int start, int end) {
		super(name, nameStart, nameEnd, start, end);
	}
}
