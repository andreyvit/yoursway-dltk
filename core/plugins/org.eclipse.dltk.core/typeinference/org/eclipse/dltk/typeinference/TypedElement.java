package org.eclipse.dltk.typeinference;

import java.util.HashSet;
import java.util.Set;

public abstract class TypedElement extends ContainedNodeElement implements ITypedElement {

	public TypedElement(INodeElement enclosingElement) {
		super(enclosingElement);
	}

	private Set dependentItems = new HashSet();
	
}
