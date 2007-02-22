package org.eclipse.dltk.typeinference;

public class RecursiveCallTypeDescriptor implements ITypeDescriptor {
	
	public static final RecursiveCallTypeDescriptor INSTANCE = new RecursiveCallTypeDescriptor();

	public IMethodDescriptor getMethodByName(String name) {
		return null;
	}

	public boolean worthCompleting() {
		return false;
	}

}
