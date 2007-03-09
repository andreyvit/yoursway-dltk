package org.eclipse.dltk.typeinference;

import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.core.ISourceModule;

public interface ITypeModel extends IElement {
	
	ITypeDescriptor makeTypeDescriptorFromListOfTypeDescriptors(Set types);

	void invalidateMethodTypeInfo(IMethodDescriptor method);
	
	IUnit getUnit(ISourceModule sourceModule);

	public Object getNodeMapping(ASTNode key);

	public void putNodeMapping(ASTNode key, Object value);

	void removeNodeMapping(ASTNode oldNode);

	ITypeDescriptor calculateType(IUnit unit, ASTNode node);

}
