package org.eclipse.dltk.typeinference;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;

public class ArgumentVariableDescriptor extends ContainedNodeElement implements IVariableDescriptor {

	private Set assignedTypes = new HashSet();
	
	private ITypeDescriptor type;

	private final Scope scope;

	private final ArgumentDescriptor argument;

	public ArgumentVariableDescriptor(INodeElement enclosingElement, Scope scope, ArgumentDescriptor argument) {
		super(enclosingElement);
		this.scope = scope;
		this.argument = argument;
	}
	
	public void addAssignedType(ITypeDescriptor type) {
		assignedTypes.add(type);
		type = null;
	}
	
	public ITypeDescriptor getType() {
		if (type == null) {
			Set types = new HashSet();
			types.addAll(assignedTypes);
			types.add(argument.getType());
			type = scope.getModelMaster().makeTypeDescriptorFromListOfTypeDescriptors(types);
		}
		return type;
	}

	public String getName() {
		return argument.getName();
	}

	public ITypeDescriptor getCalculatedType() {
		return getType();
	}

	public IElementKind getKind() {
		return IElementKind.ARGUMENT_VARIABLE;
	}

	public ITypeModel getModel() {
		return scope.getEnclosingType().getModel();
	}

	protected void synchronizeChildren(ASTNode node, boolean offsetsOnly) {
		// no children, dude! yahooo!
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
