package org.eclipse.dltk.javascript.core;

import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;

public class FunctionDeclarationReference extends SimpleReference{

	private HostCollection collection;
	
	public FunctionDeclarationReference(int start, int end, String name,HostCollection collection) {
		super(start, end, name);
		this.collection=collection;
	}
	
	public HostCollection getCollection(){
		return this.collection;
	}
}
