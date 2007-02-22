package org.eclipse.dltk.typeinference;

import java.util.HashSet;
import java.util.Set;

public class MethodScope extends Scope {

	private Set returnedTypes = new HashSet();

	public MethodScope(IScope parentScope, IMethodDescriptor enclosingMethod) {
		super(parentScope, enclosingMethod.getEnclosingType(), enclosingMethod
				.getEnclosingClassFragment(), enclosingMethod);
	}

	public void addReturnedType(ITypeDescriptor type) {
		returnedTypes.add(type);
	}

	public Set getReturnedTypes() {
		return returnedTypes;
	}
	
	public void resetReturnedTypes() {
		returnedTypes.clear();
	}

	public void done() {
	}

}
