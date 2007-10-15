package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ast.TclForeachStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class TclForeachCommandProcessor extends AbstractTclCommandProcessor {

	private static final int MIN_ARG_NUMBER = 3;

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) node;
		TclForeachStatement foreach = new TclForeachStatement(statement
				.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, foreach);

		if (statement.getCount() % 2 != 0
				|| statement.getCount() < MIN_ARG_NUMBER + 1) {
			this.report(parser, "Syntax error: wrong number of arguments",
					node, ProblemSeverities.Error);
		} else {
			// foreach.setArguments(null);
			Expression procCode = statement.getAt(statement.getCount() - 1);
			if (procCode instanceof TclBlockExpression) {
				Block block = new Block(procCode.sourceStart(), procCode
						.sourceEnd());

				String content = parser.substring(procCode.sourceStart(),
						procCode.sourceEnd());
				if (content.startsWith("{") && content.endsWith("}")) {
					content = parser.substring(procCode.sourceStart() + 1,
							procCode.sourceEnd() - 1);
				}
				foreach.acceptBlock(block);
				parser.parse(content, procCode.sourceStart() + 1
						- parser.getStartPos(), block);
			}

		}
		return foreach;
	}
}
