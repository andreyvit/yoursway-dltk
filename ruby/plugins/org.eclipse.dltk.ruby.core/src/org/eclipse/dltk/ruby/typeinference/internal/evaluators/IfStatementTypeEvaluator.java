/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.statements.IfStatement;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public class IfStatementTypeEvaluator implements ITypeEvaluator {

	public Class[] getRecognizedNodes() {
		return new Class[] { IfStatement.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		IfStatement expr = (IfStatement) node;
		Statement stat1 = expr.getThen();
		Set possibleTypes = new HashSet();
		if (stat1 != null) 
			possibleTypes.add(context.evaluateType(stat1));
		Statement stat2 = expr.getElse();
		if (stat2 != null) 
			possibleTypes.add(context.evaluateType(stat2));
		return context.getModelMaster().makeTypeDescriptorFromListOfTypeDescriptors(possibleTypes);
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
	}

}