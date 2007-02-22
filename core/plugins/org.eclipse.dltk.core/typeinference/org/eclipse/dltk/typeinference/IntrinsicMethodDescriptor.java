package org.eclipse.dltk.typeinference;

import java.util.HashMap;
import java.util.Map;


public class IntrinsicMethodDescriptor extends Element implements IMethodDescriptor {
	
	private final IKnownTypeDescriptor type;
	private final String name;
	private final ITypeDescriptor returnType;
	private final IClassLikeFragment enclosingClassFragment;
	
	private ArgumentDescriptor[] arguments;
	private Map argumentsByName = new HashMap();

	public IntrinsicMethodDescriptor(IClassLikeFragment enclosingClassFragment, String name, ArgumentDescriptor[] arguments,
			ITypeDescriptor returnType) {
		this.enclosingClassFragment = enclosingClassFragment;
		this.type = enclosingClassFragment.getType();
		this.name = name;
		this.returnType = returnType;
		setArguments(arguments);
	}

	public void addIncomingCall(CallDescriptor call) {
		// ignored
	}

	public IKnownTypeDescriptor getEnclosingType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public ITypeDescriptor getReturnType() {
		return returnType;
	}

	public ITypeDescriptor getCalculatedType() {
		return returnType;
	}

	public ITypeModel getModel() {
		return type.getModel();
	}

	public IClassLikeFragment getEnclosingClassFragment() {
		return enclosingClassFragment;
	}
	public ArgumentDescriptor[] getArguments() {
		return arguments;
	}

	public ArgumentDescriptor getArgumentByName(String name) {
		return (ArgumentDescriptor) argumentsByName.get(name);
	}

	public void setArguments(ArgumentDescriptor[] arguments) {
		this.arguments = arguments;
		argumentsByName.clear();
		if (arguments != null) 
			for (int i = 0; i < arguments.length; i++) {
				arguments[i].$setParent(this, i);
				argumentsByName.put(arguments[i].getName(), arguments[i]);
			}
	}

	public IElementKind getKind() {
		return IElementKind.METHOD;
	}

	public IScope getScope() {
		return null;
	}

}
