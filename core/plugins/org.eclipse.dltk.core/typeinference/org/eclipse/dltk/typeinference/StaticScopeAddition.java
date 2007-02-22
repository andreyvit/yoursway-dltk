package org.eclipse.dltk.typeinference;

public class StaticScopeAddition implements IScope {

	private final IScope mainScope;

	private final IScope additionalStaticScope;

	public StaticScopeAddition(IScope mainScope, IScope additionalStaticScope) {
		this.mainScope = mainScope;
		this.additionalStaticScope = additionalStaticScope;
	}

	public ITypeDescriptor getConstantTypeHere(String constantName) {
		return mainScope.getConstantTypeHere(constantName);
	}

	public IMethodDescriptor getEnclosingMethod() {
		return mainScope.getEnclosingMethod();
	}

	public IKnownTypeDescriptor getEnclosingType() {
		return mainScope.getEnclosingType();
	}

	public ITypeModel getModelMaster() {
		return mainScope.getModelMaster();
	}

	public IScope getParentScope() {
		return additionalStaticScope; // XXX decide what to return and why
	}

	public IVariableDescriptor getVariableHere(String variableName) {
		return mainScope.getVariableHere(variableName);
	}

	public ITypeDescriptor lookupConstantType(String constantName) {
		ITypeDescriptor result = mainScope.lookupConstantType(constantName);
		if (result == null)
			result = additionalStaticScope.lookupConstantType(constantName);
		return result;
	}

	public IVariableDescriptor lookupVariable(String variableName, boolean allowCreation) {
		IVariableDescriptor result = mainScope.lookupVariable(variableName, allowCreation);
		if (result == null)
			result = additionalStaticScope.lookupVariable(variableName, allowCreation);
		return result;
	}

	public void setConstantType(String constantName, ITypeDescriptor type) {
		mainScope.setConstantType(constantName, type);
	}

	public void setVariable(String variableName, IVariableDescriptor variableDescriptor) {
		mainScope.setVariable(variableName, variableDescriptor);
	}

	public void addReturnedType(ITypeDescriptor type) {
		mainScope.addReturnedType(type);
	}

	public ITypeDescriptor getLastCalculatedValueType() {
		return mainScope.getLastCalculatedValueType();
	}

	public void setLastCalculatedValueType(ITypeDescriptor type) {
		mainScope.setLastCalculatedValueType(type);
	}

	public void done() {
	}

	public IClassLikeFragment getEnclosingClassFragment() {
		IClassLikeFragment result = mainScope.getEnclosingClassFragment();
		if (result == null)
			result = additionalStaticScope.getEnclosingClassFragment();
		System.out.println();
		return result;
	}

	public IElement getCurrentElement(IElementKind kind) {
		IElement result = mainScope.getCurrentElement(kind);
		if (result == null)
			result = additionalStaticScope.getCurrentElement(kind);
		return null;
	}

	public INodeElement getEnclosingElement() {
		INodeElement result = mainScope.getEnclosingElement();
		if (result == null)
			result = additionalStaticScope.getEnclosingElement();
		return result;
	}

}
