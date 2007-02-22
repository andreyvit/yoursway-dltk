package org.eclipse.dltk.typeinference;


public interface ITypeDescriptor {
	
	boolean worthCompleting();

	IMethodDescriptor getMethodByName(String name);
	
}
