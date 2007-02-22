/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ruby.typeinference.RubyTypeUtils;
import org.eclipse.dltk.ruby.typeinference.internal.FixnumTypeDescriptor;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeModel;

public class NumericLiteralTypeEvaluator implements ITypeEvaluator {

	private FixnumTypeDescriptor fixnumType;

	public Class[] getRecognizedNodes() {
		return new Class[] { NumericLiteral.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		Assert.isTrue(node instanceof NumericLiteral);
		return fixnumType;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
		ITypeModel modelMaster = globalContext.getModelMaster();
		fixnumType = new FixnumTypeDescriptor(modelMaster);
		RubyTypeUtils.addType(globalContext, fixnumType);
	}

}