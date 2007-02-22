package org.eclipse.dltk.typeinference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FragmentedScope extends Scope {
	
	private Collection fragments = new ArrayList();

	public FragmentedScope(IScope parentScope, IKnownTypeDescriptor enclosingType, IClassLikeFragment enclosingClassFragment, IMethodDescriptor enclosingMethod) {
		super(parentScope, enclosingType, enclosingClassFragment, enclosingMethod);
	}
	
	public void addFragment(IScope scope) {
		fragments.add(scope);
	}

	public ITypeDescriptor getConstantTypeHere(String constantName) {
		ITypeDescriptor result = super.getConstantTypeHere(constantName);
		if (result == null)
			for (Iterator iter = fragments.iterator(); iter.hasNext();) {
				IScope fragment = (IScope) iter.next();
				result = fragment.getConstantTypeHere(constantName);
				if (result != null)
					break;
			}
		return result;
	}

	public IVariableDescriptor getVariableHere(String variableName) {
		IVariableDescriptor result = super.getVariableHere(variableName);
		if (result == null)
			for (Iterator iter = fragments.iterator(); iter.hasNext();) {
				IScope fragment = (IScope) iter.next();
				result = fragment.getVariableHere(variableName);
				if (result != null)
					break;
			}
		return result;
	}

}
