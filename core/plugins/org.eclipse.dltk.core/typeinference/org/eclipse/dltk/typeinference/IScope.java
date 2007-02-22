package org.eclipse.dltk.typeinference;

public interface IScope {

	/**
	 * @deprecated
	 */
	public abstract IScope getParentScope();

	public abstract ITypeModel getModelMaster();

	/**
	 * @deprecated
	 */
	public abstract IMethodDescriptor getEnclosingMethod();

	/**
	 * @deprecated
	 */
	public abstract IKnownTypeDescriptor getEnclosingType();

	/**
	 * @deprecated
	 */
	public abstract ITypeDescriptor getConstantTypeHere(String constantName);

	/**
	 * @deprecated
	 */
	public abstract ITypeDescriptor lookupConstantType(String constantName);

	/**
	 * @deprecated
	 */
	public abstract void setConstantType(String constantName, ITypeDescriptor type);

	/**
	 * @deprecated
	 */
	public abstract IVariableDescriptor getVariableHere(String variableName);

	/**
	 * @deprecated
	 */
	public abstract void setVariable(String variableName, IVariableDescriptor variableDescriptor);

	/**
	 * @deprecated
	 */
	public abstract IVariableDescriptor lookupVariable(String variableName, boolean allowCreation);
	
	/**
	 * @deprecated
	 */
	void setLastCalculatedValueType(ITypeDescriptor type);
	
	/**
	 * @deprecated
	 */
	ITypeDescriptor getLastCalculatedValueType();
	
	void addReturnedType(ITypeDescriptor type);
	
	/**
	 * @deprecated
	 */
	void done();
	
	IClassLikeFragment getEnclosingClassFragment();

	INodeElement getEnclosingElement();
	
	IElement getCurrentElement(IElementKind kind);
	
//	IElement find(IElementKind kind, String name);
//	
//	void define(IElement element);

}