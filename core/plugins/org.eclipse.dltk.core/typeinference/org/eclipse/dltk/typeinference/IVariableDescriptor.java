package org.eclipse.dltk.typeinference;

public interface IVariableDescriptor extends ITypedElement {
	
	String getName();

	public abstract void addAssignedType(ITypeDescriptor type);

	public abstract ITypeDescriptor getType();

}