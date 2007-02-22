package org.eclipse.dltk.typeinference;

import java.util.HashMap;
import java.util.Map;

public abstract class MethodDescriptor implements IMethodDescriptor {
	
	private ArgumentDescriptor[] arguments;
	
	private Map argumentsByName = new HashMap();

	public ArgumentDescriptor[] getArguments() {
		if (arguments == null)
			needArguments();
		return arguments;
	}

	public ArgumentDescriptor getArgumentByName(String name) {
		if (arguments == null)
			needArguments();
		return (ArgumentDescriptor) argumentsByName.get(name);
	}

	protected void needArguments() {
		throw new AssertionError("Arguments are not available, please report a bug in DLTK");
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

}
