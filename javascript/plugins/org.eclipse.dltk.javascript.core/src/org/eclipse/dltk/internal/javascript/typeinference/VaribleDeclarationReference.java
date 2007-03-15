package org.eclipse.dltk.internal.javascript.typeinference;

import org.eclipse.dltk.ast.references.SimpleReference;

public class VaribleDeclarationReference extends SimpleReference{

	private IReference ref;
	public VaribleDeclarationReference(int start, int end, String name,IReference ref) {
		super(start, end, name);
		this.ref=ref;
	}

	public IReference getReference(){
		return ref;
	}
}
