package org.eclipse.dltk.typeinference;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;

public class Scope implements IScope {
	
	private IScope parentScope;

	private Map constantNamesToTypes = new HashMap();
	
	private Map variableNamesToDescriptors = new HashMap();

	private final IKnownTypeDescriptor enclosingType;

	private final IMethodDescriptor enclosingMethod;
	
	private IClassLikeFragment enclosingClassFragment;

	private ITypeDescriptor lastCalculatedValueType;
	
	public Scope(IScope parentScope, IKnownTypeDescriptor enclosingType, IClassLikeFragment enclosingClassFragment, IMethodDescriptor enclosingMethod) {
		Assert.isTrue(enclosingClassFragment != null || enclosingType != null);
		Assert.isTrue(enclosingMethod == null || enclosingMethod instanceof INodeElement);
		this.enclosingClassFragment = enclosingClassFragment;
		this.parentScope = parentScope;
		this.enclosingType = enclosingType;
		this.enclosingMethod = enclosingMethod;
	}
	
	public IScope getParentScope() {
		return parentScope;
	}

	public ITypeModel getModelMaster() {
		return enclosingType.getModel();
	}
	
	public IMethodDescriptor getEnclosingMethod() {
		return enclosingMethod;
	}

	public IKnownTypeDescriptor getEnclosingType() {
		return enclosingType;
	}

	public ITypeDescriptor getConstantTypeHere(String constantName) {
		return (ITypeDescriptor) constantNamesToTypes.get(constantName);
	}
	
	public ITypeDescriptor lookupConstantType(String constantName) {
		for (IScope scope = this; scope != null; scope = scope.getParentScope()) {
			ITypeDescriptor type = scope.getConstantTypeHere(constantName);
			if (type != null)
				return type;
		}
		return null;
	}
	
	public void setConstantType(String constantName, ITypeDescriptor type) {
		constantNamesToTypes.put(constantName, type);
	}
	
	public IVariableDescriptor getVariableHere(String variableName) {
		return (IVariableDescriptor) variableNamesToDescriptors.get(variableName);
	}
	
	public void setVariable(String variableName, IVariableDescriptor variableDescriptor) {
		variableNamesToDescriptors.put(variableName, variableDescriptor);
	}
	
	public IVariableDescriptor lookupVariable(String variableName, boolean allowCreation) {
		for (IScope scope = this; scope != null; scope = scope.getParentScope()) {
			IVariableDescriptor var = scope.getVariableHere(variableName);
			if (var != null)
				return var;
		}
		if (!allowCreation)
			return null;
		IVariableDescriptor var = new VariableDescriptor(null /* FIXME */, this, variableName);
		variableNamesToDescriptors.put(variableName, var);
		return var;
	}

	public void addReturnedType(ITypeDescriptor type) {
		if (parentScope != null) 
			parentScope.addReturnedType(type);
	}

	public ITypeDescriptor getLastCalculatedValueType() {
		return lastCalculatedValueType;
	}

	public void setLastCalculatedValueType(ITypeDescriptor type) {
		this.lastCalculatedValueType = type;
	}

	public void done() {
	}

	public IClassLikeFragment getEnclosingClassFragment() {
		return enclosingClassFragment;
	}

	public IElement getCurrentElement(IElementKind kind) {
		if (kind == IElementKind.UNIT)
			return getEnclosingElement().getUnit();
		if (kind == IElementKind.MODEL)
			return getEnclosingElement().getModel();
		return null;
	}

	public INodeElement getEnclosingElement() {
		if (enclosingMethod != null)
			return (INodeElement) enclosingMethod;
		return enclosingClassFragment;
	}
	
}
