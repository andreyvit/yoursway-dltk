package org.eclipse.dltk.typeinference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserTypeDescriptor extends Element implements IKnownTypeDescriptor {
	
	private final Map methods = new HashMap();
	private final String name;
	private final ITypeModel master;
	private final Scope scope;
	private final Collection fragments = new ArrayList();
	
	public UserTypeDescriptor(IKnownTypeDescriptor superclass, ITypeModel master, String name) {
		this.master = master;
		this.name = name;
		if (superclass == null) 
			this.scope = new FragmentedScope(null, this, null, null);
		else
			this.scope = new FragmentedScope(superclass.getScope(), this, null, null);
	}
	
	public ITypeModel getModel() {
		return master;
	}

	public boolean worthCompleting() {
		return true;
	}
	
	public void addMethod(IMethodDescriptor method) {
		methods.put(method.getName(), method);
	}
	
	public void removeMethod(IMethodDescriptor method) {
		methods.remove(method.getName());
	}

	public IMethodDescriptor getMethodByName(String name) {
		return (IMethodDescriptor) methods.get(name);
	}
	
	public Collection getMethods() {
		return methods.values();
	}
	
	public String getName() {
		return name;
	}

	public IScope getScope() {
		return scope;
	}

	public IElementKind getKind() {
		return IElementKind.CLASS;
	}
	
	public void addFragment(IClassLikeFragment fragment) {
		fragments.add(fragment);
	}
	
	public void removeFragment(IClassLikeFragment fragment) {
		fragments.remove(fragment);
	}
	
	public IClassLikeFragment[] getFragments() {
		return (IClassLikeFragment[]) fragments.toArray(new IClassLikeFragment[fragments.size()]);
	}

}
