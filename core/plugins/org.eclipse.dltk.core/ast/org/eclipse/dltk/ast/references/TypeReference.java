package org.eclipse.dltk.ast.references;

import org.eclipse.dltk.ast.DLTKToken;

public class TypeReference extends SimpleReference {

	public TypeReference(DLTKToken token) {
		super(token);
	}
	public TypeReference(int start, int end, String name) {
		super(start, end, name );
	}
}
