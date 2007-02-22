package org.eclipse.dltk.typeinference;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.VariableKind;

public class VariableDescriptor extends TypedElement implements IVariableDescriptor {
	
	private final VariableKind variableKind = VariableKind.LOCAL;

	private final String name;
	
	private Set assignedTypes = new HashSet();
	
	private ITypeDescriptor type;

	private final Scope scope;

	public VariableDescriptor(INodeElement enclosingElement, Scope scope, String name) {
		super(enclosingElement);
		this.scope = scope;
		this.name = name;
	}
	
	public void addAssignedType(ITypeDescriptor type) {
		assignedTypes.add(type);
		type = null;
	}
	
	public ITypeDescriptor getType() {
		if (type == null) 
			type = scope.getModelMaster().makeTypeDescriptorFromListOfTypeDescriptors(assignedTypes);
		return type;
	}

	public String getName() {
		return name;
	}

	public ITypeDescriptor getCalculatedType() {
		return getType();
	}

	public IElementKind getKind() {
		return IElementKind.Variable.byVariableKind(variableKind);
	}

	public ITypeModel getModel() {
		return scope.getEnclosingType().getModel();
	}

	protected void synchronizeChildren(ASTNode node, boolean offsetsOnly) {
		// TODO Auto-generated method stub
	}

	public IScope getScope() {
		return getEnclosingElement().getScope();
	}

	public INodeElement getInnermostModelElement(ASTNode node) {
		if (node == getASTNode(ASTCaching.CACHED_ONLY))
			return this;
		return null;
	}
	
}
