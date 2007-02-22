/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.AnyTypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public class ConstantReferenceTypeEvaluator implements ITypeEvaluator {

	public Class[] getRecognizedNodes() {
		return new Class[] { ConstantReference.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		ConstantReference thisNode = (ConstantReference) node;
		ITypeDescriptor type = context.getCurrentScope().lookupConstantType(thisNode.getName());
		if (type != null)
			return type;
		return AnyTypeDescriptor.INSTANCE;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
	}

}