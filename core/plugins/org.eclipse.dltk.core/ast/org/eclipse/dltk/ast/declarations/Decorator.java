package org.eclipse.dltk.ast.declarations;

import org.eclipse.dltk.ast.DLTKToken;

public abstract class Decorator extends Declaration {

	protected Decorator(DLTKToken nameToken, int sourceStart, int sourceEnd) {
		super(nameToken, sourceStart, sourceEnd);
	}

	public int getKind() {
		return Declaration.D_DECLARATOR;
	}
}
