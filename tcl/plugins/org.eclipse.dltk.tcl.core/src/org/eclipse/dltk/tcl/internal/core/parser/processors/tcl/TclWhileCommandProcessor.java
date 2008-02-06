package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ast.TclWhileStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class TclWhileCommandProcessor extends AbstractTclCommandProcessor {

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) node;
		TclWhileStatement foreach = new TclWhileStatement(statement
				.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, foreach);

		if (statement.getCount() != 3) {
			this.report(parser, "Syntax error: wrong number of arguments",
					node, ProblemSeverities.Error);
		} else {
			Expression test = statement.getAt(1);
			foreach.setTest(test);
			Expression procCode = statement.getAt(2);
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
