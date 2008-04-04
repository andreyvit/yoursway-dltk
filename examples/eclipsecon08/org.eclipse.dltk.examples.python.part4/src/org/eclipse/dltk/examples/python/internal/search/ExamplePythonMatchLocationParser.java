package org.eclipse.dltk.examples.python.internal.search;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.python.parser.ast.expressions.ExtendedVariableReference;

public class ExamplePythonMatchLocationParser extends MatchLocatorParser {
	protected ExamplePythonMatchLocationParser(MatchLocator locator) {
		super(locator);
	}
	protected void processStatement(ASTNode node, PatternLocator locator) {
		super.processStatement(node, locator);
		if (node instanceof ExtendedVariableReference) {
			ExtendedVariableReference ref = (ExtendedVariableReference) node;
			int expressionCount = ref.getExpressionCount();
			for (int i = 0; i < expressionCount; i++) {
				Expression e = ref.getExpression(i);
				if (ref.isCall(i) && e instanceof VariableReference) {
					CallExpression call = new CallExpression(null,
							((VariableReference) e).getName(), null);
					call.setStart(e.sourceStart());
					call.setEnd(e.sourceEnd());
					locator.match(call, getNodeSet());
				} else if (e instanceof VariableReference) {
					locator.match(node, getNodeSet());
				}
			}
		}
		if (node instanceof VariableReference) {
			locator.match((VariableReference)node, getNodeSet());
		}
	}
}
