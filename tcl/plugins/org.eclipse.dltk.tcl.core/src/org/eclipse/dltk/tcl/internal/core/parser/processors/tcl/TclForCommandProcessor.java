package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ast.TclForStatement;

public class TclForCommandProcessor extends AbstractTclCommandProcessor {
	
	private static final int STATEMENTS_NUMBER = 5;
	private static final int START_IDNEX = 1;
	private static final int TEST_IDNEX = 2;
	private static final int NEXT_IDNEX = 3;
	private static final int BODY_IDNEX = 4;
	
	private Block matchSection(Statement statement, ITclParser parser, ASTNode parent) {
		Block block = new Block(statement.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, block);
		if (statement instanceof TclBlockExpression) {
			String content = ((TclBlockExpression)statement).getBlock().substring(1, ((TclBlockExpression)statement).getBlock().length()-1);
			parser.parse(content, 0, parent);
		}
		else {
			block.addStatement(statement);
		}
		return block;
	}
	
	private ASTNode matchCondition(Statement statement, ITclParser parser, ASTNode parent) {
		if (statement instanceof TclBlockExpression) {
			TclBlockExpression bl = (TclBlockExpression) statement;
			List parseBlock = bl.parseBlock();
			ASTListNode list = new ASTListNode(bl.sourceStart() + 1, bl.sourceEnd() - 1);
			for (int j = 0; j < parseBlock.size(); j++) {
				Object st = parseBlock.get(j);
				if( st instanceof TclStatement ) {
					List expressions = ((TclStatement)st).getExpressions();
					for (int k = 0; k < expressions.size(); k++) {
						list.addNode((ASTNode)expressions.get(k));
					}
				}
			}
			return list;
		}
		else if( statement instanceof SimpleReference ) {
			return statement;
		}
		this.report(parser, "Incorrect condition", statement, ProblemSeverities.Error);
		return null;
	}
	
	public ASTNode process(TclCommand command, ITclParser parser, int offset,ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement)node;
		if (statement.getCount() != STATEMENTS_NUMBER) {
			this.report(parser, "Syntax error: exactly " + (STATEMENTS_NUMBER - 1) +" arguments expected.", statement, ProblemSeverities.Error);
			return null;
		}
		TclForStatement forStatement = new TclForStatement(node.sourceStart(), node.sourceEnd());
		addToParent(parent, forStatement);
		Block start = matchSection(statement.getAt(START_IDNEX), parser, parent);
		ASTNode test = matchCondition(statement.getAt(TEST_IDNEX), parser, parent);
		Block next = matchSection(statement.getAt(NEXT_IDNEX), parser, parent);
		Block body = matchSection(statement.getAt(BODY_IDNEX), parser, parent);
		
		forStatement.setBody(body);
		forStatement.setStart(start);
		forStatement.setNext(next);
		forStatement.setTest(test);
		
		return forStatement;
	}
}
