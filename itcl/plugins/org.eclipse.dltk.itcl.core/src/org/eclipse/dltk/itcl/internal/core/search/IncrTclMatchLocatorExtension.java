package org.eclipse.dltk.itcl.internal.core.search;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclMethodCallStatement;
import org.eclipse.dltk.tcl.core.extensions.IMatchLocatorExtension;

public class IncrTclMatchLocatorExtension implements IMatchLocatorExtension {

	public void visitGeneral(ASTNode node, PatternLocator locator,
			MatchingNodeSet nodeSet) {

		if (node instanceof IncrTclMethodCallStatement) {
			IncrTclMethodCallStatement st = (IncrTclMethodCallStatement) node;
			FieldDeclaration instanceVariable = st.getInstanceVariable();
			CallExpression call = new CallExpression(instanceVariable, st
					.getName(), null);
			locator.match(call, nodeSet);
		}
	}
}
