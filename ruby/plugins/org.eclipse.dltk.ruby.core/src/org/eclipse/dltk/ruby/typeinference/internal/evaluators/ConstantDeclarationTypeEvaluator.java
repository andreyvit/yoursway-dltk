/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ruby.ast.ConstantDeclaration;
import org.eclipse.dltk.ruby.typeinference.RubyTypeUtils;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public class ConstantDeclarationTypeEvaluator implements ITypeEvaluator {

	public Class[] getRecognizedNodes() {
		return new Class[] { ConstantDeclaration.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		ConstantDeclaration thisNode = (ConstantDeclaration) node;
		IScope targetScope = null;
		if (thisNode.getPath() != null) {
			IKnownTypeDescriptor type = RubyTypeUtils.getConstant(context, thisNode.getPath());
			if (type != null)
				targetScope = type.getScope();
		} else {
			targetScope = context.getCurrentScope();
		}
		ITypeDescriptor rhsType = context.evaluateType(thisNode.getValue());
		if (targetScope != null)
			targetScope.setConstantType(thisNode.getName().getName(), rhsType);
		return rhsType;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
	}

}