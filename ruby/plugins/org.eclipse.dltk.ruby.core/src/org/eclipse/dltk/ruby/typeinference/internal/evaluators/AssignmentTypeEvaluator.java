/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.AnyTypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.IVariableDescriptor;

public class AssignmentTypeEvaluator implements ITypeEvaluator {

	public Class[] getRecognizedNodes() {
		return new Class[] { Assignment.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		Assignment thisNode = (Assignment) node;
		Expression lhs = thisNode.getLeft();
		if (lhs instanceof VariableReference) {
			VariableReference varNode = (VariableReference) lhs;
			IVariableDescriptor var = context.getCurrentScope().lookupVariable(
					varNode.getName(), true);
			ITypeDescriptor rhsType = context.evaluateType(thisNode.getRight());
			var.addAssignedType(rhsType);
			return rhsType;
		}
		return AnyTypeDescriptor.INSTANCE;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
	}

}