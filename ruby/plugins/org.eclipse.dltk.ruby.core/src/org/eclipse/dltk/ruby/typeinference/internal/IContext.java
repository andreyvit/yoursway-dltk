/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeModel;

public interface IContext {
	
	void enterScope(IScope scope);

	IScope leaveScope();

	IScope getCurrentScope();

	ITypeModel getModelMaster();

	void putNodeMapping(ASTNode key, Object value);

	Object getNodeMapping(ASTNode key);

	ITypeDescriptor evaluateType(ASTNode node);
	
}