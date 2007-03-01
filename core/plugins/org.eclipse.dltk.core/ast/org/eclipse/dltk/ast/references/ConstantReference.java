package org.eclipse.dltk.ast.references;

import org.eclipse.dltk.ast.DLTKToken;

public class ConstantReference extends SimpleReference {
	public ConstantReference(DLTKToken token) {
		super(token);
	}

	public ConstantReference(int start, int end, String name) {
		super(start, end, name);
	}
	

}
