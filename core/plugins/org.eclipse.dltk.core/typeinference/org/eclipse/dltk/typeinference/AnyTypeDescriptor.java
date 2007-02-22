package org.eclipse.dltk.typeinference;

public class AnyTypeDescriptor implements ITypeDescriptor {
	
	public static final AnyTypeDescriptor INSTANCE = new AnyTypeDescriptor();

	public boolean worthCompleting() {
		return false;
	}

	public IMethodDescriptor getMethodByName(String name) {
		return null;
	}

}
