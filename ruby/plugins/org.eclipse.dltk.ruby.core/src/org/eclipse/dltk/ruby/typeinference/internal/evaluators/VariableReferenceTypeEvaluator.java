/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.AnyTypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.IVariableDescriptor;

public class VariableReferenceTypeEvaluator implements ITypeEvaluator {

	public Class[] getRecognizedNodes() {
		return new Class[] { VariableReference.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		VariableReference thisNode = (VariableReference) node;
		IVariableDescriptor var = context.getCurrentScope().lookupVariable(thisNode.getName(),
				false);
		if (var != null)
			return var.getType();
		return AnyTypeDescriptor.INSTANCE;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
	}

}
