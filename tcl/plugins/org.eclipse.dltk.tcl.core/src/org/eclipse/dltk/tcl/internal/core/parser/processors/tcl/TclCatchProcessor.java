package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.TclAdvancedExecuteExpression;
import org.eclipse.dltk.tcl.core.ast.TclCatchStatement;
import org.eclipse.dltk.tcl.core.ast.TclVariableDeclaration;

public class TclCatchProcessor extends AbstractTclCommandProcessor {

	public TclCatchProcessor() {
	}

	public ASTNode process(TclStatement statement, ITclParser parser, ASTNode parent) {
		if (statement.getCount() >= 2) {
			Expression e = statement.getAt(1);
			TclVariableDeclaration variable = null;
			if (statement.getCount() > 2) {
				Expression variableName = statement.getAt(2);
				variable = new TclVariableDeclaration(TclParseUtil
						.makeVariable(variableName), null, variableName
						.sourceStart(), variableName.sourceEnd());
			}
			if (e instanceof TclBlockExpression) {
				TclBlockExpression block = (TclBlockExpression) e;
				String blockContent = block.getBlock();
				blockContent = blockContent.substring(1,
						blockContent.length() - 1);
				Block bl = new Block(e.sourceStart(), e.sourceEnd());
				TclCatchStatement catchStatement = new TclCatchStatement(bl,
						variable, statement.sourceStart(), statement
								.sourceEnd());
				addToParent(parent, catchStatement);
				parser.parse(blockContent, block.sourceStart() + 1
						- parser.getStartPos(), bl);
				return catchStatement;
			} else if (e instanceof SimpleReference) {
				Block bl = new Block(e.sourceStart(), e.sourceEnd());
				TclCatchStatement catchStatement = new TclCatchStatement(bl,
						variable, statement.sourceStart(), statement
								.sourceEnd());
				addToParent(parent, catchStatement);
				bl.addStatement(e);
				return catchStatement;
			} else if (e instanceof StringLiteral) {
				Block bl = new Block(e.sourceStart(), e.sourceEnd());
				TclCatchStatement catchStatement = new TclCatchStatement(bl,
						variable, statement.sourceStart(), statement
								.sourceEnd());
				addToParent(parent, catchStatement);
				bl.addStatement(e);
				return catchStatement;
			} else if (e instanceof TclAdvancedExecuteExpression) {
				TclAdvancedExecuteExpression block = (TclAdvancedExecuteExpression) e;
				Block bl = new Block(e.sourceStart(), e.sourceEnd());
				TclCatchStatement catchStatement = new TclCatchStatement(bl,
						variable, block.sourceStart(), block.sourceEnd());
				addToParent(parent, catchStatement);
				bl.getStatements().addAll(block.getChilds());
				return catchStatement;
			}
		} else {
			this.report(parser,
					Messages.TclProcProcessor_Wrong_Number_of_Arguments,
					statement, ProblemSeverities.Error);
		}
		this.report(parser, "Parsing error.", statement,
				ProblemSeverities.Error); // TODO appropriate message
		return null;
	}
}
