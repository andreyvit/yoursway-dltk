package org.eclipse.dltk.xotcl.internal.core.search;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;
import org.eclipse.dltk.tcl.core.extensions.IMatchLocatorExtension;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;

public class XOTclMatchLocatorExtension implements IMatchLocatorExtension {

	public void visitGeneral(ASTNode node, PatternLocator locator,
			MatchingNodeSet nodeSet) {

		if (node instanceof XOTclMethodCallStatement) {
			XOTclMethodCallStatement st = (XOTclMethodCallStatement) node;
			FieldDeclaration instanceVariable = st
					.getInstanceVariable();
			CallExpression call = new CallExpression(instanceVariable, st
					.getName(), null);
			locator.match(call, nodeSet);
		} else if (node instanceof XOTclProcCallStatement) {
			XOTclProcCallStatement st = (XOTclProcCallStatement) node;
			CallExpression call = new CallExpression(st.getObject(), st
					.getCallName().getName(), null);
			locator.match(call, nodeSet);
		}
	}

}
