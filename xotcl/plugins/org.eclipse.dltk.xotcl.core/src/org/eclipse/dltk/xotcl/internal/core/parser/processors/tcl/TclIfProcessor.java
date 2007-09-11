package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import java.util.ArrayList;
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
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.ast.IfStatement;

public class TclIfProcessor extends AbstractTclCommandProcessor {

	public TclIfProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command,
				offset, parent);
		List exprs = statement.getExpressions();
		int start = statement.sourceStart();
		int end = statement.sourceEnd();
		IfStatement ifStatement = new IfStatement(start, end);
		addToParent(parent, ifStatement);
		ifStatement.acceptCondition(this.extractCondition(exprs, 1, parser));
		Block bl = new Block(ifStatement.sourceStart(), ifStatement.sourceEnd());
		ifStatement.acceptThen(bl);
		this.extractThen(exprs, 2, parser, start, end, bl);
		if(statement.getCount() == 3 ) {
			return ifStatement;
		}
		List elseList = this.makeElseList(exprs, 2, parser, start, end);
		Block el = new Block(ifStatement.sourceStart(), ifStatement.sourceEnd());
		ifStatement.acceptElse(el);
		ifStatement.acceptElse(this.extractElse(elseList, parser, start, end, el));
		return ifStatement;
	}

	private Statement extractElse(List exprs, ITclParser parser, int start,
			int end, Block el) {
		if( exprs.size() == 0 ) {
			return null;
		}
		if( exprs.size() == 1 ) {
			ASTNode node = (ASTNode) exprs.get(0);
			if( node instanceof TclBlockExpression ) {
				TclBlockExpression block = (TclBlockExpression) node;
				parseBlock(parser, el, block);
				return el;
//				return this.toBlock((TclBlockExpression) node, el);
			}
			else if( node instanceof SimpleReference ) {
				el.addStatement(node);
			}
			if( node instanceof Statement ) {
				return (Statement) node;
			}
			return null;
		}
		if (exprs.size() < 2) {
			this.report(parser, "Incorrect else", start, end,
					ProblemSeverities.Error);
			return null;
		}
		ASTNode node = (ASTNode) exprs.get(0);
		if (!(node instanceof SimpleReference)) {
			this.report(parser, "Incorrect else block", start, end,
					ProblemSeverities.Error);
			return null;
		}
		SimpleReference ref = (SimpleReference) node;
		if (ref.getName().equals("else")) {
			ASTNode nde= (ASTNode) exprs.get(1);
			if (nde instanceof TclBlockExpression) {
				TclBlockExpression block = (TclBlockExpression) nde;
				parseBlock(parser, el, block);
				return el;
//				return this.toBlock((TclBlockExpression) block, el);
			} else {
				this.report(parser, "Incorrect else block", ref.sourceStart(),
						ref.sourceEnd(), ProblemSeverities.Error);
				return null;
			}
		} else if(ref.getName().equals("elseif")) {
			IfStatement ifStatement = new IfStatement(ref.sourceStart(), end);
			el.addStatement(ifStatement);
			ifStatement.acceptCondition(this.extractCondition(exprs, 1, parser));
			Block bl = new Block(start, end);
			ifStatement.acceptThen(bl);
			this.extractThen(exprs, 2, parser, start, end, bl);
			List elseList = this.makeElseList(exprs, 2, parser, start, end);
			Block el2 = new Block(start,end);
			ifStatement.acceptElse(el2);
			ifStatement.acceptElse(this.extractElse(elseList, parser, start, end, el2));
			return ifStatement;
		}

		return null;
	}

	private void parseBlock(ITclParser parser, Block el,
			TclBlockExpression block) {
		String blockContent = block.getBlock();
		blockContent = blockContent.substring(1, blockContent.length() - 1);
		parser.parse(blockContent, block.sourceStart() + 1 - parser.getStartPos(), el );
	}

	private List makeElseList(List exprs, int i, ITclParser parser, int start,
			int end) {
		if (exprs.size() <= i) {
			this.report(parser, "Incorrect if statement", start, end,
					ProblemSeverities.Error);
			return new ArrayList();
		}
		ASTNode node = (ASTNode) exprs.get(i);
		if (node instanceof SimpleReference
				&& ((SimpleReference) node).getName().equals("then")) {
			if (exprs.size() >= i + 1) {
				this.report(parser, "Incorrect if statement", node,
						ProblemSeverities.Error);
			}
			return exprs.subList(i + 2, exprs.size());
		} else {
			return exprs.subList(i + 1, exprs.size());
		}
	}

	private Statement extractThen(List exprs, int i, ITclParser parser,
			int start, int end, Block bl) {
		if (exprs.size() <= i) {
			this.report(parser, "Incorrect if statement", start, end,
					ProblemSeverities.Error);
			return null;
		}
		ASTNode node = (ASTNode) exprs.get(i);
		if (node instanceof SimpleReference
				&& ((SimpleReference) node).getName().equals("then")) {
			if (exprs.size() >= i + 1) {
				this.report(parser, "Incorrect if statement", node,
						ProblemSeverities.Error);
			}
			node = (ASTNode) exprs.get(i + 1);
		}
		if (node instanceof TclBlockExpression) {
			TclBlockExpression block = (TclBlockExpression) node;
			parseBlock(parser, bl, block);
			return bl;
//			return this.toBlock((TclBlockExpression) node, bl);
		}
		else if( node instanceof TclStatement ) {
			bl.addStatement(node);
			bl.setStart(node.sourceStart());
			bl.setEnd(node.sourceEnd());
			return bl;
		}
		else if( node instanceof SimpleReference ) {
			List es = new ArrayList();
			es.add(node);
			TclStatement st = new TclStatement(es);
			st.setStart(node.sourceStart());
			st.setEnd(node.sourceEnd());
			bl.addStatement(node);
			bl.setStart(node.sourceStart());
			bl.setEnd(node.sourceEnd());
			return bl;
		}
		this.report(parser, "Incorrect if then block", node,
				ProblemSeverities.Error);
		return null;
	}

	private ASTNode extractCondition(List exprs, int i, ITclParser parser) {
		if( exprs.size() <= i ) {
			return null;
		}
		ASTNode node = (ASTNode) exprs.get(i);
		if (node instanceof TclBlockExpression) {
			TclBlockExpression bl = (TclBlockExpression) node;
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
		else if( node instanceof SimpleReference ) {
			return (Statement) node;
		}
		this.report(parser, "Incorrect if condition", node,
				ProblemSeverities.Error);
		return null;
	}

}
