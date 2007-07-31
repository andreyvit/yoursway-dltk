package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.ast.TclCatchStatement;

public class TclCatchProcessor extends AbstractTclCommandProcessor {

	public TclCatchProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent ) {
		TclStatement statement = (TclStatement) parser.processLocal(command, offset);
		if (statement.getCount() >= 2) {
			Expression e = statement.getAt(1);
			if (e instanceof TclBlockExpression) {
//				Block bl = new Block();
				TclBlockExpression block = (TclBlockExpression) e;
				String blockContent = block.getBlock();
				blockContent = blockContent.substring(1, blockContent.length() - 1);
				Block bl = new Block(e.sourceStart(), e.sourceEnd());
				TclCatchStatement catchStatement = new TclCatchStatement(bl, command.getStart() + offset, command.getEnd() + 1 + offset);
				addToParent(parent, catchStatement);
				parser.parse(blockContent, block.sourceStart() + 1 - parser.getStartPos(), bl );
//				this.toBlock((TclBlockExpression) e, bl);
				return catchStatement;
			}
		}
		return null;
	}
}
