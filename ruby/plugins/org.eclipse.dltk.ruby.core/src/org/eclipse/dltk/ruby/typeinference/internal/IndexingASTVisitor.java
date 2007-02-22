/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class IndexingASTVisitor extends EvaluatingASTVisitor {

	public IndexingASTVisitor(IContext context, EvaluatorConfiguration evaluatorConfiguration) {
		super(context, null, evaluatorConfiguration);
	}

	public boolean visitGeneral(ASTNode node) throws Exception {
		boolean returnValue = super.visitGeneral(node);
		if (node instanceof MethodDeclaration) {
			if (returnValue)
				endvisitGeneral(node);
			return false; // never go inside
		} else
			return returnValue;
	}

}