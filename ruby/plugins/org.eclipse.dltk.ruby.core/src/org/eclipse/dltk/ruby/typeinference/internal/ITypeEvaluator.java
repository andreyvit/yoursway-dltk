/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public interface ITypeEvaluator extends INodeProcessor {

	ITypeDescriptor startTraverse(ASTNode node, IContext context);

	ITypeDescriptor endTraverse(ASTNode node, IContext context);

}