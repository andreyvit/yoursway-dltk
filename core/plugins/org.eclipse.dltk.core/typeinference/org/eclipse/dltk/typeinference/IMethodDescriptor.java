package org.eclipse.dltk.typeinference;

public interface IMethodDescriptor extends ITypedElement {

	public abstract ITypeDescriptor getReturnType();

	public abstract String getName();

	public abstract void addIncomingCall(CallDescriptor call);

	public abstract IKnownTypeDescriptor getEnclosingType();
	
	public abstract IClassLikeFragment getEnclosingClassFragment();

	public abstract ArgumentDescriptor[] getArguments();
	
	ArgumentDescriptor getArgumentByName(String name);

}